package com.hk.server.wx_server.controller;

import com.hk.server.entity.*;
import com.hk.server.utils.FileSave;
import com.hk.server.utils.ResponseUtil;
import com.hk.server.wx_server.dao.UserMapper;
import com.hk.server.wx_server.dto.*;
import com.hk.server.wx_server.service.RedisService;
import com.hk.server.wx_server.service.WxService;
import com.hk.server.wx_server.utils.WxTokenUtil;
import com.hk.server.wx_server.utils.WxUtils;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("wx_user")
@SuppressWarnings("all")
public class WxController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisService redisService;

    private static String IMAGE_PATH;

    private static final String MORDER_KEY_PREFIX="morder:";

    @Value("${application.path.name}")
    public void setImagePath(String imagePath){
        IMAGE_PATH = imagePath;
    }

    /**
     * 根据code获取到用户信息
     * @param code
     * @return
     */
    @PostMapping("login")
    public ResponseUtil login(@RequestParam("code") String code){

        //根据code获取openid
        String openid = WxUtils.useCodeToGetOpenId(code);

        if (openid==null||openid.length()<=0) {
            return new ResponseUtil(460,"fail",null);
        }

        //从数据库查询此记录是否存在
        //不存在，则创建一条记录放在数据库中
        //查出此记录
        User user = userMapper.getUserByOpenid(openid);
        if (user == null) {
            int result = userMapper.insertUser(new User(null,null,openid,null));
            if (result == 1) {
                user = userMapper.getUserByOpenid(openid);
            }
        }
        if (user!=null) return new ResponseUtil(200,"ok", WxTokenUtil.sign(user.getWxOpenId(),user.getId()));
        else return new ResponseUtil(460,"fail",null);
    }

    /**
     * 根据用户token
     * @param
     * @return
     */
    @GetMapping("get_health_record")
    public ResponseUtil getHealthRecord(HttpServletRequest request){

        String token = request.getHeader("token");
        Long id = WxTokenUtil.fromTokenGetId(token);

        if (id==null) {
            return new ResponseUtil(460,"fail",null);
        }

        Healthrecord record = userMapper.getHealthRecordByUserId(id);

        //有健康档案信息
        if (record != null) return new ResponseUtil(200,"ok",record);
        // 无健康档案信息
        else return new ResponseUtil(200,"no data",null);

    }

    /**
     * 新建初始化的健康档案
     * @param
     * @return
     */
    @GetMapping("init_health_record")
    public ResponseUtil initHealthRecord(HttpServletRequest request){

        String token = request.getHeader("token");
        Long id = WxTokenUtil.fromTokenGetId(token);
//        Healthrecord record = new Healthrecord(null,id,"",1,null,0.0,0,
//                0,0,0,"","","","",0.0);
        int result = userMapper.insertInitHealthRecord(id);
        if (result == 1) return new ResponseUtil(200,"ok",null);
        else return new ResponseUtil(460,"fail",null);

    }

    /**
     * 更新用户健康档案
     * @param record
     * @return
     */
    @PostMapping("update_health_record")
    public ResponseUtil updateHealthRecord(Healthrecord record){

        if (record.getId()==null||record.getId()<=0) return new ResponseUtil(460,"fail",null);
        int result = userMapper.updateHealthRecord(record);
        if (result == 1) return new ResponseUtil(200,"ok",null);
        else return new ResponseUtil(460,"fail",null);

    }

    /**
     * 根据科室获取医生基本数据
     * @param department
     * @return
     */
    @GetMapping("find_doctor_by_department")
    public ResponseUtil findDoctorByDepartment(String department,@RequestParam(value = "page",defaultValue = "1") Integer page){

        int start = (page-1)*10;
        List<BaseDoctorMessage> result = userMapper.findDoctorByDepartment(department,start);
        for (BaseDoctorMessage re:result){
            String head = IMAGE_PATH+"images/"+re.getId()+"/head.jpg";
            re.setHead(head);
        }
        return new ResponseUtil(200,"ok",result);

    }

    /**
     * 根据id获取医生全部数据
     */
    @GetMapping("get_doctor_message")
     public ResponseUtil findDoctorMessageById(@RequestParam("id") Long id){

         DoctorDTO doctor = userMapper.findDoctorMessageById(id);
         String head = IMAGE_PATH+"images/"+id+"/head.jpg";
         if (doctor!=null) doctor.setHead(head);
         List<CommontDTO> commonts = userMapper.findCommontByDoctorId(id,0);
         HashMap<String,Object> map = new HashMap<>();
         map.put("doctor",doctor);
         map.put("commonts",commonts);
         return new ResponseUtil(200,"ok",map);

     }


    /**
     * 上传并且返回图文咨询单的图片路径
     * @param file
     * @param request
     * @return
     */
     @PostMapping("upload_image")
     public ResponseUtil transfImage(MultipartFile file,HttpServletRequest request){

         String token = request.getHeader("token");
         Long id = WxTokenUtil.fromTokenGetId(token);
         String path = "morder/" + id + "/";
         String newName =String.valueOf(new Date().getTime())+String.valueOf((int)(Math.random()*10))+".jpg";
         if (FileSave.saveFile(file,path+newName)){
             return new ResponseUtil(200,"ok",IMAGE_PATH+"morder/"+id+"/"+newName);
         }else {
             return new ResponseUtil(460,"fail",null);
         }
     }


    /**
     * 新增图文订单
     * @param id
     * @param morderPrice
     * @param content
     * @param request
     * @return
     */
     @PostMapping("publish_morder")
     public ResponseUtil publishMorder(@RequestParam("id") Long id,@RequestParam("price")Double morderPrice,
                                       @RequestParam("content") String content,HttpServletRequest request){
         String token = request.getHeader("token");
         Long userId = WxTokenUtil.fromTokenGetId(token);  //用户id

         SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
         Date now = new Date();
         String begin_time = f.format(now);
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(now);
         calendar.add(Calendar.DAY_OF_MONTH, +1);
         Date date = calendar.getTime();
         String active_time = f.format(date);
         Integer userConfirm = 0;
         Integer isCommont = 0;
         Morder morder = new Morder(null,begin_time,active_time,content,null,
                 morderPrice,userConfirm,null,userId,id,isCommont);
         userMapper.insertMorder(morder);
         System.out.println(morder);
         if (morder.getId()==null) return new ResponseUtil(460,"fail",null);

         //在redis中设置一个过期键，服务器对其进行监听，过期以后自动把图文订单确认
         String key = MORDER_KEY_PREFIX+morder.getId();
         long num = (date.getTime()-now.getTime())/1000/60;
         redisTemplate.opsForValue().set(key,morder.getId(),num, TimeUnit.MINUTES);

         return new ResponseUtil(200,"ok",null);
     }


    /**
     * 查看用户进行中的订单
     * @param request
     * @return
     */
     @GetMapping("find_going_morder")
     public ResponseUtil findAllMorder(HttpServletRequest request){
         String token = request.getHeader("token");
         Long userId = WxTokenUtil.fromTokenGetId(token);  //用户id

         List<BaseMorderDTO> results = userMapper.findOngoingMorder(userId);
         return new ResponseUtil(200,"ok",results);

     }

    /**
     * 查看用户待评价的订单
     * @param request
     * @return
     */
    @GetMapping("find_evaluate_morder")
    public ResponseUtil findEvaluateMorder(HttpServletRequest request){
        String token = request.getHeader("token");
        Long userId = WxTokenUtil.fromTokenGetId(token);  //用户id

        List<BaseMorderDTO> results = userMapper.findEvaluateMorder(userId);
        return new ResponseUtil(200,"ok",results);

    }

    /**
     * 查看用户已完成的订单
     * @param request
     * @return
     */
    @GetMapping("find_complete_morder")
    public ResponseUtil findCompleteMorder(HttpServletRequest request){
        String token = request.getHeader("token");
        Long userId = WxTokenUtil.fromTokenGetId(token);  //用户id

        List<BaseMorderDTO> results = userMapper.findCompleteMorder(userId);
        return new ResponseUtil(200,"ok",results);

    }

    /**
     * 根据医生id查询医生评论（一次10条）
     * @param id
     * @param page
     * @return
     */
    @GetMapping("get_comments")
    public ResponseUtil getComments(@RequestParam("id") Long id,@RequestParam(value = "page",defaultValue = "1")Integer page){

        if (page<1) page=1;

        int start = (page-1)*10;

        List<CommontDTO> result = userMapper.findCommontByDoctorId(id,start);

        return new ResponseUtil(200,"ok",result);

    }

    /**
     * 根据图文订单的id返回图文订单的所有聊天记录(type:1-用户 2-医生)
     * @param id
     * @return
     */
    @GetMapping("get_morder_message")
    public ResponseUtil getMorderMessage(@RequestParam("id")Long id){

        if (id<1) return new ResponseUtil(460,"error",null);

        List<MorderMessages> result = userMapper.findAllMorderMessage(id);

        return new ResponseUtil(200,"ok",result);

    }


    /**
     * 根据订单id得到图文订单详细信息
     * @param id
     * @return
     */
    @GetMapping("show_morder")
    public ResponseUtil showMorder(@RequestParam("id")Long id){

        if (id<1) return new ResponseUtil(460,"error",null);

        DetailMorderDTO morder = userMapper.findDetailMorder(id);

        return new ResponseUtil(200,"ok",morder);
    }


    /**
     * 回复图文订单
     * @param id
     * @param content
     * @param type (type:1-用户 2-医生)
     * @return
     */
    @PostMapping("reply_morder_message")
    public ResponseUtil replyMorderMessage(@RequestParam("id")Long id,
                                           @RequestParam(value = "content",required = false)String content,
                                           @RequestParam("type")Integer type){

        SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String time = f.format(now);
        MorderMessages message = new MorderMessages(null,id,time,content,type);

        int result = userMapper.replyMorderMessage(message);
        if (result==1) return new ResponseUtil(200,"ok",null);
        else return new ResponseUtil(460,"error",null);

    }


    /**
     * 用户确认图文订单
     * @param id
     * @return
     */
    @GetMapping("confirm_morder")
    public ResponseUtil confirmMorder(@RequestParam("id")Long id){

        SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String time = f.format(now);

        int result = userMapper.confirmMorder(id, time);
        if (result==1){
            Long doctorid = userMapper.findDoctorIdByMorderId(id);
            int sign = userMapper.increDoctorServices(doctorid);
        }

        return new ResponseUtil(200,"ok",null);
    }


    /**
     * 用户评价图文订单
     * @param id
     * @param content
     * @param star
     * @return
     */
    @PostMapping("evalute_morder")
    public ResponseUtil evaluateMorder(@RequestParam("id")Long id,@RequestParam("content")String content,
                                       @RequestParam("star")Long star){

        //将订单状态变为已完成
        int sign = userMapper.evaluateMorder(id);
        if (sign!=1) return new ResponseUtil(460,"error",null);

        SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String time = f.format(now);

        Long type = 1L; //1-图文订单 2-电话订单

        Morder morder = userMapper.findMorder(id);
        Long doctorid = null;
        if (morder!=null) doctorid = morder.getDoctorid();

        CommontDTO commont = new CommontDTO(null,id,content,star,type,doctorid,time);

        //插入评价信息
        int result = userMapper.insertCommont(commont);

        if (result!=1) return new ResponseUtil(460,"error",null);

        //更新医生评价字段
        if (star>=4) userMapper.updateDoctorCommontAndGood(doctorid);
        else userMapper.updateDoctorCommont(doctorid);

        return new ResponseUtil(200,"ok",null);
    }


    /**
     * 根据订单id查询评价
     * @param id
     * @return
     */
    @GetMapping("getCommonts")
    public ResponseUtil getCommontById(@RequestParam("id")Long id){

        if (id<1) return new ResponseUtil(460,"error",null);

        CommontDTO result = userMapper.getCommontById(id);

        return new ResponseUtil(200,"ok",result);

    }



    /**
     * 新增电话订单
     * @param id
     * @param porderPrice
     * @param phone
     * @param freeTime
     * @param request
     * @return
     */
    @PostMapping("publish_porder")
    public ResponseUtil publishPorder(@RequestParam("id")Long id,@RequestParam("price")Double porderPrice,
                                      @RequestParam("phone")String phone,@RequestParam("freeTime")String freeTime,
                                      HttpServletRequest request){

        String token = request.getHeader("token");
        Long userId = WxTokenUtil.fromTokenGetId(token);  //用户id

        SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String order_time = f.format(now);
        String freed_time;
        if (!freeTime.contains("-")) freed_time =  f.format(new Date(Long.parseLong(freeTime)));
        else freed_time = freeTime;

        Porder porder = new Porder(null,order_time,phone,freed_time,0,null,porderPrice,userId,id,0,0);
        int result = userMapper.insertPorder(porder);
        if (result==1) return new ResponseUtil(200,"ok",null);
        else return new ResponseUtil(460,"error",null);

    }

    /**
     * 获取用户进行中的电话订单
     * @param request
     * @return
     */
    @GetMapping("find_going_porder")
    public ResponseUtil findGoingPorder(HttpServletRequest request){

        String token = request.getHeader("token");
        Long userId = WxTokenUtil.fromTokenGetId(token);  //用户id

        List<BasePorderDTO> result = userMapper.findGoingPorder(userId);

        return new ResponseUtil(200,"ok",result);
    }

    /**
     * 获取用户待评价的电话订单
     * @param request
     * @return
     */
    @GetMapping("find_evaluate_porder")
    public ResponseUtil findEvaluatePorder(HttpServletRequest request){

        String token = request.getHeader("token");
        Long userId = WxTokenUtil.fromTokenGetId(token);  //用户id

        List<BasePorderDTO> result = userMapper.findEvaluatePorder(userId);

        return new ResponseUtil(200,"ok",result);
    }

    /**
     * 获取用户已完成的电话订单
     * @param request
     * @return
     */
    @GetMapping("find_complete_porder")
    public ResponseUtil findCompletePorder(HttpServletRequest request){

        String token = request.getHeader("token");
        Long userId = WxTokenUtil.fromTokenGetId(token);  //用户id

        List<BasePorderDTO> result = userMapper.findCompletePorder(userId);

        return new ResponseUtil(200,"ok",result);
    }


    /**
     * 根据电话订单id查询电话订单详情
     * @param id
     * @return
     */
    @GetMapping("show_porder")
    public ResponseUtil showPorder(@RequestParam("id")Long id){

        if (id<1)  new ResponseUtil(460,"error",null);

        DetailPorderDTO porder = userMapper.findDetailPorderById(id);

        return new ResponseUtil(200,"ok",porder);

    }

    /**
     * 用户确认电话订单
     * @param id
     * @return
     */
    @GetMapping("confirm_porder")
    public ResponseUtil confirmPorder(@RequestParam("id")Long id){

        SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String time = f.format(now);

        int result = userMapper.confirmPorder(id,time);
        if (result==1){
            Long doctorid = userMapper.findDoctorIdByPorderId(id);
            int sign = userMapper.increDoctorServices(doctorid);
        }

        return new ResponseUtil(200,"ok",null);
    }

    /**
     * 用户评价电话订单
     * @param id
     * @param content
     * @param star
     * @return
     */
    @PostMapping("evalute_porder")
    public ResponseUtil evaluatePorder(@RequestParam("id")Long id,@RequestParam("content")String content,
                                       @RequestParam("star")Long star){

        //将订单状态变为已完成
        int sign = userMapper.evaluatePorder(id);
        if (sign!=1) return new ResponseUtil(460,"error",null);

        SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String time = f.format(now);

        Long type = 2L; //1-图文订单 2-电话订单

        Porder porder = userMapper.findPorderById(id);
        Long doctorid = null;
        if (porder!=null) doctorid = porder.getDoctorid();

        CommontDTO commont = new CommontDTO(null,id,content,star,type,doctorid,time);

        //插入评价信息
        int result = userMapper.insertCommont(commont);

        if (result!=1) return new ResponseUtil(460,"error",null);

        //更新医生评价字段
        if (star>=4) userMapper.updateDoctorCommontAndGood(doctorid);
        else userMapper.updateDoctorCommont(doctorid);

        return new ResponseUtil(200,"ok",null);
    }

    /**
     * 根据科室获取医生基本数据并且按照好评数排序
     * @param department
     * @return
     */
    @GetMapping("find_goodCommonts_doctor")
    public ResponseUtil findDoctorOrderByGoodcommonts(String department,@RequestParam(value = "page",defaultValue = "1") Integer page){

        int start = (page-1)*10;
        List<BaseDoctorMessage> result = userMapper.findDoctorOrderByGoodCommonts(department,start);
        for (BaseDoctorMessage re:result){
            String head = IMAGE_PATH+"images/"+re.getId()+"/head.jpg";
            re.setHead(head);
        }
        return new ResponseUtil(200,"ok",result);
    }

    /**
     * 根据科室获取医生基本数据并且按照服务次数排序
     * @param department
     * @return
     */
    @GetMapping("find_services_doctor")
    public ResponseUtil findDoctorOrderByServices(String department,@RequestParam(value = "page",defaultValue = "1") Integer page){

        int start = (page-1)*10;
        List<BaseDoctorMessage> result = userMapper.findDoctorOrderByServices(department,start);
        for (BaseDoctorMessage re:result){
            String head = IMAGE_PATH+"images/"+re.getId()+"/head.jpg";
            re.setHead(head);
        }
        return new ResponseUtil(200,"ok",result);
    }

    /**
     * 根据科室获取医生基本数据并且按照价格从低到高
     * @param department
     * @return
     */
    @GetMapping("find_priceasc_doctor")
    public ResponseUtil findDoctorOrderByPriceAsc(String department,@RequestParam(value = "page",defaultValue = "1") Integer page){

        int start = (page-1)*10;
        List<BaseDoctorMessage> result = userMapper.findDoctorOrderByPriceAsc(department,start);
        for (BaseDoctorMessage re:result){
            String head = IMAGE_PATH+"images/"+re.getId()+"/head.jpg";
            re.setHead(head);
        }
        return new ResponseUtil(200,"ok",result);
    }

    /**
     * 根据科室获取医生基本数据并且按照价格从高到低
     * @param department
     * @return
     */
    @GetMapping("find_pricedesc_doctor")
    public ResponseUtil findDoctorOrderByPriceDesc(String department,@RequestParam(value = "page",defaultValue = "1") Integer page){

        int start = (page-1)*10;
        List<BaseDoctorMessage> result = userMapper.findDoctorOrderByPriceDesc(department,start);
        for (BaseDoctorMessage re:result){
            String head = IMAGE_PATH+"images/"+re.getId()+"/head.jpg";
            re.setHead(head);
        }
        return new ResponseUtil(200,"ok",result);
    }

    /**
     * 根据科室获取医生基本数据并且按照省市筛选
     * @param department
     * @return
     */
    @GetMapping("find_place_doctor")
    public ResponseUtil findDoctorOrderByPlace(String department,@RequestParam(value = "page",defaultValue = "1") Integer page,
                                               @RequestParam(value = "province",defaultValue = "")String province,
                                               @RequestParam(value = "city",defaultValue = "")String city){

        int start = (page-1)*10;
        String pro2 = province+"%";
        String c2 = city+"%";
        List<BaseDoctorMessage> result = userMapper.findDoctorOrderByPlace(department,start,pro2,c2);
        for (BaseDoctorMessage re:result){
            String head = IMAGE_PATH+"images/"+re.getId()+"/head.jpg";
            re.setHead(head);
        }
        return new ResponseUtil(200,"ok",result);
    }

    /**
     * 根据关键字查询医生信息（姓名/医院/科室 /疾病）
     * @param keyword
     * @return
     */
    @GetMapping("search_doctor")
    public ResponseUtil searchDoctorMessage(@RequestParam(value = "keyword",defaultValue = "")String keyword,
                                            @RequestParam(value = "page",defaultValue = "1") Integer page){

        if (keyword.equals("")) return new ResponseUtil(460,"error",null);

        int start = (page-1)*10;
        List<BaseDoctorMessage> result = null;

        result = userMapper.searchDoctorByKeyWord(keyword,start);

        if ((result==null)||(result.size()==0)){
            result = userMapper.searchDoctorBySkill(keyword,start);
        }

        for (BaseDoctorMessage re:result){
            String head = IMAGE_PATH+"images/"+re.getId()+"/head.jpg";
            re.setHead(head);
        }

        return new ResponseUtil(200,"ok",result);
    }




    /**
     * 获取健康资讯文章列表(一页五条)
     * @param page
     * @return
     */
    @GetMapping("getHealthyAbouts")
    public ResponseUtil getHealthyAbouts(@RequestParam(value = "page",defaultValue = "1") Integer page){

        int start = (page-1)*5;

        List<BaseHealthyAbout> result = userMapper.findAllHealthyAbouts(start);
        for (BaseHealthyAbout temp : result){

            String id = temp.getId();
            Long name = Long.valueOf(id)%5+1;
            String image = "https://www.qnm.green:8080/healthy/"+name+".jpg";
            temp.setCoverImage(image);
            Integer view = redisService.getArticleView(id);
            if (view==null) view = 0;
            Integer likes = redisService.getArticleLikes(id);
            if (likes==null) likes = 0;
            temp.setWatchs(view);
            temp.setLikes(likes);
        }

        return new ResponseUtil(200,"ok",result);

    }


    /**
     * 根据文章id获取文章详细信息
     * @param id
     * @return
     */
    @GetMapping("getHealthyAboutById")
    public ResponseUtil getHealthyAboutById(@RequestParam("id")String id){

        Long new_id = Long.valueOf(id);

        DetailHealthyAbout result = userMapper.findHealthyAboutById(new_id);

        Integer view = redisService.getArticleView(id);
        if (view==null) view = 0;
        Integer likes = redisService.getArticleLikes(id);
        if (likes==null) likes = 0;

        result.setWatchs(view);
        result.setLikes(likes);

        return new ResponseUtil(200,"ok",result);

    }

    /**
     * 根据文章id获取文章观看数
     * @param id
     * @return
     */
    @GetMapping("getWatch")
    public ResponseUtil getWatch(@RequestParam("id")String id){

        Integer result = redisService.getArticleView(id);
        if (result==null) result = 0;
        return new ResponseUtil(200,"ok",result);

    }

    /**
     * 增加文章观看数（+1）
     * @param id
     * @return
     */
    @GetMapping("incrWatch")
    public ResponseUtil incrWatch(@RequestParam("id")String id){

        redisService.increArticleView(id);
        return new ResponseUtil(200,"ok",null);

    }

    /**
     * 根据文章id获取文章点赞数
     * @param id
     * @return
     */
    @GetMapping("getLikes")
    public ResponseUtil getLikes(@RequestParam("id")String id){

        Integer result = redisService.getArticleLikes(id);
        if (result==null) result = 0;
        return new ResponseUtil(200,"ok",result);

    }

    /**
     * 增加文章点赞数（+1）
     * @param id
     * @return
     */
    @GetMapping("incrLikes")
    public ResponseUtil incrLikes(@RequestParam("id")String id){

        redisService.increArticleLikes(id);
        return new ResponseUtil(200,"ok",null);

    }


    /**
     * 统一异常处理
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResponseUtil handlerException(Exception e){
        System.out.println("error catch");
        e.printStackTrace();
        return new ResponseUtil(460,"fail",null);
    }

}

package com.hk.server.utils;

import com.hk.server.entity.Audit;
import com.hk.server.service.AuditService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @program: gg2020
 * @description: 数据库批量插入
 * @author: 头微凉
 * @create: 2020-09-10 10:43
 */
@RestController
@RequestMapping("sql")
@Api(value = "管理员端的接口")
public class SqlInsertUtil {

    @Autowired
    AuditService service;

    @GetMapping("insert")
    @ApiOperation(value = "插入随机审核表记录")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "okk"),
                    @ApiResponse(code = 460, message = "error")
            }
    )
    public ResponseUtil inserAudit() {
        //13个姓氏
        ArrayList<String> firstName = new ArrayList<>(Arrays.asList("刘", "李", "陈", "林", "梁", "黄", "杨", "肖", "钟", "杜", "范", "汪", "郑"));

        //30个名称
        ArrayList<String> secondName = new ArrayList<>(Arrays.asList(
                "时针", "友奇", "国栋", "番禺", "天佑", "乐町", "乐天", "炜燕", "继伟", "爱思",
                "康乐", "思乐", "富于", "钿储", "丝锥", "无忧", "无虑", "无极", "无愁", "极炜",
                "泽天", "天泽", "田亮", "思宝", "思佑", "斯敏", "丝丝", "毛毛", "亮丽", "凉凉"
        ));

        //21个省份
        ArrayList<String> provinces = new ArrayList<>(Arrays.asList(
                "北京", "天津", "上海", "重庆", "河北", "山西", "辽宁", "吉林", "黑龙江", "江苏",
                "浙江", "安徽", "福建", "江西", "山东", "河南", "湖北", "湖南", "广东", "海南", "四川"
        ));

        //5个医院统称
        ArrayList<String> hospital = new ArrayList<>(Arrays.asList("人民医院", "民生医院", "市中医院", "平安医院", "解放军医院"));

        //10个科室
        ArrayList<String> department = new ArrayList<>(Arrays.asList(
                "内科", "外科", "妇产科", "男科", "儿科",
                "五官科", "皮肤科", "心理科", "骨科", "脑科"
        ));

        Audit temp = null;
        Random random = new Random();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 50; i++) {
            //性别
            int sex = random.nextInt(2);
            //姓氏下标
            int rf = random.nextInt(13);
            //名称下标
            int rs = random.nextInt(30);
            //省份下标
            int rp = random.nextInt(21);
            //医院下标
            int rh = random.nextInt(5);
            //科室下标
            int rd = random.nextInt(10);

            temp = new Audit(0L, sex, firstName.get(rf) + secondName.get(rs),provinces.get(rp), "市中心", "详细地址", "济世救人", "世代为医", provinces.get(rp) + hospital.get(rh), department.get(rd), department.get(rd) + "副科", "主任", "针灸", 50.5, 100.5, format.format(date),"123@qq.com");
            System.out.println(temp);
            service.insertOnce(temp);
        }
        return new ResponseUtil(200, "ok", 123);
    }

}
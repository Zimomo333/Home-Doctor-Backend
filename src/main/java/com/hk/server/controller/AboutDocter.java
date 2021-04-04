package com.hk.server.controller;

import com.hk.server.dao.DoctorMapper;
import com.hk.server.dto.*;
import com.hk.server.entity.Admin;
import com.hk.server.entity.Healthrecord;
import com.hk.server.entity.Morder1;
import com.hk.server.entity.MorderMessages;
import com.hk.server.service.AdminService;
import com.hk.server.service.DoctorService;
import com.hk.server.service.HealthyRecordService;
import com.hk.server.utils.ResponseUtil;
import com.hk.server.utils.TokenUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: gg2020
 * @description: 与医生用户相关的接口
 * @author: 头微凉
 * @create: 2020-10-04 21:31
 */

@RestController
@RequestMapping("doctor")
@Api(value = "医生端的接口")
@SuppressWarnings("all")
public class AboutDocter {

    @Autowired
    DoctorService doctorService;

    @Autowired
    HealthyRecordService healthyRecordService;

    @Autowired
    AdminService adminService;

    @Autowired
    DoctorMapper doctorMapper;

    @PostMapping("login")
    @ApiOperation("医生登录接口")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "string", name = "account", value = "医生账号", required = true, dataType = "string"),
                    @ApiImplicitParam(paramType = "string", name = "password", value = "密码", required = true, dataType = "string")
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "登录成功", response = TokenDTO.class),
                    @ApiResponse(code = 460, message = "账号或密码错误", response = TokenDTO.class)
            }
    )
    public TokenDTO login(@RequestParam(value = "account", required = true) String account,
                          @RequestParam(value = "password", required = true) String password) {
        if (account == null || password == null
                || account.length() <= 0 || account.length() > 24
                || password.length() <= 0 || password.length() > 24) {
            return new TokenDTO(460, "账号或密码超出范围", null);
        }
        Admin admin = doctorService.findDocterByAccount(account);
        if (admin == null || !admin.getPassword().equals(password))
            return new TokenDTO(460, "账号或密码错误", null);
        String token = TokenUtil.sign(admin);
        return new TokenDTO(200, "登录成功", token);
    }

    @GetMapping("test")
    public ResponseUtil test() {
        return new ResponseUtil(200, "token有效", null);
    }

    @GetMapping("findHealthyRecord")
    public ResponseUtil findHealthyRecord(@RequestParam(value = "id", required = true) int id) {
        Healthrecord temp = healthyRecordService.findById(id);
        return new ResponseUtil(200, "健康档案", temp);
    }


    @GetMapping("changeWorkState")
    public ResponseUtil changeWorkState(HttpServletRequest request) {
        String token = request.getHeader("token");
        String account = TokenUtil.getAccount(token);
        int state = adminService.selectState(Integer.valueOf(account));
        if (state == 0) state = 1;
        else state = 0;
        if (adminService.changeDoctorState(state, Integer.valueOf(account)) <= 0)
            return new ResponseUtil(462, "修改失败", state);
        return new ResponseUtil(200, "修改成功", state);
    }

    @GetMapping("getMorders")
    public AllMordersDTO getGoingMorders(HttpServletRequest request,
                                         @RequestParam(value = "curPage", defaultValue = "1") Integer curPage,
                                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                         @RequestParam(value = "user_confirm", defaultValue = "0") Integer user_confirm,
                                         @RequestParam Map<String, Object> map
    ) {
        String token = request.getHeader("token");

        String id = TokenUtil.getAccount(token);
        map.put("id", Long.valueOf(id));
        map.put("start", (curPage - 1) * pageSize);
        map.put("end", pageSize);
        map.put("user_confirm", user_confirm);
        List<Morder1> myMorder = doctorMapper.findMyMorder(map);
        return new AllMordersDTO(200, "查询成功", myMorder);
//
//        int start = (curPage-1)*pageSize;
//        int end = pageSize;
//
//        List<NewBaseMorderDTO> result = doctorMapper.getGoingMorders(Long.valueOf(id), start, end);
//
//        return new ResponseUtil(200,"查询成功",result);

    }

    @GetMapping("getMorder")
    public AllMessagesDTO getMorder(@RequestParam("id") Long id) {
        List<MorderMessages> messages = doctorMapper.findMessageById(id);
        return new AllMessagesDTO(200, "查询成功", messages);
    }

    @PostMapping("replyMorder")
    public ResponseUtil replyMorder(@RequestParam("id") Long id,
                                    @RequestParam("content") String content,
                                    @RequestParam("type") Integer type) {


        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String time = f.format(now);


        MorderMessages morderMessages = new MorderMessages(null, id, time, content, type);

        int result = doctorMapper.insertMorderMessage(morderMessages);

        if (result == 1) return new ResponseUtil(200, "回复成功", null);
        else return new ResponseUtil(460, "回复失败", null);

    }
}
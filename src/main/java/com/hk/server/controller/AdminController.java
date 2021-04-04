package com.hk.server.controller;

import com.hk.server.dto.AllAuditDTO;
import com.hk.server.dto.AllDoctorDTO;
import com.hk.server.dto.FailDTO;
import com.hk.server.dto.TokenDTO;
import com.hk.server.entity.Admin;
import com.hk.server.entity.Audit;
import com.hk.server.entity.Doctor;
import com.hk.server.service.AdminService;
import com.hk.server.service.AuditService;
import com.hk.server.service.DoctorService;
import com.hk.server.utils.EmailUtil;
import com.hk.server.utils.ResponseUtil;
import com.hk.server.utils.TokenUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("admin")
@Api(value = "管理员端的接口")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    AuditService auditService;

    @Autowired
    DoctorService doctorService;

    @PostMapping("/login")
    @ApiOperation(value = "管理员登录接口")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "string", name = "account", value = "管理员账号", required = true, dataType = "string"),
                    @ApiImplicitParam(paramType = "string", name = "password", value = "管理员密码", required = true, dataType = "string")
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "okk", response = TokenDTO.class),
                    @ApiResponse(code = 460, message = "fail", response = TokenDTO.class)
            }
    )
    public TokenDTO login(@RequestParam("account") String account, @RequestParam("password") String password) {

        if (account == null || password == null
                || account.length() <= 0 || account.length() > 24
                || password.length() <= 0 || password.length() > 24) {
            return new TokenDTO(460, "账号或密码错误", null);
        }

        Admin admin = adminService.findAdminByAccount(account);
        if (admin == null || !admin.getPassword().equals(password)) return new TokenDTO(460, "fail", null);

        String token = TokenUtil.sign(admin);
        return new TokenDTO(200, "登录成功", token);
    }


    @GetMapping("changeDocterState")
    @ApiOperation("管理员改变医生账号状态，停用或启用")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "int", name = "id", value = "需要修改状态的医生id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "int", name = "state", value = "医生账号状态即将变更的状态，0表示停用，1表示启用", required = true, dataType = "int"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功", response = ResponseUtil.class),
            @ApiResponse(code = 461, message = "修改失败", response = ResponseUtil.class)
    })
    public ResponseUtil changeState(@RequestParam(value = "id", required = true) int id,
                                    @RequestParam(value = "state", required = true) int state) {
        try {
            adminService.changeDoctorState(state, id);
        } catch (Exception e) {
            return new ResponseUtil(461, "修改失败", null);
        }
        return new ResponseUtil(200, "修改成功", null);
    }

    @GetMapping("findAllDocter")
    @ApiOperation("管理员列表查询医用用户信息")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "int", name = "curPage", value = "当前页数(默认为1)", required = false, dataType = "int"),
                    @ApiImplicitParam(paramType = "int", name = "pageSize", value = "页面大小(默认为10)", required = false, dataType = "int")
            }
    )
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "okk", response = AllDoctorDTO.class),
                    @ApiResponse(code = 461, message = "fail", response = AllDoctorDTO.class)
            }
    )
    public AllDoctorDTO findAllDoctor(@RequestParam(value = "curPage", defaultValue = "1") int curPage,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                      @RequestParam Map<String,Object> map) {
        map.put("start", (curPage - 1) * pageSize);
        map.put("end", pageSize);
        List<Doctor> alldoctor = adminService.findAlldoctor(map);
        Long count = doctorService.findCount();
        return new AllDoctorDTO(200, "查询成功", alldoctor, count);
    }

    @GetMapping("findAllAudit")
    @ApiOperation(value = "查询所有的审核记录（初步信息，点击详细再出来全部信息）")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "int", name = "curPage", value = "当前页数(默认为1)", required = false, dataType = "int"),
                    @ApiImplicitParam(paramType = "int", name = "pageSize", value = "页面大小(默认为10)", required = false, dataType = "int")
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "返回响应", response = AllAuditDTO.class),
            }
    )
    public AllAuditDTO findAllAudit(@RequestParam(value = "curPage", defaultValue = "1") int curPage,
                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam Map<String, Object> map
    ) {
        map.put("start", (curPage - 1) * pageSize);
        map.put("end", pageSize);
        List<Audit> result = adminService.findAll(map);
        return new AllAuditDTO(200, "okk", result);
    }

    @GetMapping("dealAudit")
    @ApiOperation("管理员审核医生审核表的接口，点击审核通过或不通过提交，不通过一般要带不通过原因")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "int", name = "id", value = "需要处理的审核记录id", required = true, dataType = "int"),
                    @ApiImplicitParam(paramType = "int", name = "result", value = "判定的结果，通过或不通过，1通过，0不通过", required = true, dataType = "int"),
                    @ApiImplicitParam(paramType = "String", name = "content", value = "不通过的原因", required = false, dataType = "String")
            }
    )
    @ApiResponses(
            {
                    @ApiResponse(code = 400, message = "请求参数错误", response = ResponseUtil.class),
                    @ApiResponse(code = 200, message = "okk", response = ResponseUtil.class),
            }
    )
    public ResponseUtil dealAudit(@RequestParam(value = "id", required = true) int id,
                                  @RequestParam(value = "result", required = true) int result,
                                  @RequestParam(value = "content", required = false) String content) {
        Audit temp = auditService.findById(id);
        if (id == 0 || temp == null)
            return new ResponseUtil(400, "badrequest", null);


        //审核通过的业务逻辑
        //      创建医生用户
        //      将审核表的信息赋给医生
        //      发送邮件通知审核通过，告诉用户账号和密码
        if (result == 1) {
            Doctor doctor = createDoctor(temp);
            doctor.setPassword(randomPassword());
            doctorService.inserOne(doctor);
            String finalContent = "尊敬的" + doctor.getRealName();
            finalContent += doctor.getSex() == 0 ? "女士" : "男士";
            finalContent += "，感谢您选择了我们，您的审核记录通过了，登录账号为 " + doctor.getEmail() + " ,登录密码为: " + doctor.getPassword();
            new EmailUtil(finalContent, temp.getEmail()).start();
        }
        //审核不通过的业务逻辑
        //      发送邮件通知医生审核失败，并且告知失败原因
        //      删除审核记录，方便医生下次提交审核
        else {
            String finalContent = "尊敬的" + temp.getRealName();
            finalContent += temp.getSex() == 0 ? "女士" : "男士";
            finalContent += "，您的审核结果为失败，失败原因如下,请检查修改后重新提交:\n";
            finalContent += content;
            new EmailUtil(finalContent, temp.getEmail()).start();
        }
        auditService.deleteOne(id);
        return new ResponseUtil(200, "okk", null);
    }


    @GetMapping("test")
    public ResponseUtil test() {
        return new ResponseUtil(200, "token有效", null);
    }


    /**
     * 统一异常处理
     *
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResponseUtil handlerException(Exception e) {
        e.printStackTrace();
        return new ResponseUtil(460, "服务器发生错误", null);
    }


    /**
     * @Description:得到随机密码
     * @param:
     * @return: 随机密码的字符串
     * @Author: 头微凉
     * @date: 2020/9/16
     */
    public String randomPassword() {
        char[] s = new char[10];
        for (int i = 0; i < 10; i++) {
            switch (i % 3) {
                case 0:
                    s[i] = (char) ('A' + Math.random() * 26);
                    break;
                case 1:
                    s[i] = (char) ('a' + Math.random() * 26);
                    break;
                case 2:
                    s[i] = (char) ('0' + Math.random() * 10);
                    break;
            }
        }
        return s.toString();
    }


    /**
     * Description:将记录实体对象的字段赋值给医生实体对象
     *
     * @param audit: 记录实体对象
     * @return com.hk.server.entity.Doctor
     * @author 头微凉
     * 创建时间: 2020/9/18
     */
    public Doctor createDoctor(Audit audit) {
        Doctor doctor = new Doctor();
        doctor.setId(audit.getId());
        doctor.setSex(audit.getSex());
        doctor.setRealName(audit.getRealName());
        doctor.setProvince(audit.getProvince());
        doctor.setArea(audit.getArea());
        doctor.setAddress(audit.getAddress());
        doctor.setMessage(audit.getMessage());
        doctor.setBackground(audit.getBackground());
        doctor.setHospital(audit.getHospital());
        doctor.setDepartment(audit.getDepartment());
        doctor.setDeputyDeparment(audit.getDeputyDeparment());
        doctor.setProfessor(audit.getProfessor());
        doctor.setSkills(audit.getSkills());
        doctor.setMorderPrice(audit.getMorderPrice());
        doctor.setPorderPrice(audit.getPorderPrice());
        doctor.setEmail(audit.getEmail());
        return doctor;
    }
}

package com.hk.server.controller;

import com.hk.server.entity.Audit;
import com.hk.server.service.AdminService;
import com.hk.server.service.AuditService;
import com.hk.server.service.DoctorService;
import com.hk.server.utils.FileSave;
import com.hk.server.utils.ResponseUtil;
import com.hk.server.utils.TokenUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: gg2020
 * @description: 审核表相关控制器
 * @author: 头微凉
 * @create: 2020-09-12 08:51
 */
@RestController
@RequestMapping("audit")
@Api("有关审核表的接口，主要有医生提交审核资料接口")
public class AuditController {

    @Autowired
    AuditService auditService;


    @PostMapping("upload")
    @ApiOperation("医生提交审核表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "image", name = "files", value = "选择上传的证明图片，默认没有", required = false, dataType = "MultipartFile"),
            @ApiImplicitParam(paramType = "int", name = "sex", value = "医生性别,0女，1性", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "string", name = "realName", value = "真实姓名", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "string", name = "province", value = "省份，Json文件中省份对应的字符串编号", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "string", name = "area", value = "市区，同上", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "string", name = "address", value = "补充详细地址", required = false, dataType = "string"),
            @ApiImplicitParam(paramType = "string", name = "message", value = "寄语", required = false, dataType = "string"),
            @ApiImplicitParam(paramType = "string", name = "background", value = "医学背景", required = false, dataType = "string"),
            @ApiImplicitParam(paramType = "string", name = "hospital", value = "所在医院", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "string", name = "department", value = "所在科室", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "string", name = "deputyDepartment", value = "所在科室的副科室", required = false, dataType = "string"),
            @ApiImplicitParam(paramType = "string", name = "professor", value = "在医院的职位", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "string", name = "skills", value = "擅长", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "float", name = "moderPrice", value = "图文问诊期望价格", required = true, dataType = "float"),
            @ApiImplicitParam(paramType = "float", name = "porderPrice", value = "电话问诊期望价格", required = true, dataType = "float"),
            @ApiImplicitParam(paramType = "string", name = "Code", value = "所填写的验证码", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "string", name = "email", value = "邮箱", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "file", name = "files", value = "证明图片上传，多图片", required = false, dataType = "file"),
            @ApiImplicitParam(paramType = "file", name = "head", value = "医生头像图片", required = true, dataType = "file")
    })
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "okk", response = ResponseUtil.class),
                    @ApiResponse(code = 460, message = "fail"),
                    @ApiResponse(code = 459, message = "验证码错误"),
                    @ApiResponse(code = 461, message = "没有验证码或验证码错误"),
                    @ApiResponse(code = 462, message = "必填项不能为空"),
                    @ApiResponse(code = 463, message = "价格错误"),
                    @ApiResponse(code = 464, message = "邮箱已被占用或之前提交过审核表,请勿重复提交"),
                    @ApiResponse(code = 465, message = "个人头像图片不能为空")
            }
    )
    public ResponseUtil upload(@RequestParam(value = "files", required = false) MultipartFile[] files, @RequestParam(value = "head", required = true) MultipartFile head, Audit audit, HttpSession session, HttpServletRequest request) {
        /*
            判断必填项是否非空
         */
        int emptyNums = 0;
        if (StringUtils.isEmpty(audit.getRealName()))
            emptyNums++;
        else if (StringUtils.isEmpty(audit.getProvince()))
            emptyNums++;
        else if (StringUtils.isEmpty(audit.getArea()))
            emptyNums++;
        else if (StringUtils.isEmpty(audit.getHospital()))
            emptyNums++;
        else if (StringUtils.isEmpty(audit.getDepartment()))
            emptyNums++;
        else if (StringUtils.isEmpty(audit.getProfessor()))
            emptyNums++;
        else if (StringUtils.isEmpty(audit.getSkills()))
            emptyNums++;
        else if (StringUtils.isEmpty(audit.getArea()))
            emptyNums++;
        else if (StringUtils.isEmpty(audit.getEmail()))
            emptyNums++;
        if (emptyNums != 0)
            return new ResponseUtil(462, "必填项不能为空", null);
        //判断头像图片
        if (head == null) return new ResponseUtil(465, "头像不能为空", null);


        if (audit.getPorderPrice() < 0 || audit.getMorderPrice() < 0)
            return new ResponseUtil(463, "期望价格填写错误", null);

        audit.setCommitTime(String.valueOf(new Date().getTime()));


        /*
            这里得先判断邮箱是否被占用
            如果抛出DuplicateKeyException异常，说明唯一字段邮箱重复，则返回464错误
         */
        try {
            auditService.insertOnce(audit);
        } catch (DuplicateKeyException e) {
            return new ResponseUtil(464, "邮箱已被占用", null);
        }

        String path = "images/" + audit.getId() + "/";

        //头像文件转存
        if (!FileSave.saveFile(head, path + "head.jpg"))
            auditService.deleteOne(audit.getId());
        //图片文件转存
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                if (!FileSave.saveFile(files[i], path + i + ".jpg")) {
                    //文件上传失败，将审核表对应的记录删除
                    auditService.deleteOne(audit.getId());
                    break;
                }
            }
        }

        return new ResponseUtil(200, "okk", null);
    }

    @GetMapping("getNumsById")
    @ApiOperation("根据审核id获取审图图片数目")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "int", name = "id", value = "审核id", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功", response = ResponseUtil.class)
    })
    public ResponseUtil getNumsById(@RequestParam(value = "id", required = true) int id) {
        int nums = FileSave.fileNums("images/" + id);
        return new ResponseUtil(200, "查询成功", Integer.valueOf(nums));
    }


}
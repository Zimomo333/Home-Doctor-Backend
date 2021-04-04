package com.hk.server.controller;

import com.hk.server.utils.Image_Create;
import com.hk.server.utils.ResponseUtil;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @program: gg2020
 * @description: 医生前端验证码，防止恶意注册
 * @author: 头微凉
 * @create: 2020-09-11 10:29
 */

@RestController
@RequestMapping("verify")
@Api(value = "前端接受动态二维码的接口")
public class VerifyCode {

    @GetMapping("image")
    @ApiOperation(value = "前端医生提交审核资料时验证码的接口")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "int",name = "width",value = "验证码宽度(默认为80px)",required = false,dataType = "int"),
                    @ApiImplicitParam(paramType = "int",name = "heigth",value = "验证码高度(默认为20px)",required = false,dataType = "int")
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200,message = "ok"),
                    @ApiResponse(code = 460,message = "error")
            }
    )
    public void Verify_Image(@RequestParam(value = "width",defaultValue ="90")int width,
                                     @RequestParam(value = "heigth",defaultValue = "20") int height,
                                     HttpSession session, HttpServletResponse response) {
        Map<String, Object> map = Image_Create.generateCodeAndPic(width, height);
        //响应内容类型为图片
        response.setContentType("image/png");

        try {
            //获取响应输出流
            ServletOutputStream outputStream = response.getOutputStream();
            //把验证码放进session
            session.setAttribute("verifyCode",map.get("code"));
            //写回图片
            ImageIO.write((RenderedImage) map.get("codePic"),"jpeg",outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
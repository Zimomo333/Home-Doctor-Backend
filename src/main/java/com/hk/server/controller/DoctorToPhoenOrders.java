package com.hk.server.controller;

import com.hk.server.dto.AllPordersDTO;
import com.hk.server.entity.Porder;
import com.hk.server.entity.Porder1;
import com.hk.server.service.PorderService;
import com.hk.server.utils.ResponseUtil;
import com.hk.server.utils.TokenUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @program: gg2020
 * @description: 医生电话订单模块接口
 * @author: 头微凉
 * @create: 2020-10-06 15:16
 */
@RestController
@RequestMapping("doctor/porder")
@Api(value = "医生电话订单的接口")
public class DoctorToPhoenOrders {

    @Autowired
    PorderService porderService;

    @GetMapping("findMyPorders")
    @ApiOperation("查询该医生电话订单接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "int", name = "curPage", value = "当前页数(默认为1)", required = false, dataType = "int"),
            @ApiImplicitParam(paramType = "int", name = "pageSize", value = "页面大小(默认为10)", required = false, dataType = "int"),
            @ApiImplicitParam(paramType = "int", name = "isConfirm", value = "选择查询医生已确认还是未确认的订单，0未确认，1已确认,默认未确认", required = true, dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功", response = AllPordersDTO.class)
    })
    public AllPordersDTO findMyPorders(@RequestParam(value = "curPage", defaultValue = "1") int curPage,
                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                       @RequestParam(value = "isConfirm", defaultValue = "0") int isConfirm,
                                       @RequestParam Map<String, Object> map,
                                       HttpServletRequest request) {
        //先获取当前登陆医生的id
        String token = request.getHeader("token");
        String id = TokenUtil.getAccount(token);
        map.put("doctorid", Integer.valueOf(id));
        map.put("start",(curPage-1)*pageSize);
        map.put("end",pageSize);
        if (!map.containsKey("isConfirm"))
            map.put("isConfirm", "0");
        List<Porder1> myPorders = porderService.findMyPorders(map);
        return new AllPordersDTO(200, "查询电话订单成功", myPorders);
    }

    @PostMapping("comfirmOrder")
    @ApiOperation("医生联系用户后确认电话订单已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "int", name = "orderid", value = "要确认的电话订单id", required = true, dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "确认完成", response = ResponseUtil.class),
            @ApiResponse(code = 460, message = "确认失败", response = ResponseUtil.class)
    })
    public ResponseUtil confirmOrder(@RequestParam(value = "orderid", required = true) int orderid,
                                     HttpServletRequest request) {
        //先获取当前登陆医生id，只能确认自己的电话订单
        String token = request.getHeader("token");
        String account = TokenUtil.getAccount(token);
        if (porderService.confirm(orderid, Integer.valueOf(account)) <= 0)
            return new ResponseUtil(460, "确认失败", null);
        return new ResponseUtil(200, "确认成功", null);
    }
}
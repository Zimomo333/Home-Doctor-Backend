package com.hk.server.wx_server.utils;

import com.hk.server.entity.Doctor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@SuppressWarnings("all")
public class ScriptUtils {

    public static Doctor produceDoctorMessage(){
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

        Random random = new Random();
        //性别
        Integer sex = random.nextInt(2); //医生性别
        //姓氏下标
        int rf = random.nextInt(13);
        //名称下标
        int rs = random.nextInt(30);
        String realName = firstName.get(rf)+secondName.get(rs); //医生姓名
        //省份下标
        int rp = random.nextInt(21);
        String province = provinces.get(rp);  //省份
        String area = "";   //地区
        String address = "";  //地址
        String message = "妙手回春";  //信息
        String background = "中山大学医学院"; //背景
        //医院下标
        int rh = random.nextInt(5);
        String hospita = hospital.get(rh); //医院
        //科室下标
        int rd = random.nextInt(10);
        String departmen = department.get(rd); //科室
        String du_department = ""; //副科室
        String professor = "主治医生"; //职称
        String skills = "开脑 截肢";
        Double m_price = 1.0; //图文咨询价格
        Double p_price = 1.0;  //电话咨询价格
        Long services = 21l;  //总服务数
        Long allcommonts = 19L;  //所有评论
        Long goodcommonts = 12L;  //好评
        Double balance = 2.0; //余额
        Long state = 1l; //状态
        int number = (int)(Math.random()*90000+10000);
        String email =  number+"@qq.com"; //邮箱
        String password = String.valueOf(number); //密码
        //创建医生记录
        Doctor doctor = new Doctor(null,sex,realName,province,area,address,message,background,hospita,
                departmen,du_department,professor,skills,m_price,p_price,services,allcommonts,goodcommonts,
                balance,state,email,password);
        return doctor;
    }

}

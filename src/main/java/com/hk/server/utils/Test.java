package com.hk.server.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public String test() {
        return "慧慧qqwe";
    }

    public void config() {

    }

    private String aa;


    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = simpleDateFormat.parse("2020-09-19 08:54:30");
            System.out.println(parse.getTime());
        } catch (Exception e) {
            System.out.println("123");
        }

    }

    public void setAa(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //转成时间戳
        if (s.contains("-")) {
            try {
                Date parse = simpleDateFormat.parse(s);
                this.aa = String.valueOf(parse.getTime());
            } catch (Exception e) {
                this.aa = String.valueOf(new Date().getTime());
            }
        }
        //转成时间
        else {
            try {
                this.aa = simpleDateFormat.format(new Date(Long.parseLong(s)));
            }catch (Exception e){
                this.aa =simpleDateFormat.format(new Date());
            }

        }
    }
}

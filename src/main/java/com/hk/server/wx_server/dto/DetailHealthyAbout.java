package com.hk.server.wx_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailHealthyAbout implements Serializable {

    private String id;

    private String content;

    private String type;

    private String time;

    private String title;

    private Integer watchs;

    private Integer likes;

}

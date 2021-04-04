package com.hk.server.wx_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseHealthyAbout implements Serializable {

    private String id;

    private String type;

    private String time;

    private String title;

    private String coverImage;

    private Integer watchs;

    private Integer likes;
}

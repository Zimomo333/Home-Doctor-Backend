package com.hk.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewDetailMorderDTO implements Serializable {

    private Long id;

    private String beginTime;

    private String activeTime;

    private String content;

    private Double price;

    private Long userid;

}

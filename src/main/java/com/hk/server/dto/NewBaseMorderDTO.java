package com.hk.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewBaseMorderDTO implements Serializable {

    private Long id;

    private String beginTime;

    private String activeTime;

    private Double price;

    private Long userid;


}

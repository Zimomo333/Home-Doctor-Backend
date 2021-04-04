package com.hk.server.dto;

import com.hk.server.entity.MorderMessages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseMorderDTO implements Serializable {

    NewDetailMorderDTO morder;

    List<MorderMessages> morderMessages;

}

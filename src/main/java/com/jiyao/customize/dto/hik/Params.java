package com.jiyao.customize.dto.hik;

import lombok.Data;

import java.util.List;

@Data
public class Params {
    private String sendTime;

    private String ability;

    private String uids;

    private List<Events> events ;
}

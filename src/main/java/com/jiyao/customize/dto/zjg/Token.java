package com.jiyao.customize.dto.zjg;

import lombok.Data;

@Data
public class Token {

    private int expireTime;

    private String orgName;

    private String accessToken;

    private Long orgId;
}

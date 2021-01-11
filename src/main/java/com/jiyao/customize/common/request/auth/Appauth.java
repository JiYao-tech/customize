package com.jiyao.customize.common.request.auth;


import com.jiyao.customize.common.request.RequestRusultDat;
import lombok.Data;

/**
 * @author 36536
 */
@Data
public class Appauth extends RequestRusultDat {

    private int expireTime;

    private String orgName;

    private String accessToken;

    private int orgId;
}

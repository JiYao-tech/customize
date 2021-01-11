package com.jiyao.customize.service;

/**
 * @author 36536
 */
public interface AuthenticationService {

    /**
     * 获取签名
     * @return 签名信息
     */
    String getSign();

    /**
     * 获取token
     * @return
     */
    String getToken();

}

package com.jiyao.customize.common.request;

import com.alibaba.fastjson.JSONObject;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HikCommonRequest {

    @Value("${hik.ip}")
    private  String hikIp;

    @Value("${hik.port}")
    private  String hikPort;

    @Value("${hik.appKey}")
    private  String appKey;

    @Value("${hik.appSecret}")
    private  String appSecret;

    public  String  requestRusult(String uri,String requestParam ,String contentType){

        /**
         * STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
         */
        // artemis网关服务器ip端口
        ArtemisConfig.host = hikIp+":"+hikPort;
        // 秘钥appkey
        ArtemisConfig.appKey = appKey;
        // 秘钥appSecret
        ArtemisConfig.appSecret = appSecret;

        /**
         * STEP2：设置OpenAPI接口的上下文
         */
        final String ARTEMIS_PATH = "/artemis";

        /**
         * STEP3：设置接口的URI地址
         */
        final String previewURLsApi = ARTEMIS_PATH + uri;
        Map<String, String> path = new HashMap<String, String>(2) {
            {
                put("https://", previewURLsApi);//根据现场环境部署确认是http还是https
            }
        };

        JSONObject jsonObject = new JSONObject();
        /**
         * STEP6：调用接口
         */
        return ArtemisHttpUtil.doPostStringArtemis(path, requestParam, null, null, contentType , null);
    }

    public  String  requestRusult(String uri,String requestParam){

        /**
         * STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
         */
        // artemis网关服务器ip端口
        ArtemisConfig.host = hikIp+":"+hikPort;
        // 秘钥appkey
        ArtemisConfig.appKey = appKey;
        // 秘钥appSecret
        ArtemisConfig.appSecret = appSecret;

        /**
         * STEP2：设置OpenAPI接口的上下文
         */
        final String ARTEMIS_PATH = "/artemis";

        /**
         * STEP3：设置接口的URI地址
         */
        final String previewURLsApi = ARTEMIS_PATH + uri;
        Map<String, String> path = new HashMap<String, String>(2) {
            {
                put("https://", previewURLsApi);//根据现场环境部署确认是http还是https
            }
        };

        String contentType = "application/json";
        /**
         * STEP6：调用接口
         */
        return ArtemisHttpUtil.doPostStringArtemis(path, requestParam, null, null, contentType , null);
    }
}

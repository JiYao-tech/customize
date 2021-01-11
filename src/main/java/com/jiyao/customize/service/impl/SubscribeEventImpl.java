package com.jiyao.customize.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import com.jiyao.customize.service.SubscribeEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 36536
 */
@Service
public class SubscribeEventImpl implements SubscribeEvent {

    @Value("${hik.ip}")
    private String hikIp;

    @Value("${hik.port}")
    private String hikPort;

    @Value("${hik.appKey}")
    private String appKey;

    @Value("${hik.appSecret}")
    private String appSecret;

    @Value("${server.ip}")
    private String localhostIp;

    @Value("${server.port}")
    private String localhostPort;

    @Override
    //@PostConstruct
    public String subscribeAccessEvent() {

        int[] eventCode = new int[]{983299,983300,983301,983307,983308,983309,983310};

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
        final String previewURLsApi = ARTEMIS_PATH + "/api/eventService/v1/eventSubscriptionByEventTypes";
        Map<String, String> path = new HashMap<String, String>(2) {
            {
                put("https://", previewURLsApi);//根据现场环境部署确认是http还是https
            }
        };

        /**
         * STEP4：设置参数提交方式
         */
        String contentType = "application/json";


        /**
         * STEP5：组装请求参数
         */
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("eventTypes", eventCode);
        jsonBody.put("eventDest", "http://"+localhostIp+":"+localhostPort+"/v1/personAccess");
        String body = jsonBody.toJSONString();
        /**
         * STEP6：调用接口
         */
        String result = ArtemisHttpUtil.doPostStringArtemis(path, body, null, null, contentType , null);

        System.out.println("订阅成功");

        //解析返回值
        JSONObject jsonObject = JSONObject.parseObject(result);
        if(jsonObject.getString("code").equals("0")){
            String event = "订阅成功：可视对讲事件";
            return event;
        }else {
            return result;
        }
    }

    @Override
    public void queryEventSubscriptionView() {

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
        final String previewURLsApi = ARTEMIS_PATH + "/api/eventService/v1/eventSubscriptionView";
        Map<String, String> path = new HashMap<String, String>(2) {
            {
                put("https://", previewURLsApi);//根据现场环境部署确认是http还是https
            }
        };

        /**
         * STEP4：设置参数提交方式
         */
        String contentType = "application/json";


        /**
         * STEP5：组装请求参数
         */
        JSONObject jsonBody = new JSONObject();
        /**
         * STEP6：调用接口
         */
        String result = ArtemisHttpUtil.doPostStringArtemis(path, null, null, null, contentType , null);

        System.out.println(result);
    }
}

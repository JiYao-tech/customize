package com.jiyao.customize.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jiyao.customize.common.request.HikCommonRequest;
import com.jiyao.customize.service.SubscribeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author 36536
 */
@Service
public class SubscribeEventImpl implements SubscribeEvent {

    @Value("${server.ip}")
    private String localhostIp;

    @Value("${server.port}")
    private String localhostPort;

    @Autowired
    private HikCommonRequest hikCommonRequest;

    @Override
    @PostConstruct
    public void subscribeAccessEvent() {

        int[] eventCode = new int[]{983299,983300,983301,983307,983308,983309,983310};

        //STEP4：请求路径
        String path = "/api/eventService/v1/eventSubscriptionByEventTypes";

        /**
         * STEP5：组装请求参数
         */
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("eventTypes", eventCode);
        jsonBody.put("eventDest", "http://"+localhostIp+":"+localhostPort+"/v1/personAccess");

        String result = hikCommonRequest.requestRusult(path, jsonBody.toJSONString(), "application/json");

        //解析返回值
        JSONObject jsonObject = JSONObject.parseObject(result);

        if(jsonObject.getString("code").equals("0")){

            System.out.println("订阅事件成功："+result);
            queryEventSubscriptionView();

        }else {

            System.out.println("订阅事件失败："+result);
        }
    }

    @Override
    public void queryEventSubscriptionView() {

        //STEP4：请求路径
        String path = "/api/eventService/v1/eventSubscriptionView";

        /**
         * STEP6：调用接口
         */
        String result = hikCommonRequest.requestRusult(path, null, "application/json");

        System.out.println("已订阅成功："+result);
    }
}

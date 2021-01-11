package com.jiyao.customize.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiyao.customize.dto.zjg.Token;
import com.jiyao.customize.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 36536
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${zjg.orgKey}")
    private String appKey;

    @Value("${zjg.orgSecret}")
    private String appSecret;

    @Value("${zjg.serve}")
    private String serve;

    private Long timeStamp;

    @Autowired
    @Qualifier("httpClientTemplate")
    private RestTemplate restTemplate;

    @Override
    public String getSign() {
        String url = serve + "/v1.0/auth/generate/sign?appKey={appKey}&appSecret={appSecret}&timestamp={timestamp}";

        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("appKey",appKey);
        paramMap.put("appSecret",appSecret);
        paramMap.put("timestamp",timeStamp);

        JSONObject jsonObject = JSON.parseObject(restTemplate.getForObject(url, String.class, paramMap));
        return jsonObject.getString("data");
    }

    @Scheduled(cron = "0 0/20 * * * ? ")
    @Override
    public String getToken() {
        String url = serve+"/v1.0/auth/appauth";

        getTimeStamp();

        MultiValueMap<String, Object> paramMap= new LinkedMultiValueMap<>();
        paramMap.add("orgKey",appKey);
        paramMap.add("orgSecret",appSecret);
        paramMap.add("timestamp",timeStamp);
        paramMap.add("platSign",getSign());

        //头信息
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //请求体
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(paramMap,httpHeaders);

        JSONObject jsonObject = JSON.parseObject(restTemplate.postForObject(url, requestEntity, String.class));
        String data = jsonObject.getString("data");
        Token token = JSON.parseObject(data, Token.class);

        return token.getAccessToken();
    }

    /**
     * 获取时间戳
     * @return
     */
    public Long getTimeStamp(){
        Date date = new Date();
        return this.timeStamp = date.getTime();
    }
}

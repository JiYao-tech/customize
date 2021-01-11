package com.jiyao.customize.service.impl;

import com.alibaba.fastjson.JSON;
import com.jiyao.customize.common.request.auth.Appauth;
import com.jiyao.customize.common.response.ResultCode;
import com.jiyao.customize.dto.zjg.Community;
import com.jiyao.customize.service.AuthenticationService;
import com.jiyao.customize.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Value("${zjg.serve}")
    private String serve;

    @Autowired
    @Qualifier("httpClientTemplate")
    private RestTemplate restTemplate;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public ResultCode create(Community community) {

        String token = authenticationService.getToken();
        Appauth appauth = JSON.parseObject(token, Appauth.class);

        String url = serve+"/v1.0/villageinfo/create";

        //头信息
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //请求参数
        MultiValueMap<String, Object> paramMap= new LinkedMultiValueMap<>();
        paramMap.add("viAddress",community.getViAddress());
        paramMap.add("viCode",community.getViAreaCode());
        paramMap.add("viName",community.getViName());
        paramMap.add("viAreaCode",community.getViCode());
        paramMap.add("accessToken",appauth.getAccessToken());

        //封装请求体
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(paramMap,httpHeaders);

        String object = restTemplate.postForObject(url, requestEntity, String.class);
        System.out.println(object);

        return null;
    }
}

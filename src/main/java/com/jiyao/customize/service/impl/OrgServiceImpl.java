package com.jiyao.customize.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiyao.customize.common.request.HikCommonRequest;
import com.jiyao.customize.common.Result;
import com.jiyao.customize.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class OrgServiceImpl implements OrgService {

    @Autowired
    private HikCommonRequest hikCommonRequest;

    @Override
    public String queryOrgList(int pageNo, int pageSize) {

        String path = "/api/resource/v1/org/rootOrg";

        if(pageNo<=0){
            pageNo = 1;
        }

        if(pageSize<=0){
            pageSize = 10;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("pageNo",pageNo);
        map.put("pageSize",pageSize);

        String requestRusult = hikCommonRequest.requestRusult(path, map.toString(), "application/json");
        JSONObject jsonObject = JSON.parseObject(requestRusult);
        return jsonObject.getJSONObject("data").getString("orgIndexCode");
    }
}

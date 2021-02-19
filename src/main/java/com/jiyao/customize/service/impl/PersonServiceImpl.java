package com.jiyao.customize.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiyao.customize.common.CommonCode;
import com.jiyao.customize.common.PersonCode;
import com.jiyao.customize.common.QueryResult;
import com.jiyao.customize.common.request.HikCommonRequest;
import com.jiyao.customize.common.Result;
import com.jiyao.customize.dto.hik.person.*;
import com.jiyao.customize.service.OrgService;
import com.jiyao.customize.service.PersonService;
import com.jiyao.customize.utils.Base64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 36536
 */

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private OrgService orgService;

    @Autowired
    private HikCommonRequest hikCommonRequest;

    @Override
    public Result addPerson(Person person) {

        if(person != null){
            String orgIndexCode = orgService.queryOrgList(1, 100);
            person.setOrgIndexCode(orgIndexCode);

            List<Face> faces = person.getFaces();
            if(faces!=null && faces.size()>0 ){
                for (Face face : faces) {
                    face.setFaceData(Base64Utils.fileImageToBase64(face.getFaceData()));
                }
            }
        }

        String path = "/api/resource/v2/person/single/add";

        Result result = JSON.parseObject(hikCommonRequest.requestRusult(path, JSON.toJSONString(person)), Result.class);

        if(!result.getCode().equals("0")){
            return new Result(PersonCode.FAIL,result);
        }else {
            return new Result(CommonCode.SUCCESS);
        }
    }

    @Override
    public Result addFace(String personId, String faceData) {
        String path = "/api/resource/v1/face/single/add";

        Map<String, Object> map = new HashMap<>();
        map.put("personId",personId);
        map.put("faceData",Base64Utils.fileImageToBase64(faceData));

        Result result = JSON.parseObject(hikCommonRequest.requestRusult(path, JSON.toJSONString(map)), Result.class);

        if(!result.getCode().equals("0")){
            return new Result(PersonCode.FAIL,result);
        }else {
            return new Result(CommonCode.SUCCESS);
        }
    }

    @Override
    public Result addCard(CardS cardList) {
        String path = "/api/cis/v1/card/bindings";

        Result result = JSON.parseObject(hikCommonRequest.requestRusult(path, JSON.toJSONString(cardList)), Result.class);

        if(!result.getCode().equals("0")){
            return new Result(PersonCode.FAIL,result);
        }else {
            return new Result(CommonCode.SUCCESS);
        }
    }

    @Override
    public String queryPersonId(String certificateNo, List<String> paramValue) {
        String path = "/api/resource/v1/person/condition/personInfo";

        Map<String, Object> map = new HashMap<>();
        map.put("paramName",certificateNo);
        map.put("paramValue",paramValue);

        String requestRusult = hikCommonRequest.requestRusult(path, JSON.toJSONString(map));

        JSONObject jsonObject = JSON.parseObject(requestRusult);
        JSONObject data = jsonObject.getJSONObject("data");
        String list = data.getString("list");
        List<PersonMessage> personMessages = JSON.parseArray(list, PersonMessage.class);
        return personMessages.get(0).getPersonId();
    }
}

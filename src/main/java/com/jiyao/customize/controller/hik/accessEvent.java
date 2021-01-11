package com.jiyao.customize.controller.hik;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping("/v1")
public class accessEvent {

    @RequestMapping(value = "/personAccess",method = RequestMethod.POST )
    public void personAccess(@RequestBody Map<String,Object> map){
        String jsonString = JSON.toJSONString(map);
        System.out.println(jsonString);
    }
}

package com.jiyao.customize.controller.hik;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

@Controller
@RequestMapping("/v1")
public class AccessEventController {

    @RequestMapping(value = "/personAccess")
    public void personAccess(@RequestBody Map<String, Object> map){
        String s = JSON.toJSONString(map);
        System.out.println(s);
    }
}

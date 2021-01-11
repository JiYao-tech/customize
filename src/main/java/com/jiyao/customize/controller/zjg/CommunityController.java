package com.jiyao.customize.controller.zjg;

import com.jiyao.customize.common.response.ResultCode;
import com.jiyao.customize.dto.zjg.Community;
import com.jiyao.customize.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 36536
 */
@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @PostMapping("/create")
    public ResultCode create(@RequestBody Community community){
        return communityService.create(community);
    }
}

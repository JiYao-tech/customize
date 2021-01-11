package com.jiyao.customize;

import com.jiyao.customize.dto.zjg.Community;
import com.jiyao.customize.service.AuthenticationService;
import com.jiyao.customize.service.CommunityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomizeApplication.class)
public class CustomizeApplicationTests {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CommunityService communityService;

    @Test
    public void testGetSign() {
        authenticationService.getSign();
    }

    @Test
    public void testGetToken(){
        authenticationService.getToken();
    }

    @Test
    public void testcared(){
        Community community = new Community("江夏区纸坊街谭鑫培路15号博雅豪庭","420115BYHT","博雅豪庭","420115");
        communityService.create(community);
    }

    @Test
    public void testJSON() throws IOException {
    }
}

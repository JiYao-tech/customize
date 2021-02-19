package com.jiyao.customize;

import com.jiyao.customize.dao.third.UserMapper;
import com.jiyao.customize.dto.User;
import com.jiyao.customize.service.OrgService;
import com.jiyao.customize.service.PersonService;
import com.jiyao.customize.service.SubscribeEvent;
import com.jiyao.customize.utils.Base64Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomizeApplication.class)
public class CustomizeApplicationTests {

    @Autowired
    private PersonService personService;

    @Autowired
    private OrgService orgService;

    @Autowired
    private SubscribeEvent subscribeEvent;

    @Test
    public void testQueryPersonId() {
        List<String> list = new ArrayList<>();
        list.add("420704199608075274");
        String personId = personService.queryPersonId("certificateNo", list);
        System.out.println(personId);
    }

    @Test
    public void testQueryOrgList(){
        String s = orgService.queryOrgList(1, 100);
        System.out.println(s);
    }

    @Test
    public void testQueryEventSubscriptionView(){
        subscribeEvent.queryEventSubscriptionView();
    }

    @Test
    public void testFileImageToBase64(){
        String s = Base64Utils.fileImageToBase64("C:\\Users\\36536\\Desktop\\微信图片_20210207135322.jpg");
        System.out.println(s);
    }
}

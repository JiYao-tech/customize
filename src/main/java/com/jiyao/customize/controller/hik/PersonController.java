package com.jiyao.customize.controller.hik;

import com.jiyao.customize.common.Result;
import com.jiyao.customize.dto.hik.person.Person;
import com.jiyao.customize.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/add")
    public Result addPerson(@RequestBody Person person){
       return personService.addPerson(person);
    }
}

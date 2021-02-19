package com.jiyao.customize.dto.hik.person;

import lombok.Data;

import java.util.List;

@Data
public class Person {

    private String personName;

    private String gender;

    private String orgIndexCode;

    private String birthday;

    private String phoneNo;

    private String email;

    private String certificateType;

    private String certificateNo;

    private String jobNo;

    private List<Face> faces;
}

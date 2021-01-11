package com.jiyao.customize.dto.zjg;

import lombok.Data;

@Data
public class Community {

    private String viAddress;
    private String viCode;
    private String viName;

    public Community(String viAddress, String viCode, String viName, String viAreaCode) {
        this.viAddress = viAddress;
        this.viCode = viCode;
        this.viName = viName;
        this.viAreaCode = viAreaCode;
    }

    private String viAreaCode;
}

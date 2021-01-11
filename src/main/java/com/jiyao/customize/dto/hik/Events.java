package com.jiyao.customize.dto.hik;


import lombok.Data;

@Data
public class Events {
    private String data;

    private String eventId;

    private int eventType;

    private String eventTypeName;

    private String happenTime;

    private String srcIndex;

    private String srcName;

    private String srcParentIndex;

    private String srcType;

    private int status;

    private int timeout;


}

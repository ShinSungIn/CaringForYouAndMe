package com.edenranch.administrator.caringforyouandme.database.domain;

import lombok.Data;

@Data
public class Alarm {
    private Integer seq;
    private String time;
    private String isUse;
    private String isSun;
    private String isMon;
    private String isTue;
    private String isWed;
    private String isThu;
    private String isFri;
    private String isSat;
    private String content;
}

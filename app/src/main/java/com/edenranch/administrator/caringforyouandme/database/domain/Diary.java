package com.edenranch.administrator.caringforyouandme.database.domain;

import lombok.Data;

@Data
public class Diary {
    private Integer seq;
    private String subject;
    private String content;
//    private String postDt;
    private String regDt;
}

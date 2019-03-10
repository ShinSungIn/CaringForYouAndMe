package com.edenranch.administrator.caringforyouandme.database.domain;

import lombok.Data;

/**
 * 돌봄일기
 * 작성양식 변경: 환자상태, 돌봄활동, 느낌, 기타
 */
@Data
public class Diary {
    private Integer seq;
    private String subject;
    private String condition;   // 환자상태
    private String activity;    // 돌봄활동
    private String feeling; // 느낌
    private String content; // 기타
//    private String postDt;
    private String regDt;
}

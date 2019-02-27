package com.edenranch.administrator.caringforyouandme.database;

/**
 * @author 곽우영
 * @since 2019-02-09
 */
public class Column {

	// Alarm
	public static String ALARM_SEQ = "seq";				// 순번
	public static String ALARM_TIME = "time";			// 시간 (반복 시간 : 시분)
	public static String ALARM_CONTENT = "content";		// 내용
	public static String ALARM_ISUSE = "isUse";			// 사용 여부
	public static String ALARM_ISSUN = "isSun";			// 일요일 체크 여부
	public static String ALARM_ISMON = "isMon";			// 월요일 체크 여부
	public static String ALARM_ISTUE = "isTue";			// 화요일 체크 여부
	public static String ALARM_ISWED = "isWed";			// 수요일 체크 여부
	public static String ALARM_ISTHU = "isThu";			// 목요일 체크 여부
	public static String ALARM_ISFRI = "isFri";			// 금요일 체크 여부
	public static String ALARM_ISSAT = "isSat";			// 토요일 체크 여부

	// Diary
	public static String DIARY_SEQ = "seq";				// 순번
	public static String DIARY_SUBJECT = "subject";		// 제목
	public static String DIARY_CONTENT = "content";		// 내용
//	public static String DIARY_POSTDT = "postDt";		// 포스팅 일자
	public static String DIARY_REGDT = "regDt";			// 등록 일자

}

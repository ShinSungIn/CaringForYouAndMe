package com.edenranch.administrator.caringforyouandme;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

	// 저장 쿼리 url
	final static private String URL = "http://sungin0605.cafe24.com/UserRegister.php";
	private Map<String, String> parameter;

	// 생성자
	public RegisterRequest(String userType, String idText, String passwordText, String nameText, String ageText, String telText, String emailText, String genderGroup, String agreeYn, Response.Listener<String> listener) {
		super(Method.POST, URL, listener, null);
		parameter = new HashMap<>();	// 해당 값을 넣기 위함
		parameter.put("userType", userType);
		parameter.put("idText", idText);
		parameter.put("passwordText", passwordText);
		parameter.put("nameText", nameText);
		parameter.put("ageText", ageText);
		parameter.put("telText", telText);
		parameter.put("emailText", emailText);
		parameter.put("genderGroup", genderGroup);
		parameter.put("agreeYn", agreeYn);
	}

	@Override
	public Map<String, String> getParams() {
		return parameter;
	}
}


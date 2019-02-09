package com.example.administrator.caringforyouandme;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

	// 저장 쿼리 url
	final static private String URL = "웹서버 주소입력/UserRegister.php";
	private Map<String, String> parameter;

	// 생성자
	public RegisterRequest(String userType, String userID, String userPassword, String userName, String userAge, String userTel, String userEmail, String userGender, Response.Listener<String> listener) {
		super(Method.POST, URL, listener, null);
		parameter = new HashMap<>();	// 해당 값을 넣기 위함
		parameter.put("userType", userType);
		parameter.put("userID", userID);
		parameter.put("userPassword", userPassword);
		parameter.put("userName", userName);
		parameter.put("userAge", userAge);
		parameter.put("userTel", userTel);
		parameter.put("userEmail", userEmail);
		parameter.put("userGender", userGender);
	}

	@Override
	public Map<String, String> getParams() {
		return parameter;
	}
}

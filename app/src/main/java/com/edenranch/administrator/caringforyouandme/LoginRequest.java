package com.edenranch.administrator.caringforyouandme;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

	// 저장 쿼리 url
	final static private String URL = "http://sungin0605.cafe24.com/UserLogin.php";
	private Map<String, String> parameter;

	// 생성자
	public LoginRequest(String idText, String passwordText, Response.Listener<String> listener) {
		super(Method.POST, URL, listener, null);
		parameter = new HashMap<>();	// 해당 값을 넣기 위함
		parameter.put("idText", idText);
		parameter.put("passwordText", passwordText);
	}

	@Override
	public Map<String, String> getParams() {
		return parameter;
	}
}

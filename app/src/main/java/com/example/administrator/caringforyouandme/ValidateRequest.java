package com.example.administrator.caringforyouandme;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateRequest extends StringRequest {

	// 저장 쿼리 url
	final static private String URL = "웹서버 주소입력/UserValidate.php";
	private Map<String, String> parameter;

	// 생성자
	public ValidateRequest(String userID, Response.Listener<String> listener) {
		super(Method.POST, URL, listener, null);
		parameter = new HashMap<>();	// 해당 값을 넣기 위함
		parameter.put("userID", userID);
	}

	@Override
	public Map<String, String> getParams() {
		return parameter;
	}
}

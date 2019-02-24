package com.example.administrator.caringforyouandme;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateRequest extends StringRequest {

	// 저장 쿼리 url
	final static private String URL = "http://sungin0605.cafe24.com/UserValidate.php";
	private Map<String, String> parameter;

	// 생성자
	public ValidateRequest(String idText, Response.Listener<String> listener) {
		super(Method.POST, URL, listener, null);
		parameter = new HashMap<>();	// 해당 값을 넣기 위함
		parameter.put("idText", idText);
	}

	@Override
	public Map<String, String> getParams() {
		return parameter;
	}
}

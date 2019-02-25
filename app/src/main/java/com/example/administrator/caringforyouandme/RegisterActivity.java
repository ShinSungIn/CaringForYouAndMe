package com.example.administrator.caringforyouandme;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

	private ArrayAdapter adapter;

	// 각 사용자 정보를 담을 변수 그릇을 선언
	private String userType;
	private String userID;
	private String userPassword;
	private String userName;
	private String userAge;
	private String userTel;
	private String userEmail;
	private String userGender;
	private boolean agreeYn = false;
	private AlertDialog dialog;
	private boolean validate = false;	// 사용할수 있는 아이디인지 체크
	private RequestQueue queue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		// 변수 초기화
		final EditText idText = (EditText) findViewById(R.id.idText);
		final EditText nameText = (EditText) findViewById(R.id.nameText);
		final EditText passwordText = (EditText) findViewById(R.id.passwordText);
		final EditText telText = (EditText) findViewById(R.id.telText);
		final EditText ageText = (EditText) findViewById(R.id.ageText);
		final EditText emailText = (EditText) findViewById(R.id.emailText);

		// 사용자 구분
		RadioGroup userTypeGroup = (RadioGroup) findViewById(R.id.UserType);
		int userTypeGroupID = userTypeGroup.getCheckedRadioButtonId();
		userType = ((RadioButton) findViewById(userTypeGroupID)).getText().toString();	// 현재 선택되어있는 값

		userTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				RadioButton typeButton = (RadioButton) findViewById(checkedId);
				userType = typeButton.getText().toString();
			}
		});
		// 성별
		RadioGroup genderGroup = (RadioGroup) findViewById(R.id.genderGroup);
		int genderGroupID = genderGroup.getCheckedRadioButtonId();
		userGender = ((RadioButton) findViewById(genderGroupID)).getText().toString();

		genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				RadioButton genderButton = (RadioButton) findViewById(checkedId);
				userGender = genderButton.getText().toString();
			}
		});

		// 동의여부
		CheckBox agreeYn = (CheckBox) findViewById(R.id.agreeYn);

		// 중복 체크
		final Button validateButton  = (Button) findViewById(R.id.validateButton);
		validateButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String userID = idText.getText().toString();
				if (validate) {
					return;
				}
				if (userID.equals("")) {
					AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
					dialog = builder.setMessage("아이디가 공란입니다.")
						.setPositiveButton("확인", null)
						.create();
					dialog.show();
					return;
				}

				// 정상적인 경우라면 중복체크한다
				Response.Listener<String> responseListener = new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						try {
							//json 값 받아오기
							JSONObject jsonResponse = new JSONObject(response);
							boolean success = jsonResponse.getBoolean("success");
							if (success) {
								AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
								builder.setMessage("사용할 수 있는 아이디입니다.")
									.setPositiveButton("확인", null)
									.create()
									.show();

								idText.setEnabled(false);	// 비활성화
								validate = true;
								idText.setBackgroundColor(getResources().getColor(R.color.colorGray));
							} else {
								AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
								builder.setMessage("이미 회원가입된 아이디입니다.")
									.setNegativeButton("확인", null)
									.create()
									.show();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				};
				// 접속 할 수 있도록 생성자를 통해 객체를 만들어준다
				ValidateRequest validateRequest = new ValidateRequest(userID, responseListener);
				// request를 보냄
				queue = Volley.newRequestQueue(RegisterActivity.this);
				queue.add(validateRequest);
			}

		});

		// 회원가입하기 버튼
		Button registButton = (Button) findViewById(R.id.registerButton);
		registButton.setOnClickListener(new View.OnClickListener() {	// 이벤트 처리
			@Override
			public void onClick(View view) {
				// 폼에 있는 값 가져오기
				String userID = idText.getText().toString();
				String userPassword = passwordText.getText().toString();
				String userName = nameText.getText().toString();
				String userTel = telText.getText().toString();
				String userAge = ageText.getText().toString();
				String userEmail = emailText.getText().toString();

				if (!validate) {
					AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
					builder.setMessage("중복 체크를 해주세요.")
						.setNegativeButton("확인", null)
						.create()
						.show();
					return;
				}

				if (userID.equals("") || userPassword.equals("")) {
					AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
					builder.setMessage("아이디 또는 비밀번호를 입력해주세요.")
						.setNegativeButton("확인", null)
						.create()
						.show();
					return;
				}
				if (userAge.equals("") || userTel.equals("")) {
					AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
					builder.setMessage("연락처 또는 나이를 입력해주세요.")
						.setNegativeButton("확인", null)
						.create()
						.show();
					return;
				}
				if (!agreeYn.isChecked()) {
					AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
					builder.setMessage("개인정보수집 동의여부를 체크해주십시오.")
						.setNegativeButton("확인", null)
						.create()
						.show();
					return;
				}

				Response.Listener<String> responseListener = new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						try {
							JSONObject jsonResponse = new JSONObject(response);
							boolean success = jsonResponse.getBoolean("success");

							if (success) {

								AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
								builder.setMessage("회원 등록에 성공하였습니다.")
									.setPositiveButton("확인", null)
									.create()
									.show();

								finish();	// 회원가입창 닫기
								//Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
								//RegisterActivity.this.startActivity(intent);

							} else {
								AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
								builder.setMessage("회원 등록에 실패하였습니다.")
									.setNegativeButton("다시시도", null)
									.create()
									.show();

								Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
								RegisterActivity.this.startActivity(intent);
							}

							//finish();

						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				};

				//System.out.println(userType + " / " + userID+ " / " + userPassword+ " / " + userName+ " / " + userAge+ " / " + userTel+ " / " + userEmail+ " / " + userGender + " / " + String.valueOf(agreeYn.isChecked()));
				RegisterRequest registerRequest = new RegisterRequest(userType, userID, userPassword, userName, userAge, userTel, userEmail, userGender, String.valueOf(agreeYn.isChecked()), responseListener);
				queue = Volley.newRequestQueue(RegisterActivity.this);
				queue.add(registerRequest);
			}
		});
	}

	// 회원등록 이후 회원등록창이 꺼지게 되면 실행되는 부분
	@Override
	public void onStop() {
		super.onStop();
		if (dialog != null) {
			dialog.dismiss();
			dialog = null;
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		//finish();
	}
}

package com.example.administrator.caringforyouandme;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.administrator.caringforyouandme.activity.MainActivity;
import org.json.JSONException;
import org.json.JSONObject;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * 2019-01-14 CaringForYouAndMe 프로젝트 생성
 * 로그인
 */
public class LoginActivity extends AppCompatActivity {

	/**
	 * Id to identity READ_CONTACTS permission request.
	 */
	private static final int REQUEST_READ_CONTACTS = 0;

	private long backKeyPressedTime = 0;
	private Toast toast;
	private Activity activity;

	private UserLoginTask mAuthTask = null;

	// UI references.
	private AutoCompleteTextView mIdView;
	private EditText mPasswordView;
	private View mProgressView;
	private View mLoginFormView;

	private AlertDialog dialog;
	private RequestQueue queue;
	private boolean result = false;

	public boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		return cm.getActiveNetworkInfo() != null;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		if (!isNetworkConnected()) {
			toast = Toast.makeText(activity, "네트워크 연결 상태 확인 후 다시 시도해 주십시오.", Toast.LENGTH_LONG);
			toast.show();
		}

		// Set up the login form.
		mIdView = (AutoCompleteTextView) findViewById(R.id.idText);
		mPasswordView = (EditText) findViewById(R.id.passwordText);

		mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
				if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
					attemptLogin();
					return true;
				}
				return false;
			}
		});

		// 로그인 클릭
		Button loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				String userID = mIdView.getText().toString();
				String userPassword = mPasswordView.getText().toString();

				attemptLogin();
				//CompleteAttemptLogin();	// 단순 로그인
			}

			private void CompleteAttemptLogin() {
				Intent loginButtonIntent = new Intent(LoginActivity.this, MainActivity.class);
				LoginActivity.this.startActivity(loginButtonIntent);
			}
		});

		// 회원가입 클릭
		TextView registerButton = (TextView) findViewById(R.id.registerButton);
		registerButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
				LoginActivity.this.startActivity(registerIntent);
			}
		});

		mLoginFormView = findViewById(R.id.login_form);
		mProgressView = findViewById(R.id.login_progress);

		this.activity = this;
	}

	@Override
	public void onPause() {
		super.onPause();
		//finish();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		finish();
	}

	@Override
	public void onBackPressed() {
		//super.onBackPressed();
		if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
			backKeyPressedTime = System.currentTimeMillis();
			showGuide();
			return;
		}
		if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
			activity.finish();
			toast.cancel();
		}
	}

	private void showGuide() {
		toast = Toast.makeText(activity, "뒤로 버튼을 한번 더 누르면 앱을 종료합니다", Toast.LENGTH_LONG);
		toast.show();
	}

	private boolean mayRequestContacts() {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
			return true;
		}
		if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
			return true;
		}
		if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
			Snackbar.make(mIdView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
				.setAction(android.R.string.ok, new View.OnClickListener() {
					@Override
					@TargetApi(Build.VERSION_CODES.M)
					public void onClick(View v) {
						requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
					}
				});
		} else {
			requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
		}
		return false;
	}

	private void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		mIdView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		String id = mIdView.getText().toString();
		String password = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password, if the user entered one.
		if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(id)) {
			mIdView.setError(getString(R.string.error_field_required));
			focusView = mIdView;
			cancel = true;
		} else if (!isIdValid(id)) {
			mIdView.setError(getString(R.string.error_invalid_id));
			focusView = mIdView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.onPostExcuted
			showProgress(true);

			Response.Listener<String> responseListener = new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject jsonResponse = new JSONObject(response);
						boolean success = jsonResponse.getBoolean("success");
						if (success) {
							finish();

							Intent loginButtonIntent = new Intent(LoginActivity.this, MainActivity.class);
							loginButtonIntent.putExtra("ID", id);
							LoginActivity.this.startActivity(loginButtonIntent);

						} else {
							AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
							builder.setMessage("아이디 또는 비밀번호를 확인하십시오.")
								.setNegativeButton("다시시도", null)
								.create()
								.show();
						}

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			};

			LoginRequest loginRequest = new LoginRequest(id, password, responseListener);
			queue = Volley.newRequestQueue(LoginActivity.this);
			queue.add(loginRequest);

			mAuthTask = new UserLoginTask(id, password);
			mAuthTask.execute((Void) null);	// AsyncTask 실행
		}
	}

	private boolean isIdValid(String id) {
		return true;
	}

	private boolean isPasswordValid(String password) {
		return true;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime).alpha(
				show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
				}
			});

			mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
			mProgressView.animate().setDuration(shortAnimTime).alpha(
				show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
				}
			});
		} else {
			mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

		private final String mID;
		private final String mPassword;

		UserLoginTask(String id, String password) {
			mID = id;
			mPassword = password;
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			return null;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			// 로그인 마지막 실행 부분
			mAuthTask = null;
			showProgress(false);
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}
}


package com.example.administrator.caringforyouandme;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
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

	/**
	 * A dummy authentication store containing known user names and passwords. TODO: remove after connecting to a real authentication system.
	 */
	private static final String[] DUMMY_CREDENTIALS = new String[]{
		"foo@example.com:hello", "bar@example.com:world"
	};

	private long backKeyPressedTime = 0;
	private Toast toast;
	private Activity activity;

	private UserLoginTask mAuthTask = null;

	// UI references.
	private AutoCompleteTextView mIdView;
	private EditText mPasswordView;
	private View mProgressView;
	private View mLoginFormView;

	public boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

		return cm.getActiveNetworkInfo() != null;
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

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
				//attemptLogin();
				CompleteAttemptLogin();
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
		System.out.println("LoginActivity onPause in");
		//finish();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("LoginActivity onDestroy in");
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

	/**
	 * Attempts to sign in or register the account specified by the login form. If there are form errors (invalid email, missing fields, etc.), the errors are presented and no actual login attempt is made.
	 */
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
			// perform the user login attempt.
			showProgress(true);
			mAuthTask = new UserLoginTask(id, password);
			mAuthTask.execute((Void) null);
		}
	}

	private boolean isIdValid(String id) {
		//TODO: Replace this with your own logic
		return id.contains("@");
	}

	private boolean isPasswordValid(String password) {
		//TODO: Replace this with your own logic
		return password.length() > 4;
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
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
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

		private final String mID;
		private final String mPassword;

		UserLoginTask(String id, String password) {
			mID = id;
			mPassword = password;
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.

			try {
				// Simulate network access.
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				return false;
			}

			for (String credential : DUMMY_CREDENTIALS) {
				String[] pieces = credential.split(":");
				if (pieces[0].equals(mID)) {
					// Account exists, return true if the password matches.
					return pieces[1].equals(mPassword);
				}
			}

			// TODO: register the new account here.
			return true;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			showProgress(false);

			if (success) {
				finish();
			} else {
				mPasswordView.setError(getString(R.string.error_incorrect_password));
				mPasswordView.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}
}


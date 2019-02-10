package com.example.administrator.caringforyouandme.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.TextView;
import com.example.administrator.caringforyouandme.R;

public class DialogActivity extends Activity {

    private String TAG = "DialogActivity";

    private Dialog dialog;

    private Activity activity = null;

    private TextView dialogContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_dialog);

        dialogContent = findViewById(R.id.dialog_content);

        Intent intent = getIntent();
        String content = intent.getStringExtra("content");

        dialogContent.setText(content);

        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    _setCancel();
                }
                return false;
            }
        });

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                _setCancel();
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (dialog != null) {
            dialog.cancel();
        }
    }

    /**
     * 창 닫기
     */
    private void _setCancel() {
        if (activity != null) {
            activity.finish();
        }
    }

}

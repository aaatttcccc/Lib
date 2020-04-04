package com.lib.crash;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;

/**
 * Created by shenhuniurou
 * on 2017/1/3.
 */

public abstract class BasePatchDialogActivity extends PatchBaseActivity {

    private static final String EXTRA_TITLE = "extra_title";
    private static final String EXTRA_ULTIMATE_MESSAGE = "extra_ultimate_message";
    private static final String EXTRA_MESSAGE = "extra_message";
    private static final String EXTRA_VERSIONCODE = "extra_versionCode";

    protected String title, ultimateMessage, getExtraMessage, getExtraVersioncode;

    public static Intent newIntent(Context context, String title, String ultimateMessage, String message, String versionCode,Class classs) {

        Intent intent = new Intent();
        intent.setClass(context, classs);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_VERSIONCODE, versionCode);
        return intent;
    }


    private void parseIntent(Intent intent) {
        title = intent.getStringExtra(EXTRA_TITLE);
        ultimateMessage = intent.getStringExtra(EXTRA_ULTIMATE_MESSAGE);
        getExtraMessage = intent.getStringExtra(EXTRA_MESSAGE);
        getExtraVersioncode = intent.getStringExtra(EXTRA_VERSIONCODE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        parseIntent(getIntent());
        if (ultimateMessage == null) {
            ultimateMessage = getString(R.string.cw_error_message);
        }
        if (title == null) {
            title = getString(R.string.cw_error_title);
        }
        uploadNetwork();
        ultimateSolution();
    }


    private void ultimateSolution() {
        new AlertDialog.Builder(this, R.style.MyAlertDialogStyle)
                .setTitle(title)
                .setMessage(ultimateMessage)
                .setCancelable(true)
                .setIcon(R.drawable.ic_error)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        finish();
                    }
                })
                .setNegativeButton("退出", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setPositiveButton(R.string.cw_action_restart, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                        restart();
                    }
                }).show();
    }


    private void restart() {
        Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        super.onDestroy();
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    protected abstract void uploadNetwork();




}

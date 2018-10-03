package com.example.markprime.test.Discover;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BaseActivity extends FragmentActivity {

    public static SharedPreferences prefs;
//    public static boolean filterShowing = false;
    public static Activity mThis;

    public BaseActivity() {
        mThis = this;
    }

//    public static boolean isDeveloper(){
//        return BuildConfig.DEBUG;
//    }
//
//    public static void printOut(String tag, Object body) {
//        System.out.println(tag + " >>>> " + body);
//    }

//    public static void setupPrefs(Context context) {
//        prefs = context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
//    }

    public void startActivity(Class newClass){
        Intent intent = new Intent(getApplicationContext(), newClass);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fade_out);
        finish();
    }

    public void startActivityWithExtras(Class newClass, String extraKey, String extraValue){
        Intent intent = new Intent(getApplicationContext(), newClass);
        intent.putExtra(extraKey, extraValue);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fade_out);
        finish();
    }

    public void dismissKeyboard(EditText editText){

        if (editText != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    public void showKeyboard(EditText editText){
        InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        editText.requestFocus();
    }

    public static void shareEvent(String packageName, String message, Context context) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        sendIntent.setPackage(packageName);
        try {
            context.startActivity(sendIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            ex.printStackTrace();
            try {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
        }
    }

//    public static String getPlaceAutoCompleteUrl(String input) {
//        StringBuilder urlString = new StringBuilder();
//        urlString.append("https://maps.googleapis.com/maps/api/place/autocomplete/json");
//        urlString.append("?input=");
//        try {
//            urlString.append(URLEncoder.encode(input, "utf8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        urlString.append("&language=en");
//        urlString.append("&types=regions");
//        urlString.append("&key=" + Constants.PLACES_API_KEY);
//        return urlString.toString();
//    }

//    public static boolean isLocationServicesAvailable(Context context) {
//        try {
//            int locationMode = 0;
//            String locationProviders;
//            boolean isAvailable = false;
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                try {
//                    locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
//                } catch (Settings.SettingNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//                isAvailable = (locationMode != Settings.Secure.LOCATION_MODE_OFF);
//            } else {
//                locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
//                isAvailable = !TextUtils.isEmpty(locationProviders);
//            }
//
//            boolean coarsePermissionCheck = (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
//            boolean finePermissionCheck = (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
//
//            return isAvailable && (coarsePermissionCheck || finePermissionCheck);
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public static void showInfoDialog(String title, String desc, Activity context){
//        showInfoDialog(title,desc,context,null);
//    }
//
//    public static void showInfoDialog(final String title, final String desc, final Activity context, final @Nullable ResponseDialogListener listener){
//        mThis.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                if(BaseActivity.mThis.isFinishing()) return;
//
//                String msgDesc = desc;
//                if(msgDesc == null || msgDesc == "") {
//                    msgDesc = context.getString(R.string.dialog_generic_error);
//                }
//
//                InfoDialog dialog = new InfoDialog(context, title, msgDesc, listener);
//                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
//                dialog.show();
//
//            }
//        });
//    }

//    public static void showResponseDialog(String title, String desc, String posBtn, String negBtn, ResponseDialogListener listener, Activity context){
//        ResponseDialog dialog = new ResponseDialog(context, title, desc, posBtn, negBtn, listener);
//        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
//        dialog.show();
//    }

    public long getCurrentUnixTimestamp(){
        return System.currentTimeMillis() / 1000L;
    }

    public long convertDateToUnixTimeStamp(String dateString){
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
            Date date = format.parse(dateString.replace("T", " "));
            return (date.getTime() / 1000);
        } catch (Exception e){
            return 0;
        }
    }

}

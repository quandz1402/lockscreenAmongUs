package com.lockscreenamongus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.lockscreenamongus.utils.BackUpModel;
import com.lockscreenamongus.utils.HttpHandler;
import com.lockscreenamongus.utils.LockDeviceAdminReceiver;
import com.lockscreenamongus.utils.LockScreen;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {
    public  static  int height,width;
    public static int notificationId = 1090;
    public static SharedPreferences pres = null;
    public static Boolean lockScreenShow, vibrate;
    public static String[] listQuestion = new String[]{"What is your first pet's name?", "What is your favorite color?", "What is your favorite food"};
    public static String answer;
    public static int possQues;
    public static String answer2 = "";
    public static String passWord = "";
    public static int possQues2 = 0;
    public static boolean video;

    private LinearLayout setLockScreen, setVideo;
    private ImageView btn_lockScreen, btn_video, preview, changeQuestion, changePassWord, permission, disable_lock, support;

    public static String INTER_ID_FB = "1";
    public static String BANNER_ID_FB = "1";
    public static String NATIVE_ID_FB = "1";

    public static int PERCENT_SHOW_BANNER_AD = 100;
    public static int PERCENT_SHOW_INTER_AD = 100;
    public static int PERCENT_SHOW_NATIVE_AD = 100;
    public static int NUMBER_OF_NATIVE_AD = 3;
    public static int TIME_REQUEST_ADS = 5000;
    public static int NUMBER_OF_INTER = 15;

    public static String NATIVE_AD_ID = "ca-app-pub-2991887587140391/9516628296";
    public static String INTER_ID = "ca-app-pub-2991887587140391/5720853518";
    public static String BANNER_ID = "ca-app-pub-2991887587140391/1829709967";
    private BackUpModel backUpModel;
    private com.facebook.ads.AdView adView;
    public static InterstitialAd mInterstitialAd;
    public static com.facebook.ads.InterstitialAd interstitialAd;
    private  ImageView wallpaper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        try {
            Void aVoid = new GetBackUp().execute().get();
            if(backUpModel != null){
                if(!backUpModel.isLive){
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Notice from developer")
                            .setMessage("Please update the new application to continue using it. We are really sorry for this issue.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    showApp(MainActivity.this, backUpModel.newAppPackage);
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setCancelable(false)
                            .show();
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        pres = getSharedPreferences("setting", 0);  //load the preferences
        lockScreenShow = pres.getBoolean("lock", false);
        answer = pres.getString("answer", "");
        passWord = pres.getString("passWord", "");
        possQues = pres.getInt("possQues", 0);
        video = pres.getBoolean("video", true);
        final String packageName = this.getPackageName();

        if(answer.equals("")){
            dialogSupport();
        }
        loadBannerFB();
        loadInterFB();

        permission = findViewById(R.id.permission);
        permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Uri uri = Uri.fromParts("package", packageName, null);
                intent.setData(uri);
                startActivity(intent);


            }
        });

        wallpaper = findViewById(R.id.wallpaper);
        wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), ListWallpaper.class);
                startActivity(myIntent);

            }
        });
        disable_lock = findViewById(R.id.disable_lock);
        disable_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DevicePolicyManager.ACTION_SET_NEW_PASSWORD);
                startActivity(intent);
            }
        });


        setVideo = findViewById(R.id.setVideo);
        btn_video = findViewById(R.id.btn_video);
        if (video) {
            btn_video.setImageResource(R.mipmap.btn_on);
        } else {
            btn_video.setImageResource(R.mipmap.btn_off);
        }
        setVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInterFb(MainActivity.this);
                if (video) {
                    btn_video.setImageResource(R.mipmap.btn_off);
                    video = false;
                    SharedPreferences settings = getSharedPreferences("setting", 0);
                    SharedPreferences.Editor edit = settings.edit();
                    edit.putBoolean("video", false);
                    edit.commit();
                } else {
                    btn_video.setImageResource(R.mipmap.btn_on);
                    video = true;
                    SharedPreferences settings = getSharedPreferences("setting", 0);
                    SharedPreferences.Editor edit = settings.edit();
                    edit.putBoolean("video", true);
                    edit.commit();
                }
            }
        });


        setLockScreen = findViewById(R.id.setLockScreen);
        btn_lockScreen = findViewById(R.id.btn_lockScreen);
        LockScreen.getInstance().init(this, true);
        if (LockScreen.getInstance().isActive()) {

            btn_lockScreen.setImageResource(R.mipmap.btn_on);

        } else {

            btn_lockScreen.setImageResource(R.mipmap.btn_off);

        }
        setLockScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer.equals("")) {
                    dialogQuestion();
                } else {
                    if (LockScreen.getInstance().isActive()) {
                        btn_lockScreen.setImageResource(R.mipmap.btn_off);
                        LockScreen.getInstance().deactivate();
                        SharedPreferences settings = getSharedPreferences("setting", 0);
                        SharedPreferences.Editor edit = settings.edit();
                        edit.putBoolean("lock", false);
                        edit.commit();
                        showInterFb(MainActivity.this);
                    } else {
                        if (passWord.equals("")) {
                            Intent myIntent = new Intent(view.getContext(), ChangPassWord.class);
                            startActivity(myIntent);
                        } else {
                            LockScreen.getInstance().active();
                            SharedPreferences settings = getSharedPreferences("setting", 0);
                            SharedPreferences.Editor edit = settings.edit();
                            edit.putBoolean("lock", true);
                            edit.commit();
                            btn_lockScreen.setImageResource(R.mipmap.btn_on);
                            showInterFb(MainActivity.this);
                        }
                    }
                }
            }
        });

        preview = findViewById(R.id.preview);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showInterFb(MainActivity.this);
                if (passWord.equals("")) {
                    Intent myIntent = new Intent(view.getContext(), ChangPassWord.class);
                    startActivity(myIntent);
                } else {
                    Intent myIntent = new Intent(getApplicationContext(), LockScreenActivity.class);
                    myIntent.putExtra("test_lock", true);
                    startActivity(myIntent);
                }
            }
        });


        changeQuestion = findViewById(R.id.changeQuestion);
        changeQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogQuestion();
            }
        });


        changePassWord = findViewById(R.id.changPassWord);
        changePassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInterFb(MainActivity.this);
                Intent myIntent = new Intent(view.getContext(), ChangPassWord.class);
                startActivity(myIntent);
            }
        });


//        SharedPreferences prefs = getSharedPreferences("rate_dialog", MODE_PRIVATE);
//        Boolean rated = prefs.getBoolean("rate", false);
//        if (!rated) {
//            showRateDialog();
//        }
        support = findViewById(R.id.support);
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogSupport();
            }
        });

    }

    public static void showApp(Context context, String pkg) {
        Intent intent = new Intent(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://play.google.com/store/apps/details?id=" + pkg)));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void saveAnswer(Context context, String text, int question) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("setting", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("answer", text);
        editor.putInt("possQues", question);
        editor.apply();
    }

    @Override
    protected void onResume() {
        if (LockScreen.getInstance().isActive()) {

            btn_lockScreen.setImageResource(R.mipmap.btn_on);

        } else {

            btn_lockScreen.setImageResource(R.mipmap.btn_off);

        }
        super.onResume();
    }

    private void dialogQuestion() {
        Log.d("Answer", "dialogLogin: " + answer);
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.show();
        Spinner dropdown = dialog.findViewById(R.id.spinnerQuestion);
        String[] items = listQuestion;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                possQues2 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        final EditText answerEdit = dialog.findViewById(R.id.answer);

        answerEdit.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here
                answer2 = s.toString();
                // yourEditText...
                Log.d("TAG", "afterTextChanged: " + s);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        int dialogWindowWidth = (int) (displayWidth * 0.9);
        int dialogWindowHeight = (int) (displayHeight * 0.7);
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;
        dialog.getWindow().setAttributes(layoutParams);
        ImageView save = dialog.findViewById(R.id.saveAnswer);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer2.length() >= 3) {
                    saveAnswer(getApplicationContext(), answer2, possQues2);

                    answer = answer2;
                    possQues = possQues2;
                    Toast.makeText(MainActivity.this, "Question and Answer has been saved!",
                            Toast.LENGTH_LONG).show();
                    dialog.cancel();
                } else {
                    Toast.makeText(MainActivity.this, "You should chose Question or Answer more than 3 character!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void dialogSupport() {
        final Dialog dialog2 = new Dialog(this);
        dialog2.setContentView(R.layout.dialog_question);
        dialog2.show();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog2.getWindow().getAttributes());
        int dialogWindowWidth = (int) (displayWidth);
        int dialogWindowHeight = (int) (displayHeight);
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;
        dialog2.getWindow().setAttributes(layoutParams);

        ImageView setPer1 = dialog2.findViewById(R.id.per1);
        setPer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
            }
        });
        final String packageName = this.getPackageName();
        ImageView setPer2 = dialog2.findViewById(R.id.per2);
        setPer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Uri uri = Uri.fromParts("package", packageName, null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        ImageView back = dialog2.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.cancel();
            }
        });

    }

    private class GetBackUp extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://raw.githubusercontent.com/gquan1402/amonglockscreen/main/backupdata.json";
            String jsonStr = sh.makeServiceCall(url);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    String appPackage = jsonObj.getString("appPackage");
                    Boolean isLive = jsonObj.getBoolean("isLive");
                    String newAppPackage = jsonObj.getString("newAppPackage");
                    Boolean isAdsShow = jsonObj.getBoolean("isAdsShow");
                    String inter = jsonObj.getString("inter");
                    String fb_inter = jsonObj.getString("fb_inter");
                    String fb_native = jsonObj.getString("fb_native");
                    String fb_banner = jsonObj.getString("fb_banner");
                    Boolean isShowGG = jsonObj.getBoolean("isShowGG");
                    String banner = jsonObj.getString("banner");
                    String nativeAd = jsonObj.getString("nativeAd");
                    String rewarded = jsonObj.getString("rewarded");
                    int percent_banner = jsonObj.getInt("percent_banner");
                    int percent_inter = jsonObj.getInt("percent_inter");
                    int percent_native = jsonObj.getInt("percent_native");
                    int numberNativeAd = jsonObj.getInt("numberNativeAd");
                    int numberInterAd = jsonObj.getInt("numberInterAd");
                    int timeRequestAd = jsonObj.getInt("timeRequestAd");

                    backUpModel = new BackUpModel();
                    backUpModel.appPackage = appPackage;
                    backUpModel.isLive = isLive;
                    backUpModel.newAppPackage = newAppPackage;
                    backUpModel.isAdsShow = isAdsShow;
                    backUpModel.inter = inter;
                    backUpModel.fb_inter = fb_inter;
                    backUpModel.fb_native = fb_native;
                    backUpModel.fb_banner = fb_banner;
                    backUpModel.isShowGG = isShowGG;
                    backUpModel.banner = banner;
                    backUpModel.nativeAd = nativeAd;
                    backUpModel.rewarded = rewarded;
                    backUpModel.percent_banner = percent_banner;
                    backUpModel.percent_inter = percent_inter;
                    backUpModel.percent_native = percent_native;
                    backUpModel.numberNativeAd = numberNativeAd;
                    backUpModel.numberInterAd = numberInterAd;
                    backUpModel.timeRequestAd = timeRequestAd;

                    INTER_ID = backUpModel.inter;
                    BANNER_ID = backUpModel.banner;
                    NATIVE_AD_ID = backUpModel.nativeAd;
                    INTER_ID_FB = backUpModel.fb_inter;
                    BANNER_ID_FB = backUpModel.fb_banner;
                    NATIVE_ID_FB = backUpModel.fb_native;

                    PERCENT_SHOW_BANNER_AD = backUpModel.percent_banner;
                    PERCENT_SHOW_INTER_AD = backUpModel.percent_inter;
                    PERCENT_SHOW_NATIVE_AD = backUpModel.percent_native;
                    NUMBER_OF_NATIVE_AD = backUpModel.numberNativeAd;
                    NUMBER_OF_INTER = backUpModel.numberInterAd;
                    TIME_REQUEST_ADS = backUpModel.timeRequestAd;

                } catch (final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });

                }

            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();

    }


    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
        super.onDestroy();

    }


    public static void rateApp(Context context) {
        Intent intent = new Intent(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    public void loadBannerGG() {
        final View adContainer = findViewById(R.id.adMobView);
        final AdView mAdView = new AdView(this);
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId(MainActivity.BANNER_ID);
        ((LinearLayout) adContainer).addView(mAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                adContainer.setBackgroundColor(Color.GRAY);
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        Random r = new Random();
        int ads = r.nextInt(100);

        if (ads >= MainActivity.PERCENT_SHOW_BANNER_AD) {
            mAdView.destroy();
            mAdView.setVisibility(View.GONE);
        }
    }

    public void loadBannerFB() {
        final View adContainer = findViewById(R.id.adMobView);
        AudienceNetworkAds.initialize(this);
        adView = new com.facebook.ads.AdView(this, BANNER_ID_FB, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        ((LinearLayout) adContainer).addView(adView);

// Request an ad
        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                loadBannerGG();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Ad loaded callback
                adContainer.setBackgroundColor(Color.GRAY);
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
            }
        };

        // Request an ad
        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());

        Random r = new Random();
        int ads = r.nextInt(100);

        if (ads >= MainActivity.PERCENT_SHOW_BANNER_AD) {
            adView.destroy();
            adView.setVisibility(View.GONE);
        }

    }

    public static void showInterFb(Context context) {

       if (interstitialAd == null || !interstitialAd.isAdLoaded()) {

            showInterstitial(context);
        } else {
            // Show the ad
            interstitialAd.show();
        }


    }


    public void loadInterFB() {
        interstitialAd = new com.facebook.ads.InterstitialAd(MainActivity.this, MainActivity.INTER_ID_FB);
        // Create listeners for the Interstitial Ad
        com.facebook.ads.InterstitialAdListener interstitialAdListener = new com.facebook.ads.InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e("TAG", "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e("TAG", "Interstitial ad dismissed.");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadInterFB();
                    }
                }, MainActivity.TIME_REQUEST_ADS);
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e("TAG", "Interstitial ad failed to load: " + adError.getErrorMessage());
                loadInterGG();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d("TAG", "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad

            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d("TAG", "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d("TAG", "Interstitial ad impression logged!");
            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());
    }

    public void loadInterGG() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mInterstitialAd = new InterstitialAd(MainActivity.this);
        mInterstitialAd.setAdUnitId(MainActivity.INTER_ID);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }


            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadInterGG();
                    }
                }, MainActivity.TIME_REQUEST_ADS);
            }
        });
    }


    public static void showInterstitial(Context m) {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
//            Toast.makeText(m, "Please check network connection!", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.lockscreenamongus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.PowerManager;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.androidsx.rateme.OnRatingListener;
import com.androidsx.rateme.RateMeDialog;
import com.lockscreenamongus.utils.LockScreen;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class LockScreenActivity extends AppCompatActivity {

    private String problemText = "";
    private TextView captionTextView;
    private VideoView videoView;
    private String answer ="";
    private String actualAnswer = "";
    private MediaPlayer player = new MediaPlayer();
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnCancel,btnDone;
    private TextView passWordNum;
    private Handler handler;
    LinearLayout view1;
    RelativeLayout death;
    Runnable lockScreenTask;
    ImageView forgotPass;
    String answerQues;
    public static String[] listQuestion = new String[]{"What is your first pet's name?","What is your favorite color?","What is your favorite food"};
    PowerManager.WakeLock wakeLock;
    private boolean test_lock = false;
    private String password;
    public static SharedPreferences pres = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lockscreen);
        pres = getSharedPreferences("setting", 0);
        password =  pres.getString("passWord", "");
        test_lock = getIntent().getBooleanExtra("test_lock", false);

        this.lockScreenTask = new LockPhone(this);
        this.captionTextView = findViewById(R.id.caption);

        handler = new Handler(Looper.getMainLooper());
        death = findViewById(R.id.death);
        view1 = findViewById(R.id.view1);
        passWordNum = findViewById(R.id.passWordNum);
        videoView = findViewById(R.id.videoView);
        btn0 = findViewById(R.id.unlock_button_0);
        btn1 = findViewById(R.id.unlock_button_1);
        btn2 = findViewById(R.id.unlock_button_2);
        btn3 = findViewById(R.id.unlock_button_3);
        btn4 = findViewById(R.id.unlock_button_4);
        btn5 = findViewById(R.id.unlock_button_5);
        btn6 = findViewById(R.id.unlock_button_6);
        btn7 = findViewById(R.id.unlock_button_7);
        btn8 = findViewById(R.id.unlock_button_8);
        btn9 = findViewById(R.id.unlock_button_9);
        btnCancel = findViewById(R.id.unlock_button_cancel);
        btnDone = findViewById(R.id.unlock_button_done);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);


                if(actualAnswer.length()<=4){
                    actualAnswer+="*";
                    answer+="0";
                }
                setTextPassWord();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);


                if(actualAnswer.length()<=4){
                    actualAnswer+="*";
                    answer+="1";
                }
                setTextPassWord();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);


                if(actualAnswer.length()<=4){
                    actualAnswer+="*";
                    answer+="2";
                }
                setTextPassWord();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);


                if(actualAnswer.length()<=4){
                    actualAnswer+="*";
                    answer+="3";
                }
                setTextPassWord();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);


                if(actualAnswer.length()<=4){
                    actualAnswer+="*";
                    answer+="4";
                }
                setTextPassWord();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);


                if(actualAnswer.length()<=4){
                    actualAnswer+="*";
                    answer+="5";
                }
                setTextPassWord();
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);


                if(actualAnswer.length()<=4){
                    actualAnswer+="*";
                    answer+="6";
                }
                setTextPassWord();
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);


                if(actualAnswer.length()<=4){
                    actualAnswer+="*";
                    answer+="7";
                }
                setTextPassWord();
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);


                if(actualAnswer.length()<=4){
                    actualAnswer+="*";
                    answer+="8";
                }
                setTextPassWord();
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);
                if(actualAnswer.length()<=4){
                    actualAnswer+="*";
                    answer+="9";
                }
                setTextPassWord();
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("TAG", "onClick: "+answer);
               if(answer.equals(password)){
                   startSound(R.raw.done);
                   if(test_lock) {
                       SharedPreferences prefs = getSharedPreferences("rate_dialog", MODE_PRIVATE);
                       Boolean rated = prefs.getBoolean("rate", false);
                       if (!rated) {
                           showRateDialog();
                       } else {
                           MainActivity.showInterstitial(LockScreenActivity.this);
                           finish();
                       }
                   } else {
                       finish();
                   }

               }
               else {
                   startSound(R.raw.death);
                   Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                   vibrator.vibrate(100);
                   if(MainActivity.video){
                       death();
                   }
                   else {
                       answer = "";
                       actualAnswer= "";
                       setTextPassWord();
                   }

               }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);
                answer = "";
                actualAnswer= "";
                setTextPassWord();
            }
        });
        hideNavigationBar();
        forgotPass = findViewById(R.id.forgotPass);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogQuestion();
            }
        });

    }

    private void showRateDialog() {
        RateMeDialog rateMeDialog = new RateMeDialog.Builder(getPackageName(), getString(R.string.app_name))
                .setHeaderBackgroundColor(getResources().getColor(R.color.dialog_primary))
                .setBodyBackgroundColor(getResources().getColor(R.color.dialog_primary_light))
                .setBodyTextColor(getResources().getColor(R.color.colorBlack))
                .setHeaderTextColor(getResources().getColor(R.color.dialog_text_foreground))
                .showAppIcon(R.mipmap.ic_launcher)
                .setShowShareButton(true)
                .setRateButtonBackgroundColor(getResources().getColor(R.color.dialog_primary))
                .setRateButtonPressedBackgroundColor(getResources().getColor(R.color.dialog_primary_dark))
                .setOnRatingListener(new OnRatingListener() {
                    @Override
                    public void onRating(RatingAction action, float rating) {

                        if(action == RatingAction.HIGH_RATING_WENT_TO_GOOGLE_PLAY || action == RatingAction.LOW_RATING_GAVE_FEEDBACK
                                || action == RatingAction.LOW_RATING_REFUSED_TO_GIVE_FEEDBACK || action == RatingAction.LOW_RATING){
                            rateApp(LockScreenActivity.this);
                            SharedPreferences.Editor editor = getSharedPreferences("rate_dialog", MODE_PRIVATE).edit();
                            editor.putBoolean("rate", true);
                            editor.commit();
                        }
                        LockScreenActivity.this.finish();
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        // Nothing to write
                    }
                })
                .build();
        rateMeDialog.setCancelable(false);
        rateMeDialog.show(getFragmentManager(), "custom-dialog");

    }

    public static void rateApp(Context context) {
        Intent intent = new Intent(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    private void dialogQuestion() {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.show();
        Spinner dropdown = dialog.findViewById(R.id.spinnerQuestion);
        String[] items = listQuestion;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        final EditText answerEdit =  dialog.findViewById(R.id.answer);

        answerEdit.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here
                answerQues = s.toString();
                // yourEditText...
                Log.d("TAG", "afterTextChanged: " + s);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        int dialogWindowWidth = (int) (displayWidth *0.9);
        int dialogWindowHeight = (int) (displayHeight *0.6);
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;
        dialog.getWindow().setAttributes(layoutParams);
        ImageView save = dialog.findViewById(R.id.saveAnswer);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answerQues.equals(MainActivity.answer) ){
                    MainActivity.passWord = "";
                    SharedPreferences sharedPreferences = LockScreenActivity.this.getSharedPreferences("setting", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("passWord", "");
                    editor.apply();
                    LockScreen.getInstance().deactivate();
                    Intent myIntent = new Intent(view.getContext(),ChangPassWord.class);
                    startActivity(myIntent);

                    finish();
                }
                else{
                    Toast.makeText(LockScreenActivity.this, "Answer Wrong!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    private void death(){
        view1.setVisibility(View.GONE);
        death.setVisibility(View.VISIBLE);
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);
        //specify the location of media file
        String path = "android.resource://" + getPackageName() + "/" + R.raw.death1;
        //Setting MediaController and URI, then starting the videoView


        videoView.setMediaController(null);
        videoView.setVideoURI(Uri.parse(path));
        videoView.requestFocus();
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                view1.setVisibility(View.VISIBLE);
                death.setVisibility(View.GONE);
                answer = "";
                actualAnswer= "";
                setTextPassWord();
            }
        });

    }
    private void setTextPassWord(){
        passWordNum.setText(actualAnswer);
    }




    private void startSound(int resourceID) {
        // player = new MediaPlayer();
        player = MediaPlayer.create(this, resourceID);
        player.start();
    }

    private class LockPhone implements Runnable {
        private LockScreenActivity activity;

        LockPhone(LockScreenActivity activity) {
            this.activity = activity;
        }

        @Override
        public void run() {
//            DevicePolicyManager manager = ((DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE));
//            manager.lockNow();

        }
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        hideNavigationBar();
    }
    private void hideNavigationBar() {
        final View decorView = this.getWindow().getDecorView();
        final int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        decorView.setSystemUiVisibility(uiOptions);
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(task, 1, 2);
    }


    @Override
    public void onAttachedToWindow() {
        if (Build.VERSION.SDK_INT >= 27) {
            setShowWhenLocked(true);
            setTurnScreenOn(true);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//            KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
//            keyguardManager.requestDismissKeyguard(this, null);
            KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
            if(keyguardManager!=null)
                keyguardManager.requestDismissKeyguard(this, null);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        }

        super.onAttachedToWindow();
    }

    @Override
    protected void onStart() {
        super.onStart();
        handler.postDelayed(lockScreenTask, 10 * 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(lockScreenTask);
    }


    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }
}

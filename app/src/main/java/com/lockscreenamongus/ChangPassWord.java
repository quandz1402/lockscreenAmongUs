package com.lockscreenamongus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ChangPassWord extends AppCompatActivity {
    private String answer = "";
    private String actualAnswer = "";
    private MediaPlayer player = new MediaPlayer();
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnCancel, btnDone;
    private TextView passWordNum;
    private TextView captionTextView;
    private String newPass = "";
    private String verifyPass = "";
    private ImageView cancel;

    private int step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chang_pass_word);
        captionTextView = findViewById(R.id.caption);
        passWordNum = findViewById(R.id.passWordNum);
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


                if (actualAnswer.length() <= 4) {
                    actualAnswer += "*";
                    answer += "0";
                }
                setTextPassWord();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);


                if (actualAnswer.length() <= 4) {
                    actualAnswer += "*";
                    answer += "1";
                }
                setTextPassWord();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);


                if (actualAnswer.length() <= 4) {
                    actualAnswer += "*";
                    answer += "2";
                }
                setTextPassWord();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);


                if (actualAnswer.length() <= 4) {
                    actualAnswer += "*";
                    answer += "3";
                }
                setTextPassWord();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);


                if (actualAnswer.length() <= 4) {
                    actualAnswer += "*";
                    answer += "4";
                }
                setTextPassWord();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);


                if (actualAnswer.length() <= 4) {
                    actualAnswer += "*";
                    answer += "5";
                }
                setTextPassWord();
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);


                if (actualAnswer.length() <= 4) {
                    actualAnswer += "*";
                    answer += "6";
                }
                setTextPassWord();
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);


                if (actualAnswer.length() <= 4) {
                    actualAnswer += "*";
                    answer += "7";
                }
                setTextPassWord();
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);


                if (actualAnswer.length() <= 4) {
                    actualAnswer += "*";
                    answer += "8";
                }
                setTextPassWord();
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);
                if (actualAnswer.length() <= 4) {
                    actualAnswer += "*";
                    answer += "9";
                }
                setTextPassWord();
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (step == 0) {
                    if (answer.equals(MainActivity.passWord)) {
                        step = 1;
                        answer = "";
                        actualAnswer = "";
                        setTextPassWord();
                        startSound(R.raw.done);
                        captionTextView.setText("New Password");

                    } else {
                        startSound(R.raw.death);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(100);
                        Toast.makeText(ChangPassWord.this, "Password wrong!",
                                Toast.LENGTH_LONG).show();
                        answer = "";
                        actualAnswer = "";
                        setTextPassWord();
                    }
                } else if (step == 1) {
                    if(actualAnswer.length()<4){

                        Toast.makeText(ChangPassWord.this, "Password must be at least 4 digits!",
                                Toast.LENGTH_LONG).show();
                        startSound(R.raw.death);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(100);
                        actualAnswer="";
                        answer = "";
                        setTextPassWord();
                    }
                    else{
                        newPass = answer;
                        step=2;
                        captionTextView.setText("Verify Password");
                        actualAnswer="";
                        answer = "";
                        startSound(R.raw.done);
                        setTextPassWord();
                    }
                }else {
                    if(answer.equals(newPass)){
                        startSound(R.raw.done);
                        SharedPreferences sharedPreferences = ChangPassWord.this.getSharedPreferences("setting", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("passWord", answer);
                        editor.apply();
                        MainActivity.passWord = answer;
                        Toast.makeText(ChangPassWord.this, "Password has been saved!",
                                Toast.LENGTH_LONG).show();
                        MainActivity.showInterFb(ChangPassWord.this);
                        finish();
                    }
                    else {
                        startSound(R.raw.death);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(100);
                        actualAnswer="";
                        answer = "";
                        setTextPassWord();
                        Toast.makeText(ChangPassWord.this, "Password incorrect!",
                                Toast.LENGTH_LONG).show();

                    }
                }



            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSound(R.raw.beep_key);
                answer = "";
                actualAnswer = "";
                setTextPassWord();
            }
        });
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        if (MainActivity.passWord.equals("")) {
            step = 1;
            captionTextView.setText("New Password");
        } else {

            captionTextView.setText("Current Password");
            step = 0;
        }
    }

    private void setTextPassWord() {
        passWordNum.setText(actualAnswer);
    }


    private void startSound(int resourceID) {
        // player = new MediaPlayer();
        player = MediaPlayer.create(this, resourceID);
        player.start();
    }
}

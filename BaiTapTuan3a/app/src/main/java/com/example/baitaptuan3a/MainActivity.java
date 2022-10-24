package com.example.baitaptuan3a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView scoreTextView;
    ImageButton playImageButton;
    CheckBox checkBoxOne, checkBoxTwo, checkBoxThree;
    SeekBar seekBarOne, seekBarTwo, seekBarThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();

        CountDownTimer countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long l) {
                int number = 5;
                Random random = new Random();
                seekBarOne.setProgress(seekBarOne.getProgress() + random.nextInt(number));
                seekBarTwo.setProgress(seekBarTwo.getProgress() + random.nextInt(number));
                seekBarThree.setProgress(seekBarThree.getProgress() + random.nextInt(number));
            }

            @Override
            public void onFinish() {

            }
        };

        playImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.start();
            }
        });
    }

    private void mapping() {
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        playImageButton = (ImageButton) findViewById(R.id.playImageButton);
        checkBoxOne = (CheckBox) findViewById(R.id.checkboxOne);
        checkBoxTwo = (CheckBox) findViewById(R.id.checkboxTwo);
        checkBoxThree = (CheckBox) findViewById(R.id.checkboxThree);
        seekBarOne = (SeekBar) findViewById(R.id.seekBarOne);
        seekBarTwo = (SeekBar) findViewById(R.id.seekBarTwo);
        seekBarThree = (SeekBar) findViewById(R.id.seekBarThree);
    }
}
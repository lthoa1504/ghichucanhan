package com.example.ghichucanhan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ghichucanhan.fragment.AddNoteFragment;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView myimg;
    TextView mytv;
    Intent homeAct;
    Animation myanim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        myimg = (ImageView) findViewById(R.id.noteImg);
        myanim = AnimationUtils.loadAnimation(this, R.anim.myanim);
        myimg.startAnimation(myanim);
//        mytv.startAnimation(myanim);


//        homeAct = new Intent(this, manager.getClass());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AddNoteFragment fragment = new AddNoteFragment();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().add(R.id.notefrag,fragment).commit();


//                startActivity(homeAct);
                finish();
            }
        }, 3000);
    }
}
package com.mao.practise617;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivityAnimation extends AppCompatActivity {

    private Button scaleBtn,rotateBtn,alphaBtn,translateBtn;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_animation);
        img = (ImageView)findViewById(R.id.Imageview01);


        RotateAnimation rAnimation =
                new RotateAnimation(
                        0,360,Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
        AnimationSet set = new AnimationSet(true);
//设置动画执行时间毫秒
        rAnimation.setDuration(3000);
        set.addAnimation(rAnimation);
        img.startAnimation(set);

        //开始动画
    }

    public void toScale(View view){

//  2D
        Animation scaleAnimation = new ScaleAnimation(0f,1f,0f,1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        //设置动画持续时间
        scaleAnimation.setDuration(2000);
        img.startAnimation(scaleAnimation);
    }

    public void toAlpha(View view){


         //从哪种透明度到哪种透明度(1完全不透明，0完全透明)
        AlphaAnimation animation = new AlphaAnimation(1, 0);
        //设置动画执行时间毫秒
        animation.setDuration(3000);
        img.startAnimation(animation);
    }

    public void toRotate(View view){

        RotateAnimation rAnimation =
                new RotateAnimation(
                        0,360,Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
        AnimationSet set = new AnimationSet(true);
//设置动画执行时间毫秒
        rAnimation.setDuration(3000);
        set.addAnimation(rAnimation);
        img.startAnimation(set);

    }

    public void toTransl(View view){

        TranslateAnimation tAnimation =
                new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF,4f);
        AnimationSet set = new AnimationSet(true);
//设置动画执行时间毫秒
        tAnimation.setDuration(3000);
        set.addAnimation(tAnimation);
        img.startAnimation(set);

    }

    public void toTiaoGuo(View view){
//        pbIndex setMax(10);
        new Thread(){
            @Override
            public void run(){
                int i = 0;
                while (i <= 10) {
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }
//                    pbIndex.setProgress(i);
                    i += 3;
                }
                Intent intent = new Intent(MainActivityAnimation.this, ViewPagerActivity.class);
                startActivity(intent);
            }
        }.start();
    }

    public void toWeChat(View view){
        Intent intent = new Intent(MainActivityAnimation.this, ViewPagerActivity.class);
        startActivity(intent);
    }
}
package com.mao.practise617;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.mao.practise617.Adapter.ImageSliderAdapter;

import java.util.Arrays;
import java.util.List;

public class LunBoActivity extends AppCompatActivity {
    private ViewPager2  viewPager;
    private List<Integer> images = Arrays.asList(
            R.drawable.lun_bo_1,
            R.drawable.lun_bo_2,
            R.drawable.lun_bo_3,
            R.drawable.lun_bo_4,
            R.drawable.lun_bo_5
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lun_bo);



        viewPager = findViewById(R.id.lun_bo);
        viewPager.setAdapter(new ImageSliderAdapter(images));
    }

}
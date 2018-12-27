package com.devhwang.a84974.mymusicapp.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.devhwang.a84974.mymusicapp.Adapter.MainViewPagerAdapter;
import com.devhwang.a84974.mymusicapp.Fragment.FragmentTimKiem;
import com.devhwang.a84974.mymusicapp.Fragment.FragmentTrangChu;
import com.devhwang.a84974.mymusicapp.R;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout =findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
        init();
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new FragmentTrangChu(),"Trang Chu");
        mainViewPagerAdapter.addFragment(new FragmentTimKiem(),"Tim Kiem");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);

    }

}

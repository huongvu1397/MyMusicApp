package com.devhwang.a84974.mymusicapp.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devhwang.a84974.mymusicapp.Adapter.BannerAdapter;
import com.devhwang.a84974.mymusicapp.Model.QuangCao;
import com.devhwang.a84974.mymusicapp.R;
import com.devhwang.a84974.mymusicapp.Service.APIRetrofitClient;
import com.devhwang.a84974.mymusicapp.Service.APIService;
import com.devhwang.a84974.mymusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBanner extends Fragment {
    View view;
    ViewPager viewPager;
    BannerAdapter adapter;
    CircleIndicator circleIndicator;
    Runnable runnable;
    Handler handler;
    int currentitem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner,container,false);
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.indicator);
        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<QuangCao>> callback = dataService.GetDataBanner();
        callback.enqueue(new Callback<List<QuangCao>>() {
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) {
                ArrayList<QuangCao> banner = (ArrayList<QuangCao>) response.body();
                adapter = new BannerAdapter(getActivity(),banner);
                viewPager.setAdapter(adapter);
                circleIndicator.setViewPager(viewPager);
                handler=new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currentitem = viewPager.getCurrentItem();
                        currentitem++;
                        if(currentitem>=viewPager.getAdapter().getCount()){
                            currentitem =0;
                        }
                        viewPager.setCurrentItem(currentitem,true);
                        handler.postDelayed(runnable,4500);
                    }
                };
                handler.postDelayed(runnable,4500);
            }

            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) {

            }
        });
    }
}

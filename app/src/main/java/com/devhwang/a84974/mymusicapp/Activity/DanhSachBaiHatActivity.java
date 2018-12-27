package com.devhwang.a84974.mymusicapp.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.devhwang.a84974.mymusicapp.Model.BaiHat;
import com.devhwang.a84974.mymusicapp.Model.QuangCao;
import com.devhwang.a84974.mymusicapp.R;
import com.devhwang.a84974.mymusicapp.Service.APIService;
import com.devhwang.a84974.mymusicapp.Service.DataService;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class DanhSachBaiHatActivity extends AppCompatActivity {
    QuangCao quangCao;
    RecyclerView recyclerView;
    CollapsingToolbarLayout collapsingToolbarLayout;
    CoordinatorLayout coordinatorLayout;
    FloatingActionButton floatingActionButton;
    Toolbar toolbar;
    ImageView imageView;
    ArrayList<BaiHat> baiHats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        recyclerView = findViewById(R.id.recycle_danhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        toolbar = findViewById(R.id.toolbar);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        coordinatorLayout = findViewById(R.id.coordinator);
        imageView = findViewById(R.id.img_danhsachcakhuc);
        init();
        DataIntent();
        if(quangCao != null && !quangCao.getTenBaiHat().equals("")){
            setValueInView(quangCao.getTenBaiHat(),quangCao.getHinhBaiHat());
            GetDataQuangCao(quangCao.getId());
        }
    }

    public void setValueInView(String ten,String hinh){
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url =new URL(hinh);
            //Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            //BitmapDrawable bitmapDrawable =new BitmapDrawable(getResources(),bitmap);
            //collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.get().load(hinh).into(imageView);
    }

    public void GetDataQuangCao(String idquangcao) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhSachBaiHat(idquangcao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHats = (ArrayList<BaiHat>) response.body();

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent!=null){
            if(intent.hasExtra("banner")){
                quangCao = (QuangCao) intent.getSerializableExtra("banner");

            }
        }
    }
}

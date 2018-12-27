package com.devhwang.a84974.mymusicapp.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devhwang.a84974.mymusicapp.Model.ChuDe;
import com.devhwang.a84974.mymusicapp.Model.ChuDeTheLoai;
import com.devhwang.a84974.mymusicapp.Model.TheLoai;
import com.devhwang.a84974.mymusicapp.R;
import com.devhwang.a84974.mymusicapp.Service.APIService;
import com.devhwang.a84974.mymusicapp.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentChuDeTheLoai extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txt_xemthem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chudetheloai,container,false);
        horizontalScrollView = view.findViewById(R.id.horizontal_chudetheloai);
        txt_xemthem = view.findViewById(R.id.txt_xemthem);
        GetData();
        return view;
    }

    private void GetData(){
        DataService dataService = APIService.getService();
        Call<ChuDeTheLoai> callback = dataService.GetCategoryMusic();
        callback.enqueue(new Callback<ChuDeTheLoai>() {
            @Override
            public void onResponse(Call<ChuDeTheLoai> call, Response<ChuDeTheLoai> response) {
                ChuDeTheLoai theLoai = response.body();

                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(theLoai.getChuDe());

                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(theLoai.getTheLoai());
                //boi vi dang dung tren fragment nen k co context ho tre -> can cai getactivity
                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                // gán giá trị vào cardview
                //set kích thước đề phòng giãn xấu
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580,250);
                layout.setMargins(10,20,10,30);
                //set cho chủ đề
                for (int i =0 ; i <(chuDeArrayList.size());i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(chuDeArrayList.get(i).getHinhChuDe()!=null){
                        Picasso.get().load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                }
                // set cho thể loại
                for (int i =0 ; i <(theLoaiArrayList.size());i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(theLoaiArrayList.get(i).getHinhTheLoai()!=null){
                        Picasso.get().load(theLoaiArrayList.get(i).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<ChuDeTheLoai> call, Throwable t) {

            }
        });
    }
}

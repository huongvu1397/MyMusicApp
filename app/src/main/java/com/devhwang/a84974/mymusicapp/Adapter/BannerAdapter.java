package com.devhwang.a84974.mymusicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devhwang.a84974.mymusicapp.Activity.DanhSachBaiHatActivity;
import com.devhwang.a84974.mymusicapp.Model.QuangCao;
import com.devhwang.a84974.mymusicapp.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<QuangCao> arrayList;

    public BannerAdapter(Context context, ArrayList<QuangCao> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }
    // trả về view tùy theo object
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
    //định hình , gán dữ liệu cho object
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater  = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner,null);
        ImageView imgViewBackgroundBanner = view.findViewById(R.id.imageViewBackground);
        ImageView imgViewBanner = view.findViewById(R.id.imgview_banner);
        TextView txtTitle = view.findViewById(R.id.txt_title);
        TextView txtNoiDung = view.findViewById(R.id.txtNoidung);
        Picasso.get().load(arrayList.get(position).getHinhAnh()).into(imgViewBackgroundBanner);
        Picasso.get().load(arrayList.get(position).getHinhBaiHat()).into(imgViewBanner);
        txtTitle.setText(arrayList.get(position).getTenBaiHat());
        txtNoiDung.setText(arrayList.get(position).getNoiDung());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DanhSachBaiHatActivity.class);
                // QuangCao implements Serializable - chuyen duoi dang object qua man hinh khac thi implement vao
                intent.putExtra("banner",arrayList.get(position));
                context.startActivity(intent);
            }
        });

        container.addView(view);
        return view;
    }

    //th lỗi
    // xóa các view khi đã thực hiễn xong
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }
}

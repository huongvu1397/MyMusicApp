package com.devhwang.a84974.mymusicapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devhwang.a84974.mymusicapp.Model.BaiHat;
import com.devhwang.a84974.mymusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BaiHatHotAdapter extends RecyclerView.Adapter<BaiHatHotAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> baiHatHotAdapters;


    public BaiHatHotAdapter(Context context, ArrayList<BaiHat> baiHatHotAdapters) {
        this.context = context;
        this.baiHatHotAdapters = baiHatHotAdapters;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_baihathot,viewGroup,false);
        // vif view holder chua cac view ben trong nen muon dung phai khoi tao lai cac view
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BaiHat baiHat = baiHatHotAdapters.get(i);
        viewHolder.txt_casi.setText(baiHat.getCasi());
        viewHolder.txt_ten.setText(baiHat.getTenbaihat());
        Picasso.get().load(baiHat.getHinhbaihat()).into(viewHolder.img_hinh);
    }

    @Override
    public int getItemCount() {
        return baiHatHotAdapters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_ten,txt_casi;
        ImageView img_hinh,img_luotthich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_ten = itemView.findViewById(R.id.txt_ten_baihathot);
            txt_casi = itemView.findViewById(R.id.txt_casibaihathot);
            img_hinh = itemView.findViewById(R.id.img_baihathot);
            img_luotthich = itemView.findViewById(R.id.img_luotthich);
        }

    }
}

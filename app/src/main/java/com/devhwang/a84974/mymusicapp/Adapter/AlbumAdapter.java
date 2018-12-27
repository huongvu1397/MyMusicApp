package com.devhwang.a84974.mymusicapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devhwang.a84974.mymusicapp.Model.Album;
import com.devhwang.a84974.mymusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>  {
    Context context;
    ArrayList<Album> albumArrayList;


    public AlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Album album = albumArrayList.get(i);
        viewHolder.txt_tencasialbum.setText(album.getTenCasi());
        viewHolder.txt_tenalbum.setText(album.getTenAlbum());
        Picasso.get().load(album.getHinhAnhAlbum()).into(viewHolder.img_hinhalbum);

    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_hinhalbum;
        TextView txt_tenalbum,txt_tencasialbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_hinhalbum = itemView.findViewById(R.id.img_album);
            txt_tenalbum = itemView.findViewById(R.id.txt_ten_album);
            txt_tencasialbum = itemView.findViewById(R.id.txt_tencasi_album);
        }

    }
}

package com.devhwang.a84974.mymusicapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.devhwang.a84974.mymusicapp.Model.Playlist;
import com.devhwang.a84974.mymusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {

    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }

    class ViewHolder{
        TextView txt_ten_playlist;
        ImageView img_background,img_playlist;

    }
    // gawsn nhung layout ben item vao list
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.dong_playlist,null);
            viewHolder = new ViewHolder();
            viewHolder.txt_ten_playlist = convertView.findViewById(R.id.txt_view_ten_playlist);
            viewHolder.img_background = convertView.findViewById(R.id.img_background_playlist);
            viewHolder.img_playlist = convertView.findViewById(R.id.img_view_playlist);
            convertView.setTag(viewHolder);
        }else{
            //ton tai r lay gia tri dc luu ra
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Playlist playlist = getItem(position);
        Picasso.get().load(playlist.getHinhPlaylist()).into(viewHolder.img_background);
        Picasso.get().load(playlist.getIcon()).into(viewHolder.img_playlist);
        viewHolder.txt_ten_playlist.setText(playlist.getTen());
        return  convertView;
    }



}

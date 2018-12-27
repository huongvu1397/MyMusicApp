package com.devhwang.a84974.mymusicapp.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devhwang.a84974.mymusicapp.Adapter.AlbumAdapter;
import com.devhwang.a84974.mymusicapp.Model.Album;
import com.devhwang.a84974.mymusicapp.R;
import com.devhwang.a84974.mymusicapp.Service.APIService;
import com.devhwang.a84974.mymusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAlbum extends Fragment {
    View view;
    ArrayList<Album> albumArrayList;
    RecyclerView recyclerView;
    TextView txt_xemthem;
    AlbumAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_album,container,false);
        recyclerView = view.findViewById(R.id.recycler_album);
        txt_xemthem = view.findViewById(R.id.txt_xemthem_album);
        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService  = APIService.getService();
        Call<List<Album>> callback = dataService.GetAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                albumArrayList = (ArrayList<Album>) response.body();
                adapter = new AlbumAdapter(getActivity(),albumArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}

package com.devhwang.a84974.mymusicapp.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.devhwang.a84974.mymusicapp.Adapter.BaiHatHotAdapter;
import com.devhwang.a84974.mymusicapp.Model.BaiHat;
import com.devhwang.a84974.mymusicapp.R;
import com.devhwang.a84974.mymusicapp.Service.APIService;
import com.devhwang.a84974.mymusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBaiHatHot extends Fragment {
    View view;
    RecyclerView recyclerView;
    BaiHatHotAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_baihat_hot,container,false);
        recyclerView = view.findViewById(R.id.recycle_baihathot);
        GetData();
        return view;
    }

    private void GetData() {

        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetBaiHatHot();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> baiHats = (ArrayList<BaiHat>) response.body();
                adapter = new BaiHatHotAdapter(getActivity(),baiHats);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}

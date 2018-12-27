package com.devhwang.a84974.mymusicapp.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.devhwang.a84974.mymusicapp.Adapter.PlaylistAdapter;
import com.devhwang.a84974.mymusicapp.Model.Playlist;
import com.devhwang.a84974.mymusicapp.R;
import com.devhwang.a84974.mymusicapp.Service.APIService;
import com.devhwang.a84974.mymusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPlaylist extends Fragment {
    View view;
    ListView lvPlaylist;
    TextView txt_title,txt_xemthem;
    PlaylistAdapter adapter;
    ArrayList<Playlist> mangplaylist;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist,container,false);
        lvPlaylist = view.findViewById(R.id.listview_playlist);
        txt_title = view.findViewById(R.id.txt_title_playlist);
        txt_xemthem = view.findViewById(R.id.txt_view_playlist);
        GetData();
        return view;
    }

    private void GetData() {
        //cấu hình lại rtf
        DataService dataService = APIService.getService();
        // truyền phương thức ở dataservice để get
        Call<List<Playlist>> callback = dataService.GetDataPlaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                //trả về mảng -> dùng mảnh lấy  -> cast về arraylist
                mangplaylist = (ArrayList<Playlist>) response.body();
                //vd mangplaylist.get(0).getTen() - > ten play list
                adapter = new PlaylistAdapter(getActivity(),android.R.layout.simple_list_item_1,mangplaylist);
                lvPlaylist.setAdapter(adapter);
                setListViewHeightBasedOnChildren(lvPlaylist);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}

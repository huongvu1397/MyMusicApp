package com.devhwang.a84974.mymusicapp.Service;

import com.devhwang.a84974.mymusicapp.Model.Album;
import com.devhwang.a84974.mymusicapp.Model.BaiHat;
import com.devhwang.a84974.mymusicapp.Model.ChuDeTheLoai;
import com.devhwang.a84974.mymusicapp.Model.Playlist;
import com.devhwang.a84974.mymusicapp.Model.QuangCao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    // json trả về mảng -> dùng List<QuangCao>  - [{} {}]
    // nếu json trả về object {} thì dùng Call<QuangCao> thôi
    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetDataPlaylist();

    @GET("chudetheloai.php")
    Call<ChuDeTheLoai> GetCategoryMusic();

    @GET("album.php")
    Call<List<Album>> GetAlbum();

    @GET("baihatyeuthich.php")
    Call<List<BaiHat>> GetBaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHat(@Field("idquangcao") String idquangcao);

}

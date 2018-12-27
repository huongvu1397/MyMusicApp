package com.devhwang.a84974.mymusicapp.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRetrofitClient {
    private static Retrofit retrofit = null;

    // function trả về cấu hình khi thực hiện xong retrofit - truyền vào đường dẫn url
    public static Retrofit getClient(String base_url) {
        // các giao thức mạng , các tương tác mạng sẽ thông qua OkHttpClient - kiểm tra mạng
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                // đọc - chờ quá lâu thì sẽ ngắt
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                // ghi - chờ quá lâu thì sẽ ngắt
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                // ngắt kết nối nếu đợi quá lâu
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                // khi có bị lỗi về mạng sẽ cố gắng kết nối lại đến khi được
                .retryOnConnectionFailure(true)
                // khi kết nối với giao thức bên server không thể kết nối được - thì sẽ set giao thức HTTP 1_1
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();
        // khi mà dữ liệu trả về đó là dữ liệu của api đọc ra rất mệt -> gson convert về từ khóa java
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                // nhận url
                .baseUrl(base_url)
                // cấu hình phương thức
                .client(okHttpClient)
                // convert dữ liệu api thành biến java
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        return retrofit;
    }
}

package com.devhwang.a84974.mymusicapp.Service;

public class APIService {
    //truyền địa chỉ url tương tác với server
    private static String base_url = "https://uncalculated-choice.000webhostapp.com/Server/";

    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }

}

package com.devhwang.a84974.mymusicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {

@SerializedName("IdAlbum")
@Expose
private String idAlbum;
@SerializedName("TenAlbum")
@Expose
private String tenAlbum;
@SerializedName("TenCasi")
@Expose
private String tenCasi;
@SerializedName("HinhAnhAlbum")
@Expose
private String hinhAnhAlbum;

public String getIdAlbum() {
return idAlbum;
}

public void setIdAlbum(String idAlbum) {
this.idAlbum = idAlbum;
}

public String getTenAlbum() {
return tenAlbum;
}

public void setTenAlbum(String tenAlbum) {
this.tenAlbum = tenAlbum;
}

public String getTenCasi() {
return tenCasi;
}

public void setTenCasi(String tenCasi) {
this.tenCasi = tenCasi;
}

public String getHinhAnhAlbum() {
return hinhAnhAlbum;
}

public void setHinhAnhAlbum(String hinhAnhAlbum) {
this.hinhAnhAlbum = hinhAnhAlbum;
}

}
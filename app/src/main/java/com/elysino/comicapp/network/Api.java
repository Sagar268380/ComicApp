package com.elysino.comicapp.network;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    @GET("info.0.json")
    Observable<Response<ResponseBody>>  getComic();

    @GET("{id}/info.0.json")
    Observable<Response<ResponseBody>> getComicById(@Path("id") int num);
}

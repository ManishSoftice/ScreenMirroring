package com.example.softice.networking;


import com.example.softice.model.AppSettings;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("addapikey")
    Call<AppSettings> addapikey(@Field("packagename") String PackageName,
                                @Field("apikey") String Apikey,
                                @Field("isdebug") Boolean isDebug,
                                @Field("isfirsttime") Boolean isFirstTime);

}
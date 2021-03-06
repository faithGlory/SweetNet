package com.faith.sweetnet.okhttp;

import android.util.Log;

import com.faith.sweetnet.SweetUrl;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpManager {

    private OkHttpManager() {
    }

    private static OkHttpManager manager = null;
    private static OkHttpClient okHttpClient = null;

    public static OkHttpManager Builder() {
        if (manager == null) {
            manager = new OkHttpManager();
        }
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        return manager;
    }

    public void get() {
        Request request = new Request.Builder().url(SweetUrl.url).get().build();
        okHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //网络请求失败
                        Log.e("call ", "onFailure");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //网络请求成功
                        Log.e("call ", "onResponse: " + response.body());
                    }
                });
    }

    public void post() {
        MediaType mediaType = MediaType.parse("");
        Request request = new Request.Builder()
                .url(SweetUrl.url)
                .post(RequestBody.create(mediaType, ""))
                .build();
        okHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d("call ", response.protocol() + " " + response.code() + " " + response.message());
                        Headers headers = response.headers();
                        for (int i = 0; i < headers.size(); i++) {
                            Log.d("call ", headers.name(i) + ":" + headers.value(i));
                        }
                        Log.d("call ", "onResponse: " + response.body().string());

                    }
                });
    }
}

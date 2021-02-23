package com.elysino.comicapp.slashscreen;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.elysino.comicapp.network.Api;
import com.elysino.comicapp.ComicData;
import com.elysino.comicapp.slashscreen.swapcar.SwapCard;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

class SplashPresenter implements SplashController.View{

    Api api;
    ArrayList<ComicData> arrayList;
    ContentValues values;
    SQLiteDatabase sqLiteDatabase;
    int lastValue=24;
    Context context;


    SplashPresenter(Api api, ArrayList<ComicData> arrayList, ContentValues values, SQLiteDatabase sqLiteDatabase, SplashScreen context){
        this.api=api;
        this.arrayList=arrayList;
        this.values=values;
        this.sqLiteDatabase=sqLiteDatabase;
        this.context=context;
    }

    @Override
    public void getComic(int position) {
        Observable<Response<ResponseBody>> request = api.getComicById(position);
        request.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<ResponseBody> value) {
                        switch (value.code()) {
                            case 200:
                                int currentComicPosition = position;
                                try {
                                    String s = value.body().string();
                                    ComicData comicData = new Gson().fromJson(s,ComicData.class);

                                    arrayList.add(new ComicData(comicData.getMonth(),comicData.getNum(),"",comicData.getYear(),"",comicData.getSafeTitle(),comicData.getTranscript(),"",comicData.getImg(),"",comicData.getDay()));
                                    values.put("NAME", comicData.getSafeTitle());
                                    values.put("IMAGE", comicData.getImg());
                                    values.put("ALT",comicData.getTranscript().replaceAll("[\\p{Ps}\\p{Pe}]", ""));
                                    values.put("DAY",comicData.getDay());
                                    values.put("MONTH",comicData.getMonth());
                                    values.put("YEAR",comicData.getYear());
                                    values.put("NUM",comicData.getNum());
                                    Log.d("DateData", "onNext: "+comicData.getDay() +comicData.getMonth()+comicData.getYear());
                                    sqLiteDatabase.insert("COMIC", null, values);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                if (currentComicPosition<lastValue){
                                    getComic(++currentComicPosition);
                                }else {
                                    sqLiteDatabase.close();
                                    openSwapCardScreen();
                                }
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void openSwapCardScreen() {
        Intent intent=new Intent(context,SwapCard.class);
        context.startActivity(intent);
        ((Activity)context).finish();
    }

}

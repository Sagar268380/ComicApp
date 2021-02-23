package com.elysino.comicapp.slashscreen;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.elysino.comicapp.network.Api;
import com.elysino.comicapp.network.ApiClient;
import com.elysino.comicapp.ComicData;
import com.elysino.comicapp.sqllite.DatabaseHelper;
import com.elysino.comicapp.R;

import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity implements SplashController.Presenter{

    Api api;
    ArrayList<ComicData> arrayList;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    public ProgressDialog progressDialog;

    ArrayList<ComicData> comicData;
    ContentValues values;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    SplashPresenter presenter;

    private int lastValue = 24;
    boolean apiCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        sharedPreferences=getSharedPreferences("DATA",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        values=new ContentValues();

       // showProgressDialog();
        //progressDialog.show();
        arrayList=new ArrayList<>();
        api= ApiClient.getClient().create(Api.class);
        comicData=new ArrayList<>();

       // presenter.getComic(1);

        databaseHelper=new DatabaseHelper(getApplicationContext());
        sqLiteDatabase=databaseHelper.getWritableDatabase();

        presenter=new SplashPresenter(api,arrayList,values,sqLiteDatabase,this);

       apiCheck=sharedPreferences.getBoolean("api_check",false);

        if (!apiCheck)
        {
            editor.putBoolean("api_check", true);
            editor.apply();
            presenter.getComic(1);
        }else {
           presenter.openSwapCardScreen();
        }


    }

  /*  private void getComicInfo(int position) {
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
                                    getComicInfo(++currentComicPosition);
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

    }*/

    /*private void openSwapCardScreen() {
        Intent intent=new Intent(SplashScreen.this, SwapCard.class);
        startActivity(intent);
        finish();
    }*/

  /*  private void showProgressDialog() {
        progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setContentView(R.layout.prgogessdialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }*/
}
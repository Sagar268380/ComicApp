package com.elysino.comicapp.slashscreen;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.elysino.comicapp.network.Api;
import com.elysino.comicapp.network.ApiClient;
import com.elysino.comicapp.ComicData;
import com.elysino.comicapp.slashscreen.swapcar.SwapCard;
import com.elysino.comicapp.sqllite.DatabaseHelper;
import com.elysino.comicapp.R;

import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity implements SplashView{

    Api api;
    ArrayList<ComicData> arrayList;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    public ProgressDialog progressDialog;

    ContentValues values;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    SplashPresenter presenter;
    boolean apiCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences=getSharedPreferences("DATA",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        values=new ContentValues();

        arrayList=new ArrayList<>();
        api= ApiClient.getClient().create(Api.class);

        databaseHelper=new DatabaseHelper(getApplicationContext());
        sqLiteDatabase=databaseHelper.getWritableDatabase();

        presenter=new SplashPresenter(this,new SplashItemsInteractor(),api,sqLiteDatabase,values);

        apiCheck=sharedPreferences.getBoolean("api_check",false);

        if (!apiCheck)
        {
            editor.putBoolean("api_check", true);
            editor.apply();
            presenter.getComic(1);
        }else {
           openSwapCardScreen();
        }
    }

    @Override
    public void openSwapCardScreen() {
        startActivity(new Intent(this, SwapCard.class));
        finish();
    }
}
/*
 *
 *  * Copyright (C) 2018 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.elysino.comicapp.slashscreen.swapcar;

import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.widget.Toast;

import com.elysino.comicapp.ComicData;
import com.elysino.comicapp.sqllite.DatabaseHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwapItemsInteractor {

    ArrayList<ComicData> arrayList=new ArrayList<>();
    DatabaseHelper databaseHelper;

    SwapItemsInteractor(DatabaseHelper databaseHelper){
        this.databaseHelper=databaseHelper;
    }

    interface OnFinishedListener {
        void onFinished(ArrayList<ComicData> comicData);
    }

    public void findItems(final OnFinishedListener listener) {
       listener.onFinished(createData());
    }


    public ArrayList<ComicData> createData(){
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        String sql = "select * from COMIC";
        Cursor c1 = database.rawQuery(sql, null);
        if(c1!=null) {
            while (c1.moveToNext()) {
                String name = c1.getString(1);
                String image = c1.getString(2);
                String alt = c1.getString(3);
                String day = c1.getString(4);
                String month = c1.getString(5);
                String year = c1.getString(6);
                int num = c1.getInt(7);
                arrayList.add(new ComicData(month, num, "", year, "", name, alt, "", image, "", day));
            }
        }
        return arrayList;
    }



}

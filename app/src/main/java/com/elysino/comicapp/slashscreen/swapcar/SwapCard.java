package com.elysino.comicapp.slashscreen.swapcar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.elysino.comicapp.ComicData;
import com.elysino.comicapp.sqllite.DatabaseHelper;
import com.elysino.comicapp.R;
import com.elysino.comicapp.SwipeCardAdapter;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class SwapCard extends AppCompatActivity implements SwapCarController.Presenter{
    private String TAG = SwapCard.class.getSimpleName();
    ArrayList<ComicData> arrayList;
    SwipeCardAdapter swipe_card_adapter;
    DatabaseHelper databaseHelper;
    private SwipeFlingAdapterView flingContainer;
    SwapPresenter swapPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap_card);

        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        databaseHelper=new DatabaseHelper(getApplicationContext());
        swapPresenter=new SwapPresenter(databaseHelper,flingContainer,this);
        swapPresenter.showData();

        // arrayList=new ArrayList<>();

       /* SQLiteDatabase database = databaseHelper.getReadableDatabase();
        String sql = "select * from COMIC";
        Cursor c1 = database.rawQuery(sql, null);

        Log.d("Count", "onCreate: "+databaseHelper.getCount());*/


        /*if(c1!=null){
            Toast.makeText(this, "Enter", Toast.LENGTH_SHORT).show();
            while (c1.moveToNext()){
                String name=c1.getString(1);
                String image=c1.getString(2);
                String alt=c1.getString(3);
                String day=c1.getString(4);
                String month=c1.getString(5);
                String year=c1.getString(6);
                int num=c1.getInt(7);
                Log.d(TAG, "onCreate: "+name +"  "+ image+ " "+alt + " "+day +" "+num);
                arrayList.add(new ComicData(month,num,"",year,"",name,alt,"",image,"",day));
                swipe_card_adapter = new SwipeCardAdapter(getApplicationContext(),arrayList);
            }
        }*/

       /* flingContainer.setAdapter(swipe_card_adapter);

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                arrayList.remove(0);
                swipe_card_adapter.notifyDataSetChanged();
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                arrayList.remove(0);
                swipe_card_adapter.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });*/

        // Optionally add an OnItemClickListener
       /* flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);

                swipe_card_adapter.notifyDataSetChanged();
            }
        });*/

    }


}
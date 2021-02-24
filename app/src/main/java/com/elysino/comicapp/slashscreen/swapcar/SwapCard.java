package com.elysino.comicapp.slashscreen.swapcar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.elysino.comicapp.ComicData;
import com.elysino.comicapp.sqllite.DatabaseHelper;
import com.elysino.comicapp.R;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class SwapCard extends AppCompatActivity implements SwapCardView{
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
        swapPresenter=new SwapPresenter(this,new SwapItemsInteractor(databaseHelper));
    }

    @Override
    protected void onResume() {
        super.onResume();
        swapPresenter.onResume();
    }

    @Override
    public void showData(ArrayList<ComicData> arrayList) {
        swipe_card_adapter = new SwipeCardAdapter(getApplicationContext(),arrayList);
        flingContainer.setAdapter(swipe_card_adapter);

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
        });

        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);

                swipe_card_adapter.notifyDataSetChanged();
            }
        });
    }
}
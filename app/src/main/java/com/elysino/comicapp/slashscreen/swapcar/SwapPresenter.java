package com.elysino.comicapp.slashscreen.swapcar;

import com.elysino.comicapp.ComicData;
import com.elysino.comicapp.sqllite.DatabaseHelper;

import java.util.ArrayList;

class SwapPresenter implements SwapItemsInteractor.OnFinishedListener{
    SwapCardView swapCardView;
    SwapItemsInteractor swapItemsInteractor;

    public SwapPresenter(SwapCardView swapCardView, SwapItemsInteractor swapItemsInteractor) {
        this.swapCardView = swapCardView;
        this.swapItemsInteractor = swapItemsInteractor;
    }


    void onResume() {
        swapItemsInteractor.findItems(this::onFinished);
    }

    @Override
    public void onFinished(ArrayList<ComicData> comicData) {
        swapCardView.showData(comicData);
    }

}

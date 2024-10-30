package org.domino.seamless.search;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ListView;


import androidx.recyclerview.widget.RecyclerView;

import com.yandex.mapkit.geometry.Geometry;
import com.yandex.mapkit.map.VisibleRegionUtils;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.search.SearchManager;
import com.yandex.mapkit.search.SearchOptions;

public final class SearchWatcher implements TextWatcher {
    private final RecyclerView suggestResultView;
    private final Context context;
    private final SearchManager searchManager;
    private final MapView mapView;

    public SearchWatcher(RecyclerView suggestResultView, SearchManager searchManager, Context context, MapView mapView) {
        this.suggestResultView = suggestResultView;
        this.context = context;
        this.searchManager = searchManager;
        this.mapView = mapView;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void afterTextChanged(Editable editable) {
        requestSuggest(editable.toString());
    }

    private void requestSuggest(String query) {
        final Geometry visibleRegion = VisibleRegionUtils.toPolygon(mapView.getMapWindow().getMap().getVisibleRegion());
        searchManager.submit(query, visibleRegion, new SearchOptions(), new Search(suggestResultView, context));
        suggestResultView.setVisibility(View.INVISIBLE);
    }
}

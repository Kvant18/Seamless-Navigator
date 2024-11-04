package org.domino.seamless.search;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.yandex.mapkit.GeoObjectCollection;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.search.Response;
import com.yandex.mapkit.search.Session;
import com.yandex.runtime.Error;
import com.yandex.runtime.network.NetworkError;
import com.yandex.runtime.network.RemoteError;

import org.domino.seamless.Pin;
import org.domino.seamless.R;
import org.domino.seamless.listener.items.OnClickListener;
import org.domino.seamless.search.recyclerview.RecyclerAdapterPins;

import java.util.ArrayList;

public final class Search implements Session.SearchListener {
    private final ArrayList<Pin> pins;
    private final RecyclerView suggestResultView;
    private final Context context;

    public Search(final RecyclerView suggestResultView, final Context context) {
        this.suggestResultView = suggestResultView;
        this.context = context;
        this.pins = new ArrayList<>();
    }

    @Override
    public void onSearchResponse(Response response) {
        pins.clear();
        for(GeoObjectCollection.Item item : response.getCollection().getChildren()) {
            final String name = item.getObj().getName();
            final String desc = item.getObj().getDescriptionText();
            final Point point = item.getObj().getGeometry().get(0).getPoint();
            this.pins.add(new Pin(name, desc, point, R.drawable.cup));
        }
        //final PinsAdapter adapter = new PinsAdapter(context, this.pins);
        final RecyclerAdapterPins adapter = new RecyclerAdapterPins(this.pins);
        suggestResultView.setAdapter(adapter);
        suggestResultView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSearchError(Error error) {
        String errorMessage;
        if (error instanceof RemoteError) {
            errorMessage = context.getString(R.string.remote_error_message);
        } else if (error instanceof NetworkError) {
            errorMessage = context.getString(R.string.network_error_message);
        } else {
            errorMessage = context.getString(R.string.unknown_error_message);
        }

        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
package org.domino.seamless.listener.items;

import android.view.View;
import android.widget.AdapterView;


import com.google.android.material.search.SearchView;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CompositeIcon;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.RotationType;
import com.yandex.mapkit.map.TextStyle;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import org.domino.seamless.Pin;
import org.domino.seamless.R;

public final class OnItemClick implements AdapterView.OnItemClickListener {
    private final MapView mapView;
    private final ImageProvider provider;
    private final SearchView searchView;

    public OnItemClick(MapView mapView, SearchView searchView) {
        this.mapView = mapView;
        this.provider = ImageProvider.fromResource(mapView.getContext(), R.drawable.location_pin, true);
        this.searchView = searchView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Pin pin = (Pin) adapterView.getItemAtPosition(i);
        MapObjectCollection mapObjects = mapView.getMapWindow().getMap().getMapObjects();
        mapObjects.clear();
        mapObjects.addPlacemark(placemark -> {
           placemark.setGeometry(pin.getPoint());
           placemark.setText(pin.getName(), new TextStyle().setSize(10f).setPlacement(TextStyle.Placement.RIGHT));
           final CompositeIcon icon = placemark.useCompositeIcon();
           icon.setIcon("pin", this.provider, new IconStyle()
                   .setZIndex(1f)
                   .setRotationType(RotationType.ROTATE)
                   .setScale(0.07f));
        });
        searchView.hide();
        mapView.getMapWindow().getMap().move(new CameraPosition(pin.getPoint(), 14, 0,0));
    }
}

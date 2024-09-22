package org.domino.seamless.listener.location;


import android.content.Context;
import android.widget.Toast;

import com.yandex.mapkit.directions.driving.DrivingRoute;
import com.yandex.mapkit.directions.driving.DrivingSession;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.Error;

import java.util.List;

public final class RoutesListener implements DrivingSession.DrivingRouteListener {
    private final Context context;
    private final MapView mapView;

    public RoutesListener(Context context, MapView mapView) {
        this.context = context;
        this.mapView = mapView;
    }

    @Override
    public void onDrivingRoutes(List<DrivingRoute> list) {
        MapObjectCollection collection = mapView.getMapWindow().getMap().getMapObjects().addCollection();
        collection.addPolyline(list.get(0).getGeometry());
    }

    @Override
    public void onDrivingRoutesError(Error error) {
        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
    }
}

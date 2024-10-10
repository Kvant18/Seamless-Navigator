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
    private static final RoutesListener instance = new RoutesListener();

    private Context context;
    private MapView mapView;
    private List<DrivingRoute> routes;

    public void set(Context context, MapView mapView) {
        this.context = context;
        this.mapView = mapView;
    }

    public static RoutesListener getInstance() {
        return instance;
    }

    @Override
    public void onDrivingRoutes(List<DrivingRoute> list) {
        routes = list;
        MapObjectCollection collection = mapView.getMapWindow().getMap().getMapObjects().addCollection();
        collection.addPolyline(list.get(0).getGeometry());
    }

    @Override
    public void onDrivingRoutesError(Error error) {
        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
    }

    public List<DrivingRoute> getRoutes() {
        return routes;
    }
}

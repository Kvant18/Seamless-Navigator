package org.domino.seamless.listener.items;

import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;

import com.google.android.material.search.SearchView;
import com.yandex.mapkit.RequestPoint;
import com.yandex.mapkit.RequestPointType;
import com.yandex.mapkit.directions.DirectionsFactory;
import com.yandex.mapkit.directions.driving.DrivingOptions;
import com.yandex.mapkit.directions.driving.DrivingRouter;
import com.yandex.mapkit.directions.driving.DrivingRouterType;
import com.yandex.mapkit.directions.driving.VehicleOptions;
import com.yandex.mapkit.geometry.Point;
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
import org.domino.seamless.listener.location.LocationUserPin;
import org.domino.seamless.listener.location.RoutesListener;
import org.domino.seamless.listener.location.UserLocation;

import java.util.ArrayList;
import java.util.List;

public final class OnItemClick implements AdapterView.OnItemClickListener {
    private final MapView mapView;
    private final ImageProvider provider;
    private final SearchView searchView;
    private final DrivingRouter drivingRouter;

    public OnItemClick(MapView mapView, SearchView searchView) {
        this.mapView = mapView;
        this.provider = ImageProvider.fromResource(mapView.getContext(), R.drawable.location_pin, true);
        this.searchView = searchView;
        this.drivingRouter = DirectionsFactory.getInstance().createDrivingRouter(DrivingRouterType.COMBINED);
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
        mapView.getMapWindow().getMap().move(new CameraPosition(pin.getPoint(), 14, 0,0));
        GeneratingRoutes(pin);
        searchView.hide();
    }

    private void GeneratingRoutes(Pin pin) {
        final List<RequestPoint> lists = getRequestPoints(pin);
        final DrivingOptions options = new DrivingOptions();
        options.setRoutesCount(lists.size());

        drivingRouter.requestRoutes(lists, options, new VehicleOptions(), RoutesListener.getInstance());

    }

    private static List<RequestPoint> getRequestPoints(Pin pin) {
        List<RequestPoint> lists = new ArrayList<>();
        Point point = UserLocation.getInstance().getUserLocation();
        lists.add(new RequestPoint(point, RequestPointType.WAYPOINT, null, null));
        lists.add(new RequestPoint(pin.getPoint(), RequestPointType.WAYPOINT, null, null));
        return lists;
    }
}

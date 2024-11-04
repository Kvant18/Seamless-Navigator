package org.domino.seamless.listener.items;

import com.google.android.material.search.SearchView;
import com.yandex.mapkit.directions.DirectionsFactory;
import com.yandex.mapkit.directions.driving.DrivingRouter;
import com.yandex.mapkit.directions.driving.DrivingRouterType;
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
import org.domino.seamless.routes.generating.RoutersGenerator;

public final class OnClickListener {
    private static final OnClickListener instance = new OnClickListener();
    private static final DrivingRouter drivingRouter = DirectionsFactory.getInstance().createDrivingRouter(DrivingRouterType.COMBINED);
    private MapView mapView;
    private ImageProvider provider;
    private SearchView searchView;
    private Pin pin;

    public static OnClickListener getInstance() {
        return instance;
    }

    public void init(final MapView mapView, final SearchView searchView) {
        this.provider = ImageProvider.fromResource(mapView.getContext(), R.drawable.location_pin, true);
        this.mapView = mapView;
        this.searchView = searchView;
    }

    public void onClick(final Pin pin) {
        this.pin = pin;
        //mapView.getMapWindow().getMap().move(new CameraPosition(pin.getPoint(), 14f, 0f,0f));
        RoutersGenerator.getInstance().GeneratingRoutes(pin);
        searchView.hide();
    }

    public void setEndIcon() {
        MapObjectCollection mapObjects = mapView.getMapWindow().getMap().getMapObjects();
        mapObjects.addPlacemark(placemark -> {
            placemark.setGeometry(pin.getPoint());
            placemark.setText(pin.getName(), new TextStyle().setSize(10f).setPlacement(TextStyle.Placement.RIGHT));
            final CompositeIcon icon = placemark.useCompositeIcon();
            icon.setIcon("pin", this.provider, new IconStyle()
                    .setZIndex(1f)
                    .setRotationType(RotationType.ROTATE)
                    .setScale(0.07f));
        });
    }
}

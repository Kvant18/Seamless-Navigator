package org.domino.seamless.listener.location;


import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.layers.ObjectEvent;
import com.yandex.mapkit.location.Location;
import com.yandex.mapkit.location.LocationListener;
import com.yandex.mapkit.location.LocationStatus;
import com.yandex.mapkit.map.CompositeIcon;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.map.RotationType;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.user_location.UserLocationLayer;
import com.yandex.mapkit.user_location.UserLocationObjectListener;
import com.yandex.mapkit.user_location.UserLocationView;
import com.yandex.runtime.image.ImageProvider;

import org.domino.seamless.R;

public final class LocationObjectListener implements UserLocationObjectListener, LocationListener {
    private final static LocationObjectListener instance = new LocationObjectListener();
    private UserLocationLayer userLocationLayer;
    private MapView mapView;
    private Context context;

    private Point userLocation;


    @Override
    public void onObjectAdded(UserLocationView userLocationView) {
        userLocationLayer.setAnchor(
                new PointF((float) (mapView.getWidth() * 0.5), (float) (mapView.getHeight() * 0.5)),
                new PointF((float) (mapView.getWidth() * 0.5), (float) (mapView.getHeight() * 0.83)));

        PlacemarkMapObject icon = userLocationView.getArrow();

        icon.setIcon(ImageProvider.fromResource(
                context, R.drawable.arrow, true),
                new IconStyle().setAnchor(new PointF(0.5f, 0.5f))
                        .setRotationType(RotationType.ROTATE)
                        .setZIndex(1f)
                        .setScale(0.1f));


        CompositeIcon pinIcon = userLocationView.getPin().useCompositeIcon();

        pinIcon.setIcon(
                "pin",
                ImageProvider.fromResource(context, R.drawable.whereami, true),
                new IconStyle().setAnchor(new PointF(0.5f, 0.5f))
                        .setRotationType(RotationType.ROTATE)
                        .setZIndex(1f)
                        .setScale(0.06f)
        );

        userLocationView.getAccuracyCircle().setFillColor(Color.GRAY & 0x99ffffff);
    }

    @Override
    public void onObjectRemoved(UserLocationView userLocationView) {}

    @Override
    public void onObjectUpdated(UserLocationView userLocationView, ObjectEvent objectEvent) {}

    public Point getUserLocation() {
        return userLocation;
    }

    public void set(UserLocationLayer layer, MapView mapView, Context context) {
        this.userLocationLayer = layer;
        this.mapView = mapView;
        this.context = context;
    }

    public static LocationObjectListener getInstance() {
        return instance;
    }

    @Override
    public void onLocationUpdated(Location location) {
        this.userLocation = location.getPosition();
        //Point point = location.getPosition();
        //Toast.makeText(mapView.getContext(), String.valueOf(point.getLongitude()) + " " + String.valueOf(point.getLatitude()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationStatusUpdated(LocationStatus locationStatus) {

    }
}

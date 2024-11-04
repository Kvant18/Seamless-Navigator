package org.domino.seamless.listener.location;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;

import com.yandex.mapkit.map.CompositeIcon;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.map.RotationType;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.user_location.UserLocationLayer;
import com.yandex.mapkit.user_location.UserLocationView;
import com.yandex.runtime.image.ImageProvider;

import org.domino.seamless.R;

public final class LocationUserPin extends LocationAbstraction {
    private final UserLocationLayer userLocationLayer;
    private final MapView mapView;
    private final Context context;

    public LocationUserPin(final UserLocationLayer layer, final MapView mapView, final Context context) {
        this.userLocationLayer = layer;
        this.mapView = mapView;
        this.context = context;
    }

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
}

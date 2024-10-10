package org.domino.seamless.listener.location;

import com.yandex.mapkit.layers.ObjectEvent;
import com.yandex.mapkit.location.Location;
import com.yandex.mapkit.location.LocationListener;
import com.yandex.mapkit.location.LocationStatus;
import com.yandex.mapkit.user_location.UserLocationObjectListener;
import com.yandex.mapkit.user_location.UserLocationView;

public abstract class LocationAbstraction implements UserLocationObjectListener, LocationListener {

    @Override
    public void onObjectAdded(UserLocationView userLocationView) {}

    @Override
    public void onObjectRemoved(UserLocationView userLocationView) {}

    @Override
    public void onObjectUpdated(UserLocationView userLocationView, ObjectEvent objectEvent) {}

    @Override
    public void onLocationUpdated(Location location) {}

    @Override
    public void onLocationStatusUpdated(LocationStatus locationStatus) {}
}

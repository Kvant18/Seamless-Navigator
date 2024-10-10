package org.domino.seamless.listener.location;

import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.location.Location;

public final class UserLocation extends LocationAbstraction {
    private static final UserLocation instance = new UserLocation();
    private Point userLocation;

    public Point getUserLocation() {
        return userLocation;
    }

    public static UserLocation getInstance() {
        return instance;
    }

    @Override
    public void onLocationUpdated(Location location) {
        this.userLocation = location.getPosition();
    }
}

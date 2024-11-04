package org.domino.seamless.routes;

import org.domino.seamless.R;

public enum RouteType {
    DRIVING(R.drawable.car_icon),
    WALKING(R.drawable.directions_walk),
    BICYCLE(R.drawable.pedal_bike),
    PUBLIC_TRANSPORT(R.drawable.directions_bus);

    private final int value;

    RouteType(int icon) {
        this.value = icon;
    }
    public int getIcon() {
        return value;
    }
}

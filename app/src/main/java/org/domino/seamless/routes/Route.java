package org.domino.seamless.routes;

import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.geometry.Polyline;

public final class Route {
    private final RouteType type;
    private final String time, distance;
    private final int iconId;
    private final Polyline mapRoute;
    private final Point point;

    public Route(final RouteType type, final String time, final String distance, final Polyline mapRoute, Point point) {
        this.type = type;
        this.time = time;
        this.distance = distance;
        this.iconId = type.getIcon();
        this.mapRoute = mapRoute;
        this.point = point;
    }

    public RouteType getType() {
        return type;
    }

    public String getTime() {
        return time;
    }

    public String getDistance() {
        return distance;
    }

    public int getIconId() {
        return iconId;
    }

    public Polyline getMapRoute() {
        return mapRoute;
    }

    public Point getPoint() {
        return point;
    }
}

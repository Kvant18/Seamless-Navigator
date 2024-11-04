package org.domino.seamless;

import com.yandex.mapkit.geometry.Point;

public final class Pin {
    private final String name;
    private final String description;
    private final Point point;
    private final int icon;

    public Pin(final String name, final String description, final Point point, final int icon) {
        this.name = name;
        this.description = description;
        this.point = point;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public Point getPoint() {
        return point;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}

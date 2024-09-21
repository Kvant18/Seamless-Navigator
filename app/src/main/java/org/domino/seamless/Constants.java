package org.domino.seamless;

import com.yandex.mapkit.geometry.BoundingBox;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.search.SuggestOptions;
import com.yandex.mapkit.search.SuggestType;

import java.util.Arrays;
import java.util.List;

public final class Constants {
    public static final int PERMISSIONS_REQUEST_FINE_LOCATION = 1;
    public static final int RESULT_NUMBER_LIMIT = 10;

    public static final Point DEFAULT_POINT = new Point(59.945933, 30.320045);

    // Map Objects
    public static final Point ANIMATED_RECTANGLE_CENTER = new Point(59.956, 30.313);
    public static final Point TRIANGLE_CENTER = new Point(59.948, 30.313);
    public static final Point POLYLINE_CENTER = DEFAULT_POINT;
    public static final Point CIRCLE_CENTER = new Point(59.956, 30.323);
    public static final Point DRAGGABLE_PLACEMARK_CENTER = new Point(59.948, 30.323);
    public static final Point ANIMATED_PLACEMARK_CENTER = new Point(59.948, 30.318);

    // Custom Layer
    static final String LOGO_URL = "https://maps-ios-pods-public.s3.yandex.net/mapkit_logo.png";

    // Masstransit
    public static final Point MASSTRANSIT_POINT = new Point(55.752078, 37.592664);
    public static final Point MASSTRANSIT_ROUTE_START_LOCATION = new Point(55.699671, 37.567286);
    public static final Point MASSTRANSIT_ROUTE_END_LOCATION = new Point(55.790621, 37.558571);

    // Clustering
    public static final List<Point> CLUSTER_CENTERS = Arrays.asList(
            new Point(55.756, 37.618),
            new Point(59.956, 30.313),
            new Point(56.838, 60.597),
            new Point(43.117, 131.900),
            new Point(56.852, 53.204)
    );

    public static final double BOX_SIZE = 0.2;

    public static final BoundingBox BOUNDING_BOX = new BoundingBox(
            new Point(DEFAULT_POINT.getLatitude() - BOX_SIZE, DEFAULT_POINT.getLongitude() - BOX_SIZE),
            new Point(DEFAULT_POINT.getLatitude() + BOX_SIZE, DEFAULT_POINT.getLongitude() + BOX_SIZE));
    public static final SuggestOptions SEARCH_OPTIONS =  new SuggestOptions().setSuggestTypes(
            SuggestType.GEO.value |
                    SuggestType.BIZ.value |
                    SuggestType.TRANSIT.value);
}

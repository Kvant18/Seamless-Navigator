package org.domino.seamless.routes.generating;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.yandex.mapkit.RequestPoint;
import com.yandex.mapkit.RequestPointType;
import com.yandex.mapkit.directions.DirectionsFactory;
import com.yandex.mapkit.directions.driving.DrivingOptions;
import com.yandex.mapkit.directions.driving.DrivingRoute;
import com.yandex.mapkit.directions.driving.DrivingRouter;
import com.yandex.mapkit.directions.driving.DrivingRouterType;
import com.yandex.mapkit.directions.driving.VehicleOptions;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.transport.TransportFactory;
import com.yandex.mapkit.transport.masstransit.BicycleRouterV2;
import com.yandex.mapkit.transport.masstransit.FitnessOptions;
import com.yandex.mapkit.transport.masstransit.PedestrianRouter;
import com.yandex.mapkit.transport.masstransit.RouteOptions;
import com.yandex.mapkit.transport.masstransit.TimeOptions;
import com.yandex.mapkit.transport.masstransit.Weight;

import org.domino.seamless.Pin;
import org.domino.seamless.R;
import org.domino.seamless.components.RoutesList;
import org.domino.seamless.listener.location.UserLocation;
import org.domino.seamless.routes.Route;
import org.domino.seamless.routes.RouteType;

import java.util.ArrayList;
import java.util.List;

public final class RoutersGenerator {
    private static final RoutersGenerator instance = new RoutersGenerator();
    private static final DrivingRouter drivingRouter = DirectionsFactory.getInstance().createDrivingRouter(DrivingRouterType.COMBINED);
    private static final BicycleRouterV2 bicyclerRouter = TransportFactory.getInstance().createBicycleRouterV2();
    private static final PedestrianRouter pedestrianRouter = TransportFactory.getInstance().createPedestrianRouter();
    private final List<Route> routes = new ArrayList<>();

    private FrameLayout searchBarView, routesView;
    private Context context;
    private Pin pin;
    private MapView mapView;


    public void init(final FrameLayout searchBarView, final FrameLayout routesView, final MapView mapView) {
        this.searchBarView = searchBarView;
        this.routesView = routesView;
        this.context = routesView.getContext();
        this.mapView = mapView;
    }

    public void GeneratingRoutes(final Pin pin) {
        this.pin = pin;
        this.routes.clear();
        final List<RequestPoint> lists = getRequestPoints(pin);
        final TimeOptions timeOptions = new TimeOptions();
        final RouteOptions routeOptions = new RouteOptions(new FitnessOptions(false));

        drivingRouter.requestRoutes(lists, new DrivingOptions().setRoutesCount(lists.size()), new VehicleOptions(), new DrivingRoutes());
        bicyclerRouter.requestRoutes(lists, timeOptions, routeOptions, new BicycleRoutes());
        pedestrianRouter.requestRoutes(lists, timeOptions, routeOptions, new PedestrianRouters());
    }

    public void AddingDrivingRoute(final DrivingRoute route) {
        final com.yandex.mapkit.directions.driving.Weight metadata = route.getMetadata().getWeight();
        routes.add(new Route(RouteType.DRIVING, metadata.getTime().getText(), metadata.getDistance().getText(), route.getGeometry(), this.pin.getPoint()));
    }

    public void AddingBicycleRoute(final com.yandex.mapkit.transport.masstransit.Route route) {
        final Weight metadata = route.getMetadata().getWeight();
        routes.add(new Route(RouteType.BICYCLE, metadata.getTime().getText(), metadata.getWalkingDistance().getText(), route.getGeometry(), this.pin.getPoint()));
    }

    public void AddingPedestrianRoute(final com.yandex.mapkit.transport.masstransit.Route route) {
        final Weight metadata = route.getMetadata().getWeight();
        routes.add(new Route(RouteType.WALKING, metadata.getTime().getText(), metadata.getWalkingDistance().getText(), route.getGeometry(), this.pin.getPoint()));
        routesView.addView(new RoutesList(context, routes, context.getString(R.string.your_positon), pin.getName(), mapView, removeRoutes()));
        searchBarView.setVisibility(View.INVISIBLE);
    }

    private View.OnClickListener removeRoutes() {
        return v -> {
            mapView.getMapWindow().getMap().getMapObjects().clear();
            this.searchBarView.setVisibility(View.VISIBLE);
            routesView.removeAllViews();
        };
    }

    private static List<RequestPoint> getRequestPoints(final Pin pin) {
        final List<RequestPoint> lists = new ArrayList<>();
        final Point point = UserLocation.getInstance().getUserLocation();
        lists.add(new RequestPoint(point, RequestPointType.WAYPOINT, null, null));
        lists.add(new RequestPoint(pin.getPoint(), RequestPointType.WAYPOINT, null, null));
        return lists;
    }

    public static RoutersGenerator getInstance() {
        return instance;
    }
}

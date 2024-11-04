package org.domino.seamless.routes.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CompositeIcon;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.RotationType;
import com.yandex.mapkit.map.TextStyle;
import com.yandex.mapkit.mapview.MapView;

import org.domino.seamless.R;
import org.domino.seamless.listener.items.OnClickListener;
import org.domino.seamless.routes.Route;

import java.util.ArrayList;
import java.util.List;

public final class RoutesView extends RecyclerView.Adapter<RoutesHolder> {
    private final List<Route> routes;
    private final MapView mapView;
    private static final List<RoutesHolder> routesHolders = new ArrayList<>();

    public RoutesView(final List<Route> routes, final MapView mapView) {
        this.routes = routes;
        this.mapView = mapView;
    }

    @Override
    public RoutesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.routeitem, parent, false);
        return new RoutesHolder(root);
    }

    @Override
    public void onBindViewHolder(RoutesHolder holder, int position) {
        Route route = routes.get(position);
        routesHolders.add(holder);
        holder.set(route.getTime(), route.getDistance(), route.getIconId());

        if(position == 0) {
            holder.setChecked(true);
            setPolyline(mapView, route);
        }

        holder.setListener(v -> {
            routesHolders.forEach(item -> item.setChecked(false));
            holder.setChecked(true);
            setPolyline(mapView, route);
        });
    }

    private static void setPolyline(final MapView mapView, final Route route) {
        MapObjectCollection mapObjectCollection = mapView.getMapWindow().getMap().getMapObjects();
        mapObjectCollection.clear();
        mapObjectCollection.addPolyline(route.getMapRoute());
        OnClickListener.getInstance().setEndIcon();
        CameraPosition cameraPosition = mapView.getMapWindow().getMap().getCameraPosition();
        CameraPosition newPosition = new CameraPosition(route.getPoint(), cameraPosition.getZoom(), cameraPosition.getAzimuth(), cameraPosition.getTilt());
        mapView.getMapWindow().getMap().move(newPosition, new Animation(Animation.Type.LINEAR, 0.15f), null);
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }
}

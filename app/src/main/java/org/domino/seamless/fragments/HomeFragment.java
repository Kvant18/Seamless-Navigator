package org.domino.seamless.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.search.SearchView;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.location.LocationManager;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.search.SearchFactory;
import com.yandex.mapkit.search.SearchManager;
import com.yandex.mapkit.search.SearchManagerType;
import com.yandex.mapkit.user_location.UserLocationLayer;

import org.domino.seamless.listener.items.OnClickListener;
import org.domino.seamless.listener.location.LocationUserPin;
import org.domino.seamless.R;
import org.domino.seamless.listener.location.UserLocation;
import org.domino.seamless.routes.generating.RoutersGenerator;
import org.domino.seamless.search.SearchWatcher;

public final class HomeFragment extends Fragment {
    private static final float factor = 1.2f;
    private MapView mapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        final SearchManager searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.COMBINED);
        final FloatingActionButton zoomIn = root.findViewById(R.id.fabZoomIn);
        final FloatingActionButton zoomOut = root.findViewById(R.id.fabZoomOut);

        mapView = root.findViewById(R.id.mapview);

        setLocation(mapView, getContext());
        fabinit(zoomOut, zoomIn, mapView);

        final RecyclerView listView = root.findViewById(R.id.recycler_view_pins);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));

        final SearchView searchView = root.findViewById(R.id.search_view_item);
        OnClickListener.getInstance().init(mapView, searchView);
        RoutersGenerator.getInstance().init(root.findViewById(R.id.search_bar_view), root.findViewById(R.id.recycler_result), mapView);
        searchView.getEditText().addTextChangedListener(new SearchWatcher(listView, searchManager, getContext(), mapView));

        return root;
    }

    @Override
    public void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    private static void setLocation(final MapView mapView, final Context context) {
        mapView.getMapWindow().getMap().setRotateGesturesEnabled(true);

        final MapKit mapKit = MapKitFactory.getInstance();
        final UserLocationLayer userLocationLayer = mapKit.createUserLocationLayer(mapView.getMapWindow());
        final LocationUserPin locationUserPin = new LocationUserPin(userLocationLayer, mapView, context);

        final LocationManager locationManager = mapKit.createLocationManager();
        locationManager.requestSingleUpdate(UserLocation.getInstance());

        userLocationLayer.setVisible(true);
        userLocationLayer.setHeadingEnabled(false);
        userLocationLayer.setObjectListener(locationUserPin);
    }

    private static void fabinit(final FloatingActionButton zoomOut, final FloatingActionButton zoomIn, final MapView mapView) {
        zoomOut.setOnClickListener(view -> {
            CameraPosition cameraPosition = mapView.getMapWindow().getMap().getCameraPosition();
            CameraPosition newPosition = new CameraPosition(cameraPosition.getTarget(), cameraPosition.getZoom() / factor, cameraPosition.getAzimuth(), cameraPosition.getTilt());
            mapView.getMapWindow().getMap().move(newPosition, new Animation(Animation.Type.LINEAR, 0.25f), null);
        });

        zoomIn.setOnClickListener(view -> {
            CameraPosition cameraPosition = mapView.getMapWindow().getMap().getCameraPosition();
            CameraPosition newPosition = new CameraPosition(cameraPosition.getTarget(), cameraPosition.getZoom() * factor, cameraPosition.getAzimuth(), cameraPosition.getTilt());
            mapView.getMapWindow().getMap().move(newPosition, new Animation(Animation.Type.LINEAR, 0.25f), null);
        });
    }
}
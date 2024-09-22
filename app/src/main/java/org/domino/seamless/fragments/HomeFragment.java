package org.domino.seamless.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.material.search.SearchView;
import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.location.LocationManager;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.search.SearchFactory;
import com.yandex.mapkit.search.SearchManager;
import com.yandex.mapkit.search.SearchManagerType;
import com.yandex.mapkit.user_location.UserLocationLayer;

import org.domino.seamless.listener.items.OnItemClick;
import org.domino.seamless.listener.location.LocationObjectListener;
import org.domino.seamless.R;
import org.domino.seamless.search.SearchWatcher;

public class HomeFragment extends Fragment {
    private MapView mapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        final SearchManager searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.COMBINED);
        mapView = root.findViewById(R.id.mapview);

        setLocation();

        final ListView listView = root.findViewById(R.id.list_item_view);
        final SearchView searchView = root.findViewById(R.id.search_view_item);

        searchView.getEditText().addTextChangedListener(new SearchWatcher(listView, searchManager, getContext(), mapView));

        listView.setOnItemClickListener(new OnItemClick(mapView, searchView));

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

    private void setLocation() {
        LocationObjectListener locationObjectListener = LocationObjectListener.getInstance();
        mapView.getMapWindow().getMap().setRotateGesturesEnabled(false);
        mapView.getMapWindow().getMap().move(new CameraPosition(new Point(0, 0), 14, 0, 0));
        MapKit mapKit = MapKitFactory.getInstance();
        mapKit.resetLocationManagerToDefault();
        LocationManager locationManager = mapKit.createLocationManager();
        locationManager.requestSingleUpdate(locationObjectListener);
        final UserLocationLayer userLocationLayer = mapKit.createUserLocationLayer(mapView.getMapWindow());
        locationObjectListener.set(userLocationLayer, mapView, getContext());
        userLocationLayer.setVisible(true);
        userLocationLayer.setHeadingEnabled(true);
        userLocationLayer.setObjectListener(locationObjectListener);
    }
}
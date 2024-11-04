package org.domino.seamless.routes.generating;

import android.util.Log;

import com.yandex.runtime.Error;

import java.util.List;

public final class BicycleRoutes implements com.yandex.mapkit.transport.masstransit.Session.RouteListener {


    @Override
    public void onMasstransitRoutes(List<com.yandex.mapkit.transport.masstransit.Route> list) {
        RoutersGenerator.getInstance().AddingBicycleRoute(list.get(0));
    }

    @Override
    public void onMasstransitRoutesError(Error error) {
        Log.e("BicycleRoute", error.toString());
    }
}

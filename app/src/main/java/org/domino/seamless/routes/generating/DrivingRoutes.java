package org.domino.seamless.routes.generating;

import android.util.Log;

import com.yandex.mapkit.directions.driving.DrivingRoute;
import com.yandex.mapkit.directions.driving.DrivingSession;
import com.yandex.runtime.Error;

import org.domino.seamless.Pin;
import org.domino.seamless.listener.items.OnClickListener;

import java.util.List;

public final class DrivingRoutes implements DrivingSession.DrivingRouteListener {

    @Override
    public void onDrivingRoutes(List<DrivingRoute> list) {
        RoutersGenerator.getInstance().AddingDrivingRoute(list.get(0));
    }

    @Override
    public void onDrivingRoutesError(Error error) {
        Log.e("DrivingRoute", error.toString());
    }

}

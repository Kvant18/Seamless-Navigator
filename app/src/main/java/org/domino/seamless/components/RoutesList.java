package org.domino.seamless.components;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.AdjustedCornerSize;
import com.google.android.material.shape.ClampedCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RelativeCornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.textview.MaterialTextView;
import com.yandex.mapkit.mapview.MapView;

import org.domino.seamless.R;
import org.domino.seamless.routes.Route;
import org.domino.seamless.routes.RouteType;
import org.domino.seamless.routes.recyclerview.RoutesView;

import java.util.ArrayList;
import java.util.List;

public final class RoutesList extends LinearLayout {

    public RoutesList(final Context context, final List<Route> routes, final String start, final String end, final MapView mapView, final View.OnClickListener clickListener) {
        this(context, null, 0);

        final MaterialTextView startText = findViewById(R.id.routes_list_from);
        startText.setText(start);

        final MaterialTextView endText = findViewById(R.id.routes_list_to);
        endText.setText(end);

        final FloatingActionButton fab = findViewById(R.id.back_to_search);
        fab.setOnClickListener(clickListener);

        final LinearLayout layout = findViewById(R.id.root_routes_list);
        layout.addView(new RoutesRecycler(getContext(), new RoutesView(routes, mapView)));
    }

    public RoutesList(Context context) {
        this(context, null, 0);
    }

    public RoutesList(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoutesList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.routes_list, this);
        MaterialShapeDrawable shape = MaterialShapeDrawable.createWithElevationOverlay(getContext());
        ShapeAppearanceModel appearanceModel = ShapeAppearanceModel.builder().setTopRightCornerSize(60f).setTopLeftCornerSize(60f).build();
        shape.setShapeAppearanceModel(appearanceModel);
        setBackground(shape);
    }

}

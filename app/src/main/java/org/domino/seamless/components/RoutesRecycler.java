package org.domino.seamless.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.divider.MaterialDividerItemDecoration;

import org.domino.seamless.R;
import org.domino.seamless.routes.recyclerview.RoutesView;

public final class RoutesRecycler extends RelativeLayout {

    public RoutesRecycler(Context context, final RoutesView adapter) {
        this(context, null, 0);
        final RecyclerView recyclerView = findViewById(R.id.recycler_view_routes);

        recyclerView.addItemDecoration(setDividerDecorator(getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }

    public RoutesRecycler(Context context) {
        this(context, null, 0);
    }

    public RoutesRecycler(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoutesRecycler(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.routes, this);
    }

    private static MaterialDividerItemDecoration setDividerDecorator(final Context context) {
        MaterialDividerItemDecoration materialDividerItemDecoration = new MaterialDividerItemDecoration(context, LinearLayoutManager.HORIZONTAL);
        materialDividerItemDecoration.setDividerInsetEnd(32);
        materialDividerItemDecoration.setDividerInsetStart(32);
        materialDividerItemDecoration.setDividerThickness(6);
        materialDividerItemDecoration.setLastItemDecorated(false);
        return materialDividerItemDecoration;
    }
}

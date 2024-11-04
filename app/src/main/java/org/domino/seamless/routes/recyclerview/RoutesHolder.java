package org.domino.seamless.routes.recyclerview;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import org.domino.seamless.R;

public final class RoutesHolder extends RecyclerView.ViewHolder {
    private final ImageView icon;
    private final MaterialTextView time, distance;
    private final MaterialCardView materialCardView;

    public RoutesHolder(final View root) {
        super(root);
        this.distance = root.findViewById(R.id.rotes_item_distance);
        this.icon = root.findViewById(R.id.rotes_item_icon);
        this.time = root.findViewById(R.id.rotes_item_time);
        this.materialCardView = root.findViewById(R.id.route_item_card);
        this.materialCardView.setCheckedIcon(new ColorDrawable());
    }

    public void set(final String time, final String distance, final int iconId) {
        this.time.setText(time);
        this.icon.setImageResource(iconId);
        this.distance.setText(distance);
    }

    public void setChecked(boolean status) {
        this.materialCardView.setChecked(status);
    }

    public void setListener(View.OnClickListener clickListener) {
        this.materialCardView.setOnClickListener(clickListener);
    }
}

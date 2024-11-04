package org.domino.seamless.search.recyclerview;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import org.domino.seamless.Pin;
import org.domino.seamless.R;
import org.domino.seamless.listener.items.OnClickListener;

public final class PinsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ImageView icon;
    private final MaterialTextView name, desc;
    private Pin pin;

    public PinsHolder(View itemView) {
        super(itemView);
        icon = itemView.findViewById(R.id.pin_view_icon);
        name = itemView.findViewById(R.id.pin_view_name);
        desc = itemView.findViewById(R.id.pin_view_desc);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        OnClickListener.getInstance().onClick(this.pin);
    }

    public void setPin(Pin pin) {
        this.pin = pin;
        this.desc.setText(pin.getDescription());
        this.name.setText(pin.getName());
        this.icon.setImageResource(pin.getIcon());
    }
}

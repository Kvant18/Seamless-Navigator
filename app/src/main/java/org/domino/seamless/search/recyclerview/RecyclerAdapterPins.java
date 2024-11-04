package org.domino.seamless.search.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import org.domino.seamless.Pin;
import org.domino.seamless.R;

import java.util.List;

public final class RecyclerAdapterPins extends RecyclerView.Adapter<PinsHolder> {
    private final List<Pin> pins;

    public RecyclerAdapterPins(List<Pin> pins) {
        this.pins = pins;
    }

    @Override
    public PinsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pin_view, parent, false);

        return new PinsHolder(root);
    }

    @Override
    public void onBindViewHolder(PinsHolder holder, int position) {
        final Pin pin = this.pins.get(position);
        holder.setPin(pin);
    }

    @Override
    public int getItemCount() {
        return pins.size();
    }


}

package org.domino.seamless.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;


import com.google.android.material.textview.MaterialTextView;

import org.domino.seamless.Pin;
import org.domino.seamless.R;

import java.util.ArrayList;

public final class PinsAdapter extends ArrayAdapter<Pin> {
    public PinsAdapter(Context context, ArrayList<Pin> pins) {
        super(context, 0, pins);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = convertView;
        if(root == null) {
            root = LayoutInflater.from(getContext()).inflate(R.layout.pin_view, parent, false);
        }
        Pin pin = getItem(position);

        ImageView icon = root.findViewById(R.id.pin_view_icon);
        assert pin != null;
        icon.setImageResource(pin.getIcon());

        MaterialTextView name = root.findViewById(R.id.pin_view_name);
        name.setText(pin.getName());

        MaterialTextView desc = root.findViewById(R.id.pin_view_desc);
        desc.setText(pin.getDescription());

        return root;
    }
}

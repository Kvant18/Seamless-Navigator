package org.domino.seamless.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yandex.mapkit.mapview.MapView;

import org.domino.seamless.R;
import org.domino.seamless.listener.camera.DecodeCallbackMap;

public class CameraFragment extends Fragment {
    private CodeScanner codeScanner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root =  inflater.inflate(R.layout.fragment_camera, container, false);
        final CodeScannerView scannerView = root.findViewById(R.id.scanner_view);
        final ImageView imageView = root.findViewById(R.id.image_camera);
        final FloatingActionButton back_button = root.findViewById(R.id.button_back_camera);

        back_button.setOnClickListener(view -> {
            imageView.setVisibility(View.INVISIBLE);
            scannerView.setVisibility(View.VISIBLE);
            codeScanner.startPreview();
            back_button.setVisibility(View.INVISIBLE);
        });

        codeScanner = new CodeScanner(getActivity(), scannerView);
        codeScanner.setDecodeCallback(new DecodeCallbackMap(imageView, scannerView, back_button));
        codeScanner.setAutoFocusMode(AutoFocusMode.CONTINUOUS);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        codeScanner.startPreview();
    }

    @Override
    public void onPause() {
        codeScanner.releaseResources();
        super.onPause();
    }
}
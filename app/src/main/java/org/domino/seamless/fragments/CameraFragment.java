package org.domino.seamless.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;

import org.domino.seamless.R;
import org.domino.seamless.listener.camera.DecodeCallbackMap;

public final class CameraFragment extends Fragment {
    private CodeScanner mCodeScanner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_camera, container, false);
        final ImageView imageView = root.findViewById(R.id.image_camera);
        final CodeScannerView scannerView = root.findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(getActivity(), scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallbackMap(imageView, scannerView, getActivity()));
        scannerView.setOnClickListener(view -> mCodeScanner.startPreview());

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}
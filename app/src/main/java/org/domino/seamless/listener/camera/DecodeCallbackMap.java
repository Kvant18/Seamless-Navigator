package org.domino.seamless.listener.camera;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.Result;
import com.yandex.mapkit.mapview.MapView;

import org.domino.seamless.R;

public final class DecodeCallbackMap implements DecodeCallback {
    private final FloatingActionButton back_button;
    private final ImageView imageView;
    private final CodeScannerView codeScanner;

    public DecodeCallbackMap(ImageView imageView, CodeScannerView codeScanner, FloatingActionButton backButton) {
        this.back_button = backButton;
        this.imageView = imageView;
        this.codeScanner = codeScanner;
    }

    @Override
    public void onDecoded(Result result) {
        Toast.makeText(back_button.getContext(), result.getText(), Toast.LENGTH_SHORT).show();
        back_button.setVisibility(View.VISIBLE);
        codeScanner.setVisibility(View.INVISIBLE);
        switch (result.getText()) {
            case "kvant18f1": imageView.setImageResource(R.drawable.whereami); break;
            case "kvant18f2": imageView.setImageResource(R.drawable.location_pin); break;
            default: {
                Toast.makeText(back_button.getContext(), result.getText(), Toast.LENGTH_SHORT).show();
            }
        }
        imageView.setVisibility(View.VISIBLE);
    }
}

package org.domino.seamless.listener.camera;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import org.domino.seamless.R;

public final class DecodeCallbackMap implements DecodeCallback {
    private final ImageView imageView;
    private final CodeScannerView codeScanner;
    private final Activity activity;

    public DecodeCallbackMap(final ImageView imageView, final CodeScannerView codeScanner, final Activity activity) {
        this.imageView = imageView;
        this.codeScanner = codeScanner;
        this.activity = activity;
    }

    @Override
    public void onDecoded(Result result) {
        activity.runOnUiThread(() -> {
            codeScanner.setVisibility(View.INVISIBLE);
            switch (result.getText()) {
                case "kvant18f1": imageView.setImageResource(R.drawable.kvant18f1); break;
                case "kvant18f2": imageView.setImageResource(R.drawable.kvant18f2); break;
                default: {
                    Toast.makeText(activity, result.getText(), Toast.LENGTH_SHORT).show();
                }
            }
            imageView.setVisibility(View.VISIBLE);
        });
    }
}

package com.example.flashlightapp;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import android.content.Context;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CameraManager cameraManager;
    private String cameraId;
    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = findViewById(R.id.toggleButton);
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    if (isChecked) {
                        // Turn on the flashlight
                        cameraManager.setTorchMode(cameraId, true);
                        Toast.makeText(MainActivity.this, "Flashlight ON", Toast.LENGTH_SHORT).show();
                    } else {
                        // Turn off the flashlight
                        cameraManager.setTorchMode(cameraId, false);
                        Toast.makeText(MainActivity.this, "Flashlight OFF", Toast.LENGTH_SHORT).show();
                    }
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

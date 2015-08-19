package com.tencent.newrajawali;

import rajawali.RajawaliActivity;
import rajawali.renderer.RajawaliRenderer;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class MainActivity extends RajawaliActivity implements
		SensorEventListener {

	private final float ALPHA = 0.8f;
	private final int SENSITIVITY = 5;

	private SensorManager mSensorManager;
	private float mGravity[];

	private RajawaliRenderer renderer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		mMultisamplingEnabled = true;
		super.onCreate(savedInstanceState);

		renderer = new OBJRenderer(this);
		renderer.setSurfaceView(mSurfaceView);
		super.setRenderer(renderer);

		mGravity = new float[3];
		mSensorManager = (SensorManager) this
				.getSystemService(Context.SENSOR_SERVICE);
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_FASTEST);

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
			
			if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
				mGravity[0] = ALPHA * mGravity[0] + (1 - ALPHA) * event.values[0];
				mGravity[1] = ALPHA * mGravity[1] + (1 - ALPHA) * event.values[1];
				mGravity[2] = ALPHA * mGravity[2] + (1 - ALPHA) * event.values[2];

				float x = Math.round(((event.values[1] - mGravity[1] * SENSITIVITY)/5.0f)*100.0f)/100.0f;
				float y = Math.round(((event.values[0] - mGravity[0] * SENSITIVITY)/5.0f)*100.0f)/100.0f;
				
				((OBJRenderer) renderer).setAccelerometerValues(x,y, 0);
			}
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}
}

package com.tencent.newrajawali;

import android.content.Context;
import android.view.MotionEvent;

import rajawali.Object3D;
import rajawali.animation.Animation;
import rajawali.animation.Animation3D;
import rajawali.animation.RotateOnAxisAnimation;
import rajawali.lights.DirectionalLight;
import rajawali.math.vector.Vector3;
import rajawali.parser.ParsingException;
import rajawali.parser.fbx.LoaderFBX;
import rajawali.renderer.RajawaliRenderer;


/**
 * Created on 2015/8/6.
 */
public class FBXRenderer extends RajawaliRenderer {
    private Animation3D mAnim;
    private DirectionalLight directionalLight;
    public FBXRenderer(Context context) {
        super(context);
    }

    @Override
    protected void initScene() {
        directionalLight = new DirectionalLight(1f, .2f, -1.0f);
        directionalLight.setColor(1.0f, 1.0f, 1.0f);
        directionalLight.setPower(2);
        getCurrentScene().addLight(directionalLight);

        mAnim = new RotateOnAxisAnimation(Vector3.Axis.Y, 360);
        mAnim.setDurationMilliseconds(16000);
        mAnim.setRepeatMode(Animation.RepeatMode.INFINITE);
        getCurrentScene().registerAnimation(mAnim);

        try {
            // -- Model by Sampo Rask
            // (http://www.blendswap.com/blends/characters/low-poly-rocks-character/)
            LoaderFBX parser = new LoaderFBX(this,
                    R.raw.minute_hand);
            parser.parse();
            Object3D o = parser.getParsedObject();
            o.setY(-.5f);
            getCurrentScene().addChild(o);

            mAnim.setTransformable3D(o);
            mAnim.play();
        } catch (ParsingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOffsetsChanged(float v, float v1, float v2, float v3, int i, int i1) {

    }

    @Override
    public void onTouchEvent(MotionEvent motionEvent) {

    }
}

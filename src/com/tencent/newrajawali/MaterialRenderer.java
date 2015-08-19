package com.tencent.newrajawali;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;

import rajawali.animation.Animation;
import rajawali.animation.RotateOnAxisAnimation;
import rajawali.animation.TranslateAnimation3D;
import rajawali.lights.DirectionalLight;
import rajawali.lights.PointLight;
import rajawali.materials.Material;
import rajawali.materials.methods.DiffuseMethod;
import rajawali.materials.methods.SpecularMethod;
import rajawali.materials.textures.ATexture;
import rajawali.materials.textures.NormalMapTexture;
import rajawali.materials.textures.SpecularMapTexture;
import rajawali.materials.textures.Texture;
import rajawali.math.vector.Vector3;
import rajawali.primitives.Sphere;
import rajawali.renderer.RajawaliRenderer;

/**
 * Created on 2015/8/7.
 */
public class MaterialRenderer extends RajawaliRenderer {
    public MaterialRenderer(Context context) {
        super(context);
    }

    @Override
    protected void initScene() {
        PointLight pointLight = new PointLight();
        pointLight.setPower(1);
        pointLight.setPosition(-1, 1, 4);

        getCurrentScene().addLight(pointLight);

        DirectionalLight directionalLight = new DirectionalLight();
        directionalLight.setPower(2);
        directionalLight.setLookAt(new Vector3(1,0,0));

        getCurrentScene().addLight(directionalLight);

        try {
            Texture earthTexture = new Texture("earthDiffuseTex", R.drawable.earth_diffuse);

            Material material = new Material();
            material.enableLighting(true);
            material.setDiffuseMethod(new DiffuseMethod.Lambert());
            material.setSpecularMethod(new SpecularMethod.Phong(Color.WHITE, 40));
            material.addTexture(earthTexture);
            material.addTexture(new SpecularMapTexture("earthSpecularTex", R.drawable.balck_white));
//            material.addTexture(new NormalMapTexture("earthNormalTex", R.drawable.earth_normal));
            material.setColorInfluence(0);

            Sphere sphere = new Sphere(1, 32, 24);
            sphere.setMaterial(material);
            sphere.setY(1.2f);
            getCurrentScene().addChild(sphere);

            RotateOnAxisAnimation sphereAnim = new RotateOnAxisAnimation(Vector3.Axis.Y, 359);
            sphereAnim.setDurationMilliseconds(14000);
            sphereAnim.setRepeatMode(Animation.RepeatMode.INFINITE);
            sphereAnim.setTransformable3D(sphere);
            getCurrentScene().registerAnimation(sphereAnim);
            sphereAnim.play();

        } catch (ATexture.TextureException e) {
            e.printStackTrace();
        }

        TranslateAnimation3D lightAnim = new TranslateAnimation3D(
                new Vector3(-2, 3, 3), new Vector3(2, -1, 3));
        lightAnim.setDurationMilliseconds(3000);
        lightAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        lightAnim.setRepeatMode(Animation.RepeatMode.REVERSE_INFINITE);
        lightAnim.setTransformable3D(pointLight);
        getCurrentScene().registerAnimation(lightAnim);
        lightAnim.play();

        getCurrentCamera().setZ(6);
    }

    @Override
    public void onOffsetsChanged(float v, float v1, float v2, float v3, int i, int i1) {

    }

    @Override
    public void onTouchEvent(MotionEvent motionEvent) {

    }
}

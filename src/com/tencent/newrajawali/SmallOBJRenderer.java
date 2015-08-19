package com.tencent.newrajawali;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import rajawali.Object3D;
import rajawali.animation.Animation;
import rajawali.animation.Animation3D;
import rajawali.animation.TranslateAnimation3D;
import rajawali.lights.DirectionalLight;
import rajawali.lights.SpotLight;
import rajawali.math.vector.Vector3;
import rajawali.parser.LoaderOBJ;
import rajawali.parser.ParsingException;
import rajawali.postprocessing.PostProcessingManager;
import rajawali.postprocessing.effects.ShadowEffect;
import rajawali.renderer.RajawaliRenderer;

/**
 * Created on 2015/8/7.
 */
public class SmallOBJRenderer extends RajawaliRenderer {
    private DirectionalLight mLight;
    private DirectionalLight rightLight, leftLight;
    private SpotLight[] spotLights = new SpotLight[12];
    private Object3D mObjectGroup;
    private Object3D mEmpty;
    private Animation3D mCameraAnim, mLightAnim;
    private PostProcessingManager mPostProcessingManager;

    public SmallOBJRenderer(Context context) {
        super(context);
    }

    @Override
    protected void initScene() {
        mLight = new DirectionalLight();
        mLight.setDirection(0, -1, -3);
        mLight.setPower(2.5f);

        rightLight = new DirectionalLight();
        rightLight.setPosition(30, 30, 30);
        rightLight.setLookAt(0, 0, 0);
        rightLight.setPower(1.0f);

        leftLight = new DirectionalLight();
        leftLight.setPosition(-30, 30, 30);
        leftLight.setLookAt(0, 0, 0);
        leftLight.setPower(1.0f);

        getCurrentScene().addLight(mLight);
//        getCurrentScene().addLight(rightLight);
//        getCurrentScene().addLight(leftLight);

//        for (int i = 0; i < 2; i++) {
//            spotLights[i] = new SpotLight();
//            double angle = i * Math.PI / 6;
//            Log.d("angle", "" + angle);
//            spotLights[i].setPosition(40 * Math.sin(angle), 40 * Math.cos(angle), 30);
//            spotLights[i].setLookAt(0,0,0);
//            spotLights[i].setPower(0.0001f);
//            getCurrentScene().addLight(spotLights[i]);
//        }

        getCurrentCamera().setFarPlane(120);
        getCurrentCamera().setPosition(0, -28, 84);
        getCurrentCamera().setLookAt(0, 0, 0);

        try {
            LoaderOBJ objParser = new LoaderOBJ(mContext.getResources(),
                    mTextureManager, R.raw.small_disc_obj);
            objParser.parse();
            mObjectGroup = objParser.getParsedObject();
            Log.d("object tree", getChildTree(mObjectGroup));
//            long now = System.currentTimeMillis();
//            double second = (now % 60000.0) / 1000.0;
//            double minute = (now % 3600000.0) / 60000.0;
//            double hour = (now % 86400000.0) / 3600000.0 + 8;
//            Vector3 r = new Vector3(0, 0, 1);
//            mObjectGroup.getChildByName("secondHand").rotateAround(r, second * 6);
//            mObjectGroup.getChildByName("minuteHand").rotateAround(r, minute * 6);
//            mObjectGroup.getChildByName("hourHand").rotateAround(r, hour * 30);

//            Material iMaterial = new Material();
//            iMaterial.enableLighting(true);
//            iMaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
//            iMaterial.setSpecularMethod(new SpecularMethod.Phong(Color.WHITE, 40));
//            iMaterial.addTexture(new Texture("diffuse", R.drawable.i_suture_diffuse_map));
//            iMaterial.addTexture(new SpecularMapTexture("earthSpecularTex", R.drawable.i_suture_specular_map));
//            iMaterial.setColorInfluence(0);
//
//            for (int i = 1; i <= 9; i++) {
//                if (mObjectGroup.getChildByName("Igroup00" + i).getNumChildren() == 0) {
//                    mObjectGroup.getChildByName("Igroup00" + i).setMaterial(iMaterial);
//                } else {
//                    mObjectGroup.getChildByName("Igroup00" + i).getChildAt(0).setMaterial(iMaterial);
//                }
//            }
//
//            for (int i = 10; i <= 16; i++) {
//                if (mObjectGroup.getChildByName("Igroup0" + i).getNumChildren() == 0) {
//                    mObjectGroup.getChildByName("Igroup0" + i).setMaterial(iMaterial);
//                } else {
//                    mObjectGroup.getChildByName("Igroup0" + i).getChildAt(0).setMaterial(iMaterial);
//                }
//            }
//
//            Material vMaterial = new Material();
//            vMaterial.enableLighting(true);
//            vMaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
//            vMaterial.setSpecularMethod(new SpecularMethod.Phong(Color.WHITE, 40));
//            vMaterial.addTexture(new Texture("diffuse", R.drawable.v_diffuse));
//            vMaterial.addTexture(new SpecularMapTexture("earthSpecularTex", R.drawable.v_specular));
//            vMaterial.setColorInfluence(0);
//
//            for (int i = 3; i <= 9; i++) {
//                if (mObjectGroup.getChildByName("V00" + i).getNumChildren() == 0) {
//                    mObjectGroup.getChildByName("V00" + i).setMaterial(vMaterial);
//                } else {
//                    mObjectGroup.getChildByName("V00" + i).getChildAt(0).setMaterial(vMaterial);
//                }
//            }
//
//            for (int i = 10; i <= 12; i++) {
//                if (mObjectGroup.getChildByName("V0" + i).getNumChildren() == 0) {
//                    mObjectGroup.getChildByName("V0" + i).setMaterial(vMaterial);
//                } else {
//                    mObjectGroup.getChildByName("V0" + i).getChildAt(0).setMaterial(vMaterial);
//                }
//            }

//            Material smallMaterial = new Material();
//            smallMaterial.enableLighting(true);
//            smallMaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
//            smallMaterial.setSpecularMethod(new SpecularMethod.Phong(Color.WHITE, 40));
//            smallMaterial.addTexture(new Texture("diffuse", R.drawable.small_diffuse));
//            smallMaterial.addTexture(new SpecularMapTexture("earthSpecularTex", R.drawable.small_specular1));
//            smallMaterial.setColorInfluence(0);
//
//            if (mObjectGroup.getChildByName("innerCircle").getNumChildren() == 0) {
//                mObjectGroup.getChildByName("innerCircle").setMaterial(smallMaterial);
//            } else {
//                mObjectGroup.getChildByName("innerCircle").getChildAt(0).setMaterial(smallMaterial);
//            }

        } catch (ParsingException e) {
            e.printStackTrace();
//        } catch (ATexture.TextureException e) {
//            e.printStackTrace();
        }

        getCurrentScene().addChild(mObjectGroup);

        mPostProcessingManager = new PostProcessingManager(this);
        ShadowEffect shadowEffect = new ShadowEffect(getCurrentScene(), getCurrentCamera(), mLight, 2048);
        shadowEffect.setShadowInfluence(.5f);
        mPostProcessingManager.addEffect(shadowEffect);
        shadowEffect.setRenderToScreen(true);

        mEmpty = new Object3D();
        TranslateAnimation3D anim = new TranslateAnimation3D(new Vector3(-0.3, -1.4, -3), new Vector3(0.3, -0.4, -3));
        anim.setDurationMilliseconds(10000);
        anim.setRepeatMode(Animation.RepeatMode.REVERSE_INFINITE);
        anim.setTransformable3D(mEmpty);
        getCurrentScene().registerAnimation(anim);
        anim.play();

    }

    @Override
    protected void onRender(double deltaTime) {
//        Vector3 r = new Vector3(0, 0, 1);
//        mObjectGroup.getChildByName("secondHand").rotateAround(r, deltaTime * 6);
//        mObjectGroup.getChildByName("minuteHand").rotateAround(r, deltaTime / 10);
//        mObjectGroup.getChildByName("hourHand").rotateAround(r, deltaTime / 120);
        mPostProcessingManager.render(deltaTime);
        mLight.setLookAt(mEmpty.getPosition());
//        mObjectGroup.rotateAround(new Vector3(1,0,0), deltaTime * 60);
    }

    @Override
    public void onOffsetsChanged(float v, float v1, float v2, float v3, int i, int i1) {

    }

    @Override
    public void onTouchEvent(MotionEvent motionEvent) {

    }

    private String getChildTree(Object3D object) {
        StringBuilder stringBuilder = new StringBuilder();
        if (object.getNumChildren() == 0) {
            stringBuilder.append("    ");
            stringBuilder.append(object.getName());
            stringBuilder.append("\n");
        } else {
            stringBuilder.append(object.getName());
            stringBuilder.append(":{\n");
            for (int i = 0; i < object.getNumChildren(); i++) {
                stringBuilder.append("    ");
                stringBuilder.append(getChildTree(object.getChildAt(i)));
            }
            stringBuilder.append("\n}\n");
        }
        return stringBuilder.toString();
    }
}

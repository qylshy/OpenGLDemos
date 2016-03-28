package qyl.com.opengldemos;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

import qyl.com.opengldemos.renders.BlastRenderer;

/**
 * Created by qiuyunlong on 16/3/27.
 */
public class ParticleActivity extends Activity {
    /** The OpenGL View */
    private BlastRenderer glSurface;

    public Bitmap bitmap;

    /**
     * Initiate the OpenGL View and set our own Renderer
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        glSurface = new BlastRenderer(this);
        setContentView(glSurface);
    }

    /**
     * Remember to resume the glSurface
     */
    @Override
    protected void onResume() {
        super.onResume();
        glSurface.onResume();
    }

    /**
     * Also pause the glSurface
     */
    @Override
    protected void onPause() {
        super.onPause();
        glSurface.onPause();
    }

}

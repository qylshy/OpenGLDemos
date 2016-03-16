package qyl.com.opengldemos;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import javax.microedition.khronos.opengles.GL10;

import qyl.com.opengldemos.renders.IOpenGLDemo;
import qyl.com.opengldemos.renders.OpenGLRenderer;

/**
 * Created by qiuyunlong on 16/3/5.
 */
public class HelloWorld extends Activity implements IOpenGLDemo {

    protected GLSurfaceView mGLSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow()
                .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mGLSurfaceView = new GLSurfaceView(this);
        mGLSurfaceView.setRenderer(new OpenGLRenderer(this));
        setContentView(mGLSurfaceView);
    }

    @Override
    public void DrawScene(GL10 gl) {

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        // Clears the screen and depth buffer.
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT
                | GL10.GL_DEPTH_BUFFER_BIT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }
}

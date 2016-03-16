package qyl.com.opengldemos.renders;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by qiuyunlong on 16/3/5.
 */
public class OpenGLRenderer implements GLSurfaceView.Renderer {

    private final IOpenGLDemo openGLDemo;

    public OpenGLRenderer(IOpenGLDemo openGLDemo) {
        this.openGLDemo = openGLDemo;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        // Set the background color to black ( rgba ).
        gl10.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
        // Enable Smooth Shading, default not really needed.
        gl10.glShadeModel(GL10.GL_SMOOTH);
        // Depth buffer setup.
        gl10.glClearDepthf(1.0f);
        // Enables depth testing.
        gl10.glEnable(GL10.GL_DEPTH_TEST);
        // The type of depth testing to do.
        gl10.glDepthFunc(GL10.GL_LEQUAL);
        // Really nice perspective calculations.
        gl10.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                GL10.GL_NICEST);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        // Sets the current view port to the new size.
        gl10.glViewport(0, 0, width, height);
        // Select the projection matrix
        gl10.glMatrixMode(GL10.GL_PROJECTION);
        // Reset the projection matrix
        gl10.glLoadIdentity();
        // Calculate the aspect ratio of the window
        GLU.gluPerspective(gl10, 45.0f,
                (float) width / (float) height,
                0.1f, 100.0f);
        // Select the modelview matrix
        gl10.glMatrixMode(GL10.GL_MODELVIEW);
        // Reset the modelview matrix
        gl10.glLoadIdentity();

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        if (openGLDemo != null){
            openGLDemo.DrawScene(gl10);
        }
    }
}

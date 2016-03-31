package qyl.com.opengldemos;

import android.graphics.BitmapFactory;
import android.opengl.GLU;
import android.os.Bundle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import qyl.com.opengldemos.shape.Cube;
import qyl.com.opengldemos.shape.FlatColoredSquare;
import qyl.com.opengldemos.shape.Icosahedrons;
import qyl.com.opengldemos.shape.SimplePlane;
import qyl.com.opengldemos.shape.SmoothColoredSquare;
import qyl.com.opengldemos.shape.Sphere;
import qyl.com.opengldemos.shape.Square;
import qyl.com.opengldemos.shape.Star;

/**
 * Created by qiuyunlong on 16/3/27.
 */
public class SquareActivity extends HelloWorld {

    private Square mSquare1;

    private Square mSquare2;

    private Square mSquare3;

    private Cube cube;

    private SimplePlane simplePlane;

    private Icosahedrons icosahedrons;

    private Star star;

    private Sphere sphere;

    private int angle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSquare1 = new Square();
        mSquare2 = new SmoothColoredSquare();//Square();
        mSquare3 = new FlatColoredSquare();//Square();
        cube = new Cube(2f, 2f, 1f);
        simplePlane = new SimplePlane(1, 1);

        // Move and rotate the plane.
        simplePlane.z = 1.7f;
        simplePlane.rx = -65;

        // Load the texture.
        simplePlane.loadBitmap(BitmapFactory.decodeResource(getResources(),
                R.drawable.particle));

        // Add the plane to the renderer.
        //renderer.addMesh(plane);
        icosahedrons = new Icosahedrons();

        star = new Star();

        sphere = new Sphere();

    }

    @Override
    public void DrawScene(GL10 gl) {
        super.DrawScene(gl);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -10.0f);
        //initScene(gl);

        //square1
        gl.glPushMatrix();
        // Rotate square A counter-clockwise.
        gl.glRotatef(angle, 0, 1, 1);
        // Draw square A.
        star.draw(gl);
        icosahedrons.draw(gl);
        gl.glPopMatrix();

        //square2
        gl.glPushMatrix();
        gl.glRotatef(-angle, 0, 0, 1);
        // Move square B.
        gl.glTranslatef(2, 0, 0);
        // Scale it to 50% of square A
        gl.glScalef(.5f, .5f, .5f);
        // Draw square B.
        mSquare2.draw(gl);
        //sphere.draw(gl);

        //square3
        gl.glPushMatrix();
        gl.glRotatef(-angle, 0, 0, 1);
        gl.glTranslatef(2, 0, 0);
        // Scale it to 50% of square B
        gl.glScalef(.5f, .5f, .5f);
        // Rotate around it's own center.
        gl.glRotatef(angle * 10, 0, 0, 1);
        // Draw square C.
        mSquare3.draw(gl);

        gl.glPopMatrix();
        // Restore to the matrix as it was before B.
        gl.glPopMatrix();
        // Increse the angle.
        angle++;
    }

    public void initScene(GL10 gl){
        float[] amb = { 1.0f, 1.0f, 1.0f, 1.0f, };
        float[] diff = { 1.0f, 1.0f, 1.0f, 1.0f, };
        float[] spec = { 1.0f, 1.0f, 1.0f, 1.0f, };
        float[] pos = { 0.0f, 5.0f, 5.0f, 1.0f, };
        float[] spot_dir = { 0.0f, -1.0f, 0.0f, };
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glEnable(GL10.GL_LIGHTING);
        gl.glEnable(GL10.GL_LIGHT0);
        ByteBuffer abb
                = ByteBuffer.allocateDirect(amb.length*4);
        abb.order(ByteOrder.nativeOrder());
        FloatBuffer ambBuf = abb.asFloatBuffer();
        ambBuf.put(amb);
        ambBuf.position(0);
        ByteBuffer dbb
                = ByteBuffer.allocateDirect(diff.length*4);
        dbb.order(ByteOrder.nativeOrder());
        FloatBuffer diffBuf = dbb.asFloatBuffer();
        diffBuf.put(diff);
        diffBuf.position(0);
        ByteBuffer sbb
                = ByteBuffer.allocateDirect(spec.length*4);
        sbb.order(ByteOrder.nativeOrder());
        FloatBuffer specBuf = sbb.asFloatBuffer();
        specBuf.put(spec);
        specBuf.position(0);
        ByteBuffer pbb
                = ByteBuffer.allocateDirect(pos.length*4);
        pbb.order(ByteOrder.nativeOrder());
        FloatBuffer posBuf = pbb.asFloatBuffer();
        posBuf.put(pos);
        posBuf.position(0);
        ByteBuffer spbb
                = ByteBuffer.allocateDirect(spot_dir.length*4);
        spbb.order(ByteOrder.nativeOrder());
        FloatBuffer spot_dirBuf = spbb.asFloatBuffer();
        spot_dirBuf.put(spot_dir);
        spot_dirBuf.position(0);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, ambBuf);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, diffBuf);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, specBuf);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, posBuf);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION,
                spot_dirBuf);
        gl.glLightf(GL10.GL_LIGHT0, GL10.GL_SPOT_EXPONENT, 0.0f);
        gl.glLightf(GL10.GL_LIGHT0, GL10.GL_SPOT_CUTOFF, 45.0f);
        gl.glLoadIdentity();
        GLU.gluLookAt(gl, 0.0f, 4.0f, 4.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}

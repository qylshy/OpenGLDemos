package qyl.com.opengldemos;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import javax.microedition.khronos.opengles.GL10;

import qyl.com.opengldemos.shape.Cube;
import qyl.com.opengldemos.shape.FlatColoredSquare;
import qyl.com.opengldemos.shape.SimplePlane;
import qyl.com.opengldemos.shape.SmoothColoredSquare;
import qyl.com.opengldemos.shape.Square;

/**
 * Created by qiuyunlong on 16/3/27.
 */
public class SquareActivity extends HelloWorld {

    private Square mSquare1;

    private Square mSquare2;

    private Square mSquare3;

    private Cube cube;

    private SimplePlane simplePlane;

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
    }

    @Override
    public void DrawScene(GL10 gl) {
        super.DrawScene(gl);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -10.0f);

        //square1
        gl.glPushMatrix();
        // Rotate square A counter-clockwise.
        gl.glRotatef(angle, 0, 1, 1);
        // Draw square A.
        simplePlane.draw(gl);
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}

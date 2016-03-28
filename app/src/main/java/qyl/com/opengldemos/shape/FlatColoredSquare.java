package qyl.com.opengldemos.shape;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by qiuyunlong on 16/3/27.
 */
public class FlatColoredSquare extends Square {

    @Override
    public void draw(GL10 gl) {
        gl.glColor4f(0.5f, 0.5f, 1.0f, 1.0f);
        super.draw(gl);
        //gl.glColor4f(0.5f, 0.5f, 1.0f, 1.0f);
    }
}

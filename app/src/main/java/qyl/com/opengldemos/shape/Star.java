package qyl.com.opengldemos.shape;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by qiuyunlong on 16/3/30.
 */
public class Star {
    // Our vertices.
    protected float vertices[];
    // Our vertex buffer.
    protected FloatBuffer vertexBuffer;

    public Star() {
        float a = (float) (1.0f / (2.0f - 2f * Math.cos(72f * Math.PI / 180.f)));
        float bx = (float) (a * Math.cos(18 * Math.PI / 180.0f));
        float by = (float) (a * Math.sin(18 * Math.PI / 180f));
        float cy = (float) (-a * Math.cos(18 * Math.PI / 180f));
//        vertices = new float[]{
//                0, a, 0.5f, cy, -bx, by, bx, by, -0.5f, cy
//        };

        vertices = new float[]{
                0, 1, 0,
                (float)Math.cos(54 * Math.PI / 180.0f), -(float)Math.sin(54 * Math.PI / 180.0f), 0,
                -(float)Math.cos(18 * Math.PI / 180.0f), (float)Math.sin(18 * Math.PI / 180.0f), 0,
                (float)Math.cos(18 * Math.PI / 180.0f), (float)Math.sin(18 * Math.PI / 180.0f), 0,
                -(float)Math.cos(54 * Math.PI / 180.0f), -(float)Math.sin(54 * Math.PI / 180.0f), 0
        };

        ByteBuffer vbb
                = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
    }

    /**
     * This function draws our star on screen.
     *
     * @param gl
     */
    public void draw(GL10 gl) {
        // Counter-clockwise winding.
        gl.glFrontFace(GL10.GL_CCW);
        // Enable face culling.
        gl.glEnable(GL10.GL_CULL_FACE);
        // What faces to remove with the face culling.
        gl.glCullFace(GL10.GL_BACK);
        // Enabled the vertices buffer for writing
        //and to be used during
        // rendering.
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // Specifies the location and data format of
        //an array of vertex
        // coordinates to use when rendering.
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0,
                vertexBuffer);
        gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 5);
        // Disable the vertices buffer.
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        // Disable face culling.
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}

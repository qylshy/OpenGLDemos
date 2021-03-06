package qyl.com.opengldemos.es2guide.objects;

import java.util.List;

import qyl.com.opengldemos.es2guide.data.VertexArray;
import qyl.com.opengldemos.es2guide.progarms.ColorShaderProgram;
import qyl.com.opengldemos.es2guide.util.Geometry.*;
import qyl.com.opengldemos.es2guide.objects.ObjectBuilder.*;

import static android.opengl.GLES20.glDrawArrays;
import static javax.microedition.khronos.opengles.GL10.GL_POINTS;
import static qyl.com.opengldemos.es2guide.util.Constants.BYTES_PER_FLOAT;

/**
 * Created by qiuyunlong on 16/11/14.
 */

public class Mallet {

    private static final int POSITION_COMPONENT_COUNT = 3;

    public final float radius;
    public final float height;

    private final VertexArray vertexArray;
    private final List<DrawCommand> drawList;

    public Mallet(float radius, float height, int numPointsAroundMallet) {
        GeneratedData generatedData = ObjectBuilder.createMallet(new Point(0f,
                0f, 0f), radius, height, numPointsAroundMallet);

        this.radius = radius;
        this.height = height;

        vertexArray = new VertexArray(generatedData.vertexData);
        drawList = generatedData.drawList;
    }
    public void bindData(ColorShaderProgram colorProgram) {
        vertexArray.setVertexAttribPointer(0,
                colorProgram.getPositionAttributeLocation(),
                POSITION_COMPONENT_COUNT, 0);
    }
    public void draw() {
        for (DrawCommand drawCommand : drawList) {
            drawCommand.draw();
        }
    }

}

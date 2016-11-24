package qyl.com.opengldemos.es2guide.objects;


import java.util.List;

import qyl.com.opengldemos.es2guide.data.VertexArray;
import qyl.com.opengldemos.es2guide.progarms.ColorShaderProgram;
import qyl.com.opengldemos.es2guide.util.Geometry.*;
import qyl.com.opengldemos.es2guide.objects.ObjectBuilder.*;

/**
 * Created by qiuyunlong on 16/11/15.
 */

public class Puck {
    private static final int POSITION_COMPONENT_COUNT = 3;

    public final float radius, height;

    private final VertexArray vertexArray;
    private final List<DrawCommand> drawList;

    public Puck(float radius, float height, int numPointsAroundPuck) {
        GeneratedData generatedData = ObjectBuilder.createPuck(new Cylinder(
                new Point(0f, 0f, 0f), radius, height), numPointsAroundPuck);
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

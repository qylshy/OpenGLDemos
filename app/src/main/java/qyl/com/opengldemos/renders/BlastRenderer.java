package qyl.com.opengldemos.renders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import qyl.com.opengldemos.R;
import qyl.com.opengldemos.shape.Particle;

/**
 * Created by qiuyunlong on 16/3/27.
 */
public class BlastRenderer extends GLSurfaceView implements GLSurfaceView.Renderer {
    private float slowdown = 20.f; // Slow Down Particles
    private float zoom = -40.0f; // Used To Zoom Out
    private int[] texture = new int[1];// Storage For Our Particle Texture
    private Random random = new Random(); // to generate a int value randomly
    private FloatBuffer vertexBuffer; // to hold the particle's vertices
    private FloatBuffer textureBuffer; // to hold the particle's texture
    // vertices
    private float radius = 200;

    private float[] textureCoordinate = { 0.0f, 0.f, 1.0f, 0.0f, 0.0f, 1.0f,
            1.0f, 1.0f }; // the particle's texture coordinate

    private static final int MAX_PARTICLES = 400;

    private Particle[] particles = new Particle[MAX_PARTICLES];// to hold the
    private Context context;// the activity context

    private float[] temVectex = new float[12];

    /**
     * the method to generate a FloatBuffer reference with pass a float array
     * */
    public void LoadBuffer() {
        ByteBuffer vertexByteBuffer = ByteBuffer
                .allocateDirect(temVectex.length * 4);
        vertexByteBuffer.order(ByteOrder.nativeOrder());
        vertexBuffer = vertexByteBuffer.asFloatBuffer();
        vertexBuffer.put(temVectex);
        vertexBuffer.position(0);

        ByteBuffer texCoordByteBuffer = ByteBuffer
                .allocateDirect(textureCoordinate.length * 4);
        texCoordByteBuffer.order(ByteOrder.nativeOrder());
        textureBuffer = texCoordByteBuffer.asFloatBuffer();
        textureBuffer.put(textureCoordinate);
        textureBuffer.position(0);
    }

    // the particle color array
    private float colors[][] = { { 1.0f, 0.5f, 0.5f }, { 1.0f, 0.75f, 0.5f },
            { 1.0f, 1.0f, 0.5f }, { 0.75f, 1.0f, 0.5f }, { 0.5f, 1.0f, 0.5f },
            { 0.5f, 1.0f, 0.75f }, { 0.5f, 1.0f, 1.0f }, { 0.5f, 0.75f, 1.0f },
            { 0.5f, 0.5f, 1.0f }, { 0.75f, 0.5f, 1.0f }, { 1.0f, 0.5f, 1.0f },
            { 1.0f, 0.5f, 0.75f } };

    /**
     * the MyRenderer constructor
     * */
    public BlastRenderer(Context context) {
        super(context);
        this.setRenderer(this);
        this.context = context;
    }

    private final int MAX_Y = 22;
    private final int MAX_X = 10;

    /**
     * the method to initiate the particle objects
     *
     * */
    private void initParticals() {
        int color = random.nextInt(12);
        for (int i = 0; i < MAX_PARTICLES; i++) {
            particles[i] = new Particle();
            particles[i].active = true;
            particles[i].life = 1.5f;
            particles[i].fade = 0.00013f;
            particles[i].r = colors[color][0];
            particles[i].g = colors[color][1];
            particles[i].b = colors[color][2];

            particles[i].x = -color;//(float) (Math.random() * 2 * MAX_X - MAX_X);//-color;
            particles[i].y = -color;//(float) (Math.random() * 2 * MAX_Y - MAX_Y);//color;
            particles[i].z = 10.0f;

            particles[i].xi = (float) ((random.nextInt(100) % 50) - 26.0f) * 10.0f;
            particles[i].yi = -(float) ((random.nextInt(100) % 50) - 26.0f) * 10.0f;
            float result = radius * radius - particles[i].xi * particles[i].xi
                    - particles[i].yi * particles[i].yi;
            particles[i].zi =(float) Math.sqrt(result);
            if (i % 2 == 0) {
                if (particles[i].zi > 0) {
                    particles[i].zi = -particles[i].zi;
                }
            }
            particles[i].yg = -0.8f;
        }
    }

    /**
     * draws the whole particles and change the each one's attribute after draws
     * it
     * */
    private void drawParticle(GL10 gl) {
        Particle tmp = null;
        for (int i = 0; i < particles.length; i++) {
            if (particles[i].active) {
                tmp = particles[i];
                float x = tmp.x;
                float y = tmp.y;
                float z = tmp.z;
                vertexBuffer.clear();
                vertexBuffer.put(x + 0.5f);
                vertexBuffer.put(y + 0.5f);
                vertexBuffer.put(z + zoom);

                vertexBuffer.put(x + 0.5f);
                vertexBuffer.put(y - 0.5f);
                vertexBuffer.put(z + zoom);

                vertexBuffer.put(x - 0.5f);
                vertexBuffer.put(y + 0.5f);
                vertexBuffer.put(z + zoom);

                vertexBuffer.put(x - 0.5f);
                vertexBuffer.put(y - 0.5f);
                vertexBuffer.put(z + zoom);
                gl.glColor4f(tmp.r, tmp.g, tmp.b, tmp.life);
                gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
                if (Math.abs(x) > 20 || Math.abs(y) > 20 || Math.abs(z) > 20) {
                } else {
                    tmp.x += tmp.xi / (slowdown * 100);
                    tmp.y += tmp.yi / (slowdown * 100);
                    tmp.z += tmp.zi / (slowdown * 100);
                    tmp.xi += tmp.xg;
                    tmp.yi += tmp.yg;
                    tmp.zi += tmp.zg;
                    tmp.life -= tmp.fade;
                }
                particles[i] = tmp;
                if (particles[i].life < 0.0f) {
                    tmp = null;
                    initParticals();
                }
            }
        }
    }

    public void onDrawFrame(GL10 gl) {
        LoadBuffer();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
        gl.glTranslatef(0.0f, 0.0f, -5.0f);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        drawParticle(gl);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (height == 0) {
            height = 1;
        }
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 60.0f, (float) width / (float) height, 0.1f,
                200.0f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClearDepthf(1.0f);
        gl.glDisable(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texture[0]);
        loadTexture(gl, context);
        initParticals();
    }

    /**
     * the method is to load texture for the particle
     * */
    public void loadTexture(GL10 gl, Context context) {
        InputStream is = context.getResources().openRawResource(
                R.drawable.particle);
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(is);
        } finally {
            try {
                if (is != null) {
                    is.close();
                    is = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        gl.glGenTextures(1, texture, 0);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
                GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
                GL10.GL_LINEAR);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();
    }
}

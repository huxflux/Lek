/*
 * Sick.java
 *
 * Yeye, bare testings!
 *
 * huxfucks
 */
        import org.lwjgl.BufferUtils;
        import org.lwjgl.LWJGLException;
        import org.lwjgl.opengl.ContextAttribs;
        import org.lwjgl.opengl.Display;
        import org.lwjgl.opengl.DisplayMode;
        import org.lwjgl.input.Keyboard;
        import org.lwjgl.opengl.GL11;
        import org.lwjgl.opengl.GL15;
        import org.lwjgl.opengl.GL20;
        import org.lwjgl.opengl.GL30;
        import org.lwjgl.opengl.PixelFormat;
        import org.lwjgl.util.glu.GLU;

        import java.nio.FloatBuffer;

public class Sick {
            public static void main(String[] args) {
                final int       SCREEN_WIDTH = 800;
                final int       SCREEN_HEIGHT = 600;
                final int       FPS = 60;
                int             vaoId = 0;
                int             vboId = 0;
                int             vertexCount = 0;

                PixelFormat     pixelFormat = new PixelFormat();
                ContextAttribs  contextAttribs = new ContextAttribs(3, 2);

                contextAttribs.withForwardCompatible(true);
                contextAttribs.withProfileCore(true);

                try {
                    Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));
                    Display.setTitle("Perdurabo");
                    Display.create(pixelFormat, contextAttribs);
                } catch (LWJGLException e) {
                    e.printStackTrace();
                    System.exit(-1);
                }

                System.out.println(GL11.glGetString(GL11.GL_VERSION));

                GL11.glClearColor(0.4f, 0.4f, 0.9f, 0);
                GL11.glViewport(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

                float[] vertices = {-0.5f, 0.5f, 0.f,
                                    -0.5f, -0.5f, 0f,
                                    0.5f, -0.5f, 0f};

                FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
                verticesBuffer.put(vertices);
                verticesBuffer.flip();

                vertexCount = 3;

                vaoId = GL30.glGenVertexArrays();
                GL30.glBindVertexArray(vaoId);

                vboId = GL15.glGenBuffers();
                GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vaoId);
                GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesBuffer, GL15.GL_STATIC_DRAW);
                GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);

                GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

                GL30.glBindVertexArray(0);


                while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

                    GL30.glBindVertexArray(vaoId);
                    GL20.glEnableVertexAttribArray(0);

                    GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertexCount);

                    GL20.glDisableVertexAttribArray(0);
                    GL30.glBindVertexArray(0);

                    Display.sync(FPS);
                    Display.update();
                }

                GL20.glDisableVertexAttribArray(0);
                GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
                GL15.glDeleteBuffers(vboId);
                GL30.glBindVertexArray(0);
                GL30.glDeleteVertexArrays(vaoId);

                Display.destroy();
    }
}

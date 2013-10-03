/*
 * Sick.java
 *
 * Yeye, bare testings!
 *
 * huxfucks
 */

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class Sick {
    public static void main(String[] args) {
        final int       SCREEN_WIDTH = 800;
        final int       SCREEN_HEIGHT = 600;
        final int       FPS = 60;

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

        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            Display.sync(FPS);
            Display.update();
        }

        Display.destroy();
    }
}

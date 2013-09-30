/*
 * Sick.java
 *
 * Yeye, bare testings!
 *
 * huxfucks
 */

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class Sick {
    public static void main(String[] args) {
        final int       SCREEN_WIDTH = 800;
        final int       SCREEN_HEIGHT = 600;

        try {
            Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));
            Display.create();
            Display.setTitle("Aurora Borealis");
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            Display.update();
        }
        Display.destroy();
    }
}

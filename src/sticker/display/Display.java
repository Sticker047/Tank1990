package sticker.display;

import sticker.IO.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class Display {
    public static boolean created = false;
    public static JFrame window;
    public static Canvas content;

    public static BufferedImage buffer;
    public static int[] bufferData;
    public static Graphics bufferGraphics;
    public static int clearColor;

    private static BufferStrategy bufferStrategy;

    //temp
    private static float delta = 0;
    //end temp


    public static void create(int width, int height, String title, int _clearColor, int numBuffers) {
        if (created) return;

        window = new JFrame(title);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        content = new Canvas();

        Dimension size = new Dimension(width, height);
        content.setPreferredSize(size);

        window.setResizable(false);
        window.getContentPane().add(content);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();
        ((Graphics2D) bufferGraphics).setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        clearColor = _clearColor;

        content.createBufferStrategy(numBuffers);
        bufferStrategy = content.getBufferStrategy();

        created = true;
    }

    public static void clear() {
        Arrays.fill(bufferData, clearColor);
    }

    public static void swapBuffers() {
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(buffer, 0, 0, null);
        bufferStrategy.show();
    }

    public static Graphics2D getGraphics() {
        return (Graphics2D) bufferGraphics;
    }

    public static void destroy() {

        if (!created) return;

        window.dispose();
    }

    public static void setTitle(String title) {
        window.setTitle(title);
    }

    public static void addInputListener(Input listener){
        window.add(listener);
    }

}

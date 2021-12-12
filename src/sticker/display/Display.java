package sticker.display;

import javax.swing.*;
import java.awt.*;

public class Display {
    public static boolean created = false;
    public static JFrame window;
    public static Canvas content;

    public static void create(int width, int height, String title) {
        if (created) return;

        window = new JFrame(title);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        content = new Canvas() {

            public void paint(Graphics g) {
                super.paint(g);
                render(g);
            }

        };

        Dimension size = new Dimension(width, height);
        content.setPreferredSize(size);
        content.setBackground(Color.BLACK);

        window.setResizable(false);
        window.getContentPane().add(content);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);


    }

    public static void render() {
        content.repaint();
    }

    public static void render(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(400 - 50, 300 - 50, 100, 100);
    }

}

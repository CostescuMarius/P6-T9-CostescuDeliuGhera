package View;

import javax.swing.*;
import java.awt.*;

public class BackgroundHomepage extends JLabel {
    private int width, height;

    public BackgroundHomepage(int width, int height) {
        this.width = width;
        this.height = height;
        this.setBounds(0, 0, this.width, this.height);
        this.setOpaque(true);
        this.setBackground(Color.magenta);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon background = new ImageIcon("src//Resources//homepage_background.jpg");
        if (background != null) {
            g.drawImage(background.getImage(), 0, 0, this.width, this.height, this);
        }
    }
}

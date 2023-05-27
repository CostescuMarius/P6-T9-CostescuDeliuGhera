package View;

import javax.swing.;
import java.awt.;

public class BackgroundLogin extends JLabel {
    private int width, height;

    public BackgroundLogin(int width, int height) {
        this.width = width;
        this.height = height;
        this.setBounds(0, 0, this.width, this.height);
        this.setOpaque(true);
        this.setBackground(Color.magenta);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d1 = (Graphics2D) g;
        Graphics2D g2d2 = (Graphics2D) g;
        ImageIcon background = new ImageIcon("src//Resources//login_screen_image.jpg");
        if (background != null) {
            g.drawImage(background.getImage(), 0, 0, this.width, this.height, this);
        }

        g2d1.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        /g2d1.fillRect((int) ((double)this.height / 16.76f), (int) ((double)this.width / 8.15f),
                (int) ((double)this.width / 3.1f), (int) ((double)this.height / 2.79f));/

        g2d1.fillRect(50, 150, 500, 250);

        g2d2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2d2.fillRect(50, 400, 500, 100);
    }
}
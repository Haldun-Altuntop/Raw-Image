package arc.haldun.image;

import javax.swing.*;
import java.awt.*;

public class ImageHolder extends JPanel {

    private Image image;
    private int pixelSize = 1;

    public ImageHolder() {

    }

    public ImageHolder (Image image) {
        this.image = image;

        System.out.println("Yükseklik: " + image.getHeight());
        System.out.println("Genişlik: " + image.getWidth());

        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int red, green, blue;

        int index = 0;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {

                int pixel = image.getPixels()[index++];

                red = (pixel >> 16) & 0xFF;
                green = (pixel >> 8) & 0xFF;
                blue = pixel & 0xFF;

                g.setColor(new Color(red, green, blue));
                g.fillRect(x, y, pixelSize, pixelSize);
            }
        }
    }

    public void setImage(Image image) {
        this.image = image;
        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        repaint();
    }
}

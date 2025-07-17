package arc.haldun.image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image == null) return;

        Graphics2D g2d = (Graphics2D) g.create();

        int imgW = image.getWidth();
        int imgH = image.getHeight();

        // Pencere boyutu
        int panelW = getWidth();
        int panelH = getHeight();

        // Ölçek oranları
        double scaleX = (double) panelW / imgW;
        double scaleY = (double) panelH / imgH;
        double scale = Math.min(scaleX, scaleY); // Oranı koru

        // Görüntüyü ortalamak için konum
        int drawW = (int) (imgW * scale);
        int drawH = (int) (imgH * scale);
        int xOffset = (panelW - drawW) / 2;
        int yOffset = (panelH - drawH) / 2;

        // BufferedImage üret
        BufferedImage bufferedImage = new BufferedImage(imgW, imgH, BufferedImage.TYPE_INT_RGB);
        int[] pixels = image.getPixels();
        bufferedImage.setRGB(0, 0, imgW, imgH, pixels, 0, imgW);

        // Görüntüyü ölçekleyip çiz
        g2d.drawImage(bufferedImage, xOffset, yOffset, drawW, drawH, null);
        g2d.dispose();
    }


    /*
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
     */

    public void setImage(Image image) {
        this.image = image;
        //setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        repaint();
    }
}

package arc.haldun.image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame("Image Viewer");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        BufferedImage bufferedImage = ImageIO.read(
                new File("/home/haldun-altuntop/Resimler/Fav Wallpaper 1920x1080.jpg")
        );

        //Image image = Utilities.convertToImage(bufferedImage);
        //Image image = Image.read(new File("test-image"));

        ImageBundle bundle = new ImageBundle(new File("bundle"));
        //bundle.addImage(image);

        Image image = bundle.getImage(0);

        ImageHolder imageHolder = new ImageHolder(image);

        frame.setSize(new Dimension(imageHolder.getWidth(), imageHolder.getHeight()));
        frame.add(imageHolder);
        frame.pack();
        frame.setVisible(true);

        //image.save(new File("test-image"));
    }
}

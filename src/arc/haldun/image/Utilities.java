package arc.haldun.image;

import java.awt.image.BufferedImage;

public class Utilities {

    public static Image convertToImage(BufferedImage originalImg) {

        int[] data = new int[originalImg.getHeight() * originalImg.getWidth()];

        int index = 0;
        for (int y = 0; y < originalImg.getHeight(); y++) {
            for (int x = 0; x < originalImg.getWidth(); x++) {

                data[index++] = originalImg.getRGB(x, y);
            }
        }

        return new Image(
                originalImg.getWidth(),
                originalImg.getHeight(),
                data
        );
    }
}

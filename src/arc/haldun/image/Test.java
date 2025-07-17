package arc.haldun.image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;

public class Test {

    private static int imageIndex = 0;
    private static ImageBundle bundle;

    public static void main(String[] args) throws IOException {

        if (args[0].equals("add")) {
            bundle = new ImageBundle(new File(args[1]));

            BufferedImage bufferedImage = ImageIO.read(
                    new File(args[2])
            );

            bundle.addImage(Utilities.convertToImage(bufferedImage));
        }

        if (args[0].equals("view")) {
            bundle = new ImageBundle(new File(args[1]));
            startFrame();
        }





        //Image image = Utilities.convertToImage(bufferedImage);
        //Image image = Image.read(new File("test-image"));

        //bundle.addImage(image);





        //image.save(new File("test-image"));
    }

    private static void startFrame() throws IOException {

        JDialog dialog = new JDialog();
        dialog.setLocationRelativeTo(null);
        dialog.setSize(new Dimension(400, 200));
        dialog.setVisible(true);
        dialog.setTitle("Yükleniyor...");

        JFrame frame = new JFrame("Image Viewer");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        ImageBundle bundle = new ImageBundle(new File("bundle"));

        Image image = bundle.getImage(imageIndex);

        ImageHolder imageHolder = new ImageHolder(image);

        frame.setSize(new Dimension(imageHolder.getWidth(), imageHolder.getHeight()));
        frame.add(imageHolder);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);
        frame.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                int keyCode = e.getKeyCode();

                int tempIndex = imageIndex;

                if (keyCode == KeyEvent.VK_RIGHT) imageIndex++;
                else if (keyCode == KeyEvent.VK_LEFT) imageIndex--;
                else return;

                if (imageIndex < 0) {
                    imageIndex = tempIndex;
                    return;
                }

                System.out.println(imageIndex);

                try {
                    dialog.setVisible(true);

                    imageHolder.setImage(bundle.getImage(imageIndex));
                    frame.pack();
                    frame.setSize(new Dimension(imageHolder.getWidth(), imageHolder.getHeight()));
                } catch (IOException ex) {

                    if (ex instanceof EOFException) {
                        imageIndex = tempIndex;
                    } else {
                        throw new RuntimeException(ex);
                    }
                } finally {
                    dialog.setVisible(false);
                }
            }
        });

        dialog.setVisible(false);
    }
}

package arc.haldun.image;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ImageBundle {

    private final File bundleFile;

    public ImageBundle(File bundleFile) {
        this.bundleFile = bundleFile;
    }

    public void addImage(Image image) throws IOException {

        RandomAccessFile raf = new RandomAccessFile(bundleFile, "rw");

        raf.seek(raf.length());

        image.save(raf);

        raf.close();
    }

    public Image getImage(int imageIndex) throws IOException {

        RandomAccessFile raf = new RandomAccessFile(bundleFile, "r");

        Image image = null;
        for (int i = 0; i <= imageIndex; i++) {

            image = Image.read(raf);
        }

        return image;
    }
}

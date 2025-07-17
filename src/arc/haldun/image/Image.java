package arc.haldun.image;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Image {

    private int width, height;
    private int[] pixels;

    public Image(int width, int height, int[] pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public static Image read(File inputFile) throws IOException {

        RandomAccessFile raf = new RandomAccessFile(inputFile, "r");

        int width = raf.readInt();
        int height = raf.readInt();

        int pixelsSize = raf.readInt();

        int[] pixels = new int[pixelsSize];

        for (int i = 0; i < pixelsSize; i++) {
            pixels[i] = raf.readInt();
        }

        raf.close();

        return new Image(
                width,
                height,
                pixels
        );
    }

    protected static Image read(RandomAccessFile raf) throws IOException {

        int width = raf.readInt();
        int height = raf.readInt();

        int pixelsSize = raf.readInt();

        int[] pixels = new int[pixelsSize];

        for (int i = 0; i < pixelsSize; i++) {
            pixels[i] = raf.readInt();
        }

        return new Image(
                width,
                height,
                pixels
        );
    }

    public void save(File outputFile) throws IOException {

        if (!outputFile.exists()) outputFile.createNewFile();

        RandomAccessFile raf = new RandomAccessFile(outputFile, "rw");
        raf.writeInt(width);
        raf.writeInt(height);

        raf.writeInt(pixels.length);

        for (int pixel : pixels) {
            raf.writeInt(pixel);
        }

        raf.close();
    }

    protected void save(RandomAccessFile raf) throws IOException {

        raf.writeInt(width);
        raf.writeInt(height);

        raf.writeInt(pixels.length);

        for (int pixel : pixels) {
            raf.writeInt(pixel);
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[] getPixels() {
        return pixels;
    }

    public void setPixels(int[] pixels) {
        this.pixels = pixels;
    }
}

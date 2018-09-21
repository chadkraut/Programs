// Chad Krauthamer
// Written with help from Rosetta Code and wikipedia
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BilinearInterpolation {
    /* gets the 'n'th byte of a 4-byte integer */
    private static int get(int self, int n) {
        return (self >> (n * 8)) & 0xFF;
    }

    private static float linearInterpolation(float s, float e, float t) {
        return s + (e - s) * t;
    }

    private static float bilinearInterpolation(final Float c00, float c10, float c01, float c11, float tx, float ty) {
        return linearInterpolation(linearInterpolation(c00, c10, tx), linearInterpolation(c01, c11, tx), ty);
    }

    private static BufferedImage scale(BufferedImage originalImg, float scale) {
        int newWidth = (int) (originalImg.getWidth() * scale);
        int newHeight = (int) (originalImg.getHeight() * scale);
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, originalImg.getType());

        for (int x = 0; x < newWidth; ++x) {
            for (int y = 0; y < newHeight; ++y) {
                float gx = ((float) x) / newWidth * (originalImg.getWidth() - 1);
                float gy = ((float) y) / newHeight * (originalImg.getHeight() - 1);
                int gxi = (int) gx;
                int gyi = (int) gy;
                int rgb = 0;
                // get rgb of nearby pixels
                int c00 = originalImg.getRGB(gxi, gyi);
                int c10 = originalImg.getRGB(gxi + 1, gyi);
                int c01 = originalImg.getRGB(gxi, gyi + 1);
                int c11 = originalImg.getRGB(gxi + 1, gyi + 1);
                for (int i = 0; i <= 2; ++i) {
                    // get bytes
                    float b00 = get(c00, i);
                    float b10 = get(c10, i);
                    float b01 = get(c01, i);
                    float b11 = get(c11, i);
                    int ble = ((int) bilinearInterpolation(b00, b10, b01, b11, gx - gxi, gy - gyi)) << (8 * i);
                    rgb = rgb | ble;
                }
                newImage.setRGB(x, y, rgb);
            }
        }
        return newImage;
    }

    public static void main(String[] args) throws IOException {
        // path to image must be correct
        File beer = new File("~\\PATH\\TO\\IMAGE\\beer.jpg");
        BufferedImage image = ImageIO.read(beer);
        // adjust scale to resize by k
        BufferedImage image2 = scale(image, 3f);
        File beer2 = new File("Beer_3xlarger.jpg");
        ImageIO.write(image2, "jpg", beer2);
    }
}

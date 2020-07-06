package zooming_bl;

import java.awt.image.BufferedImage;
import javax.swing.JPanel;
public class ImgInp extends JPanel
{    
    private static int get(int self, int n) {
        return (self >> (n * 8)) & 0xFF;
    }
 
    private static float lerp(float s, float e, float t) {
        return s + (e - s) * t;
    }
 
    private static float blerp(final float c00, float c10, float c01, float c11, float tx, float ty) {
        return lerp(lerp(c00, c10, tx), lerp(c01, c11, tx), ty);
    }
 
    public static BufferedImage scale(BufferedImage image, float sx, float sy) {
        int newWidth = (int) (image.getWidth() * sx);
        int newHeight = (int) (image.getHeight() * sy);
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, image.getType());
        for (int x = 0; x < newWidth; ++x) {
            for (int y = 0; y < newHeight; ++y) {
                float gx = ((float) x) / newWidth * (image.getWidth() - 1);
                float gy = ((float) y) / newHeight * (image.getHeight() - 1);
                int gxi = (int) gx;
                int gyi = (int) gy;
                int rgb = 0;
                int c00 = image.getRGB(gxi, gyi);
                int c10 = image.getRGB(gxi + 1, gyi);
                int c01 = image.getRGB(gxi, gyi + 1);
                int c11 = image.getRGB(gxi + 1, gyi + 1);
                for (int i = 0; i <= 2; ++i) {
                    float b00 = get(c00, i);
                    float b10 = get(c10, i);
                    float b01 = get(c01, i);
                    float b11 = get(c11, i);
                    int ble = ((int) blerp(b00, b10, b01, b11, gx - gxi, gy - gyi)) << (8 * i);
                    rgb = rgb | ble;
                }
                newImage.setRGB(x, y, rgb);
            }
        }
        return newImage;
    }
}
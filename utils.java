import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Utils {
    public static ImageIcon flipImageHorizontally(ImageIcon icon) {
        Image originalImage = icon.getImage();
        int width = originalImage.getWidth(null);
        int height = originalImage.getHeight(null);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-width, 0);
        g2d.drawImage(originalImage, tx, null);
        g2d.dispose();

        return new ImageIcon(bufferedImage);
    }
}
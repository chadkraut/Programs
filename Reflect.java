// Chad Krauthamer

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ImageFlip extends JPanel {

    private Image beer;
    private BufferedImage bufferedImage;

    private ImageFlip() {
        // path to image mus be set correctly
        beer = new ImageIcon("~\\PATH\\TO\IMG\\Beer.jpg").getImage();
        bufferedImage =  new BufferedImage(beer.getWidth(null),
                beer.getHeight(null), BufferedImage.TYPE_INT_RGB);
    }

    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        Graphics gb = bufferedImage.getGraphics();
        gb.drawImage(beer, 0, 0, null);
        gb.dispose();

        // Horizontal flip over x, basically scale and translate using affinetransform
        AffineTransform af = AffineTransform.getScaleInstance(-1, 1);
        af.translate(-beer.getWidth(null), 0);

        // AffinetranformOp performs scaling and translation
        AffineTransformOp op = new AffineTransformOp(af,
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        bufferedImage = op.filter(bufferedImage, null);

        g2d.drawImage(beer, 10, 10, null);
        g2d.drawImage(bufferedImage, null, 290, 10);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Flip image");
        frame.add(new ImageFlip());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(570, 230);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

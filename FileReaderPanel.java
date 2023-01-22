
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;
import java.awt.*;

class FileReaderPanel extends JPanel {
    BufferedImage image1;
    BufferedImage image2;
    BufferedImage image3;
    BufferedImage image4;
    Image newImage;
    Image newImage2;
    Image newImage3;
    Image newImage4;
    Random random = new Random();

    public FileReaderPanel() {

    }

    // setting the image
    public void setImage(BufferedImage img) {
        image1 = img;
        // sets the image
        newImage = image1.getScaledInstance(230, 230, ABORT);
        repaint();

    }

    public void setImageGrayScale(BufferedImage img) {
        image2 = img;

        for (int i = 0; i < image2.getHeight(); i++) {

            for (int j = 0; j < image2.getWidth(); j++) {

                Color c = new Color(image2.getRGB(j, i));
                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);
                int gray = red + green + blue;
                Color newColor = new Color(gray, gray, gray);

                image2.setRGB(j, i, newColor.getRGB());
            }
        }
    }

    public void setImageDither(BufferedImage img) {
        image3 = img;
        double[] ditherMatrix = { 0.82, 0.35, 0.70, 0.27, 0.55, 0.63, 0.22,
                0.29, 0.39, 0.51, 0.17, 0.30, 0.45, 0.34, 0.32, 0.19, 0.54, 0.76 };

        for (int i = 0; i < image3.getWidth(); i++) {
            for (int j = 0; j < image3.getHeight(); j++) {

                int c = image3.getRGB(i, j);
                int red = (c >>> 16) & 255;
                int green = (c >>> 8) & 255;
                int blue = (c >>> 0) & 255;

                double temp = (red * 0.299 + green * 0.587 + blue * 0.114) / 255;
                int value = random.nextInt(ditherMatrix.length);
                double temp1 = ditherMatrix[value]; // generating random dither matrix of same length.

                if (temp >= temp1) {
                    image3.setRGB(i, j, 0xffffff); // 0xffffff is white
                } else {
                    image3.setRGB(i, j, 0x000000); // 0x000000 is black
                }

            }
        }
    }

    // public void autoLevel(BufferedImage image) {
    // image4 = image;

    // for (int i = 0; i < image4.getHeight(); i++) {
    // for (int j = 0; j < image4.getWidth(); j++) {

    // Color c = new Color(image2.getRGB(j, i));
    // int red = (int) (c.getRed() * 0.299);
    // int green = (int) (c.getGreen() * 0.587);
    // int blue = (int) (c.getBlue() * 0.114);
    // int Y = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
    // int U = (int) (-0.299 * red - 0.587 * green + 0.886 * blue);
    // int V = (int) (0.701 * red - 0.587 * green - 0.114 * blue);
    // red = Y + V;
    // green = U + V;
    // blue = Y + U;
    // int newColor = (red << 16) | (green << 8) | blue;
    // image.setRGB(j, i, newColor);
    // }
    // }
    // }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(newImage, 0, 0, this);
        setImageGrayScale(image1);
        newImage2 = image2.getScaledInstance(230, 230, ABORT);
        g.drawImage(newImage2, 250, 0, this);

        // super.paintComponent(g);
        // setImageGrayScale(image1);
        newImage2 = image2.getScaledInstance(230, 230, ABORT);
        g.drawImage(newImage2, 0, 250, this);
        setImageDither(image2);
        newImage3 = image3.getScaledInstance(230, 230, ABORT);
        g.drawImage(newImage3, 250, 250, this);

        // g.drawImage(newImage, 0, 500, this);
        // // setImage(image1);
        // autoLevel(image1);
        // newImage4 = image2.getScaledInstance(230, 230, ABORT);
        // g.drawImage(newImage4, 250, 500, this);

    }

}
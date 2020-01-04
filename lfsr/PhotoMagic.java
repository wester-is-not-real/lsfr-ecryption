/* *****************************************************************************
 *  Name:    Wester J. Aldarondo Torres
 *  NetID:   wester.aldarondo@upr.edu
 *  Precept: P00
 *
 *  Description: Implements a simple form of encryption for digital pictures.
 *
 **************************************************************************** */

import java.awt.Color;

public class PhotoMagic {
    // returns a transformed copy of the specified picture, using the specified
    // lfsr.
    public static Picture transform(Picture picture, LFSR lfsr) {
        // Creates new picture object and int variables that will hold a color
        // value (255, 36, etc.)
        Picture newPic = new Picture(picture);
        int red, green, blue;
        int height = newPic.height(), width = newPic.width();
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                // Extracts the color of each pixel
                Color color = newPic.get(col, row);
                red = color.getRed();
                blue = color.getBlue();
                green = color.getGreen();

                // Xor's the color values with generated LFSR
                red = red ^ lfsr.generate(8);
                green = green ^ lfsr.generate(8);
                blue = blue ^ lfsr.generate(8);

                //Replaces pixels
                newPic.set(col, row, new Color(red, green, blue));
            }
        }
        return new Picture(newPic);
    }

    // takes the name of an image file and a description of an lfsr as command-line arguments;
    // displays a copy of the image that is "encrypted" using the LFSR.
    public static void main(String[] args) {
        Picture picture = new Picture(args[0]);
        String seed = args[1];
        int tap = Integer.parseInt(args[2]);
        LFSR lfsr1 = new LFSR(seed, tap);
        Picture picture2 = transform(picture, lfsr1);
        picture2.show();

    }
}

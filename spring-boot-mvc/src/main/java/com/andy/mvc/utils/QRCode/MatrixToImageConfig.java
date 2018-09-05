package com.andy.mvc.utils.QRCode;

import java.awt.image.BufferedImage;

/**
 * <p>
 *
 * @author Leone
 **/
public final class MatrixToImageConfig {

    public static final int BLACK = 0xFF000000;
    public static final int WHITE = 0xFFFFFFFF;

    private final int onColor;
    private final int offColor;


    public MatrixToImageConfig() {
        this(BLACK, WHITE);
    }


    public MatrixToImageConfig(int onColor, int offColor) {
        this.onColor = onColor;
        this.offColor = offColor;
    }

    public int getPixelOnColor() {
        return onColor;
    }

    public int getPixelOffColor() {
        return offColor;
    }

    int getBufferedImageColorModel() {
//    return onColor == BLACK && offColor == WHITE ? BufferedImage.TYPE_BYTE_BINARY : BufferedImage.TYPE_INT_RGB;
        return BufferedImage.TYPE_INT_ARGB;
    }

}
package com.jiangxq.sphericalview;

import android.graphics.Color;

public class ColorUtils {
    /**
     * int转#ARGB
     *
     * @param color
     * @return String #ARGB
     */
    public static String convertToARGB(int color) {
        String alpha = Integer.toHexString(Color.alpha(color));
        String red = Integer.toHexString(Color.red(color));
        String green = Integer.toHexString(Color.green(color));
        String blue = Integer.toHexString(Color.blue(color));
        if (alpha.length() == 1) {
            alpha = "0" + alpha;
        }

        if (red.length() == 1) {
            red = "0" + red;
        }

        if (green.length() == 1) {
            green = "0" + green;
        }

        if (blue.length() == 1) {
            blue = "0" + blue;
        }

        return "#" + alpha + red + green + blue;
    }
    /**
     * int转#ARGB
     *
     * @param color
     * @return String #ARGB
     */
    public static int[] convertToARGBInt(int color) {
        int alpha = (Color.alpha(color));
        int red = (Color.red(color));
        int green = (Color.green(color));
        int blue = (Color.blue(color));
        int[] colors = {alpha,red,green,blue};
        return colors;
    }

    /**
     * int转#RGB
     *
     * @param color
     * @return String #RGB
     */
    public static String convertToRGB(int color) {
        String red = Integer.toHexString(Color.red(color));
        String green = Integer.toHexString(Color.green(color));
        String blue = Integer.toHexString(Color.blue(color));

        if (red.length() == 1) {
            red = "0" + red;
        }

        if (green.length() == 1) {
            green = "0" + green;
        }

        if (blue.length() == 1) {
            blue = "0" + blue;
        }

        return "#" + red + green + blue;
    }

    /**
     * string#RGB转int
     *
     * @param argb
     * @throws NumberFormatException
     */
    public static int convertToColorInt(String argb)
            throws IllegalArgumentException {

        if (!argb.startsWith("#")) {
            argb = "#" + argb;
        }

        return Color.parseColor(argb);
    }
    /**
     * string#RGB转int
     *
     * @param argb
     * @throws NumberFormatException
     */
    public static int convertToColorInt(int[] argb)
            throws IllegalArgumentException {
        return Color.argb(argb[0],argb[1],argb[2],argb[3]);
    }

}

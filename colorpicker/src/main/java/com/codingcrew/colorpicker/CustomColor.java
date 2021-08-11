package com.codingcrew.colorpicker;

public class CustomColor {
    private static final int radix = 16;
    int red;
    int green;
    int blue;

    public CustomColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public CustomColor(String hexCode) {
        convertToRGB(hexCode);
    }

    public String getHexCode() {
        return convertToHexCode();
    }

    public void convertToRGB(String hexCode) throws NumberFormatException {
        if (hexCode.startsWith("#")) {
            hexCode = hexCode.substring(1);
        }
        long col = Long.parseLong(hexCode, radix);
        this.blue = (int) (col % 100);
        col /= 100;
        this.green = (int) (col % 100);
        col /= 100;
        this.red = (int) (col % 100);
    }

    private String convertToHexCode() {
        String red = Integer.toString(this.red, radix);
        if (red.length() != 2) {
            red = red + '0';
        }
        String green = Integer.toString(this.green, radix);
        if (green.length() != 2) {
            green = green + '0';
        }
        String blue = Integer.toString(this.blue, radix);
        if (blue.length() != 2) {
            blue = blue + '0';
        }
        return "#" + red + green + blue;
    }

    public void setColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public String toString() {
        return "CustomColor{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }
}

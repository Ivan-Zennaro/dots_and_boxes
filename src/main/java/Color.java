public enum Color {
    RED,
    BLU,
    GREEN,
    PURPLE;

    public String getColoredString(String string) {
        return (
                switch (this) {
                    case BLU -> "\u001B[34m";
                    case RED -> "\u001B[31m";
                    case PURPLE -> "\u001B[35m";
                    case GREEN -> "\u001B[32m";
                }
        ) + string + "\u001B[0m";
    }

    public java.awt.Color getAwtColor() {
        return switch (this) {
            case BLU -> new java.awt.Color(59, 105, 177);
            case RED -> new java.awt.Color(244, 67, 54);
            case PURPLE -> new java.awt.Color(135, 23, 191);
            case GREEN -> new java.awt.Color(117, 181, 43);
        };
    }

    public String getRGBstring() {
        java.awt.Color awtColor = this.getAwtColor();
        return "rgb(" + awtColor.getRed() + "," + awtColor.getGreen() + "," + awtColor.getBlue() + ")";
    }
}


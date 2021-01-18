package sdm.examproject.dots_and_boxes;

public enum Color {

    RED ("\u001B[31m",new java.awt.Color(244, 67, 54)),
    BLU ("\u001B[34m",new java.awt.Color(59, 105, 177)),
    GREEN("\u001B[32m",new java.awt.Color(117, 181, 43)),
    PURPLE("\u001B[35m",new java.awt.Color(135, 23, 191));

    private String cliColorCode;
    private java.awt.Color awtColor;

    Color (String cliColorCode, java.awt.Color awtColor){
        this.cliColorCode = cliColorCode;
        this.awtColor = awtColor;
    }

    public String getColoredString(String string) {
        return cliColorCode + string + "\u001B[0m";
    }

    public java.awt.Color getAwtColor() {
        return awtColor;
    }

    public String getRGBstring() {
        return "rgb(" + awtColor.getRed() + "," + awtColor.getGreen() + "," + awtColor.getBlue() + ")";
    }
}


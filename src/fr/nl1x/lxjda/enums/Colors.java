package fr.nl1x.lxjda.enums;

public enum Colors {

    RESET(-1),
    BLACK(0),
    RED(1),
    GREEN(2),
    YELLOW(3),
    BLUE(4),
    PURPLE(5),
    CYAN(6),
    WHITE(7)
    ;

    private final int color;

    Colors(int color)
    {
        this.color = color;
    }

    public String getRegular()
    {
        if (this.color == -1)
            return "\033[0m";
        return "\033[0;3" + String.valueOf(this.color) + "m";
    }

    public String getBold()
    {
        if (this.color == -1)
            return "\033[0m";
        return "\033[1;3" + String.valueOf(this.color) + "m";
    }

    public String getUnderline()
    {
        if (this.color == -1)
            return "\033[0m";
        return "\033[4;3" + String.valueOf(this.color) + "m";
    }

    public String getBackground()
    {
        if (this.color == -1)
            return "\033[0m";
        return "\033[4" + String.valueOf(this.color) + "m";
    }

    public String getHighIntensity()
    {
        if (this.color == -1)
            return "\033[0m";
        return "\033[0;9" + String.valueOf(this.color) + "m";
    }

    public String getBoldHighIntensity()
    {
        if (this.color == -1)
            return "\033[0m";
        return "\033[1;9" + String.valueOf(this.color) + "m";
    }

    public String getBackgroundHighIntensity()
    {
        if (this.color == -1)
            return "\033[0m";
        return "\033[1;10" + String.valueOf(this.color) + "m";
    }

}

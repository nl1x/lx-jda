package fr.nl1x.lxjda.enums;

/**
 * An enum of colors for the console.
 */
public enum Colors {

    /**
     * The reset color.
     */
    RESET(-1),

    /**
     * The black color.
     */
    BLACK(0),

    /**
     * The red color.
     */
    RED(1),

    /**
     * The green color.
     */
    GREEN(2),

    /**
     * The yellow color.
     */
    YELLOW(3),

    /**
     * The blue color.
     */
    BLUE(4),

    /**
     * The purple color.
     */
    PURPLE(5),

    /**
     * The cyan color.
     */
    CYAN(6),

    /**
     * The white color.
     */
    WHITE(7)
    ;

    private final int color;

    /**
     *
     *
     * @param color The color id.
     */
    Colors(int color)
    {
        this.color = color;
    }

    /**
     * Get the regular style.
     *
     * @return the regular style.
     */
    public String getRegular()
    {
        if (this.color == -1)
            return "\033[0m";
        return "\033[0;3" + String.valueOf(this.color) + "m";
    }

    /**
     * Get the bold style.
     *
     * @return the bold style.
     */
    public String getBold()
    {
        if (this.color == -1)
            return "\033[0m";
        return "\033[1;3" + String.valueOf(this.color) + "m";
    }

    /**
     * Get the underline style.
     *
     * @return the underline style.
     */
    public String getUnderline()
    {
        if (this.color == -1)
            return "\033[0m";
        return "\033[4;3" + String.valueOf(this.color) + "m";
    }

    /**
     * Get the background style.
     *
     * @return the background style.
     */
    public String getBackground()
    {
        if (this.color == -1)
            return "\033[0m";
        return "\033[4" + String.valueOf(this.color) + "m";
    }

    /**
     * Get the high intensity style.
     *
     * @return the high intensity style.
     */
    public String getHighIntensity()
    {
        if (this.color == -1)
            return "\033[0m";
        return "\033[0;9" + String.valueOf(this.color) + "m";
    }

    /**
     * Get the bold high intensity style.
     *
     * @return the bold high intensity style.
     */
    public String getBoldHighIntensity()
    {
        if (this.color == -1)
            return "\033[0m";
        return "\033[1;9" + String.valueOf(this.color) + "m";
    }

    /**
     * Get the background high intensity style.
     *
     * @return the background high intensity style.
     */
    public String getBackgroundHighIntensity()
    {
        if (this.color == -1)
            return "\033[0m";
        return "\033[1;10" + String.valueOf(this.color) + "m";
    }

}

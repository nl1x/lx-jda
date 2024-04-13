package fr.nl1x.lxjda.logger;

import fr.nl1x.lxjda.enums.Colors;

public class Logger
{

    public static void info(String data)
    {
        System.out.println(
            Colors.CYAN.getBold()
            + "[INFO] "
            + Colors.BLACK.getHighIntensity()
            + data
            + Colors.RESET.getRegular());
    }

    public static void warn(String data)
    {
        System.out.println(
            Colors.YELLOW.getBoldHighIntensity()
            + "[WARN] "
            + Colors.BLACK.getHighIntensity()
            + data
            + Colors.RESET.getRegular()
        );
    }

    public static void success(String data)
    {
        System.out.println(
            Colors.GREEN.getBoldHighIntensity()
            + "[SUCCESS] "
            + Colors.BLACK.getHighIntensity()
            + data
            + Colors.RESET.getRegular()
        );
    }

    public static void debug(Loggable clazz, String data)
    {
        System.out.println(
            Colors.PURPLE.getBold()
            + "[DEBUG] <" + clazz.getClass().getSimpleName() + ".java> "
            + Colors.BLACK.getHighIntensity()
            + data
            + Colors.RESET.getRegular()
        );
    }

    public static void error(Loggable clazz, String data)
    {
        System.err.println(
            Colors.RED.getBold()
            + "[ERROR AT " + clazz.getClass().getSimpleName() + ".java] "
            + Colors.BLACK.getHighIntensity()
            + data
            + Colors.RESET.getRegular()
        );
    }

}

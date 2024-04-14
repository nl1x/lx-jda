package fr.nl1x.lxjda.logger;

import fr.nl1x.lxjda.enums.Colors;

/**
 * The console logger class.
 */
public class Logger
{

    /**
     * The Logger class. This class should not be instanced.
     */
    public Logger() {}

    /**
     * Send an info to the console.
     *
     * @param data The message.
     */
    public static void info(String data)
    {
        System.out.println(
            Colors.CYAN.getBold()
            + "[INFO] "
            + Colors.BLACK.getHighIntensity()
            + data
            + Colors.RESET.getRegular());
    }

    /**
     * Send a warning to the console.
     *
     * @param data The message.
     */
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

    /**
     * Send a success to the console.
     *
     * @param data The message.
     */
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

    /**
     * Send a debug to the console.
     *
     * @param clazz The class that throw this error.
     * @param data The message.
     */
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

    /**
     * Send an error to the console.
     *
     * @param clazz The class that throw this error.
     * @param data The message.
     */
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

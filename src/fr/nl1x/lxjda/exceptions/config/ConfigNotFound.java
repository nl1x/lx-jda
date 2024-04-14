package fr.nl1x.lxjda.exceptions.config;

/**
 * This error is thrown when a configuration file cannot be found.
 */
public class ConfigNotFound extends Exception
{

    /**
     * Throw a new ConfigNotFound error.
     *
     * @param message The error message.
     */
    public ConfigNotFound(String message)
    {
        super(message);
    }

}

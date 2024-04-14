package fr.nl1x.lxjda.exceptions.config;

/**
 * This error is thrown when a configuration file already exists.
 */
public class ConfigAlreadyExists extends Exception
{

    /**
     * Throw a new ConfigAlreadyExists error.
     *
     * @param message The error message.
     */
    public ConfigAlreadyExists(String message)
    {
        super(message);
    }

}

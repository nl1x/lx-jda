package fr.nl1x.lxjda.manager;

import fr.nl1x.lxjda.exceptions.config.ConfigAlreadyExists;
import fr.nl1x.lxjda.exceptions.config.ConfigNotFound;
import fr.nl1x.lxjda.logger.Logger;
import fr.nl1x.lxjda.manager.config.BotConfig;
import fr.hashtek.hashconfig.HashConfig;
import fr.nl1x.lxjda.manager.config.ConfigFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * The ConfigManager class allow you to store multiple configuration files easily.
 */
public class ConfigManager
{

    private final BotConfig botConfig;
    private final Map<String, ConfigFile> configFiles;

    /**
     * Create a new instance of the ConfigManager class.
     * <strong>You are not supposed to create a new instance of this class.</strong>
     * <strong>You can find the {@code ConfigManager} instance in your {@code LxJDA} instance methods.</strong>
     *
     * @throws IOException if the bot default yaml configuration does not load correctly.
     */
    public ConfigManager() throws IOException
    {
        botConfig = new BotConfig(
            new HashConfig(this.getClass(), "config/bot.yml", "config/bot.yml", true)
        );
        configFiles = new HashMap<String, ConfigFile>();
        configFiles.put("config/bot.yml", botConfig);
    }

    /**
     * Returns the bot default configuration.
     *
     * @return The bot default configuration.
     */
    public BotConfig getBotConfig()
    {
        return botConfig;
    }

    /**
     * Get a configuration file previously added/set.
     *
     * @param configName The name of the configuration previously saved
     *                   with {@code ConfigManager#setConfig}.
     * @return The configuration found, or {@code null} if not found.
     */
    public ConfigFile getConfig(String configName)
    {
        return configFiles.get(configName);
    }

    /**
     * Get the configuration files name previously added/set.
     *
     * @return a {@code Set<String>} of the configuration files name previously added/set.
     */
    public Set<String> getConfigsName()
    {
        return configFiles.keySet();
    }

    /**
     * Add a new configuration file to the manager.
     *
     * @param configName The name to use to save the configuration.
     * @param config The {@code HashConfig} configuration class to save.
     * @param overwrite Set it to {@code true} to overwrite the previously saved configuration,
     *                  or to {@code false} to throw a {@code ConfigAlreadyExists} exception if
     *                  a configuration with this name already exists.
     * @throws ConfigAlreadyExists if a configuration already exists with this name.
     */
    public void setConfig(String configName, ConfigFile config, boolean overwrite) throws ConfigAlreadyExists
    {
        if (configFiles.containsKey(configName) && !overwrite)
            throw new ConfigAlreadyExists("This configuration file already exists.");
        configFiles.put(configName, config);
    }

    /**
     * Remove a configuration file.
     *
     * @param configName The name of the saved configuration.
     * @throws ConfigNotFound if no configuration has been found with the {@code configName} name.
     */
    public void removeConfig(String configName) throws ConfigNotFound
    {
        if (configFiles.remove(configName) == null)
            throw new ConfigNotFound("The configuration \"" + configName + "\" does not exist.");
    }

}

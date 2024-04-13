package fr.nl1x.lxjda.manager;

import fr.nl1x.lxjda.exceptions.config.ConfigAlreadyExists;
import fr.nl1x.lxjda.exceptions.config.ConfigNotFound;
import fr.nl1x.lxjda.manager.config.BotConfig;
import fr.hashtek.hashconfig.HashConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ConfigManager
{

    private final BotConfig botConfig;
    private final Map<String, HashConfig> configFiles;

    /**
     * @throws IOException if the bot default yaml configuration does not load correctly.
     */
    public ConfigManager() throws IOException
    {
        botConfig = new BotConfig(
            new HashConfig(this.getClass(), "bot.yml", "bot.yml", true)
        );
        configFiles = new HashMap<String, HashConfig>();
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
     * @param configName The name of the configuration previously saved
     *                   with {@code ConfigManager#setConfig}.
     * @return The configuration found, or {@code null} if not found.
     */
    public HashConfig getConfig(String configName)
    {
        return configFiles.get(configName);
    }

    /**
     * @param configName The name to use to save the configuration.
     * @param config The {@code HashConfig} configuration class to save.
     * @param overwrite Set it to {@code true} to overwrite the previously saved configuration,
     *                  or to {@code false} to throw a {@code ConfigAlreadyExists} exception if
     *                  a configuration with this name already exists.
     * @throws ConfigAlreadyExists if a configuration already exists with this name.
     */
    public void setConfig(String configName, HashConfig config, boolean overwrite) throws ConfigAlreadyExists
    {
        if (configFiles.containsKey(configName) && !overwrite)
            throw new ConfigAlreadyExists("This configuration file already exists.");
        configFiles.put(configName, config);
    }

    /**
     * @param configName The name of the saved configuration.
     * @throws ConfigNotFound if no configuration has been found with the {@code configName} name.
     */
    public void removeConfig(String configName) throws ConfigNotFound
    {
        if (configFiles.remove(configName) == null)
            throw new ConfigNotFound("The configuration \"" + configName + "\" does not exist.");
    }

}

package fr.nl1x.lxjda.manager.config;

import fr.hashtek.hashconfig.HashConfig;

import java.io.IOException;

public interface ConfigFile {

    /**
     * Get the name of the corresponding config file.
     *
     * @return the name of the corresponding config file.
     */
    public String getConfigName();

    /**
     * Get the {@code HashConfig} instance of the corresponding config file.
     *
     * @return the {@code HashConfig} instance of the corresponding config file.
     */
    public HashConfig getHashConfig();

    /**
     * Reload the configuration file.
     *
     * @throws IOException if the configuration file failed to reload.
     */
    public void reload() throws IOException;

}

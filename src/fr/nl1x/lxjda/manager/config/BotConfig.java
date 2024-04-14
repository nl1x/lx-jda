package fr.nl1x.lxjda.manager.config;

import fr.hashtek.hashconfig.HashConfig;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.IOException;

/**
 * The bot default configuration class.
 */
public class BotConfig implements ConfigFile
{
    private final HashConfig configFile;
    private String token;
    private int embedDefaultColor;
    private String embedFooterIcon;
    private String embedFooterString;
    private String embedSuccessTitle;
    private String embedSuccessIcon;
    private int embedSuccessColor;
    private String embedErrorTitle;
    private String embedErrorIcon;
    private int embedErrorColor;
    private String commandErrorNotFound;
    private String commandErrorInternal;
    private String commandErrorAdminOnly;
    private long channelLogReport;
    private String configErrorReloadFailed;

    /**
     * Create an instance of the default bot configuration.
     * !! There should be only one instance of the BotConfig class !!
     *
     * @param botConfigFile The HashConfig instance of the "bot.yml" file.
     */
    public BotConfig(HashConfig botConfigFile)
    {
        this.configFile = botConfigFile;
        this.setValues();
    }

    @Override
    public void reload() throws IOException
    {
        this.configFile.reload();
        this.setValues();
    }

    @Override
    public String getConfigName()
    {
        return this.configFile.getYaml().getName();
    }

    @Override
    public HashConfig getHashConfig()
    {
        return this.configFile;
    }

    /**
     * Get the token of the discord bot.
     *
     * @return the token of the discord bot.
     */
    public String getToken()
    {
        return token;
    }

    /**
     * Get the embed footer icon url.
     *
     * @return the embed footer icon url.
     */
    public String getEmbedFooterIcon()
    {
        return embedFooterIcon;
    }

    /**
     * Get the embed footer string.
     *
     * @return the embed footer string.
     */
    public String getEmbedFooterString()
    {
        return embedFooterString;
    }

    /**
     * Get the embed success icon url.
     *
     * @return the embed success icon url.
     */
    public String getEmbedSuccessIcon()
    {
        return embedSuccessIcon;
    }

    /**
     * Get the embed error icon.
     *
     * @return the embed error icon.
     */
    public String getEmbedErrorIcon()
    {
        return embedErrorIcon;
    }

    /**
     * Get the embed default color.
     *
     * @return the embed default color.
     */
    public int getEmbedDefaultColor()
    {
        return embedDefaultColor;
    }

    /**
     * Get the embed success color.
     *
     * @return the embed success color.
     */
    public int getEmbedSuccessColor()
    {
        return embedSuccessColor;
    }

    /**
     * Get the embed error color.
     *
     * @return the embed error color.
     */
    public int getEmbedErrorColor()
    {
        return embedErrorColor;
    }

    /**
     * Get the embed success title.
     *
     * @return the embed success title.
     */
    public String getEmbedSuccessTitle()
    {
        return embedSuccessTitle;
    }

    /**
     * Get the embed error title.
     *
     * @return the embed error title.
     */
    public String getEmbedErrorTitle()
    {
        return embedErrorTitle;
    }

    /**
     * Get the command not found error message.
     *
     * @return the command not found error message.
     */
    public String getCommandErrorNotFound()
    {
        return commandErrorNotFound;
    }

    /**
     * Get the command internal error message.
     *
     * @return the command internal error message.
     */
    public String getCommandErrorInternal()
    {
        return commandErrorInternal;
    }

    /**
     * Get the command is admin-only error message.
     *
     * @return the command is admin-only error message.
     */
    public String getCommandErrorAdminOnly()
    {
        return commandErrorAdminOnly;
    }

    /**
     * Get the channel id for the log reports.
     *
     * @return the channel id for the log reports.
     */
    public long getChannelLogReport()
    {
        return channelLogReport;
    }

    /**
     * Load the values from the config file.
     */
    private void setValues()
    {
        final YamlFile yaml = this.configFile.getYaml();

        this.token = this.configFile.getEnv().get("TOKEN");

        this.embedDefaultColor = yaml.getInt("embed.default-color");
        this.embedFooterIcon = yaml.getString("embed.footer.icon");
        this.embedFooterString = yaml.getString("embed.footer.string");

        this.embedSuccessTitle = yaml.getString("embed.success.title");
        this.embedSuccessIcon = yaml.getString("embed.success.icon");
        this.embedSuccessColor = yaml.getInt("embed.success.color");

        this.embedErrorTitle = yaml.getString("embed.error.title");
        this.embedErrorIcon = yaml.getString("embed.error.icon");
        this.embedErrorColor = yaml.getInt("embed.error.color");

        this.commandErrorNotFound = yaml.getString("command.error.not-found");
        this.commandErrorInternal = yaml.getString("command.error.internal");
        this.commandErrorAdminOnly = yaml.getString("command.error.admin-only");

        this.commandErrorInternal = yaml.getString("channel.log-report");

        this.configErrorReloadFailed = yaml.getString("config.error.reload-failed");
    }

    public String getConfigErrorReloadFailed()
    {
        return configErrorReloadFailed;
    }

}

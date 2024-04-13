package fr.nl1x.lxjda.manager.config;

import fr.hashtek.hashconfig.HashConfig;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.IOException;

public class BotConfig
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
    private long channelLogReport;

    public BotConfig(HashConfig botConfigFile)
    {
        this.configFile = botConfigFile;
        this.setValues();
    }

    public void reload() throws IOException
    {
        this.configFile.reload();
        this.setValues();
    }

    public String getToken()
    {
        return token;
    }

    public String getEmbedFooterIcon()
    {
        return embedFooterIcon;
    }

    public String getEmbedFooterString()
    {
        return embedFooterString;
    }

    public String getEmbedSuccessIcon()
    {
        return embedSuccessIcon;
    }

    public String getEmbedErrorIcon()
    {
        return embedErrorIcon;
    }

    public int getEmbedDefaultColor()
    {
        return embedDefaultColor;
    }

    public int getEmbedSuccessColor()
    {
        return embedSuccessColor;
    }

    public int getEmbedErrorColor()
    {
        return embedErrorColor;
    }

    public String getEmbedSuccessTitle()
    {
        return embedSuccessTitle;
    }

    public String getEmbedErrorTitle()
    {
        return embedErrorTitle;
    }

    public String getCommandErrorNotFound()
    {
        return commandErrorNotFound;
    }

    public String getCommandErrorInternal()
    {
        return commandErrorInternal;
    }

    public long getChannelLogReport()
    {
        return channelLogReport;
    }

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

        this.commandErrorInternal = yaml.getString("channel.log-report");
    }

}

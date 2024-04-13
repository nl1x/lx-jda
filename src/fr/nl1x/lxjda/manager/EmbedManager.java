package fr.nl1x.lxjda.manager;

import fr.nl1x.lxjda.manager.config.BotConfig;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class EmbedManager
{

    private static EmbedManager instance;
    private final BotConfig botConfig;

    public EmbedManager(BotConfig botConfig)
    {
        EmbedManager.instance = this;
        this.botConfig = botConfig;
    }

    public EmbedBuilder getDefaultBuilder()
    {
        EmbedBuilder builder = new EmbedBuilder();
        String footerString = this.botConfig.getEmbedFooterString();
        String footerIcon = this.botConfig.getEmbedFooterIcon();
        int defaultColor = this.botConfig.getEmbedDefaultColor();

        builder.setFooter(footerString, footerIcon);
        builder.setColor(defaultColor);
        return builder;
    }

    public EmbedBuilder getErrorBuilder(String title, String error)
    {
        EmbedBuilder builder = this.getDefaultBuilder();
        String errorIcon = this.botConfig.getEmbedErrorIcon();
        int errorColor = this.botConfig.getEmbedErrorColor();

        builder.setColor(errorColor);
        builder.setAuthor(title, null, errorIcon);
        builder.setDescription("```" + error + "```");
        return builder;
    }

    public EmbedBuilder getErrorBuilder(String error)
    {
        return this.getErrorBuilder(this.botConfig.getEmbedErrorTitle(), error);
    }

    public MessageEmbed getError(String error)
    {
        return this.getErrorBuilder(error).build();
    }

    public static EmbedManager getInstance()
    {
        return EmbedManager.instance;
    }

}

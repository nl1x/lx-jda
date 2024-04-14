package fr.nl1x.lxjda.manager;

import fr.nl1x.lxjda.manager.config.BotConfig;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

/**
 * The EmbedManager class. It allows you to use some pre-made embeds builders.
 */
public class EmbedManager
{

    private static EmbedManager instance;
    private final BotConfig botConfig;

    /**
     * Create a new instance of the EmbedManager class.
     * (You are not supposed to create an instance of the embed manager,
     * you can find the default instance in the LxJDA class.)
     *
     * @param botConfig The bot default configuration.
     */
    public EmbedManager(BotConfig botConfig)
    {
        EmbedManager.instance = this;
        this.botConfig = botConfig;
    }

    /**
     * Get the default pre-made embed builder.
     *
     * @return the default pre-made embed builder.
     */
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

    /**
     * Get the default success embed with a custom title.
     *
     * @param title The title of the embed.
     * @return the default success embed with a custom title.
     */
    public MessageEmbed getSuccessEmbed(String title)
    {
        EmbedBuilder builder = new EmbedBuilder();
        int color = this.botConfig.getEmbedSuccessColor();
        String icon = this.botConfig.getEmbedSuccessIcon();

        builder.setAuthor(title, null, icon);
        builder.setColor(color);
        return builder.build();
    }

    /**
     * Get the embed builder of the pre-made error embed.
     *
     * @param title The title of the pre-made error embed.
     * @param error The error message that will be shown in the description of the embed.
     * @return the embed builder of the pre-made error embed.
     */
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

    /**
     * Get the embed builder of the pre-made error embed.
     *
     * @param error The error message that will be shown in the description of the embed.
     * @return the embed builder of the pre-made error embed.
     */
    public EmbedBuilder getErrorBuilder(String error)
    {
        return this.getErrorBuilder(this.botConfig.getEmbedErrorTitle(), error);
    }

    /**
     * Get a pre-made embed error with a custom message.
     *
     * @param error The error message to put in the embed description.
     * @return the fully prepared error embed.
     */
    public MessageEmbed getError(String error)
    {
        return this.getErrorBuilder(error).build();
    }

    /**
     * Get the last created instance of the embed manager class.
     *
     * @return the last created instance of the embed manager class.
     */
    public static EmbedManager getInstance()
    {
        return EmbedManager.instance;
    }

}

package fr.nl1x.lxjda.manager;

import fr.hashtek.hashconfig.HashConfig;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.simpleyaml.configuration.file.YamlFile;

/**
 * The EmbedManager class. It allows you to use some pre-made embeds builders.
 */
public class EmbedManager
{

    private static EmbedManager instance;
    private final HashConfig botConfig;

    /**
     * Create a new instance of the EmbedManager class.
     * (You are not supposed to create an instance of the embed manager,
     * you can find the default instance in the LxJDA class.)
     *
     * @param botConfig The bot default configuration.
     */
    public EmbedManager(HashConfig botConfig)
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
        YamlFile yaml = this.botConfig.getYaml();
        EmbedBuilder builder = new EmbedBuilder();
        String footerString = yaml.getString("embed.footer.string");
        String footerIcon = yaml.getString("embed.footer.icon");
        int defaultColor = yaml.getInt("embed._default.color");

        if (!footerString.startsWith("https://"))
            footerString = null;

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
        YamlFile yaml = this.botConfig.getYaml();
        EmbedBuilder builder = new EmbedBuilder();
        int color = yaml.getInt("embed.success.color");
        String icon = yaml.getString("embed.success.icon");

        if (!icon.startsWith("https://"))
            icon = null;

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
        YamlFile yaml = this.botConfig.getYaml();
        EmbedBuilder builder = this.getDefaultBuilder();
        String errorIcon = yaml.getString("embed.error.icon");
        int errorColor = yaml.getInt("embed.error.color");

        if (!errorIcon.startsWith("https://"))
            errorIcon = null;

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
        YamlFile yaml = this.botConfig.getYaml();

        return this.getErrorBuilder(yaml.getString("embed.error.title"), error);
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

package fr.nl1x.lxjda.listeners;

import fr.nl1x.lxjda.LxJDA;
import fr.nl1x.lxjda.exceptions.command.CommandExecutorError;
import fr.nl1x.lxjda.exceptions.command.UserError;
import fr.nl1x.lxjda.logger.Loggable;
import fr.nl1x.lxjda.manager.CommandManager;
import fr.nl1x.lxjda.manager.EmbedManager;
import fr.nl1x.lxjda.manager.executor.CommandExecutor;
import fr.nl1x.lxjda.logger.Logger;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;
import org.simpleyaml.configuration.file.YamlFile;

import java.util.HashMap;
import java.util.List;

/**
 * The listener that catch every command.
 * You are not supposed to create an instance of this class.
 */
public class OnCommand extends ListenerAdapter implements Loggable
{

    private final LxJDA bot;

    /**
     * Create a new instance of the listener OnCommand to handle every slash commands.
     *
     * @param bot The bot.
     */
    public OnCommand(LxJDA bot)
    {
        this.bot = bot;
    }

    /**
     * This function is called when a member execute a slash command.
     *
     * @param event The slash command interaction event.
     */
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event)
    {
        final YamlFile yaml = this.bot.getConfigManager().getBotConfig().getYaml();
        final HashMap<String, CommandExecutor> registeredCommands = CommandManager.getInstance().getRegisteredCommands();
        final CommandExecutor executor;
        final String commandName = event.getName();
        String errorMessage = null;
        Member member = event.getMember();

        if (!registeredCommands.containsKey(commandName)) {
            errorMessage = yaml.getString("error.command.notFound");
            this.onError(event, errorMessage);
            return;
        }

        try {
            executor = registeredCommands.get(commandName);
            if (executor.isAdminOnly() && member != null && !member.hasPermission(Permission.ADMINISTRATOR)) {
                errorMessage = yaml.getString("error.command.adminOnly");
                this.onError(event, errorMessage);
            }
            executor.execute(event);
        } catch (CommandExecutorError error) {
            errorMessage = yaml.getString("error.config.reloadFailed");
            this.onError(event, errorMessage);
            this.logError(event, error.getLocalizedMessage());
        } catch (UserError error) {
            this.onError(event, error.getMessage());
        }
    }

    /**
     * Reply to the event with an error.
     *
     * @param event The event that failed.
     * @param error The throwed error.
     */
    private void onError(SlashCommandInteractionEvent event, String error)
    {
        event.replyEmbeds(EmbedManager.getInstance().getError(error)).queue();
    }

    /**
     * Log the error in the {@code log-report} channel.
     *
     * @param event The event that failed.
     * @param error The throwed error message.
     */
    private void logError(SlashCommandInteractionEvent event, String error)
    {
        final YamlFile yaml = this.bot.getConfigManager().getBotConfig().getYaml();
        final TextChannel logReportChannel;
        final int logReportChannelId;
        final String guildId;
        final Guild guild;

        CommandExecutor executor = this.bot.getCommandManager().getRegisteredCommand(event.getName());
        String errorMessage = this.buildLogErrorMessage(executor.getName(), event.getOptions(), error, event.getMember());
        MessageEmbed embed = this.bot.getEmbedManager().getErrorBuilder("Command error", errorMessage).build();

        try {
            guild = event.getGuild();
            if (guild == null)
                throw new NullPointerException("Cannot log error: Guild not found.");
            guildId = guild.getId();
            logReportChannelId = yaml.getInt("id.%guild%.channel.log".replace("%guild%", guildId));
            logReportChannel = LxJDA.getJDA().getTextChannelById(logReportChannelId);
            if (logReportChannel == null)
                throw new NullPointerException("Log report channel not found. (id = " + logReportChannelId + ")");
            logReportChannel.sendMessageEmbeds(embed).queue();
        } catch (NullPointerException err) {
            Logger.error(this, err.getLocalizedMessage());
        }
    }

    /**
     * Build the log error message.
     *
     * @param command The command name that failed.
     * @param options The options sent by the user.
     * @param error The error message.
     * @param author The author of the event.
     * @return the error message.
     */
    private String buildLogErrorMessage(String command, List<OptionMapping> options, String error, Member author)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("```\n__Author:__: `");
        builder.append(author == null ? "null" : author.getEffectiveName());
        builder.append("`\n__Command:__ `");
        builder.append(command);
        builder.append("`\n__Options:__\n");
        for (OptionMapping option : options) {
            builder.append("- __");
            builder.append(option.getName());
            builder.append(":__ `");
            switch (option.getType()) {
                case UNKNOWN -> builder.append("Unknown");
                case STRING -> builder.append(option.getAsString());
                case INTEGER, NUMBER -> builder.append(option.getAsInt());
                case BOOLEAN -> builder.append(option.getAsBoolean());
                case USER -> builder.append(option.getAsUser().getAsMention());
                case CHANNEL -> builder.append(option.getAsChannel().getAsMention());
                case ROLE -> builder.append(option.getAsRole().getAsMention());
                case MENTIONABLE -> builder.append(option.getAsMentionable().getAsMention());
                case ATTACHMENT -> builder.append(option.getAsAttachment().getUrl());
            }
            builder.append("`\n");
        }
        builder.append("__Error:__\n```");
        builder.append(error);
        builder.append("\n```");
        return builder.toString();
    }

}

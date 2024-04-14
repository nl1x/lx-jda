package fr.nl1x.lxjda.manager.executor;

import fr.nl1x.lxjda.exceptions.command.CommandExecutorError;
import fr.nl1x.lxjda.exceptions.command.UserError;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code CommandExecutor} class allow you to create new commands.
 * <strong>Don't forget to register your CommandExecutor ;)</strong>
 */
public abstract class CommandExecutor
{

    private final String name;
    private final String description;
    private final boolean adminOnly;
    private SlashCommandData data = null;
    private final List<OptionData> options;

    /**
     * Create a new {@code CommandExecutor}.
     *
     * @param name The name of the command.
     * @param description The description of the command.
     * @param adminOnly {@code true} if only administrators can execute
     *                   this command, {@code false} otherwise.
     */
    public CommandExecutor(String name, String description, boolean adminOnly)
    {
        this.name = name;
        this.description = description;
        this.adminOnly = adminOnly;
        this.options = new ArrayList<>();
    }

    /**
     * Add an argument to your command.
     *
     * @param optionData The {@code OptionData} to add.
     */
    public void addOptionData(OptionData optionData)
    {
        options.add(optionData);
    }

    /**
     * Add an argument to your command.
     *
     * @param type The type of the option.
     * @param name The name of the option. (In lowercase)
     * @param description The description of the option.
     */
    public void addOption(OptionType type, String name, String description)
    {
        options.add(new OptionData(type, name.toLowerCase(), description));
    }

    /**
     * Add an argument to your command.
     *
     * @param type The type of the option.
     * @param name The name of the option. (In lowercase)
     * @param description The description of the option.
     * @param isRequired If the option is required ({@code true}) or optionnal ({@code false}).
     */
    public void addOption(OptionType type, String name, String description, boolean isRequired)
    {
        options.add(new OptionData(type, name.toLowerCase(), description, isRequired));
    }

    /**
     * Add an argument to your command.
     *
     * @param type The type of the option.
     * @param name The name of the option. (In lowercase)
     * @param description The description of the option.
     * @param isRequired If the option is required ({@code true}) or optionnal ({@code false}).
     * @param isAutoComplete If the option is auto-completed or not.
     */
    public void addOption(OptionType type, String name, String description, boolean isRequired, boolean isAutoComplete)
    {
        options.add(new OptionData(type, name.toLowerCase(), description, isRequired, isAutoComplete));
    }

    /**
     * This is the method that will be executed when the command will be executed on discord.
     *
     * @param event The slash command event containing the author and all the necessary discord information about the event.
     * @throws CommandExecutorError If there is an error in the code of the command.
     * @throws UserError If there is an error coming from the user input.
     */
    public abstract void execute(SlashCommandInteractionEvent event) throws CommandExecutorError, UserError;

    /**
     * Get the name of the command.
     *
     * @return the name of the command.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Get the description of the command.
     *
     * @return the description of the command.
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * Get the options of the command.
     *
     * @return the options of the command.
     */
    public List<OptionData> getOptions()
    {
        return options;
    }

    /**
     * Get the slash command data.
     *
     * @return the slash command data.
     */
    public SlashCommandData getSlashCommandData()
    {
        return this.data;
    }

    /**
     * Determine if the command is only executable by a member that have
     * the Administrator permission on the server.
     *
     * @return {@code true} if the command is only executable by an admin,
     *          {@code false} otherwise.
     */
    public boolean isAdminOnly()
    {
        return adminOnly;
    }

    /**
     * Set the slash command data.
     * <strong>You are not supposed to use this method.</strong>
     *
     * @param data The slash command data. (Only use it in the lx-lib !!)
     */
    public void setSlashCommandData(SlashCommandData data)
    {
        this.data = data;
    }

}

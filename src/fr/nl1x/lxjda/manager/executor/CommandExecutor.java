package fr.nl1x.lxjda.manager.executor;

import fr.nl1x.lxjda.exceptions.command.CommandExecutorError;
import fr.nl1x.lxjda.exceptions.command.UserError;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandExecutor
{

    private final String name;
    private final String description;
    private final boolean adminOnly;
    private SlashCommandData data = null;
    private final List<OptionData> options;

    /**
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
     * @param type The type of the option.
     * @param name The name of the option. (In lowercase)
     * @param description The description of the option.
     */
    public void addOption(OptionType type, String name, String description)
    {
        options.add(new OptionData(type, name.toLowerCase(), description));
    }

    /**
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
     * @param event The slash command event containing the author and all the necessary discord information about the event.
     * @throws CommandExecutorError If there is an error in the code of the command.
     * @throws UserError If there is an error coming from the user input.
     */
    public abstract void execute(SlashCommandInteractionEvent event) throws CommandExecutorError, UserError;

    /**
     * @return Get the name of the command.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * @return Get the description of the command.
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * @return Get the options of the command.
     */
    public List<OptionData> getOptions()
    {
        return options;
    }

    /**
     * @return Get the slash command data.
     */
    public SlashCommandData getSlashCommandData()
    {
        return this.data;
    }

    /**
     * @return {@code true} if the command is only executable by an admin,
     * {@code false} otherwise.
     */
    public boolean isAdminOnly()
    {
        return adminOnly;
    }

    /**
     * @param data Set the slash command data. (Only use it in the lx-lib !!)
     */
    public void setSlashCommandData(SlashCommandData data)
    {
        this.data = data;
    }

}

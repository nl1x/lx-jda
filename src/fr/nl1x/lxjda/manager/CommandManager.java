package fr.nl1x.lxjda.manager;

import fr.nl1x.lxjda.LxJDA;
import fr.nl1x.lxjda.logger.Loggable;
import fr.nl1x.lxjda.manager.executor.CommandExecutor;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandManager implements Loggable
{

    private static CommandManager instance;

    private final HashMap<String, CommandExecutor> commands;
    private final List<SlashCommandData> commandsData;

    /**
     * Create a new instance of the command manager to manager the commands.
     * Only one command manager is required. Multiple command manager can cause
     * problems on the discord server available commands.
     */
    public CommandManager()
    {
        CommandManager.instance = this;
        this.commands = new HashMap<>();
        this.commandsData = new ArrayList<>();
    }

    /**
     * @param executor The {@code CommandExecutor} command executor.
     */
    public void registerCommand(CommandExecutor executor)
    {
        String name = executor.getName();
        String description = executor.getDescription();
        SlashCommandData data = Commands.slash(name, description);

        data.addOptions(executor.getOptions());
        executor.setSlashCommandData(data);
        commands.put(name, executor);
        commandsData.add(data);
    }

    /**
     * Update the commands on discord.
     * If this function is never called, then no registered command will be
     * available on discord. Call this function to update the registered
     * commands on discord.
     */
    public void updateCommands()
    {
        LxJDA.getJDA()
            .updateCommands()
            .addCommands(commandsData)
            .queue();
    }

    /**
     * @return Get all the registered commands.
     */
    public HashMap<String, CommandExecutor> getRegisteredCommands()
    {
        return commands;
    }

    /**
     * @return Get a specific registered command executor.
     */
    public CommandExecutor getRegisteredCommand(String name)
    {
        return commands.get(name);
    }

    /**
     * @return Get the instance of the command manager.
     */
    public static CommandManager getInstance()
    {
        return CommandManager.instance;
    }

}

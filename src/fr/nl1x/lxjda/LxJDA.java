package fr.nl1x.lxjda;

import fr.nl1x.lxjda.listeners.OnCommand;
import fr.nl1x.lxjda.logger.Loggable;
import fr.nl1x.lxjda.manager.ConfigManager;
import fr.nl1x.lxjda.manager.EmbedManager;
import fr.nl1x.lxjda.manager.EventManager;
import fr.nl1x.lxjda.manager.CommandManager;
import fr.nl1x.lxjda.logger.Logger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.IOException;

/**
 * The {@code LxJDA} class allow you to easily create and manage your discord bot.
 */
public abstract class LxJDA implements Loggable
{

    private static LxJDA instance;
    private final CommandManager commandManager;
    private final ConfigManager configManager;
    private final EventManager eventManager;
    private final EmbedManager embedManager;

    private final JDABuilder builder;
    private JDA jda;

    /**
     * Create an instance of LxJDA.
     * (It is better to extend from this class than to create an instance of this class.)
     *
     * @throws IOException if the configuration manager failed to load the default bot configuration file.
     */
    public LxJDA(GatewayIntent intent, GatewayIntent... intents) throws IOException
    {
        LxJDA.instance = this;

        commandManager = new CommandManager();
        configManager = new ConfigManager();
        eventManager = new EventManager();
        embedManager = new EmbedManager(configManager.getBotConfig());

        this.builder = JDABuilder.createDefault(configManager.getBotConfig().getToken());
        this.builder.enableIntents(intent, intents);
    }

    /**
     * Start the bot.
     *
     * @throws InterruptedException CTRL+C to stop the bot.
     */
    public void startBot() throws InterruptedException
    {
        Logger.info("Starting bot...");
        jda = this.builder.build().awaitReady();

        Logger.info("Registering configuration files...");
        this.registerConfigs();

        Logger.info("Registering events...");
        this.getEventManager().registerEvents(new OnCommand(this));
        this.registerEvents();

        Logger.info("Registering commands...");
        this.registerCommands();

        Logger.info("Registering buttons...");
        this.registerButtons();

        Logger.info("Bot ready to use!");
    }

    /**
     * Get the last created instance of LxJDA.
     *
     * @return the last created instance of LxJDA.
     */
    public static LxJDA getInstance()
    {
        return LxJDA.instance;
    }

    /**
     * Get the bot JDA.
     *
     * @return the bot JDA.
     */
    public static JDA getJDA()
    {
        return LxJDA.getInstance().jda;
    }

    /**
     * Get the command manager.
     *
     * @return the command manager.
     */
    public CommandManager getCommandManager()
    {
        return this.commandManager;
    }

    /**
     * Get the configuration manager.
     *
     * @return the configuration manager.
     */
    public ConfigManager getConfigManager()
    {
        return this.configManager;
    }

    /**
     * Get the embed manager.
     *
     * @return the embed manager.
     */
    public EmbedManager getEmbedManager()
    {
        return this.embedManager;
    }

    /**
     * Get the event manager.
     *
     * @return the event manager.
     */
    public EventManager getEventManager()
    {
        return eventManager;
    }

    /**
     * Register all the events that the bot will listen to.
     */
    public abstract void registerEvents();

    /**
     * Register all the commands. (A command is an instance of a class that extends from CommandExecutor)
     */
    public abstract void registerCommands();

    /**
     * Register all the buttons that the bot will handle. (Currently not available.)
     */
    public abstract void registerButtons();

    /**
     * Register all the configuration files that the bot will use.
     */
    public abstract void registerConfigs();

}

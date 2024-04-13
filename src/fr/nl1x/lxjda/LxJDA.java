package fr.nl1x.lxjda;

import fr.nl1x.lxjda.logger.Loggable;
import fr.nl1x.lxjda.manager.ConfigManager;
import fr.nl1x.lxjda.manager.EmbedManager;
import fr.nl1x.lxjda.manager.EventManager;
import fr.nl1x.lxjda.manager.CommandManager;
import fr.nl1x.lxjda.logger.Logger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import java.io.IOException;

public abstract class LxJDA implements Loggable
{

    private static LxJDA instance;
    private final CommandManager commandManager;
    private final ConfigManager configManager;
    private final EventManager eventManager;
    private final EmbedManager embedManager;

    private final JDABuilder builder;
    private JDA jda;

    public LxJDA() throws IOException
    {
        LxJDA.instance = this;

        commandManager = new CommandManager();
        configManager = new ConfigManager();
        eventManager = new EventManager();
        embedManager = new EmbedManager(configManager.getBotConfig());

        this.builder = JDABuilder.createDefault(configManager.getBotConfig().getToken());
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

        Logger.info("Registering events...");
        this.registerEvents();

        Logger.info("Registering commands...");
        this.registerCommands();

        Logger.info("Registering buttons...");
        this.registerButtons();

        Logger.info("Bot ready to use!");
    }

    public static LxJDA getInstance()
    {
        return LxJDA.instance;
    }

    public static JDA getJDA()
    {
        return LxJDA.getInstance().jda;
    }

    public CommandManager getCommandManager()
    {
        return this.commandManager;
    }

    public ConfigManager getConfigManager()
    {
        return this.configManager;
    }

    public EmbedManager getEmbedManager()
    {
        return this.embedManager;
    }

    public EventManager getEventManager()
    {
        return eventManager;
    }

    public abstract void registerEvents();

    public abstract void registerCommands();

    public abstract void registerButtons();

}

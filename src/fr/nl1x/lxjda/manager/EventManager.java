package fr.nl1x.lxjda.manager;

import fr.nl1x.lxjda.LxJDA;
import fr.nl1x.lxjda.logger.Loggable;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * The {@code EventManger} class.
 * <strong>You are not supposed to create a new instance of this class.</strong>
 * <strong>To use this class, get the instance from your {@code LxJDA} instance.</strong>
 */
public class EventManager implements Loggable
{

    private static EventManager instance;

    /**
     * Create a new instance of the event manager.
     * (You are not supposed to create a new instance of this class.)
     */
    public EventManager()
    {
        EventManager.instance = this;
    }

    /**
     * Register a new event.
     *
     * @param events The event to register.
     */
    public void registerEvents(ListenerAdapter... events)
    {
        for (ListenerAdapter event : events)
            LxJDA.getJDA().addEventListener(event);
    }

    /**
     * Unregister a previously registered event.
     *
     * @param events The event to unregister.
     */
    public void unregisterEvents(ListenerAdapter... events)
    {
        for (ListenerAdapter event : events)
            LxJDA.getJDA().removeEventListener(event);
    }

    /**
     * Get the last created instance of the event manager class.
     *
     * @return the last created instance of the event manager class.
     */
    public static EventManager getInstance()
    {
        return EventManager.instance;
    }

}

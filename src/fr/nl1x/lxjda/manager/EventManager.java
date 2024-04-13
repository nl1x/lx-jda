package fr.nl1x.lxjda.manager;

import fr.nl1x.lxjda.LxJDA;
import fr.nl1x.lxjda.logger.Loggable;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventManager implements Loggable
{

    private static EventManager instance;

    public EventManager()
    {
        EventManager.instance = this;
    }

    /**
     * @param events The event to register.
     */
    public void registerEvents(ListenerAdapter... events)
    {
        for (ListenerAdapter event : events)
            LxJDA.getJDA().addEventListener(event);
    }

    /**
     * @param events The event to unregister.
     */
    public void unregisterEvents(ListenerAdapter... events)
    {
        for (ListenerAdapter event : events)
            LxJDA.getJDA().removeEventListener(event);
    }

    /**
     * @return Get the event manager instance.
     */
    public static EventManager getInstance()
    {
        return EventManager.instance;
    }

}

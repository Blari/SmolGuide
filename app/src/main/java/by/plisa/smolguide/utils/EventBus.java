package by.plisa.smolguide.utils;

import com.squareup.otto.Bus;

/**
 * Created by aruba on 16.07.2015.
 */
public class EventBus extends Bus{
    private final static EventBus bus = new EventBus();

    public static Bus getInstance() { return bus; }

    private EventBus() {}
}

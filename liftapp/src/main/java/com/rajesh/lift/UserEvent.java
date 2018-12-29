package com.rajesh.lift;

import org.springframework.context.ApplicationEvent;

public class UserEvent extends ApplicationEvent {
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Event event;
 
    public UserEvent(Object source, Event event) {
        super(source);
        this.event = event;
    }
 
    public Event getEvent() {
        return event;
    }
}
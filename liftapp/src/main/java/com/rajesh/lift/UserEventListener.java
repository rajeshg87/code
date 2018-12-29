package com.rajesh.lift;

import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
 
@Component
public class UserEventListener{
 
	@Autowired
	Lift lift;
	
	@Autowired
	ExecutorService executorService;
	
    @EventListener
    public void handleUserEvent(UserEvent event) {
       int floor = event.getEvent().getFloor();
       lift.getEvent()[floor] = new Event(floor, true);
       
       Runnable task = () -> operationCall(floor);
       executorService.execute(task);
       
    }

	private void operationCall(int floor) {
		lift.move(floor);
	}
}

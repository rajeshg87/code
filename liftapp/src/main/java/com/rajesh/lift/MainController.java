package com.rajesh.lift;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	Lift lift;
	
	@RequestMapping(value="/floor/{floor}", method=RequestMethod.GET)
	public Lift processRequest(@PathVariable("floor") String floor) {
		if(NumberUtils.isNumber(floor) && NumberUtils.createInteger(floor) < 5) {
			Event event=new Event(NumberUtils.createInteger(floor), true);
			UserEvent userEvent=new UserEvent(this, event);
			publisher.publishEvent(userEvent);
		}else {
			System.out.println("Invalid Request !!!");
		}
		return lift;
	}

}

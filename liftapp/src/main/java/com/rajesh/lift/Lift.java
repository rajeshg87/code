package com.rajesh.lift;

import lombok.Data;

@Data
public class Lift {
	private int floor;
	private boolean up;
	private boolean down;
	private Event[] event=new Event[5];
	
	public void move(int userRequest) {
		if(userRequest > floor) {
			moveup(userRequest);
		}else if(userRequest < floor) {
			moveDown(userRequest);
		}
		
		int nextAvailableRequest = getNextRequestAvailable();
		if(nextAvailableRequest > 0) {
			move(nextAvailableRequest);
		}
	}

	private int getNextRequestAvailable() {
		for(Event e:event) {
			if(e.isProcess()) {
				return e.getFloor();
			}
		}
		return -1;
	}

	private void moveup(int userRequest) {
		for(int i=(floor+1);i<event.length;i++) {
			up = true;
			operationCall("Up", i);
			if(event[i].process) {
				System.out.println("Reached Floor :"+i);
				floor = i;
				up = false;
				event[i].process = false;
				System.out.println(toString());
				break;
			}
		}
	}
	
	private void moveDown(int userRequest) {
		for(int i=(floor-1);i>=0;i--) {
			down = true;
			operationCall("Down", i);
			if(event[i].process) {
				System.out.println("Reached Floor :"+i);
				floor = i;
				down = false;
				event[i].process = false;
				break;
			}
		}
	}
	
	private void operationCall(String direction, int floor) {
		for(int i=0;i<3;i++) {
			try {
				System.out.println(Thread.currentThread().getName()+" : Moving "+direction+"= "+floor);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

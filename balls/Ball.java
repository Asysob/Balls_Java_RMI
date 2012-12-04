package balls;

import java.io.Serializable;

public class Ball implements Serializable {
	public Ball () {
		counter = 0;
	}

	public void catchMe ( String who ) {
		counter++;
		System.out.println("I, the ball, have been catched by " + who);
		System.out.println("It was my " + counter +  ".th catch");
	}

	private int counter;
}
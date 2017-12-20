package namtran.mygift.fireworks;

import android.graphics.Point;

import java.io.Serializable;

public class PointAndTime implements Serializable {

	private static final long serialVersionUID = 585287230828819925L;

	private Point point;
	private double time;
	
	public PointAndTime(Point p, double t) {
		this.point = p;
		this.time = t;
	}

	public Point getPoint() {
		return point;
	}

	public double getTime() {
		return time;
	}

}

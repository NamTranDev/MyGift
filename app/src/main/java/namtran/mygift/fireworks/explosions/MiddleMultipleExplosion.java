package namtran.mygift.fireworks.explosions;

import android.graphics.Point;

import java.util.ArrayList;

import namtran.mygift.fireworks.Firework;

public class MiddleMultipleExplosion implements Explosion{

	private static final long serialVersionUID = 8989617932428706426L;
	
	@Override
	public ArrayList<Firework> explode(Firework f) {
		ArrayList<Firework> list = new ArrayList<Firework>();
		Point p = f.getPosition();
		double v = f.getV();
		double theta = f.getTheta();	
		int color = f.getColor();
		double ttl = f.getTtl()-1;
		
		list.add(new Firework(p.x, p.y, v, theta - 65, 
				color, ttl, new EndMultipleExplosion(), null));
		list.add(new Firework(p.x, p.y, v, theta - 30, 
				color, ttl, new EndMultipleExplosion(), null));
		list.add(new Firework(p.x, p.y, v, theta - 10, 
				color, ttl, new EndMultipleExplosion(), null));
		list.add(new Firework(p.x, p.y, v, theta + 10, 
				color, ttl, new EndMultipleExplosion(), null));
		list.add(new Firework(p.x, p.y, v, theta - 30, 
				color, ttl, new EndMultipleExplosion(), null));
		list.add(new Firework(p.x, p.y, v, theta + 65, 
				color, ttl, new EndMultipleExplosion(), null));

		return list;
	}

}

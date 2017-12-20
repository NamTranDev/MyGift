package namtran.mygift.fireworks.explosions;

import android.graphics.Point;

import java.util.ArrayList;

import namtran.mygift.fireworks.Colors;
import namtran.mygift.fireworks.Firework;
import namtran.mygift.fireworks.Trail;

public class SpiderExplosion implements Explosion {
	
	private static final long serialVersionUID = 4891254427504810123L;

	//private Random generator = new Random();
	
	@Override
	public ArrayList<Firework> explode(Firework f) {
		ArrayList<Firework> list = new ArrayList<Firework>();
		Point p = f.getPosition();
		
		for (int i = 0; i < 50; i++) {
			list.add(new Firework(p.x, p.y, (double) random.nextInt(90),
				(double) random.nextInt(359), Colors.getColor(), 5, null, new Trail(2.5)));
		}
		return list;
	}


}

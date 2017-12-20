package namtran.mygift.fireworks.explosions;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Random;

import namtran.mygift.fireworks.Colors;
import namtran.mygift.fireworks.Firework;

public class RandomExplosion implements Explosion {

	private static final long serialVersionUID = -1898321283326190984L;
	
	@Override
	public ArrayList<Firework> explode(Firework f) {
		Random generator = new Random();
		ArrayList<Firework> list = new ArrayList<Firework>();
		Point p = f.getPosition();

		for (int i = 0; i < 65; i++) {
			list.add(new Firework(p.x, p.y, (double) generator.nextInt(90),
					(double) generator.nextInt(359), Colors.getColor(), generator.nextDouble()+ 2, null,
					null));
		}

		return list;
	}


}

package namtran.mygift.fireworks.explosions;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Random;

import namtran.mygift.fireworks.Colors;
import namtran.mygift.fireworks.Firework;

public class ThreeCircleExplosion implements Explosion {

	private static final long serialVersionUID = 4373323008408893605L;

	private Random generator = new Random();

	@Override
	public ArrayList<Firework> explode(Firework f) {
		ArrayList<Firework> list = new ArrayList<Firework>();
		Point p = f.getPosition();
		double v = f.getV();

		int randomColor1 = Colors.getColor();
		int randomColor2 = Colors.getColor();
		int randomColor3 = Colors.getColor();

		for (int i = 0; i < 65; i++) {
			list.add(new Firework(p.x, p.y, v - 40, (double) generator
					.nextInt(360), randomColor1, f.getTtl() + 2, null, null));
		}
		for (int i = 0; i < 65; i++) {
			list.add(new Firework(p.x, p.y, v - 20, (double) generator
					.nextInt(360), randomColor2, f.getTtl() + 2, null, null));
		}
		for (int i = 0; i < 65; i++) {
			list.add(new Firework(p.x, p.y, v, (double) generator.nextInt(360),
					randomColor3, f.getTtl() + 2, null, null));
		}
		return list;
	}

}

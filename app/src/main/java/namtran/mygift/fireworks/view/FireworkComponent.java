package namtran.mygift.fireworks.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import namtran.mygift.fireworks.Firework;
import namtran.mygift.fireworks.World;

public class FireworkComponent extends View {

	private Paint paint;
	private World world;
	private Canvas canvas;
	private Bitmap backgroundImg;
	private Bitmap foregroundImg;
	private BitmapFactory.Options options;

	public FireworkComponent(Context context) {
		super(context);
		init();
	}

	public FireworkComponent(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public FireworkComponent(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init(){
		world = new World();
		paint = new Paint(Color.BLACK);

		options = new BitmapFactory.Options();
		options.inPurgeable = true;

//		backgroundImg = BitmapFactory.decodeResource(getResources(), R.drawable.background, options);
//		foregroundImg = BitmapFactory.decodeResource(getResources(), R.drawable.foreground, options);

//		setBackgroundDrawable(new BitmapDrawable(backgroundImg));
	}

	public void paintForeground() {
	    paint.setXfermode( new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
		canvas.drawBitmap(foregroundImg, null, new Rect(0, 0, this.getWidth(), this.getHeight()), paint);
	}
	
	@Override
	protected void onDraw(Canvas c) {
		//Debug.startMethodTracing("FWonDraw");
		this.canvas = c;
		super.onDraw(canvas);
		canvas.translate(0, this.getHeight());

		world.tick();
		drawFireworks();

		canvas.translate(0, -this.getHeight());
//		paintForeground();
		//Debug.stopMethodTracing();
	}

	private void drawFireworks() {
		for (int i = 0; i < world.getFireworks().size(); i++) {
			drawFirework(world.getFireworks().get(i));
		}
	}

	public void drawFirework(Firework f) {
		f.drawFirework(canvas, paint);
		if (f.getTrail() != null) {
			f.drawTrail(canvas, paint);
		}
	}

	public World getWorld() {
		return world;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
		
}

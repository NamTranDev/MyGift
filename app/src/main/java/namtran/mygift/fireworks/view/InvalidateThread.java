package namtran.mygift.fireworks.view;



public class InvalidateThread extends Thread {

	private FireworkComponent component;
	
	public InvalidateThread( FireworkComponent component ) {
		this.component = component;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				component.postInvalidate();
				Thread.sleep(6);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}

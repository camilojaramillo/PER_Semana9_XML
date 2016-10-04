import processing.core.PApplet;

public class Ejecutable extends PApplet{
	
	
	Logica log;
	
	public static void main(String[] args) {
		PApplet.main("Ejecutable");
	}

	@Override
	public void settings() {
		super.settings();
		size(500, 500);
	}
	
	@Override
	public void setup() {
		log= new Logica(this);
	}
	
	@Override
	public void draw() {
	background(255);
	log.pintar();
	}
	
	
	
}


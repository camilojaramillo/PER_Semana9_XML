import processing.core.PApplet;

public class Ejecutable extends PApplet {
	
	Logica log;
	
	public void setup(){
		size(700,700);
		log = new Logica(this);
	}
	
	public void draw(){
		background(255);
	}
}

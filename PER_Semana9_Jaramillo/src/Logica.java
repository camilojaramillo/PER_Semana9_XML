import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import dibujable.Bola;
import processing.core.PApplet;

public class Logica implements Observer {
	
	private PApplet app;
	private Comunicacion com;
	private ArrayList <Bola> bolas;
	private Base bd;
	
	public Logica(PApplet app){
		this.app=app;
		com = new Comunicacion();
		com.addObserver(this);
		new Thread(com).start();
		
	}

	public void pintar(){
		app.noStroke();
		for (Bola bola: bolas){
			app.fill(bola.getR(), bola.getG(), bola.getB());
			app.ellipse(bola.getX(), bola.getY(), bola.getTam(), bola.getTam());
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}

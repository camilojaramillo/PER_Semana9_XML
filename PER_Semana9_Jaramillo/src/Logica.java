import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import mensaje.Mensaje;
import processing.core.PApplet;

public class Logica implements Observer {
	
	private PApplet app;
	private Comunicacion com;
	private ArrayList <Mensaje> bolas;
	private Base bd;
	
	public Logica(PApplet app){
		this.app=app;
		com = new Comunicacion();
		bd = new Base("data"+File.separator+"bolas.xml");
		com.addObserver(this);
		bolas = bd.bolasNuevas();
		new Thread(com).start();
		
	}

	public void pintar(){
		app.noStroke();
		if(bolas!=null){
			for (Mensaje bola : bolas) {
				app.fill(bola.getR(), bola.getG(), bola.getB());
				app.ellipse(bola.getX(), bola.getY(), bola.getTam(), bola.getTam());
			}
		}
	}
	
	@Override
	public void update(Observable obs, Object obj) {
		Mensaje bola = (Mensaje) obj;
		bd.agregar(bola);
		bolas.add(bola);
	}
	
}

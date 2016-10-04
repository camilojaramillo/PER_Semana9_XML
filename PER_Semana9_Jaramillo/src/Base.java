import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import mensaje.Mensaje;
import processing.data.XML;

public class Base {

	private XML xml;
	private String ruta;
	
	public Base(String ruta){
		this.ruta = ruta;
		File file = new File(ruta);
		
		if(file.exists()){
			try{
				xml = new XML (file);
			} catch (IOException e){
				e.printStackTrace();
			} catch(ParserConfigurationException e){
				e.printStackTrace();
			} catch (SAXException e){
				e.printStackTrace();
			}
		} else {
			xml = new XML ("bolas");
		}
	}
	
	public void agregar (Mensaje b){
		XML bolas = new XML ("bola");
		bolas.setInt("x", b.getX());
		bolas.setInt("y",b.getY());
		bolas.setInt("tam", b.getTam());
		bolas.setInt("r", b.getR());
		bolas.setInt("g", b.getG());
		bolas.setInt("b", b.getB());
		
		xml.addChild(bolas);
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run(){
				//System.out.println("Cerrando la app");
				xml.save(new File(ruta));
			}	
		});
	}
	
	public ArrayList<Mensaje> bolasNuevas(){
		ArrayList<Mensaje> list = new ArrayList<Mensaje>();
		XML[] bolas = xml.getChildren("bola");
		
		for(int i = 0; i < bolas.length; i++) {
			int x = bolas[i].getInt("x");
			int y = bolas[i].getInt("y");
			int tam = bolas[i].getInt("tam");
			int r = bolas[i].getInt("r");
			int g = bolas[i].getInt("g");
			int b = bolas[i].getInt("b");
			
			Mensaje bolasNuevas = new Mensaje (x,y,tam,r,g,b);
			list.add(bolasNuevas);
		}
		return list;
	}
}

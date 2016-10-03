import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import dibujable.Bola;
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
			xml = new XML ("bola");
		}
	}
	
	public void agregar (Bola b){
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
				xml.save(new File(ruta));
			}	
		});
	}
	
	public ArrayList<Bola> bolasNuevas(){
		ArrayList<Bola> list = new ArrayList<Bola>();
		XML[] bolas = xml.getChildren("bola");
		
		for(int i = 0; i < bolas.length; i++) {
			int x = bolas[i].getInt("x");
			int y = bolas[i].getInt("y");
			int tam = bolas[i].getInt("tam");
			int r = bolas[i].getInt("r");
			int g = bolas[i].getInt("g");
			int b = bolas[i].getInt("b");
			
			Bola bolasNuevas = new Bola (x,y,tam,r,g,b);
			list.add(bolasNuevas);
		}
		return list;
	}
}

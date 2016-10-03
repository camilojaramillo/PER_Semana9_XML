import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Observable;

import dibujable.Bola;

public class Comunicacion extends Observable implements Runnable {

	private MulticastSocket ms;
	private int puerto = 5000;
	private final String ip = "224.20.21.22";
	private InetAddress ia;
	private DatagramPacket dp;
	
	public Comunicacion(){
		try {
			puerto = 5000;
			byte [] buffer = new byte [1024];
			ia = InetAddress.getByName(ip);
			ms = new MulticastSocket (puerto);
			ms.joinGroup(ia);
			dp = new DatagramPacket(buffer, buffer.length);
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public DatagramPacket recibir() throws IOException {
		byte[] buffer = new byte [1024];
		DatagramPacket data = new DatagramPacket(buffer, buffer.length);
		ms.receive(data);
		return data;
	}
	
	@Override
	public void run() {

		while (true) {
			try {
				DatagramPacket pack = recibir();
				Object recibir = deserializar(pack.getData());
				
				if(recibir != null) {
					if (recibir instanceof Bola){
						Bola bola = (Bola) recibir;
						int x = bola.getX();
						int y = bola.getY();
						int tam = bola.getTam();
						int r = bola.getR();
						int g = bola.getG();
						int b = bola.getB();
						
						setChanged();
						notifyObservers(bola);
						clearChanged();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private Object deserializar(byte[] bytes){
		Object ob = null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			ob = ois.readObject();

			// close streams
			ois.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ob;
	}
}

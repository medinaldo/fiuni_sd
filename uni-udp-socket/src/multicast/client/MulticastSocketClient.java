package multicast.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MulticastSocketClient {

	final static String INET_ADDR = "224.0.0.3";
	final static int PORT = 8888;

	public static void main(String[] args) throws UnknownHostException {
		InetAddress address = InetAddress.getByName(INET_ADDR);
		byte[] buf = new byte[256];

		int count = new Random().nextInt(5);
		count = 0 == count ? 1 : count;

		try (MulticastSocket clientSocket = new MulticastSocket(PORT)) {
			clientSocket.joinGroup(address);
			System.out.println("Cliente#"+args[0] +" se unio al grupo");
			System.out.println("Cantidad de mensaje a recibir: "+count);
			while (count > 0) {
				DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
				clientSocket.receive(msgPacket);
				List<String> list = new ArrayList<String>();

				String msg = new String(buf, 0, buf.length);
				System.out.println("Mensaje Recibido: " + msg);
				
								count--;
			}
			clientSocket.leaveGroup(address);
			System.out.println("Cliente#"+args[0] +" salio del grupo");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

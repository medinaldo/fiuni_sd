package server.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import utils.Configuration;
import utils.Logger;

public class ServerUDP {
	public static void main(String[] args) {
		try {
			System.out.println("Servidor sockets UDP");
			System.out.print("Creando socket udp servidor[puerto: " + Configuration.getPort().shortValue() + "]...");

			final DatagramSocket socket = new DatagramSocket(Configuration.getPort());
			System.out.println("ok");

			final byte[] message = new byte[1000];
			boolean fin = false;

			while (!fin) {

				final DatagramPacket dPacket = new DatagramPacket(message, message.length);
				socket.receive(dPacket);

				final String msgReceive = new String(message);
				System.out.println("Mensaje recibido en el servidor: ".concat(msgReceive));

				byte[] respuestaClient = dPacket.getData();

				final DatagramPacket dpresponse = new DatagramPacket(respuestaClient, dPacket.getLength(), dPacket.getAddress(), dPacket.getPort());
				socket.send(dpresponse);
			}
			System.out.print("Bajando el servidor...");
			socket.close();
			System.out.println("ok");
		} catch (SocketException e) {
			Logger.logException(e);
		} catch (IOException e) {
			Logger.logException(e);
		}

	}

}

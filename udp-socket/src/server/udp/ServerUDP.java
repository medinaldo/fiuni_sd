package server.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.List;

import utils.Configuration;
import utils.Logger;

public class ServerUDP {
	public static void main(String[] args) {
		try {
			System.out.println("Servidor sockets UDP");

			System.out.print("Creando socket udp servidor en el puerto " + Configuration.getPort() + "...");
			// final DatagramSocket socket = new DatagramSocket(5500);
			final DatagramSocket socket = new DatagramSocket(Configuration.getPort());
			System.out.println("ok");

			// final byte[] message = new byte[1000];
			final byte[] message = new byte[Configuration.getMessageSize()];

			boolean fin = false;

			final List<String> shutdownCommand = Configuration.getShutDownCommand();

			while (!fin) {

				final DatagramPacket dPacket = new DatagramPacket(message, message.length);
				socket.receive(dPacket);

				final String msgReceive = new String(message);
				System.out.println("Mensaje recibido en el servidor: ".concat(msgReceive));
				System.out.println("Mensaje recibido desde : " + dPacket.getAddress() + " puerto:" + dPacket.getPort());

				byte[] respuestaClient = null;
				if (shutdownCommand.contains(msgReceive.trim())) {
					fin = true;
					respuestaClient = "El servidor se apagara".getBytes();
				}

				respuestaClient = fin ? respuestaClient : dPacket.getData();

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

package client.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import utils.Configuration;
import utils.Logger;

public class ClientUDP {

	public static void main(String[] args) {

		final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Cliente socket UDP");

		try {
			System.out.print("Creando socket udp cliente [puerto: " + Configuration.getPort() + "]...");
			final DatagramSocket ds = new DatagramSocket();
			System.out.println("ok");

			System.out.println("Capturando direccion host...");
			final InetAddress host = InetAddress.getByName("localhost");
			System.out.println("ok");

			System.out.println("Introduce mensajes a enviar");
			final String mensaje = in.readLine();

			final byte[] mensajeBytes = mensaje.getBytes();

			final DatagramPacket dPacket = new DatagramPacket(mensajeBytes, mensaje.length(), host, Configuration.getPort());
			ds.send(dPacket);

			final byte[] bf = new byte[1000];
			final DatagramPacket dpresponse = new DatagramPacket(bf, bf.length);
			ds.receive(dpresponse);
			System.out.println("respuesta del servidor: " + new String(dpresponse.getData()));

			ds.close();

		} catch (SocketException e) {
			Logger.logException(e);
		} catch (UnknownHostException e) {
			Logger.logException(e);
		} catch (IOException e) {
			Logger.logException(e);
		}
	}

}

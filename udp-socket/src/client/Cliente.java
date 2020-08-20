package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Cliente {

	public static void main(String[] args) {
		try {
			final DatagramSocket ds = new DatagramSocket();
			final byte[] msg = "hola mundo".getBytes();

			final InetAddress host = InetAddress.getByName("localhost");
			final int port = 5055;

			final DatagramPacket dp = new DatagramPacket(msg, args[0].length(), host, port);
			ds.send(dp);// no bloqueante

			final byte[] bf = new byte[1000];
			final DatagramPacket dpresponse = new DatagramPacket(bf, bf.length);
			ds.receive(dpresponse);// bloqueante
			System.out.println("respuesta" + new String(dpresponse.getData()));

			ds.close();

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

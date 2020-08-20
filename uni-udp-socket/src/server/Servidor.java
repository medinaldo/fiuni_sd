package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Servidor {
	public static void main(String[] args) {
		try {
			final DatagramSocket ds = new DatagramSocket(5051);
			// ds.setSoTimeout(timeout)
			final byte[] bf = new byte[1000];

			while (true) {
				final DatagramPacket dp = new DatagramPacket(bf, bf.length);

				ds.receive(dp);
				System.out.println("servidor recivio: " + dp.getData());

				final DatagramPacket dpresponse = new DatagramPacket(dp.getData(), dp.getLength(), dp.getAddress(), dp.getPort());

				ds.send(dpresponse);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

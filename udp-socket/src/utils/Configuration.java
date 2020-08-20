package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Configuration {
	private final static String HOST = "host";
	private final static String PORT = "port";
	private final static String MESSAGESIZE = "message.size";
	private final static String SHUTDOWN = "server.down";

	private static String _host = "";
	private static Integer _port = 0;
	private static Integer _messageSize = 0;
	private static List<String> _shutdownCommand = new ArrayList<String>();

	static {
		try {
			final Properties config = new Properties();
			final FileInputStream in = new FileInputStream(
					"configuration.properties");
			config.load(in);

			for (Object o : config.keySet()) {
				final String key = o.toString();
				final String value = config.getProperty(key);

				if (key.equalsIgnoreCase(HOST)) {
					_host = value;
				} else if (key.equalsIgnoreCase(PORT)) {
					_port = Integer.valueOf(value);
				} else if (key.equalsIgnoreCase(MESSAGESIZE)) {
					_messageSize = Integer.valueOf(value);
				} else if (key.equalsIgnoreCase(SHUTDOWN)) {
					_shutdownCommand.addAll(Arrays.asList(value.split("\\s")));
				}

			}

			in.close();
		} catch (FileNotFoundException e) {
			Logger.logException(e);
		} catch (IOException e) {
			Logger.logException(e);
		}
	}

	public static String getHost() {
		return _host;
	}

	public static Integer getPort() {
		return _port;
	}

	public static Integer getMessageSize() {
		return _messageSize;
	}

	public static List<String> getShutDownCommand() {
		return _shutdownCommand;
	}

}

package utils;

public class Logger {
	public static void logException(Exception e) {
		System.err.println(e.getMessage());
		System.exit(1);
	}
}

package alb.util.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
	protected static BufferedReader kbd = new BufferedReader(
			new InputStreamReader(System.in));
	
	public static void println() {
		System.out.println();
	}

	public static void println(Object obj) {
		System.out.println( obj.toString() );
	}

	public static void println(String string) {
		System.out.println(string);
	}

	public static void print(String string) {
		System.out.print(string);
	}

	public static void printf(String format, Object... args) {
		System.out.printf(format, args);	
	}

	public static Integer readInt() {
		try {
			
			return Integer.parseInt(kbd.readLine());
			
		} catch (NumberFormatException nfe) {
			return null;
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	public static Long readLong() {
		try {
			
			return Long.parseLong(kbd.readLine());
			
		} catch (NumberFormatException nfe) {
			return null;
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	public static Double readDouble() {
		try {
			
			return Double.parseDouble(kbd.readLine());
			
		} catch (NumberFormatException nfe) {
			return null;
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	public static String readString() {
		try {
			return kbd.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String readString(String msg) {
		print(msg + ": ");
		return readString();
	}

	public static Long readLong(String msg) {
		print(msg + ": ");
		return readLong();
	}

	public static Integer readInt(String msg) {
		print(msg + ": ");
		return readInt();
	}

	public static double readDouble(String msg) {
		print(msg + ": ");
		return readDouble();
	}

}

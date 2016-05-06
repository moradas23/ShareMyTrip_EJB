package alb.util.log.adapter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import alb.util.log.LogLevel;

public abstract class BaseLogger {
	private int logLevel = LogLevel.WARN;
	private String pattern = "%s - [%s] - %s(): %s";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

	public void debug(Throwable e) {
		if (logLevel > LogLevel.DEBUG) return;
		log(LogLevel.DEBUG, e);
	}

	public void error(Throwable e) {
		if (logLevel > LogLevel.ERROR) return;
		log(LogLevel.ERROR, e);
	}

	public void info(Throwable e) {
		if (logLevel > LogLevel.INFO) return;
		log(LogLevel.INFO, e);
	}

	public void trace(Throwable e) {
		if (logLevel > LogLevel.TRACE) return;
		log(LogLevel.TRACE, e);
	}

	public void warn(Throwable e) {
		if (logLevel > LogLevel.WARN) return;
		log(LogLevel.WARN, e);
	}

	public void debug(String msg, Object... args) {
		if (logLevel > LogLevel.DEBUG) return;
		log(LogLevel.DEBUG, String.format(msg, args));
	}

	public void error(String msg, Object... args) {
		if (logLevel > LogLevel.ERROR) return;
		log(LogLevel.ERROR, String.format(msg, args));
	}

	public void info(String msg, Object... args) {
		if (logLevel > LogLevel.INFO) return;
		log(LogLevel.INFO, String.format(msg, args));
	}

	public void trace(String msg, Object... args) {
		if (logLevel > LogLevel.TRACE) return;
		log(LogLevel.TRACE, String.format(msg, args));
	}

	public void warn(String msg, Object... args) {
		if (logLevel > LogLevel.WARN) return;
		log(LogLevel.WARN, String.format(msg, args));
	}

	public void setLogLevel(int level) {
		LogLevel.assertValidValue( level );
		
		logLevel = level;
	}

	protected void log(int level, Throwable e) {
		log(level, stackTraceAsString(e) );
	}

	protected String stackTraceAsString(Throwable e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter( sw );
		e.printStackTrace( pw );
		
		String res = sw.toString();
		pw.close();
		return res;
	}

	protected void log(int level, String msg) {
		String date = dateFormatter.format( new Date() );
		String levelStr = LogLevel.toString(level);
		String clazz = getLoggingClassInfo();
		
		print( String.format( pattern, date, levelStr, clazz, msg) );
	}
	
	protected abstract void print(String line);

	/**
	 * Returns info about the class and method of the logging class (the object's
	 * class which is calling this Log.xxx() method) 
	 * 
	 * @return
	 */
	private String getLoggingClassInfo() {
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		StackTraceElement loggingClass = findLoggingClass( stack );
		return loggingClass.getClassName() + "." + loggingClass.getMethodName();
	}
	
	/**
	 * Returns the StackTraceElement for the stack entry corresponding to the 
	 * object's method that is calling the Log.xxx() method.
	 * 
	 * Finds it by exploring the call stack from top until it finds
	 * the first class that is outside the package of this logging framework.
	 * 
	 * @param stack
	 * @return the StackTraceElement
	 */
	private StackTraceElement findLoggingClass(StackTraceElement[] stack) {
		String thisPackagePrefix = "alb.util.log";
		for(int i = 1 /* starts with ONE */; i < stack.length; i++) {
			StackTraceElement ste = stack[i];
			if (! ste.getClassName().startsWith( thisPackagePrefix )) {
				return ste;
			}
		}
		return null;
	}

}

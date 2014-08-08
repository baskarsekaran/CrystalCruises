package crystalcrusiestestng;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CallingClassName {

	/**
	 * @param args
	 */
	public String print() {
		String className = this.getClass().getSimpleName();
		System.out.println(className);
		// TODO Auto-generated method stub
		StringWriter sw = new StringWriter();  
		PrintWriter pw = new PrintWriter(sw);  
		new Throwable().printStackTrace(pw);  
		String stackTrace = sw.toString(); 
		System.out.println(stackTrace);
		return stackTrace;

	}

}

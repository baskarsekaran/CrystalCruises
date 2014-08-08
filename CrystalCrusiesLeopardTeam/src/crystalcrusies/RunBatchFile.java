package crystalcrusies;

import java.io.IOException;

public class RunBatchFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		final Runtime rt = Runtime.getRuntime();
		try {
		Process p = rt.exec("cmd /C F:\\Selenium Training\\software\\lib\\StartHub.bat");
		} catch (final IOException e) {
		throw new RuntimeException("Failed to run bat file.");
		}

	}

}

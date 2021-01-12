package largeFileSplit;

import java.io.IOException;

public class WorkerThread implements Runnable {

	private String filePath;

	public WorkerThread(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " Start file = " + filePath);
		processCommand(filePath);
		System.out.println(Thread.currentThread().getName() + " End filePath");
	}

	private void processCommand(String filePath)  {
		try {
			FileManager.sortedFileContent(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

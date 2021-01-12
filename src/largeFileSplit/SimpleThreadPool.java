package largeFileSplit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleThreadPool {

	public static void main(String[] args) {
		int nblines = 292;
		String originalFilePath = "src/ressource/example.csv";
		List<String> listFilesPath = new ArrayList<>();
		try {
			// Spit the File, we can use linux command !			
			listFilesPath = FileManager.split(originalFilePath, nblines);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int nbJobs = listFilesPath.size();

		if(nbJobs == 0) {
			//logger
			System.out.println("listFilesPath is empty");
		} else {
			// create thread pool where threads count equals to jobs count
			ExecutorService executor = Executors.newFixedThreadPool(3);
			for (String filepath : listFilesPath) {
				Runnable worker = new WorkerThread(filepath);
				executor.execute(worker);
			}
			executor.shutdown();
			while (!executor.isTerminated()) {
			}
		}

		// main thread think join() ?
		System.out.println("All threads Finished");

	}

}

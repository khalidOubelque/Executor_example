package largeFileSplit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileManager {

	public static List<String> split(String filePath, int nbline) throws IOException {
		String line = "";
		File csvFile = new File(filePath);
		PrintWriter writer;
		StringBuilder sb = new StringBuilder();
		List<String> filesPath = new ArrayList<>();
		String generatedFileName = "";
		int i = 0;
		if (csvFile.isFile()) {
			// create BufferedReader and read data from csv
			BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
			while ((line = csvReader.readLine()) != null) {
				String[] data = line.split(",");
				sb.append(data[0]);
				sb.append(',');
				sb.append(data[1]);
				sb.append("\n");
				i++;
				// each file has nbline lines
				if (i % nbline == 0) {
					generatedFileName = filePath + i + ".csv";
					filesPath.add(generatedFileName);
					writer = new PrintWriter(new File(generatedFileName));
					writer.write(sb.toString());
					writer.close();
					sb = new StringBuilder();
				}
			}
			csvReader.close();
		} else {
			throw new FileNotFoundException("File not found");
		}
		return filesPath;
	}

	/**
	 * ReadAll Lines and store them in a list
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static List<String> readAllLinesFromFile(String path) throws IOException {
		List<String> aList = new ArrayList<>();
		FileReader fileReader = new FileReader(path);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			aList.add(line);
		}
		bufferedReader.close();
		return aList;
	}

	public static void writeToCsvFile(String filePath, List<Data> data) throws IOException {
		FileWriter writer = new FileWriter(filePath);

	    data.forEach(p ->  {
			try {
				writer.write(p.toString());
				writer.write("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	    writer.close();
	}
	
	public static void sortedFileContent(String filePath) throws IOException {
		List<Data> listDataSorted = new ArrayList<>();
		List<String> allLines = readAllLinesFromFile(filePath);
		for (String line : allLines) {
			String[] columns = line.split(",");
			Data s = new Data(columns[0], columns[1]);
			listDataSorted.add(s);
		}
		Collections.sort(listDataSorted, new SirenComparator());
		writeToCsvFile(filePath, listDataSorted);
	}
	
//	public List<Data> sortFileContent(List<String> filesPath) throws IOException {
//		List<Data> listDataSorted = new ArrayList<>();
//		for (String filePath : filesPath) {
//			List<String> allLines = readAllLinesFromFile(filePath);
//			for (String line : allLines) {
//				String[] columns = line.split(",");
//				Data s = new Data(columns[0], columns[1]);
//				listDataSorted.add(s);
//			}
//			Collections.sort(listDataSorted, new SirenComparator());
//		}
//		return listDataSorted;
//	}

}

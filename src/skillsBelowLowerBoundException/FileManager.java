package skillsBelowLowerBoundException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {
	
	public static void createEmptyFile() {
		Path filePath = Paths.get("./output.txt");
		try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {   
		    System.out.println("./output.txt will be empty");
		} catch (IOException e) {
			System.out.println("!!! PROBLEM WITH THE OUTPUT FILE !!!\n" + e.getMessage());
		}
	}

	public static void printLines(List<String> lines) {
		Path filePath = Paths.get("./output.txt");
		try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {   
		    writer.write(lines.stream().reduce((sum,currLine) ->  sum + "\n"  + currLine).get());
		    System.out.println("text saved in ./output.txt");
		} catch (IOException e) {
			System.out.println("!!! PROBLEM WITH THE OUTPUT FILE !!!\n" + e.getMessage());
		}
	}
	
	public static List<String> getLines(String path) {
		try (Stream<String> stream = Files.lines(Paths.get(path))) {
			return stream.collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	public static List<Ride> splitLines(List<String> lines){
		List<Ride> out = new ArrayList<Ride>();
		StringTokenizer st;
		int a, b, x, y, s, f;
		for(String line : lines){
			st = new StringTokenizer(line);
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			f = Integer.parseInt(st.nextToken());
			
			out.add(new Ride(a, b, x, y, s, f));
		}
		return out;
	}
	
}


package me.vital1ks.lab6.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class DotHandler {
	
	private static String fileName = "C:\\Users\\Vitalya\\eclipse-workspace\\lab6\\src\\main\\java\\me\\vital1ks\\lab6\\resources\\dots.txt";
	public static ArrayList<int[]> getDots(){
		BufferedReader bufferedReader;
		ArrayList<int[]> dots = new ArrayList<>();
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));
			String line = bufferedReader.readLine();
			while(line != null) {
				String[] coords = line.split(";");
				int[] dot = {Integer.parseInt(coords[0]),Integer.parseInt(coords[1])};
				dots.add(dot);
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dots;
	}
	public static void generateRandomDots() {
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(fileName));
			Random random = new Random();
			for(int i = 0; i< 100; i++) {
				bufferedWriter.write(random.nextInt(600)+";"+random.nextInt(600));
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

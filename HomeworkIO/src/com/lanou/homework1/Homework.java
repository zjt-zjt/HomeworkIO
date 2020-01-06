package com.lanou.homework1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Homework {
	public static void main(String[] args) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		try {
			FileReader fr = new FileReader("C:\\Users\\admin\\Desktop\\Intput.java");
			FileWriter fw = new FileWriter("C:\\Users\\admin\\Desktop\\a.txt");
			br = new BufferedReader(fr);
			bw = new BufferedWriter(fw);
			char[] arr = new char[br.read()];
			
	       for( int i =0;i<arr.length;) {
			String string =br.readLine();
			if(string ==null) {
				break;
			}else {
				bw.write(i+"."+string);
				bw.newLine();
				i++;
			}
			
	} 
		
		
			
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(bw!=null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
	}

}

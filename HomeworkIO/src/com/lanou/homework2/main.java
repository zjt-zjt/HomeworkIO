package com.lanou.homework2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.TreeMap;

public class main {

	public static void main(String[] args) {
		
			FileOutputStream fos = null;
			ObjectOutputStream oos = null;
			try {
				fos = new FileOutputStream("C:\\Users\\admin\\Desktop\\h.txt");
				 oos = new ObjectOutputStream(fos);
//				Map<Integer,Integer> map = new TreeMap<>();
//				map.put(1, 2);
			
				 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(fos!=null) {
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(oos!=null) {
					try {
						oos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

	}

	}

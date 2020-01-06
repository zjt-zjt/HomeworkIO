package com.lanou.homework1;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Set;
import java.util.TreeMap;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class AddressBook {
	 private Map<String, List<Contact>> map = new TreeMap<>();
	   private Pinyin4j py = new Pinyin4j();
		
	   public AddressBook() {
		   super();
		   fileRead();
	   }
	   
	   //增加
	   public void  addContact(Contact c) {
		   String group = c.getGroup();
		  List<Contact>  list= map.get(group);
		   if(list==null) {
			   list = new ArrayList<Contact>();
			   list.add(c);
			   map.put(group, list);
		   }else {
			   list.add(c);
		   }
	   }
		
	   //删除
	   
	   public void deleteContact(String name) throws BadHanyuPinyinOutputFormatCombination {
		String group = py.toPinYinUppercaseInitials(name);
		   List<Contact> list = map.get(group);
		   if(list!=null) {
		   Iterator<Contact> it = list.iterator();
		   while(it.hasNext()) {
			   Contact c = it.next();
			   if(c.getName().equals(name)) {
				   it.remove();
			   }
		   }
		   
		   if(list.size()==0) {
			   map.remove(group);
		   }
	}  
	}
	   
	   public void modify(String name, Contact c) throws BadHanyuPinyinOutputFormatCombination {
		deleteContact(name);
		addContact(c);
	}
	   
	   public List<Contact>  findContactsByGroup(String group){
		   return map.get(group);
		   
		
	}
	   public List<Contact> findCotactsByName(String name){
			List<Contact> result = new ArrayList<>();
			Set<Entry<String, List<Contact>>> s = map.entrySet();	
			Iterator<Entry<String,List<Contact>>> it = s.iterator();
			while(it.hasNext()) {
				Entry<String,List<Contact>> entry = it.next();
				List<Contact> list = entry.getValue();
				for(Contact c : list) {
					if(c.getName().contains(name) == true) {
						result.add(c);
					}
				}
			}
			return result.size() == 0 ? null : result;
		}
	   
	   
	   public List<Contact> findCotactsByPhone(String phone){
			List<Contact> result = new ArrayList<>();
			Set<Entry<String, List<Contact>>> s = map.entrySet();	
			Iterator<Entry<String,List<Contact>>> it = s.iterator();
			while(it.hasNext()) {
				Entry<String,List<Contact>> entry = it.next();
				List<Contact> list = entry.getValue();
				for(Contact c : list) {
					if(c.getPhone().contains(phone) == true) {
						result.add(c);
					}
				}
			}
			return result.size() == 0 ? null : result;
		}
	   
	   public void showAllContact() {
		   System.out.println(".................");
		   Set<String> groups = map.keySet();
		for (String group : groups) {
			System.out.println(group);
			List <Contact> list = map.get(group);
			
			for (Contact c: list) {
				System.out.println("    "+c.toString());
			}
		}
	}
	   
	   public void showContact(List<Contact> list) {
		   if(list==null) {
			   System.out.println("无该分组");
			   return ; 
						
		   }
		 System.out.println(".............."); 
		 for (Contact contact : list) {
			System.out.println("    "+contact.toString());
		}
		 
	}

	@Override
	public String toString() {
		return map.toString();
	}
	   
	public  String ToJSONString() {
		JSONObject object = new JSONObject();
		object.put("Contact", map);
		String string = JSON.toJSONString(object);
		return string;
	}

public  void fileWrite() {
	   BufferedWriter bw = null;
	  
	try {
		FileWriter fw = new FileWriter("C:\\Users\\admin\\Desktop\\a.txt");
		 bw = new BufferedWriter(fw);
		 JSONObject object = new JSONObject();
	     object.put("Contact", map);
	     String string = JSON.toJSONString(object);
		 bw.write(string);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
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

  public void fileRead(){
	  File file = new File("C:\\Users\\admin\\Desktop\\a.txt");
	 if(file.exists()==true) {
		 BufferedReader br = null;
		 try {
			FileReader fr = new FileReader(file);
			 br = new BufferedReader(fr);
			 String str = null;
			 String all = "";
			 char[] arr = new char[1024];
			 while ((str = br.readLine())!=null) {
				all+=str;
				
			}
			 
			JSONObject obj  = (JSONObject)JSON.parseObject(all).get("contact");
			Map<String, Object> m =  obj.getInnerMap();
			for(String group:m.keySet()) {
				JSONArray array =(JSONArray) m.get(group);
				List<Contact> list = array.toJavaList(Contact.class);
				map.put(group, list);
			}
		} catch (IOException e) {
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
		}
	 }
}


	
}

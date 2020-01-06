package com.lanou.homework2;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class AdressBook {
	 private Map<String, List<Contact>> map = new TreeMap<>();
	   private Pinyin4j py = new Pinyin4j();
		
	   public AdressBook() {
		   super();
		   initData() ;
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
	   
	public String ToJSONString() {
		JSONObject object = new JSONObject();
		object.put("Contact", map);
		String string = JSON.toJSONString(object);
		return string;
	}


	public void objectOutputStream() {
		
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fos = new FileOutputStream("C:\\Users\\admin\\Desktop\\h.txt");
			 oos = new ObjectOutputStream(fos);
//			JSONObject object =new JSONObject();
//			object.put("contact", map);
			 oos.writeObject(map );
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
		
		
	public void initData() {
		File file = new File("C:\\Users\\admin\\Desktop\\h.txt");
		if(file.exists() == true) {
			ObjectInputStream ois = null;
			try {
				FileInputStream fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);
				map = (TreeMap<String, List<Contact>>) ois.readObject();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(ois != null) {
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	
	
	
		
	}
	




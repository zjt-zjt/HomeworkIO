package com.lanou.homework2;


import java.util.List;
import java.util.Scanner;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Test {
	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
		Scanner scanner  = new Scanner(System.in);
		AdressBook addressBook = new AdressBook();
		boolean exit =false;
		while(exit==false) {
			Menu();
			int c = choose();
			
			switch (c) {
			case 1:{
				System.out.println("请输入姓名");
				String name = scanner.nextLine();
				System.out.println("请输入手机号");
				String phone = scanner.nextLine();
				Contact a = new Contact(name, phone);
				addressBook.addContact(a);
				
				
				break;
			}
			case 2:{
				addressBook.showAllContact();
				
				break;
			}	
			case 3:{
				System.out.println("一组查询");
				String group = scanner.nextLine();
				List<Contact> list = addressBook.findContactsByGroup(group);
				addressBook.showContact(list);
				
				break;
			}
			case 4:{
				System.out.println("按名字查找");
				String name = scanner.nextLine();
				List<Contact> list = addressBook.findContactsByGroup(name);
				addressBook.showContact(list);
				
				break;
			}
			case 5:{
				System.out.println("按号码查找");
				String number = scanner.nextLine();
				List<Contact> list = addressBook.findContactsByGroup(number);
				addressBook.showContact(list);
				break;
			}
			case 6:{
				System.out.println("添加新的名字和手机号");
				String name = scanner.nextLine();
				System.out.println("原来名字");
				String newName = scanner.nextLine();
				System.out.println("添加新的名字");
				String phone = scanner.nextLine();
				Contact h = new Contact(newName, phone);
				addressBook.modify(name, h);
				System.out.println();
				
				break;
			}
			case 7:{
				System.out.println("按名字删除");
				String name = scanner.nextLine();
				addressBook.deleteContact(name);
				break;
			}
			case 8:{
				System.out.println("您要退出吗Y/N");
				String string = scanner.next();
				if(string.equalsIgnoreCase("Y")) {
				exit = 	true;
				addressBook.objectOutputStream();
				}
				break;
			}
			
			}
		}
		

	}

	
	public static void Menu() {
		System.out.println(".............通讯录.............");
		System.out.println("1.添加联系人");
		System.out.println("2.显示联系人");
		System.out.println("3.按组名查找");
		System.out.println("4.按名字查找");
		System.out.println("5.按号码查找");
		System.out.println("6.修改联系人");
		System.out.println("7.删除");
		System.out.println("8.退出");
	}
	
	public static int choose() {
		System.out.println("请输入1-8的选择");
		Scanner scanner  = new Scanner(System.in);
		int choose = scanner.nextInt();
		while(true) {
			if(choose<1||choose>8) {
				System.out.println("输入错误，请重新输入");
				choose = scanner.nextInt();
			}else {
				break;
			}
		}
		
		return choose;
	}
	
	
}

package com.mycart.helper;

public class Helper {
	public static String get10Words(String desc) {
		String[] str = desc.split(" ");
		if (str.length > 10) {
			String res="";
            for(int i=0;i<10;i++) {
            	res+=str[i]+" ";
            }
            return (res + "...");
		} else {
			return (desc + "...");
		}
	}
}

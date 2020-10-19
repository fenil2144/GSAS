package com.gsas.utility;

import javax.servlet.http.Part;

public class FileName {
	 public static String extractFileName(Part part) {
	       String contentDisp = part.getHeader("content-disposition");
	       String[] items = contentDisp.split(";");
	       for (String s : items) {
	           if (s.trim().startsWith("filename")) {
	               return s.substring(s.indexOf("=") + 2, s.length()-1);
	           }
	       }
	       return "";
	   }
}

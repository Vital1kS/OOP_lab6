package me.vital1ks.lab6.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet{

	private static final long serialVersionUID = 2L;

	public void doGet(HttpServletRequest request,HttpServletResponse response)  
            throws IOException  
   {  
   response.setContentType("image/png");  
   ServletOutputStream out;  
   out = response.getOutputStream();  
   FileInputStream fin = new FileInputStream("C:\\Users\\Vitalya\\eclipse-workspace\\lab6\\src\\main\\java\\me\\vital1ks\\lab6\\resources\\image.png");  
     
   BufferedInputStream bin = new BufferedInputStream(fin);  
   BufferedOutputStream bout = new BufferedOutputStream(out);  
   int ch =0; ;  
   while((ch=bin.read())!=-1)  
   {  
   bout.write(ch);  
   }  
     
   bin.close();  
   fin.close();  
   bout.close();  
   out.close();  
   }  
}

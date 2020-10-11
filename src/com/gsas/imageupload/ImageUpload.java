package com.gsas.imageupload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class ImageUpload
 */
@WebServlet("/ImageUpload")
public class ImageUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletFileUpload upload=new ServletFileUpload(new DiskFileItemFactory());
		try {
			List<FileItem> images=upload.parseRequest(request);//To store of list  files FileItem datatype is used 
			for(FileItem image :images) {
				String name=image.getName();//gets the name of file  
				try{name=name.substring(name.lastIndexOf("\\")+1);}catch(Exception e){}//this will give the name of file it removes stuffs like c:\downloads and gives the name
			  //  PrintWriter pw=response.getWriter();
			   // pw.print(name);
				//System.out.println(name);
			    image.write(new File("D:\\GSAS\\GSAS\\GSAS\\WebContent\\imagescheme"+name));//create folder imagescheme where image of the  will be stored
			    //images folder created in local computer and write function writes into that folder
			   request.setAttribute("imagePath","D:\\\\GSAS\\\\GSAS\\\\GSAS\\\\WebContent\\\\imagescheme"+ name);//
			   RequestDispatcher rd=request.getRequestDispatcher("InsertSchemeServlet");
			   rd.forward(request, response);
			    
				
			}
		}catch(FileUploadException e )
		{
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}



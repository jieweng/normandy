package com.pearl.normandy.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.pearl.normandy.utils.NormandyConfiguration;


public class ReferenceUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 5509102483642154403L;
	static Logger log = Logger.getLogger(ReferenceUploadServlet.class.getName());
	static NormandyConfiguration config = NormandyConfiguration.getInstance();
	
	private int maxPostSize = 10 * 1024 * 1024;
	 
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
	  
		String path = config.REFERENCE_TEMP_FOLDER;
		
		if(isMultipart){
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4096);
	
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(maxPostSize);
		  
			try {
				List fileItems = upload.parseRequest(req);
				Iterator iter = fileItems.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					if (!item.isFormField()) {
						String name = item.getName();
						String filename = req.getParameter("filename");
						
						File file = new File(path);
						if(!file.exists()){
							file.mkdir();
						}						
						
						String suffix = name.substring(name.indexOf('.'));
						
						try {
							item.write(new File(path + "/" + filename + suffix));
						} 
						catch (Exception e) {
							log.error("Error while creating reference files: ", e);
						}
					}
				}
			}
			catch (FileUploadException e) {
				log.error("Error in referencetUpload: ", e);			
			}
		}
	}	
}

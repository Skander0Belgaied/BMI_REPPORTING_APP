package com.bmi.service.rapport;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class UploadServiceImpl implements UploadService {
//	@Autowired
//	BmiequRepository bmiequRepository;
	  @Autowired
	    ApplicationContext context;
	@Override
	public String Uploadfile(MultipartFile file) {
		
		 Resource resource = context.getResource("classpath:reports/"+file.getOriginalFilename());
		   if(resource.exists())
		   {
			   return "Unable to upload "+file.getOriginalFilename()+" is already exists or a error happend";
		   }   
		if(!(file.getOriginalFilename().contains(".jrxml")))
		{
			return "Please select a file with extension jrxml";
		}
		//preparation du msg si le fichier n'exicte pas 
		  if (file.isEmpty()) {
	           
	            return "Please select a file to upload";
	        }

	        try {
	        	final String imagePath = "src/main/resources/reports/";
	            FileOutputStream output  = new FileOutputStream(imagePath+file.getOriginalFilename());
	            output.write(file.getBytes());
	            
	           
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally {
	        	return "File "+file.getOriginalFilename()+"uploaded succsesfully ";
			}
	        	
	}
	


}

package com.bmi.controller.rapport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bmi.app.repository.RapportRepository;
import com.bmi.service.rapport.UploadServiceImpl;


@Controller
public class UploadController {
	  @Autowired
	    UploadServiceImpl	uploadServiceImpl;
@Autowired
RapportRepository rapportRepository;
	  
	  
	@GetMapping(path = "/addEtat")
	String addreport()
	{
		return "addEtat";
		
	}
	//@GetMapping(path = "/upload")
	   @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
		  // rapportRepository.save(entity)
		 System.out.println(uploadServiceImpl.Uploadfile(file));
        return "/upload-status";
    }

}

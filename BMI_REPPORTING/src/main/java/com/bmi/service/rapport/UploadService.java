package com.bmi.service.rapport;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	
	String Uploadfile(MultipartFile file);
}

package org.koreait.controllers.files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/file/upload")
public class UploadController {
	@Value("${file.upload.path}")
	private String fileUploadPath;

	@GetMapping
	public String upload() {
		return "file/upload";
	}

	@PostMapping
	public String uploadPs(@RequestParam(name = "file", required = false) MultipartFile file2){
		if(file2 != null){
			File uploadPath = new File(fileUploadPath + file2.getOriginalFilename());
			try {
				file2.transferTo(uploadPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "file/upload";
	}
}

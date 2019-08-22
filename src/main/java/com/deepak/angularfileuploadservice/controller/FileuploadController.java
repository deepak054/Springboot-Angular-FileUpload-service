package com.deepak.angularfileuploadservice.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Deepak Kumar Mahanta
 *
 * mahantadeepak1990@gmail.com
 */

@RestController
@RequestMapping(path="file-upload-service")
@CrossOrigin("*")
public class FileuploadController {

	/*if you are working on a single image file ,then use @RequestParam("file") MultipartFile file as method parameter*/
	
	@PostMapping(path="file-upload")
	public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile[] file){
		
		//Read multipart file from FrontEnd Angular 8
		for (MultipartFile multipartFile : file) {
			//we are storing uploaded file in below location 
			Path filepath = Paths.get("F:\\\\Angular\\\\Angular-FIleUpload\\\\angular-fileupload-service",
					multipartFile.getOriginalFilename());

			try (OutputStream os = Files.newOutputStream(filepath)) {
				os.write(multipartFile.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//if it is a image file we can use below code 

		/*BufferedImage image = null;
		 try{
			
			image = ImageIO.read(file.getInputStream());
			ImageIO.write(image, "jpg",new File("F:\\Angular\\Angular-FIleUpload\\angular-fileupload-service\\img.png"));
		    }catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }*/
		return new ResponseEntity<String>(HttpStatus.CREATED);
		
	}

}

package com.mohit.asyncFileupload.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mohit.asyncFileupload.entity.User;
import com.mohit.asyncFileupload.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	
	@PostMapping(value = "/users/save",consumes =org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE,produces = "application/json" )
	public ResponseEntity saveUsers(@RequestParam("files") MultipartFile[] files) throws Exception{
		
		for(MultipartFile file:files) {
		 service.saveUser(file);
		}
		return new ResponseEntity(HttpStatus.CREATED);
		
	}
	
	
	@GetMapping(value = "/users/findall",produces = "application/json")
	public CompletableFuture<ResponseEntity> findUsers(){
	 return service.findAllUsers().thenApply(ResponseEntity::ok);
	
		
		
	}
	

}

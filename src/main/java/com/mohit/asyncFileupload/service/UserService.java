package com.mohit.asyncFileupload.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mohit.asyncFileupload.entity.User;
import com.mohit.asyncFileupload.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	Logger logger= LoggerFactory.getLogger(UserService.class);
	
	@Async(value = "taskExecutor")
	public CompletableFuture<List<User>> saveUser(MultipartFile file) throws Exception{

		
		logger.info("Executing By--- {}--Start Time -{}", Thread.currentThread().getName(), System.currentTimeMillis());
		
		List<User> users= userRepo.saveAll(parseCSV(file));
		
		logger.info("Executing By--- {}--End Time -{}", Thread.currentThread().getName(), System.currentTimeMillis());
		
		
		return CompletableFuture.completedFuture(users);
		}
	@Async(value = "taskExecutor")
	public CompletableFuture<List<User>> findAllUsers(){
		System.out.println(Thread.currentThread().getName());
		return CompletableFuture.completedFuture(userRepo.findAll());
	}
	
   private List<User> parseCSV(final MultipartFile file) throws Exception{
	   
	   final List<User> users= new ArrayList<User>();
	   String line;
	   try( final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))){
		 
		   while((line=br.readLine()) != null) {
	 logger.info("ParseCSV By--- {}--file Name -{}", Thread.currentThread().getName(), file.getOriginalFilename());
			
			  final String[] userData=line.split(",");
			 final  User user = new User();
			  user.setName(userData[0]);
			  user.setEmail(userData[1]);
			  user.setGender(userData[2]);
			  users.add(user);
			
		   }
	   } catch (final IOException e) {
		   logger.info("Exception ParseCSV By--- {}--Start Time -{}-filename--{} -Exeption -- {}", Thread.currentThread().getName(),file.getName(), System.currentTimeMillis(),e.getMessage());
	       throw new Exception("Unable to parse csv");
	   }
	
	   
	   return users;
	 }
	
	
	
	
	
	

}

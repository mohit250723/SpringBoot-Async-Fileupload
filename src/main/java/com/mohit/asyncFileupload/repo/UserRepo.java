package com.mohit.asyncFileupload.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.asyncFileupload.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

}

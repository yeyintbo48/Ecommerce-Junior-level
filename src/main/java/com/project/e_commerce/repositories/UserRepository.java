package com.project.e_commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.e_commerce.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

}

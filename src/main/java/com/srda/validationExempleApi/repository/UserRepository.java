package com.srda.validationExempleApi.repository;

import com.srda.validationExempleApi.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUserId(int id);
}

package com.example.springtest.Database;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository <Users, Integer>{

    @Query(value = "SELECT * FROM Users", nativeQuery = true)
    List<Users> findAllUsers();

    @Query(value = "SELECT * FROM Users WHERE email=:email", nativeQuery = true)
    Optional<Users> findByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Users (name, email, postcode, age) VALUES (:name, :email, :postcode, :age)", nativeQuery = true)
    void addUser(@Param("name") String name, @Param("email") String email, @Param("postcode") String postcode, @Param("age") int age);
}

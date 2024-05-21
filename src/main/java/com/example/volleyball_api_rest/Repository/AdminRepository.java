package com.example.volleyball_api_rest.Repository;

import com.example.volleyball_api_rest.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByEmail(String email);
}

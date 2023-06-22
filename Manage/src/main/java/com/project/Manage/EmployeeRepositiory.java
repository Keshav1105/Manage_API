package com.project.Manage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepositiory extends JpaRepository<EmployeeEntity,Long> {
}

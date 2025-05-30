package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Employee;
import com.example.demo.models.RetrievedAssets;

@Repository("retrievedassetrepo")
public interface RetrievedAssetsRepository extends JpaRepository<RetrievedAssets, Long> {

//	List<RetrievedAssets> findByEmployee(Employee employee);
}

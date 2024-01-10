package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Activity;

@Repository("activityrepo")
public interface ActivityRepo extends JpaRepository<Activity, Long> {

}

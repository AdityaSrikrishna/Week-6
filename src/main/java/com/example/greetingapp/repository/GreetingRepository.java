package com.example.greetingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.greetingapp.model.Greeting;

public interface GreetingRepository extends JpaRepository<Greeting, Long> {
}

package com.example.greetingapp.controller;
import com.example.greetingapp.model.Greeting;
import com.example.greetingapp.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    // UC 1 & UC 2
    @GetMapping
    public ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok(greetingService.getSimpleGreeting());
    }

    @PostMapping
    public ResponseEntity<String> postGreeting() {
        return ResponseEntity.ok("POST: Hello World");
    }

    @PutMapping
    public ResponseEntity<String> putGreeting() {
        return ResponseEntity.ok("PUT: Hello World");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteGreeting() {
        return ResponseEntity.ok("DELETE: Hello World");
    }

    // UC 3: Personalized Greeting
    @GetMapping("/personal")
    public ResponseEntity<String> getPersonalGreeting(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        return ResponseEntity.ok(greetingService.personalizedGreeting(firstName, lastName));
    }

    // UC 4: Save Greeting
    @PostMapping("/save")
    public ResponseEntity<Greeting> saveGreeting(@RequestParam String message) {
        return ResponseEntity.ok(greetingService.saveGreeting(message));
    }

    // UC 5: Find by ID
    @GetMapping("/{id}")
    public ResponseEntity<Greeting> getGreetingById(@PathVariable Long id) {
        return greetingService.getGreetingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UC 6: List All
    @GetMapping("/all")
    public ResponseEntity<List<Greeting>> getAllGreetings() {
        return ResponseEntity.ok(greetingService.getAllGreetings());
    }

    // UC 7: Edit
    @PutMapping("/{id}")
    public ResponseEntity<Greeting> updateGreeting(
            @PathVariable Long id,
            @RequestParam String message) {
        return greetingService.updateGreeting(id, message)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UC 8: Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGreeting(@PathVariable Long id) {
        return greetingService.deleteGreeting(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}

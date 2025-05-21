package com.example.greetingapp.service;
import com.example.greetingapp.model.Greeting;
import com.example.greetingapp.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {

    @Autowired
    private GreetingRepository greetingRepository;

    public String getSimpleGreeting() {
        return "Hello World";
    }

    public String personalizedGreeting(String firstName, String lastName) {
        if (firstName != null && lastName != null)
            return "Hello " + firstName + " " + lastName;
        else if (firstName != null)
            return "Hello " + firstName;
        else if (lastName != null)
            return "Hello " + lastName;
        else
            return getSimpleGreeting();
    }

    public Greeting saveGreeting(String message) {
        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting);
    }

    public Optional<Greeting> getGreetingById(Long id) {
        return greetingRepository.findById(id);
    }

    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }

    public Optional<Greeting> updateGreeting(Long id, String message) {
        return greetingRepository.findById(id).map(greeting -> {
            greeting.setMessage(message);
            return greetingRepository.save(greeting);
        });
    }

    public boolean deleteGreeting(Long id) {
        if (greetingRepository.existsById(id)) {
            greetingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

package com.cmstechub.resume.controller;

import com.cmstechub.resume.entity.Service;
import com.cmstechub.resume.entity.ServiceRequest;
import com.cmstechub.resume.repository.ServiceRepository;
import com.cmstechub.resume.repository.ServiceRequestRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allows the Android app to connect
public class ApiController {

    private final ServiceRepository serviceRepository;
    private final ServiceRequestRepository requestRepository;

    public ApiController(ServiceRepository serviceRepository, ServiceRequestRepository requestRepository) {
        this.serviceRepository = serviceRepository;
        this.requestRepository = requestRepository;
    }

    // 1. GET all services (For the Android app to display)
    @GetMapping("/services")
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    // 2. POST a new service request (When a user clicks "Submit" in the app)
    @PostMapping("/requests")
    public ServiceRequest createRequest(@RequestBody ServiceRequest request) {
        return requestRepository.save(request);
    }
}

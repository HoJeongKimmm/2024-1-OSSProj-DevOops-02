package com.example.demo.controller;

import com.example.demo.dto.StatusDTO;
import com.example.demo.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statuses")
public class StatusController {

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusDTO> getStatusById(@PathVariable Long id) {
        return ResponseEntity.ok(statusService.getStatusById(id));
    }

    @PostMapping
    public ResponseEntity<StatusDTO> createStatus(@RequestBody StatusDTO statusDto) {
        return ResponseEntity.ok(statusService.createStatus(statusDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusDTO> updateStatus(@PathVariable Long id, @RequestBody StatusDTO statusDto) {
        statusDto.setId(id);
        return ResponseEntity.ok(statusService.updateStatus(statusDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Long id) {
        statusService.deleteStatus(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<StatusDTO>> getStatusesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(statusService.getStatusesByUserId(userId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<StatusDTO>> getStatusesByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(statusService.getStatusesByProjectId(projectId));
    }
}
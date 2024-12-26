package com.FoodService.controller;


import com.FoodService.dto.AppUserDTO;
import com.FoodService.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/app-user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @PostMapping("/create")
    public ResponseEntity<AppUserDTO> createAppUser(@RequestBody AppUserDTO appUserDTO) {
        return ResponseEntity.ok(appUserService.createAppUser(appUserDTO));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<AppUserDTO>> getAllAppUsers() {
        return ResponseEntity.ok(appUserService.getAllAppUser());
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<AppUserDTO> getAppUserById(@RequestParam Long id) {
        return ResponseEntity.ok(appUserService.getAppUserById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<AppUserDTO> updateAppUser(@RequestParam Long id, @RequestBody AppUserDTO appUserDTO) {
        return ResponseEntity.ok(appUserService.updateAppUser(id, appUserDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAppUser(@RequestParam Long id) {
        appUserService.deleteAppUser(id);
        return ResponseEntity.noContent().build();
    }
}
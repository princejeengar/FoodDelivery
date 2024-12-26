package com.FoodService.service.serviceImpl;

import com.FoodService.dto.AppUserDTO;
import com.FoodService.entity.AppUser;
import com.FoodService.repository.AppUserRepo;
import com.FoodService.service.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AppUserDTO createAppUser(AppUserDTO appUserDTO) {
        AppUser appUser = modelMapper.map(appUserDTO, AppUser.class);
        AppUser savedAppUser = appUserRepo.save(appUser);
        return modelMapper.map(savedAppUser, AppUserDTO.class);
    }

    @Override
    public List<AppUserDTO> getAllAppUser() {
        return appUserRepo.findAll().stream()
                .map(appUser -> modelMapper.map(appUser, AppUserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public AppUserDTO getAppUserById(Long id) {
        return appUserRepo.findById(id)
                .map(appUser -> modelMapper.map(appUser, AppUserDTO.class)).orElse(null);
    }

    @Override
    public AppUserDTO updateAppUser(Long id, AppUserDTO appUserDTO) {
        AppUser existingAppUser = appUserRepo.findById(id).orElse(null);
        if(existingAppUser != null){
            existingAppUser.setUsername(appUserDTO.getUserName());
            existingAppUser.setEmail(appUserDTO.getEmail());
            return modelMapper.map(appUserRepo.save(existingAppUser), AppUserDTO.class);
        }
        return null;
    }

    @Override
    public void deleteAppUser(Long id) {
        appUserRepo.deleteById(id);
    }
}
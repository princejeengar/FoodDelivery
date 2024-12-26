package com.FoodService.service;

import com.FoodService.dto.AppUserDTO;
import java.util.List;

public interface AppUserService {
    AppUserDTO createAppUser(AppUserDTO appUserDTO);
    List<AppUserDTO> getAllAppUser();
    AppUserDTO getAppUserById(Long id);
    AppUserDTO updateAppUser(Long id, AppUserDTO appUserDTO);
    void deleteAppUser(Long id);
}
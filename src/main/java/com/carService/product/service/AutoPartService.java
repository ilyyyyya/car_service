package com.carService.product.service;

import com.carService.product.entity.AutoPart;
import com.carService.product.repos.AutopartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoPartService {
    @Autowired
    private AutopartRepo autopartRepo;

    public List<AutoPart> getAllAutoParts(){
        return autopartRepo.findAll();
    }

    public AutoPart getAutoPartById(Long id) {
        return autopartRepo.findById(id).orElse(null);
    }

    public void saveAutoPart(AutoPart autoPart) {
        autopartRepo.save(autoPart);
    }

    public void deleteAutoPartById(Long id) {
        autopartRepo.deleteById(id);
    }
}

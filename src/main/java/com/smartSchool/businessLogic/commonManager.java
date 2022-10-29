package com.smartSchool.businessLogic;

import org.springframework.beans.factory.annotation.Autowired;
import com.smartSchool.repository.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;

@Controller
@EnableAutoConfiguration
@Component
public class commonManager {

    @Autowired
    DiscountTypeRepository discountTypeRepo;

    @Autowired
    ClassCategoryRepository classCategoryRepository;

    @Autowired
    StandardClassRepository standardClassRepository;

    @Transactional
    public int markDiscountTypeAsDeletedById(Long id)  {
        return discountTypeRepo.delById(id);
    }

    @Transactional
    public int markClassCategoryAsDeletedById(Long id)  {
        return classCategoryRepository.delById(id);
    }

    @Transactional
    public int markStandardClassAsDeletedById(Long id)  {
        return standardClassRepository.delById(id);
    }
}

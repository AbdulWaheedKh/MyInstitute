package com.smartSchool.controller;

import com.smartSchool.models.DiscountType;
import com.smartSchool.repository.DiscountTypeRepository;
import com.smartSchool.repository.GenericRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/institute")
public class mainController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DiscountTypeRepository discountTypeRepo;
    @Autowired
    GenericRepository genericRepository;

    /**
     * @param instituteId
     * @return findAllByInstituteId
     */
    @GetMapping("/{instituteId}/dicountTypes")
    public ResponseEntity<List<DiscountType>> findAllByInstituteId(@PathVariable("instituteId") Long instituteId) {
        ResponseEntity<List<DiscountType>> result;
        try {
            List<DiscountType> discountTypes = discountTypeRepo.getAll(instituteId, false);

            if (discountTypes.isEmpty()) {
                result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                result = new ResponseEntity<>(discountTypes, HttpStatus.OK);
            }
        } catch (Exception e) {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }


}

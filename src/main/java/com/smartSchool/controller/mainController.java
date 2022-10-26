package com.smartSchool.controller;

import com.smartSchool.models.AcademicSessionYear;
import com.smartSchool.models.DiscountType;
import com.smartSchool.models.Sections;
import com.smartSchool.repository.AcademicSessionYearRepository;
import com.smartSchool.repository.*;
import com.smartSchool.repository.SectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/institute")
public class mainController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DiscountTypeRepository discountTypeRepo;

    @Autowired
    AcademicSessionYearRepository academicSessionYearRepository;

    @Autowired
    SectionRepository sectionRepository;

    /**
     * @param instituteId
     * @return findAllByInstituteId dicountTypes
     */
    @GetMapping("/{instituteId}/dicountTypes")
    public ResponseEntity<List<DiscountType>> findAllByInstituteId(@PathVariable("instituteId") Long instituteId) {
        ResponseEntity<List<DiscountType>> result;
        try {
            List<DiscountType> discountTypes = discountTypeRepo.getAllDiscountTypes(instituteId, false);

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

    /**
     * @param instituteId
     * @return findAllByInstituteId academicSessionYears
     */
    @GetMapping("/{instituteId}/academicSessionYears")
    public ResponseEntity<List<AcademicSessionYear>> findAllAcademicSessionsByInstituteId(@PathVariable("instituteId") Long instituteId) {
        ResponseEntity<List<AcademicSessionYear>> result;
        try {
            List<AcademicSessionYear> academicSessionYears = academicSessionYearRepository.getAll(instituteId, false);

            if (academicSessionYears.isEmpty()) {
                result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                result = new ResponseEntity<>(academicSessionYears, HttpStatus.OK);
            }
        } catch (Exception e) {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
    /**
     * @param instituteId
     * @return findAllByInstituteId Sections
     */
    @GetMapping("/{instituteId}/Sections")
    public ResponseEntity<List<Sections>> findAllSectionsByInstituteId(@PathVariable("instituteId") Long instituteId) {
        ResponseEntity<List<Sections>> result;
        try {
            List<Sections> sections = sectionRepository.getAll(instituteId, false);

            if (sections.isEmpty()) {
                result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                result = new ResponseEntity<>(sections, HttpStatus.OK);
            }
        } catch (Exception e) {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }




}

package com.smartSchool.controller;
import com.smartSchool.exceptions.CustomException;
import com.smartSchool.models.Sections;
import com.smartSchool.repository.SectionRepository;
import com.smartSchool.utils.AppUtility;
import com.smartSchool.utils.DataBaseConstrainst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/sectionController")
public class SectionController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SectionRepository sectionRepository;

    /*************************************************************************************
     *                       CRUD FOR Sections                                           *
     *                         13 OCT 2022                                               *
     * ********************************************************************************** /


     /**
     * @param obj
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<Sections> createSections(@RequestBody Sections obj) throws CustomException {
        ResponseEntity<Sections> result;
        if(!AppUtility.isEmptyOrNull(obj.getId())){
            throw new CustomException("ID MUST BE NULL");
        }
        try {
            obj.setCreatedDate(AppUtility.getCurrentTimeStamp());
            obj.setModifiedDate(AppUtility.getCurrentTimeStamp());
            Sections SectionsObj = sectionRepository.save(obj);
            result = new ResponseEntity<>(SectionsObj, HttpStatus.CREATED);
        }
        catch (DataIntegrityViolationException e) {
            logger.error( "----- Sections Post -> " + DataBaseConstrainst.UNIQ);
            throw new RuntimeException(DataBaseConstrainst.UNIQ);
        }
        catch (Exception e){
            logger.error( "-----  Sections Post SERVER ERROR ---- ");
            result = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PutMapping("/")
    public ResponseEntity<Sections> updateSections(@RequestBody Sections obj)
            throws CustomException {
        if (AppUtility.isEmptyOrNull(obj.getId())) {
            throw new CustomException("Id Can not be null");
        } else if (AppUtility.isEmptyOrNull(obj.getName()) || AppUtility.isEmptyOrNull(obj.getInstituteId())) {
            throw  new CustomException("Mandatory Fields are Empty");
        } else {
            try {
                obj.setModifiedDate(AppUtility.getCurrentTimeStamp());
                obj = sectionRepository.save(obj);
            }
            catch (DataIntegrityViolationException e) {
                logger.error( "----- Sections put -> " +DataBaseConstrainst.UNIQ);
                throw new RuntimeException(DataBaseConstrainst.UNIQ);
            }
            catch (Exception e) {
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return ResponseEntity.ok(obj);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Sections> getSectionsById(@PathVariable("id") Long id) {
        ResponseEntity<Sections> result;
        Optional<Sections> obj = Optional.ofNullable(sectionRepository.getByIdEntityGeneric(id,false));

        if (obj.isPresent()) {
            result = new ResponseEntity<>(obj.get(), HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return result;
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSections(@PathVariable("id") Long id) {
        ResponseEntity<HttpStatus> result;
        try {
            sectionRepository.markAsDeletedById(id,true,AppUtility.getDeleteStamp());
            result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    /**
     * @return
     */
    @DeleteMapping("/Sections")
    public ResponseEntity<HttpStatus> deleteAllSection() {
        ResponseEntity<HttpStatus> result;
        try {
            sectionRepository.deleteAll();
            result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }
}

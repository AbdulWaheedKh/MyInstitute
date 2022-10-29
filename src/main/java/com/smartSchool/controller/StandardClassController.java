package com.smartSchool.controller;

import com.smartSchool.businessLogic.*;
import com.smartSchool.exceptions.CustomException;
import com.smartSchool.models.StandardClass;
import com.smartSchool.repository.*;
import com.smartSchool.utils.AppUtility;
import com.smartSchool.utils.DataBaseConstrainst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/standardClassController")
public class StandardClassController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StandardClassRepository standardClassRepository;

    @Autowired
    private commonManager manager;

    /*************************************************************************************
     *                       CRUD FOR Standard class                                     *
     *                         28 OCT 2022                                               *
     * ********************************************************************************** /


     /**
     * @param obj
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<StandardClass> createStandardClass(@RequestBody StandardClass obj) throws CustomException {
        ResponseEntity<StandardClass> result;
        if(!AppUtility.isEmptyOrNull(obj.getId())){
            throw new CustomException("ID MUST BE NULL");
        }
        try {
            obj.setCreatedDate(AppUtility.getCurrentTimeStamp());
            obj.setModifiedDate(AppUtility.getCurrentTimeStamp());
            obj.setInstituteId(32L);
            // obj.setAca(32L);
            StandardClass StandardClassObj = standardClassRepository.save(obj);
            result = new ResponseEntity<>(StandardClassObj, HttpStatus.CREATED);
        }
        catch (DataIntegrityViolationException e) {
            logger.error( "----- StandardClass Post -> " + DataBaseConstrainst.UNIQ);
            throw new RuntimeException(DataBaseConstrainst.UNIQ);
        }
        catch (Exception e){
            logger.error( "-----  StandardClass SERVER ERROR ---- ");
            result = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PutMapping("/")
    public ResponseEntity<StandardClass> updateStandardClass(@RequestBody StandardClass obj)
            throws CustomException {
        if (AppUtility.isEmptyOrNull(obj.getId())) {
            throw new CustomException("Id Can not be null");
        } else if (AppUtility.isEmptyOrNull(obj.getName()) || AppUtility.isEmptyOrNull(obj.getInstituteId())) {
            throw  new CustomException("Mandatory Fields are Empty");
        } else {
            try {
                obj.setModifiedDate(AppUtility.getCurrentTimeStamp());
                obj = standardClassRepository.save(obj);
            }
            catch (DataIntegrityViolationException e) {
                logger.error( "----- StandardClass put -> " +DataBaseConstrainst.UNIQ);
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
    public ResponseEntity<StandardClass> getStandardClassById(@PathVariable("id") Long id) {
        ResponseEntity<StandardClass> result = null;
        //Optional<StandardClass> obj = Optional.ofNullable(StandardClassRepo.getByIdEntityGeneric(id,false));

//        if (obj.isPresent()) {
//            result = new ResponseEntity<>(obj.get(), HttpStatus.OK);
//        } else {
//            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return result;
    }

    /**
     * @param id
     * @return
     */
//    @DeleteMapping("/{id}")
//    public ResponseEntity<HttpStatus> deleteStandardClass(@PathVariable("id") Long id) {
//        ResponseEntity<HttpStatus> result;
//        try {
//            StandardClassRepo.delById(id);
//            result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return result;
//    }



    @DeleteMapping("/{id}")
    public Integer deleteStandardClass(@PathVariable Long id)
            throws CustomException {

        Integer count = null;
        try {
            count = manager.markStandardClassAsDeletedById(id);
        } catch (Exception e) {
            throw new CustomException(e);
        }

        return count;
    }

    /**
     * @return
     */
    @DeleteMapping("/standardClass")
    public ResponseEntity<HttpStatus> deleteAllStandardClass() {
        ResponseEntity<HttpStatus> result;
        try {
            standardClassRepository.deleteAll();
            result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }
}

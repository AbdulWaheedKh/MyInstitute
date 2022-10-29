package com.smartSchool.controller;

import com.smartSchool.businessLogic.*;
import com.smartSchool.exceptions.CustomException;
import com.smartSchool.models.ClassCategory;
import com.smartSchool.models.ClassCategory;
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
@RequestMapping("/classCategoryController")
public class ClassCategoryController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ClassCategoryRepository classCategoryRepository;

    @Autowired
    private commonManager manager;

/*************************************************************************************
 *                       CRUD FOR ClassCategory                                      *
 *                         28 OCT 2022                                               *
 * ********************************************************************************** */

    /**
     * @param obj
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<ClassCategory> createClassCategory(@RequestBody ClassCategory obj) throws CustomException {
        ResponseEntity<ClassCategory> result;
        if(!AppUtility.isEmptyOrNull(obj.getId())){
            throw new CustomException("ID MUST BE NULL");
        }
        try {
            obj.setCreatedDate(AppUtility.getCurrentTimeStamp());
            obj.setModifiedDate(AppUtility.getCurrentTimeStamp());
            obj.setInstituteId(32L);
            ClassCategory ClassCategoryObj = classCategoryRepository.save(obj);
            result = new ResponseEntity<>(ClassCategoryObj, HttpStatus.CREATED);
        }
        catch (DataIntegrityViolationException e) {
            logger.error( "----- ClassCategory  Post -> " + DataBaseConstrainst.UNIQ);
            throw new RuntimeException(DataBaseConstrainst.UNIQ);
        }
        catch (Exception e){
            logger.error( "-----  ClassCategory  Post SERVER ERROR ---- ");
            result = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PutMapping("/")
    public ResponseEntity<ClassCategory> updateClassCategory(@RequestBody ClassCategory obj)
            throws CustomException {
        if (AppUtility.isEmptyOrNull(obj.getId())) {
            throw new CustomException("Id Can not be null");
        } else if (AppUtility.isEmptyOrNull(obj.getName()) || AppUtility.isEmptyOrNull(obj.getInstituteId())) {
            throw  new CustomException("Mandatory Fields are Empty");
        } else {
            try {
                obj.setModifiedDate(AppUtility.getCurrentTimeStamp());
                obj = classCategoryRepository.save(obj);
            }
            catch (DataIntegrityViolationException e) {
                logger.error( "----- Discount Type put -> " +DataBaseConstrainst.UNIQ);
                throw new RuntimeException(DataBaseConstrainst.UNIQ);
            }
            catch (Exception e) {
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassCategory> getClassCategoryById(@PathVariable("id") Long id) {
        ResponseEntity<ClassCategory> result = null;
        //Optional<ClassCategory> obj = Optional.ofNullable(ClassCategoryRepo.getByIdEntityGeneric(id,false));

//        if (obj.isPresent()) {
//            result = new ResponseEntity<>(obj.get(), HttpStatus.OK);
//        } else {
//            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return result;
    }

    @DeleteMapping("/{id}")
    public Integer deleteFeeSetup(@PathVariable Long id)
            throws CustomException {

        Integer count = null;
        try {
            count = manager.markClassCategoryAsDeletedById(id);
        } catch (Exception e) {
            throw new CustomException(e);
        }

        return count;
    }

    /**
     * @return
     */
    @DeleteMapping("/ClassCategorys")
    public ResponseEntity<HttpStatus> deleteAllClassCategorys() {
        ResponseEntity<HttpStatus> result;
        try {
            classCategoryRepository.deleteAll();
            result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }


 }

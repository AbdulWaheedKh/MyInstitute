package com.smartSchool.controller;


import com.smartSchool.exceptions.CustomException;
import com.smartSchool.models.DiscountType;
import com.smartSchool.repository.DiscountTypeRepository;
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
@RequestMapping("/discountTypesController")
public class discountTypeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DiscountTypeRepository discountTypeRepo;

    /*************************************************************************************
     *                       CRUD FOR DISCOUNT TYPES                                     *
     *                         13 OCT 2022                                               *
     * ********************************************************************************** /


    /**
     * @param obj
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<DiscountType> createDiscountType(@RequestBody DiscountType obj) throws CustomException {
        ResponseEntity<DiscountType> result;
        if(!AppUtility.isEmptyOrNull(obj.getId())){
            throw new CustomException("ID MUST BE NULL");
        }
        try {
            obj.setCreatedDate(AppUtility.getCurrentTimeStamp());
            obj.setModifiedDate(AppUtility.getCurrentTimeStamp());
            DiscountType discountTypeObj = discountTypeRepo.save(obj);
            result = new ResponseEntity<>(discountTypeObj, HttpStatus.CREATED);
        }
        catch (DataIntegrityViolationException e) {
            logger.error( "----- Discount Type Post -> " +DataBaseConstrainst.UNIQ);
            throw new RuntimeException(DataBaseConstrainst.UNIQ);
        }
        catch (Exception e){
            logger.error( "-----  Discount Type Post SERVER ERROR ---- ");
            result = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PutMapping("/")
    public ResponseEntity<DiscountType> updateDiscountType(@RequestBody DiscountType obj)
            throws CustomException {
        if (AppUtility.isEmptyOrNull(obj.getId())) {
            throw new CustomException("Id Can not be null");
        } else if (AppUtility.isEmptyOrNull(obj.getName()) || AppUtility.isEmptyOrNull(obj.getInstituteId())) {
            throw  new CustomException("Mandatory Fields are Empty");
        } else {
            try {
                obj.setModifiedDate(AppUtility.getCurrentTimeStamp());
                obj = discountTypeRepo.save(obj);
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

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<DiscountType> getDiscountTypeById(@PathVariable("id") Long id) {
        ResponseEntity<DiscountType> result;
        Optional<DiscountType> obj = Optional.ofNullable(discountTypeRepo.getByIdEntityGeneric(id,false));

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
    public ResponseEntity<HttpStatus> deleteDiscountType(@PathVariable("id") Long id) {
        ResponseEntity<HttpStatus> result;
        try {
            discountTypeRepo.markAsDeletedById(id,true,AppUtility.getDeleteStamp());
            result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    /**
     * @return
     */
    @DeleteMapping("/discountTypes")
    public ResponseEntity<HttpStatus> deleteAllDiscountTypes() {
        ResponseEntity<HttpStatus> result;
        try {
            discountTypeRepo.deleteAll();
            result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }

}

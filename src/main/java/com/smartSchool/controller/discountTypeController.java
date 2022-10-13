package com.smartSchool.controller;


import com.smartSchool.exceptions.CustomException;
import com.smartSchool.models.DiscountType;
import com.smartSchool.repository.DiscountTypeRepository;
import com.smartSchool.repository.GenericRepository;
import com.smartSchool.utils.AppUtility;
import com.smartSchool.utils.DataBaseConstrainst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/discountTypesController")
public class discountTypeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DiscountTypeRepository discountTypeRepo;
    @Autowired
    GenericRepository genericRepository;


    /**
     * @param discountType
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<DiscountType> createDiscountType(@RequestBody DiscountType discountType) throws CustomException {
        ResponseEntity<DiscountType> result;
        if(!AppUtility.isEmptyOrNull(discountType.getId())){
            throw new CustomException("ID MUST BE NULL");
        }
        try {
            discountType.setCreatedDate(AppUtility.getCurrentTimeStamp());
            discountType.setModifiedDate(AppUtility.getCurrentTimeStamp());
            DiscountType discountTypeObj = discountTypeRepo.save(discountType);
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
    public ResponseEntity<DiscountType> updateDiscountType(@RequestBody DiscountType discountType)
            throws CustomException {
        if (AppUtility.isEmptyOrNull(discountType.getId())) {
            throw new CustomException("Id Can not be null");
        } else if (AppUtility.isEmptyOrNull(discountType.getName()) || AppUtility.isEmptyOrNull(discountType.getInstituteId())) {

        } else {
            try {
                discountType.setModifiedDate(AppUtility.getCurrentTimeStamp());
                discountType = discountTypeRepo.save(discountType);
            }
            catch (DataIntegrityViolationException e) {
                logger.error( "----- Discount Type put -> " +DataBaseConstrainst.UNIQ);
                throw new RuntimeException(DataBaseConstrainst.UNIQ);
            }
            catch (Exception e) {
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
            return ResponseEntity.ok(discountType);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<DiscountType> getDiscountTypeById(@PathVariable("id") Long id) {
        ResponseEntity<DiscountType> result;
        Optional<DiscountType> discountType = Optional.ofNullable(discountTypeRepo.getByIdEntityGeneric(id,false));

        if (discountType.isPresent()) {
            result = new ResponseEntity<>(discountType.get(), HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return result;
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteDiscountType(@PathVariable("id") Long id) {
        ResponseEntity<HttpStatus> result;
        try {
            discountTypeRepo.deleteById(id);
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

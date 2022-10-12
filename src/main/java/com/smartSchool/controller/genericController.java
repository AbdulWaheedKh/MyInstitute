package com.smartSchool.controller;

import com.smartSchool.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/genericController")
public abstract class genericController<T extends GenericEntity<T>>{

    @Autowired
    GenericRepository genericRepository;

//    @PostMapping("")
//    public ResponseEntity<T> create(@RequestBody T created){
//        return ResponseEntity.ok(genericRepository.save(created));
//    }

//    @Transactional
//    @PostMapping("/create")
//    public T create(T newDomain){
//        T dbDomain = newDomain.createNewInstance();
//        return (T) genericRepository.save(dbDomain);
//    }

    @PostMapping("/create")
    public ResponseEntity<T> create(@RequestBody T t) {
        ResponseEntity<T> result;
        try {
            T genericObj = (T) genericRepository.save(t);
            result = new ResponseEntity<>(genericObj, HttpStatus.CREATED);
        } catch (Exception e) {
            result = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

//    @PostMapping("/")
//    @Transactional
//    public ResponseEntity<T> create(@RequestBody T newDomain) {
//        ResponseEntity<T> result;
//        try {
//            T dbDomain = newDomain.createNewInstance();
//
//            T genericObj = (T) genericRepository.save(dbDomain);
//            result = new ResponseEntity<>(genericObj, HttpStatus.CREATED);
//        } catch (Exception e) {
//            result = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return result;
//    }


    @GetMapping("/institute/{id}")
    public ResponseEntity<T> getById(@PathVariable("id") Long id) {
        ResponseEntity<T> result;
        Optional<T> genericObj = genericRepository.findById(id);

        if (genericObj.isPresent()) {
            result = new ResponseEntity<>(genericObj.get(), HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return result;
    }

    @GetMapping("/instituteId/{instituteId}")
    public ResponseEntity<List<T>> findAllByInstituteId(@PathVariable("instituteId") Long instituteId) {
        ResponseEntity<List<T>> result;
        try {
            List<T> genericListObj = genericRepository.getAll(instituteId, false);

            if (genericListObj.isEmpty()) {
                result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                result = new ResponseEntity<>(genericListObj, HttpStatus.OK);
            }
        } catch (Exception e) {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        ResponseEntity<HttpStatus> result;
        try {
            genericRepository.deleteById(id);
            result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAll() {
        ResponseEntity<HttpStatus> result;
        try {
            genericRepository.deleteAll();
            result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }

}

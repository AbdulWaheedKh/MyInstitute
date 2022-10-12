package com.smartSchool.exceptions;

import com.smartSchool.utils.AppUtility;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLIntegrityConstraintViolationException;

public class DataBaseConstraintExcepion {

    /**
     * Validates DB Constraints...
     * @param exc
     * @param constraintName
     * @param value
     * @throws
     */
    public static void DBConstraint(Exception exc, String constraintName, String value) throws org.springframework.dao.DataIntegrityViolationException{
        if(exc instanceof DataIntegrityViolationException){
            if(exc.getCause() instanceof ConstraintViolationException){
                ConstraintViolationException constraintExecption =(ConstraintViolationException)exc.getCause();

                if(!AppUtility.isEmptyOrNull(constraintExecption.getConstraintName()) && constraintExecption.getConstraintName().toUpperCase().contains(constraintName.toUpperCase())){

                    value = value + " already exist!";
                    if(constraintExecption.getCause() instanceof SQLIntegrityConstraintViolationException){
                        SQLIntegrityConstraintViolationException integrityExecption =(SQLIntegrityConstraintViolationException)constraintExecption.getCause();
                        value = integrityExecption.getMessage();
                    }
                    throw new org.springframework.dao.DataIntegrityViolationException(value,exc);

                }
            }
        }
    }
}

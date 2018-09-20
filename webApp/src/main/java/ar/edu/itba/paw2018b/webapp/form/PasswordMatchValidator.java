package ar.edu.itba.paw2018b.webapp.form;

import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {

    public void initialize(PasswordMatch constraint) {
   }

   public boolean isValid(Object obj, ConstraintValidatorContext context) {
        return ((UserForm)obj).getPassword().equals(((UserForm) obj).getRepeatPassword());
    }
}

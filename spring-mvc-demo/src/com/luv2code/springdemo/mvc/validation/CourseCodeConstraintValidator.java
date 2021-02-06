package com.luv2code.springdemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode,String>{

	public String CoursePrefix;
	
	@Override
	public void initialize(CourseCode theCourseCode) {
		
		CoursePrefix=theCourseCode.value();
	}
	
	@Override
	public boolean isValid(String theCode, 
			ConstraintValidatorContext theConstraintValidatorContext) {
		
		boolean result;
		if(theCode != null) {
			result=theCode.startsWith(CoursePrefix);
		}else {
			result=true;
		}
		
		return result;
	}

}

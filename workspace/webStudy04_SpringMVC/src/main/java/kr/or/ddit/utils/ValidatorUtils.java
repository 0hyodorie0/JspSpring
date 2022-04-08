package kr.or.ddit.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import kr.or.ddit.vo.MemberVO;

public class ValidatorUtils<T> {
	private Validator validator;
	{
		ValidatorFactory factory = Validation.byDefaultProvider()
						.configure()
						.messageInterpolator(
							new ResourceBundleMessageInterpolator(
								new PlatformResourceBundleLocator(
									"kr.or.ddit.msgs.ErrorMessages"
								)
							)	
						).buildValidatorFactory();
						
		validator = factory.getValidator();
	}
	
	public boolean validate(T target, Map<String, List<String>> errors, Class<?>...groups) {
		Set<ConstraintViolation<T>> violations = validator.validate(target, groups);
		boolean valid = violations.isEmpty();
		if(!valid) {
			for(ConstraintViolation<T>  propError : violations) {
				Path key = propError.getPropertyPath();
				String value = propError.getMessage();
				List<String> messages = errors.get(key.toString());
				if(messages==null) {
					messages = new ArrayList<>();
					errors.put(key.toString(), messages);
				}
				messages.add(value);
			}
		}
		return valid;
	}
}












package com.packt.webstore.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.packt.webstore.domain.Product;

@Component
public class UnitsInStockValidator implements Validator {

	
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;
		if (product.getUnitPrice()!=0 && 10000 <= (product.getUnitPrice())
				&& product.getUnitsInStock()>99) {
			errors.rejectValue("unitsInStock", "com.packt.webstore.validator.UnitsInStockValidator.message");
		}
		
	}

}

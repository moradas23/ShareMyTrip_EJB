package com.sdi.presentation.validators;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "passwordValidator")
public class PasswordValidator implements Validator {

	@Override
	public void validate(FacesContext fc, UIComponent component, Object value)
			throws ValidatorException {
		String attribute = (String) component.getAttributes().get("password");
		if (!value.equals(attribute)) {
			FacesMessage message = new FacesMessage();
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
	          ResourceBundle bundle = 
	           facesContext.getApplication().getResourceBundle(facesContext, "msgs");
	          
	    
			message.setSummary(bundle.getString("passNoCoincide"));
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
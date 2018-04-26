package potatoes.project.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import potatoes.project.domain_objects.User;
import potatoes.project.service.UserService;

@Component
public class UserValidator implements Validator{
	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}
	
	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		if (user.getName().length() < 5 || user.getName().length() > 20) {
			errors.rejectValue("username", "Size.userForm.username");
		}
		if (userService.findByUsername(user.getName()) != null) {
			errors.rejectValue("username", "Duplicate.userForm.username");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (user.getPassword().length() < 10) {
			errors.rejectValue("password", "Size.userForm.username");
		}
		if (!user.getConfirmPassword().equals(user.getPassword())) {
			errors.rejectValue("password", "Diff.userForm.confirmPassword");
		}
	}
}

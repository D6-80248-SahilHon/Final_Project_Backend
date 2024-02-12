package com.app.RequestDtos;


import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.app.types.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder 
public class UserEntryDto {

	@Column(nullable = false)
	@NotBlank(message = "Field cannot be blank")
	private String name;
	 
	@Min(value = 18)
	private Integer age;
	 
    private String address;
    
    @Size(min = 10, max = 10, message = "Field length must be 20 characters")    
    private String mobileNo;
    
    @Column(unique = true)
    @Email    
    private String emailId;
    
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password Must Contain 1 uppercase, 1 lowercase, 1 number, and 1 special character")
    private String password;
    
    
    private Gender gender;
    
    

    
}

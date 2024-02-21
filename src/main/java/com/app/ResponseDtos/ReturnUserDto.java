package com.app.ResponseDtos;


import com.app.types.Gender;
import com.app.types.Roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReturnUserDto {
	private Integer id;
    private String name;
    private Integer age;
    private Gender gender;
    private String address;
    private Roles role;
}

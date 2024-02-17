package com.app.models;
import lombok.*;
import org.hibernate.*;

import com.app.types.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    @NotBlank(message = "Field cannot be blank")
    private String name;

   
    private Integer age;

    
    private String address;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    
    private String mobileNo;

    @Column(unique = true)
    private String emailId;
    
    
    private String password;//Not working properly
    
    @Enumerated(value = EnumType.STRING)
    private Roles role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ticket> ticketList = new ArrayList<>();



}

//Regex Pattern:At least 8 characters long.
//Contains at least one uppercase letter.
//Contains at least one lowercase letter.
//Contains at least one digit.
//Contains at least one special character (e.g., !@#$%^&*()-+=).


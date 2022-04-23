package com.mediscreen.mediscreen.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PatientBean {

    private Long id;

    @NotBlank(message = "First name is mandatory")
    @Length(max = 100,  message = "must not exceed 100 characters")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Length(max = 100,  message = "must not exceed 100 characters")
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private EnumGenderBean gender;

    @Length(max = 50,  message = "must not exceed 50 characters")
    private String homeAddress;

    @Length(max = 20,  message = "must not exceed 20 characters")
    private String phone;
}

package com.mediscreen.patient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotBlank(message = "First name is mandatory")
    @Length(max = 100,  message = "must not exceed 100 characters")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Length(max = 100,  message = "must not exceed 100 characters")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "sex")
    private EnumGender gender;

    @Column(name = "home_address")
    @Length(max = 50,  message = "must not exceed 50 characters")
    private String homeAddress;

    @Column(name = "phone")
    @Length(max = 20,  message = "must not exceed 20 characters")
    private String phone;
}

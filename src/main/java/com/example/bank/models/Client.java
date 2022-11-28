package com.example.bank.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=2)
    @NotNull
    private String fullName;

    @Email
    @NotNull
    @Column(unique=true)
    private String email;

    @NotNull
    @Size(min = 10, max = 10)
    @Column(unique=true)
    private String phone;

    @Size(min = 10, max = 10)
    @NotNull
    @Column(unique=true)
    private String passport;


}

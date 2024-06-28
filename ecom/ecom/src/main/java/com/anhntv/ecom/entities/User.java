package com.anhntv.ecom.entities;


import com.anhntv.ecom.constants.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private UserRole role;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;
}

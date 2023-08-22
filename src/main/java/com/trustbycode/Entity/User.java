package com.trustbycode.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "User Information")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User Id")
    private Long id;

    @Column(name = "First Name")
    private String firstName;

    @Column(name = "Last Name")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password", length = 60)
    private String password;

    @Column(name = "Role")
    private String role;

    @Column(name = "User Enabled")
    private boolean enabled = false; //User wont be enabled by default
}

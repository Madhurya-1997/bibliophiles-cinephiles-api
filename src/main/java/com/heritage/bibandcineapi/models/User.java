package com.heritage.bibandcineapi.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;


@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(min = 3, max = 20, message = "Username should be between of 3 and 20 characters")
    @NotNull(message = "Username cannot be null")
    private String username;

    @Length(min = 3, max = 100, message = "Password should be between of 3 and 100 characters")
    @NotNull(message = "Password cannot be null")
    private String password;

    @Email(message = "Please provide email in valid format")
    @NotNull(message = "Email cannot be null")
    private String email;

    @Column(name = "phone_number")
    @Pattern(regexp = "^[1-9]\\d*$", message = "Please provide phone number in valid format")
    @NotNull(message = "Phone number cannot be null")
    private String phoneNumber;

    private String role;

    @OneToOne(mappedBy = "user")
    private Feedback feedback;


    public User(Long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = "ROLE_USER";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return "ROLE_USER";
    }

    public void setRole() {
        this.role = "ROLE_USER";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }
}

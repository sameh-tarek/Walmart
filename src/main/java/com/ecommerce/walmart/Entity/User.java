package com.ecommerce.walmart.Entity;

import com.ecommerce.walmart.Entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Role userType;
    private String address;
    @OneToOne(cascade = CascadeType.ALL)
    private Contact contact;
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
    @ManyToMany
    private List<Product> favorites;

}

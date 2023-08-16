package com.example.jonathandewitenterpriseapplications.models;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
}

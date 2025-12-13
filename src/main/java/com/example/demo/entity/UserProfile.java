package com.example.demo.entity;

import java.io.Serializable;
import java.io.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.*;

import jakarta.persistance.*;
@Entity
@Table(name = "user_profile")
public class UserProfile 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id",unique = true , nullable = false)
    private String userid;

    @Column(name = "full_name" , nullable = false)
    private String fullName;

    


}



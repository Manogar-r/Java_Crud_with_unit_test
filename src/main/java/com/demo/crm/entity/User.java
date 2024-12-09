package com.demo.crm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userID;

    @Column(name = "user_name" , unique = true)
    private String userName;

    @Column(name = "mail", unique = true)
    private String userMail;

    @Column(name = "create_at")
    private LocalDate createdAt;

    // updated at field will get whenever the entity detail got changed
    @Column(name = "update_at")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "password")
    private String Password;

    @Column(name = "user_status")
    private String Status;

    @Column(name = "last_login")
    private Date lastLogin;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now(); // Set the current date at creation
    }
}

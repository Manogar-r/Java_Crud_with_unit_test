package com.demo.crm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long customerID;

    @Column(name = "Name", nullable = false)
    public String customerName;

    @Column(name = "Email", nullable = false)
    public String customerMail;

    @Column(name = "Address", nullable = false)
    public String customerAddress;

    @Column(name = "phoneNumberOne", nullable = false)
    public long phoneNumberOne;

    @Column(name = "phoneNumberTwo")
    public long phoneNumberTwo;


    @Column(name = "DOB", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public LocalDate DOB;

    @Column(name = "create_at")
    private LocalDate createdAt;

    // updated at field will get whenever the entity detail got changed
    @Column(name = "update_at")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now(); // Set the current date at creation
    }
}
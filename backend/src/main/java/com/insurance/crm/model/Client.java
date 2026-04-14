package com.insurance.crm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "adviser_id")
    private Adviser adviser;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private LocalDate dateOfBirth;

    @Column
    private String policyNumber;

    @Column
    private String policyType;
}
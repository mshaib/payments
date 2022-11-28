package com.intuit.b2b.payment.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @ColumnDefault("random_uuid()")
    @Column(nullable = false)
    private String paymentId;

    @Column(nullable = false)
    private String paymentStatus;

    @Column
    private double paymentRiskPercentage;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String payeeId;

    @Column(nullable = false)
    private String paymentMethodId;
}

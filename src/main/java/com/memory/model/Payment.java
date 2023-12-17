package com.memory.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String paymentCode;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Tomb tombId;
    private String status;
    private Date paymentDate;
    @ManyToOne( cascade = CascadeType.ALL,fetch =FetchType.EAGER)
    private Customer customerId;
    @OneToMany(mappedBy = "paymentId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OtherServices> listOfOtherServices;
}

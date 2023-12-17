package com.memory.model;
import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tomb implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double cost; 
    private boolean isbooked;
    private String category;
    private double width;
    private double length;
    private String description;
    private String location;
    @OneToMany(mappedBy = "tombId",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Payment> paymentId;
    @OneToMany(mappedBy ="tombId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Decease> deceaseId;
    @OneToMany(mappedBy = "tombId")
    private List<OtherServices> otherservices;
}

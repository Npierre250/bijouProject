package com.memory.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OtherServices implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String serviceName;
    @ManyToOne(cascade = CascadeType.ALL)
    private Payment paymentId;
    @ManyToOne(cascade = CascadeType.ALL)
    private Tomb tombId;
    private Date dateOfService;
    private Date createdDate;
}

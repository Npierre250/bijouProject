package com.memory.model;

import java.sql.Date;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Decease  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String gender;
    private Date dob;
    private Date deceaseDate;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Tomb tombId;
}

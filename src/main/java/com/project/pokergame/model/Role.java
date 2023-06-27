package com.project.pokergame.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue
    @Column
    private int id;

    @Enumerated(EnumType.STRING)
    private com.project.pokergame.model.enumerated.Role name;
}

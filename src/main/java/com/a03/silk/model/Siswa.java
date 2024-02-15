package com.a03.silk.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Siswa {

    @Id
    private UUID id = UUID.randomUUID();
}

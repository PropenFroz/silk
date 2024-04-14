package com.a03.silk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guru_jurusan")
public class GuruJurusan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idGuruJurusan;

    @ManyToOne
    @JoinColumn(name = "id_guru", referencedColumnName = "idGuru", nullable = false)
    private Guru guru;

    @ManyToOne
    @JoinColumn(name = "id_jurusan_kursus", referencedColumnName = "idJurusanKursus", nullable = false)
    private JurusanKursus jurusanKursus;
}

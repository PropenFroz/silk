package com.a03.silk.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guru")
public class Guru {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idGuru;

    @NotNull
    @Column(name = "nama_guru")
    private String namaGuru;

    @NotNull
    @Column(name = "user_id")
    private long userId;

    @JsonIgnore
    @OneToMany(mappedBy = "guru", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<GuruJurusan> listGuruJurusan;

    @JsonIgnore
    @OneToMany(mappedBy = "guru", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EntryGajiGuru> daftarGajiGuru;
}

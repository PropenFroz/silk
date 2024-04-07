package com.a03.silk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "entry_gaji_guru")
public class EntryGajiGuru {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEntryGajiGuru;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_guru", referencedColumnName = "idGuru")
    private Guru guru;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_jurusan_kursus", referencedColumnName = "idJurusanKursus")
    private JurusanKursus jurusanKursus;

    @JsonIgnore
    @OneToMany(mappedBy = "entryGajiGuru", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EntryGajiGuruDetail> daftarEntryGajiGuruDetail;
}

package com.a03.silk.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE entry_gaji_guru_detail SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "entry_gaji_guru_detail")
public class EntryGajiGuruDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_entry_gaji_guru", referencedColumnName = "idEntryGajiGuru")
    private EntryGajiGuru entryGajiGuru;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grade_kursus", referencedColumnName = "idGradeKursus")
    private GradeKursus gradeKursus;

    @NotNull
    @Column(name = "murid", nullable = false)
    private String murid;

    @NotNull
    @Column(name = "uang_kursus")
    private long uangKursus;

    @NotNull
    @Column(name = "tanggal", nullable = false)
    private Date tanggal;

    @NotNull
    @Column(name = "minggu_1")
    private long minggu1;
    
    @NotNull
    @Column(name = "minggu_2")
    private long minggu2;

    @NotNull
    @Column(name = "minggu_3")
    private long minggu3;

    @NotNull
    @Column(name = "minggu_4")
    private long minggu4;

    @NotNull
    @Column(name = "fee_guru")
    private long feeGuru;

    @NotNull
    @Column(name = "keterangan")
    private String keterangan;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE;

}

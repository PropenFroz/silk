package com.a03.silk.service;

import com.a03.silk.model.EntryGajiGuruDetail;
import com.a03.silk.repository.EntryGajiGuruDetailDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.model.EntryTransaksiBuku;
import com.a03.silk.model.EntryTransaksiSiswa;
import com.a03.silk.repository.EntryTransaksiBukuDb;
import com.a03.silk.repository.EntryTransaksiSiswaDb;
import java.util.Calendar;
import java.util.Date;

import jakarta.transaction.Transactional;

import java.util.*;
@Service
@Transactional
public class DashboardService {

    @Autowired
    EntryTransaksiBukuDb entryTransaksiBukuDb;

    @Autowired
    EntryTransaksiSiswaDb entryTransaksiSiswaDb;

    @Autowired
    EntryGajiGuruDetailDb entryGajiGuruDetailDb;

    public long getTotalPendapatanSiswaByYear(int tahun) {
        Calendar cal = Calendar.getInstance();
        cal.set(tahun, Calendar.JANUARY, 1);
        Date startDate = cal.getTime();
        cal.set(tahun, Calendar.DECEMBER, 31);
        Date endDate = cal.getTime();

        List<EntryTransaksiSiswa> transaksiSiswa = entryTransaksiSiswaDb.findByTanggalPembayaranBetweenAndIsDeletedOrderByTanggalPembayaranAsc(startDate, endDate, false);
        long totalPendapatan = 0;
        for (EntryTransaksiSiswa transaksi : transaksiSiswa) {
            totalPendapatan += transaksi.getCash() + transaksi.getTransfer();
        }
        return totalPendapatan;
    }

    public long getTotalPendapatanBukuByYear(int tahun) {
        Calendar cal = Calendar.getInstance();
        cal.set(tahun, Calendar.JANUARY, 1);
        Date startDate = cal.getTime();
        cal.set(tahun, Calendar.DECEMBER, 31);
        Date endDate = cal.getTime();

        List<EntryTransaksiBuku> transaksiBuku = entryTransaksiBukuDb.findByTanggalBeliAndTanggalJualBetween(startDate, endDate);
        long totalPendapatan = 0;
        for (EntryTransaksiBuku transaksi : transaksiBuku) {
            totalPendapatan += (transaksi.getHargaJual() - transaksi.getHargaBeli()) * transaksi.getJumlahJual();
        }
        return totalPendapatan;
    }

    public long getTotalPengeluaranByYear(int tahun) {
        Calendar cal = Calendar.getInstance();
        cal.set(tahun, Calendar.JANUARY, 1);
        Date startDate = cal.getTime();
        cal.set(tahun, Calendar.DECEMBER, 31);
        Date endDate = cal.getTime();

        List<EntryGajiGuruDetail> transaksiGajiGuru = entryGajiGuruDetailDb.findByTanggalBetweenOrderByTanggalAsc(startDate, endDate);
        long totalPengeluaran = 0;
        for (EntryGajiGuruDetail transaksi : transaksiGajiGuru) {
            totalPengeluaran += transaksi.getFeeGuru();
        }
        return totalPengeluaran;
    }
    
}

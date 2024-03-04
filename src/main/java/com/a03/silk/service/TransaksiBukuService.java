package com.a03.silk.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.model.EntryBuku;
import com.a03.silk.repository.EntryTransaksiBukuDb;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransaksiBukuService {

    @Autowired
    EntryTransaksiBukuDb entryTransaksiBukuDb;

    public EntryBuku createEntryTransaksiBuku(EntryBuku entryBuku) {
        return entryTransaksiBukuDb.save(entryBuku);
    }

    public List<EntryBuku> getAllEntryTransaksiBuku() {
        List<EntryBuku> entryBukuList = entryTransaksiBukuDb.findAll();
        entryBukuList.forEach(this::calculateProfitAndTotals);
        return entryTransaksiBukuDb.findAll();
    }

    private void calculateProfitAndTotals(EntryBuku entryBuku) {
        BigDecimal keuntungan = entryBuku.getHargaJual().subtract(entryBuku.getHargaBeli());
        BigDecimal totalKeuntungan = keuntungan.multiply(BigDecimal.valueOf(entryBuku.getJumlahJual()));
        BigDecimal totalPenjualan = entryBuku.getHargaJual().multiply(BigDecimal.valueOf(entryBuku.getJumlahJual()));

        entryBuku.setKeuntungan(keuntungan);
        entryBuku.setTotalKeuntungan(totalKeuntungan);
        entryBuku.setTotalPenjualan(totalPenjualan);
    }
}

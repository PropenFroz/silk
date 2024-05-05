package com.a03.silk.service;

import com.a03.silk.dto.request.LaporanDataSiswaDTO;
import com.a03.silk.model.EntryTransaksiSiswa;
import com.a03.silk.model.IuranSiswa;
import com.a03.silk.model.JurusanKursus;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class LaporanDataSiswaPDF {

    @Autowired
    JurusanKursusService jurusanKursusService;

    private int entryCounter = 1;
    public void generateLaporanDataSiswa(HttpServletResponse response, String title, List<LaporanDataSiswaDTO> laporanDataSiswaList) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);

        Paragraph paragraph = new Paragraph("Laporan Data Siswa Purwacaraka Tahun " + title, fontTitle);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(20);
        document.add(paragraph);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{0.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f});

        Font fontHeader = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontHeader.setSize(12);

        String[] headers = {"No", "Bulan", "Siswa Baru", "Siswa Cuti", "Siswa Cuti Masuk Kembali", "Siswa Off", "Total Siswa Aktif"};
        for (String header : headers) {
            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(new Color(192, 192, 192)); // Warna abu-abu
            cell.setPadding(5);
            cell.setPhrase(new Phrase(header, fontHeader));
            table.addCell(cell);
        }

        Font fontContent = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontContent.setSize(10);

        String currentMonth = ""; // Inisialisasi bulan saat ini
        int currentMonthIndex = 1; // Inisialisasi nomor urutan

        for (LaporanDataSiswaDTO laporan : laporanDataSiswaList) {
            // Jika ada perubahan bulan, reset nomor urutan dan perbarui bulan saat ini
            if (!laporan.getBulan().equals(currentMonth)) {
                currentMonth = laporan.getBulan();
//                currentMonthIndex = 1;
            }

            PdfPCell indexCell = new PdfPCell(new Phrase(String.valueOf(currentMonthIndex), fontContent));
            indexCell.setBackgroundColor(new Color(255, 255, 255)); // Warna putih
            indexCell.setPadding(5);
            table.addCell(indexCell); // Tambahkan nomor urutan

            // Tambahkan data lainnya
            table.addCell(new Phrase(currentMonth, fontContent));
            table.addCell(new Phrase(String.valueOf(laporan.getJumlahSiswaBaru()), fontContent));
            table.addCell(new Phrase(String.valueOf(laporan.getJumlahSiswaCuti()), fontContent));
            table.addCell(new Phrase(String.valueOf(laporan.getJumlahSiswaCutiMasukKembali()), fontContent));
            table.addCell(new Phrase(String.valueOf(laporan.getJumlahSiswaOff()), fontContent));
            table.addCell(new Phrase(String.valueOf(laporan.getJumlahTotalSiswaAktif()), fontContent));

            currentMonthIndex++; // Tingkatkan nomor urutan untuk bulan berikutnya
        }

        document.add(table);
        document.close();
    }


    private void addEntryToTable(PdfPTable table, int counter, IuranSiswa entry, Font fontContent, EntryTransaksiSiswa entryKursus) {
        String tanggalPembayaran = (entryKursus != null) ? entryKursus.getTanggalPembayaran().toString() : "-";
        String namaSiswa = entry.getSiswa().getNamaSiswa();
        String namaJurusan = entry.getSiswa().getJurusanKursus().getNamaJurusan();
        String namaGrade = entry.getSiswa().getGradeKursus().getNamaGrade();

        // Periksa apakah entryKursus tidak null
        if (entryKursus != null) {
            // Jika tidak null, gunakan data dari entryKursus
            long uangPendaftaran = entryKursus.getUangPendaftaran();
            long uangKursus = entryKursus.getUangKursus(); // Ubah ke entryKursus
            long uangBuku = entryKursus.getUangBuku();
            long cash = entryKursus.getCash();
            long transfer = entryKursus.getTransfer();
            String keterangan = entryKursus.getKeterangan();

            table.addCell(new Phrase(String.valueOf(counter), fontContent));
            table.addCell(new Phrase(tanggalPembayaran, fontContent));
            table.addCell(new Phrase(namaSiswa, fontContent));
            table.addCell(new Phrase(namaJurusan, fontContent));
            table.addCell(new Phrase(namaGrade, fontContent));
            table.addCell(new Phrase(formatRupiah(uangPendaftaran), fontContent));
            table.addCell(new Phrase(formatRupiah(uangKursus), fontContent));
            table.addCell(new Phrase(formatRupiah(uangBuku), fontContent));
            table.addCell(new Phrase(formatRupiah(cash), fontContent));
            table.addCell(new Phrase(formatRupiah(transfer), fontContent));
            table.addCell(new Phrase(formatRupiah(cash + transfer), fontContent));
            table.addCell(new Phrase(keterangan, fontContent));
        } else {
            // Jika null, gunakan data dari IuranSiswa
            long uangPendaftaran = entryKursus.getUangPendaftaran();
            long uangKursus = entryKursus.getUangKursus(); // Ubah ke entryKursus
            long uangBuku = entryKursus.getUangBuku();
            long cash = entryKursus.getCash();
            long transfer = entryKursus.getTransfer();
            String keterangan = "-";

            table.addCell(new Phrase(String.valueOf(counter), fontContent));
            table.addCell(new Phrase(tanggalPembayaran, fontContent));
            table.addCell(new Phrase(namaSiswa, fontContent));
            table.addCell(new Phrase(namaJurusan, fontContent));
            table.addCell(new Phrase(namaGrade, fontContent));
            table.addCell(new Phrase(formatRupiah(uangPendaftaran), fontContent));
            table.addCell(new Phrase(formatRupiah(uangKursus), fontContent));
            table.addCell(new Phrase(formatRupiah(uangBuku), fontContent));
            table.addCell(new Phrase(formatRupiah(cash), fontContent));
            table.addCell(new Phrase(formatRupiah(transfer), fontContent));
            table.addCell(new Phrase(formatRupiah(cash + transfer), fontContent));
            table.addCell(new Phrase(keterangan, fontContent));
        }
    }

    private String formatRupiah(long nominal) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        String formattedValue = currencyFormat.format(nominal);
        formattedValue = formattedValue.replace(",00", "");
        return formattedValue;
    }
}

package com.a03.silk.service;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.a03.silk.model.EntryTransaksiSiswa;
import com.a03.silk.model.IuranSiswa;
import com.a03.silk.model.JurusanKursus;
import com.a03.silk.repository.JurusanKursusDb;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class LaporanIuranSiswaPDF {

    @Autowired
    JurusanKursusService jurusanKursusService;

    private int entryCounter = 1;

    public void generateLaporanIuranSiswa(HttpServletResponse response, String title, List<IuranSiswa> iuranSiswaList, JurusanKursus jurusanKursus) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);

        String jurusan = jurusanKursus.getNamaJurusan();

        Paragraph paragraph = new Paragraph("Laporan Iuran Siswa Jurusan " + jurusan + " Tahun " + title, fontTiltle);
        paragraph.setSpacingAfter(20);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        PdfPTable table = new PdfPTable(12);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{0.5f, 1.5f, 1.5f, 0.8f, 1.5f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.5f});

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.lightGray);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);
        font.setSize(12);

        cell.setPhrase(new Phrase("No", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Tanggal Pembayaran", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Nama Siswa", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Jurusan", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Grade", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Uang Pendaftaran", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Uang Kursus", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Uang Buku", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cash", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Transfer", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Jumlah", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Keterangan", font));
        table.addCell(cell);

        Font fontContent = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontContent.setSize(10);

        long totalUangPendaftaran = 0;
        long totalUangKursus = 0;
        long totalUangBuku = 0;
        long totalCash = 0;
        long totalTransfer = 0;

        for (IuranSiswa entry : iuranSiswaList) {
            EntryTransaksiSiswa entryKursus = entry.getEntryKursus(); // Mengambil entry kursus dari iuran siswa

            if (entryKursus != null) { // Periksa apakah ada entry kursus
                addEntryToTable(table, entryCounter++, entry, fontContent, entryKursus); // Memasukkan data ke tabel
                totalUangPendaftaran += entryKursus.getUangPendaftaran(); // Menambahkan jumlah uang ke total
                totalUangKursus += entryKursus.getUangKursus();
                totalUangBuku += entryKursus.getUangBuku();
                totalCash += entryKursus.getCash();
                totalTransfer += entryKursus.getTransfer();
            } else {
                // Jika tidak ada entry kursus, tambahkan ke tabel seperti biasa
                addEntryToTable(table, entryCounter++, entry, fontContent, null);
                totalUangPendaftaran += entryKursus.getUangPendaftaran(); // Menambahkan jumlah uang ke total
                totalUangKursus += entryKursus.getUangKursus();
                totalUangBuku += entryKursus.getUangBuku();
                totalCash += entryKursus.getCash();
                totalTransfer += entryKursus.getTransfer();
            }
        }

        Font fontTotal = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTotal.setSize(10);
        fontTotal.setStyle(Font.BOLD);

        PdfPCell totalCell = new PdfPCell(new Phrase("Total", fontTotal));
        totalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalCell.setColspan(5);

        table.addCell(totalCell);
        table.addCell(new Phrase(formatRupiah(totalUangPendaftaran), fontTotal));
        table.addCell(new Phrase(formatRupiah(totalUangKursus), fontTotal));
        table.addCell(new Phrase(formatRupiah(totalUangBuku), fontTotal));
        table.addCell(new Phrase(formatRupiah(totalCash), fontTotal));
        table.addCell(new Phrase(formatRupiah(totalTransfer), fontTotal));
        table.addCell(new Phrase(formatRupiah(totalCash + totalTransfer), fontTotal));

        PdfPCell emptyCell = new PdfPCell(new Phrase(""));
        emptyCell.setColspan(12);
        table.addCell(emptyCell);

        document.add(table);
        document.close();
    }
    private void addEntryToTable(PdfPTable table, int counter, IuranSiswa entry, Font fontContent, EntryTransaksiSiswa entryKursus) {
        String tanggalPembayaran = "-";
        if (entryKursus != null && entryKursus.getTanggalPembayaran() != null) {
            Date date = entryKursus.getTanggalPembayaran();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            tanggalPembayaran = sdf.format(date);
        }

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

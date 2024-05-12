package com.a03.silk.service;

import com.a03.silk.model.EntryTransaksiSiswa;
import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LaporanPembayaranKursusPDF {

    private int entryCounter = 1;

    public void generateLaporanTransaksiSiswa(HttpServletResponse response, String title, List<EntryTransaksiSiswa> entryTransaksiSiswaList) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);

        Paragraph paragraph = new Paragraph("LAPORAN TRANSAKSI SISWA UNTUK PERIODE " + title, fontTiltle);
        paragraph.setSpacingAfter(20);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        PdfPTable table = new PdfPTable(12);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 0.5f, 1.5f, 1.2f, 1.2f, 1.5f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.5f });

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.lightGray);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);
        font.setSize(12);

        cell.setPhrase(new Phrase("No", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Nama Siswa", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Tanggal Pembayaran", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Tanggal Daftar", font)); // Ubah nama kolom menjadi "Tanggal Daftar"
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

        for (EntryTransaksiSiswa entry : entryTransaksiSiswaList) {
            if (entry.getJenisTransaksi() == 3) {
                addEntryLainnyaToTable(table, "", entry, fontContent);
                totalUangPendaftaran += entry.getUangPendaftaran();
                totalUangKursus += entry.getUangKursus();
                totalUangBuku += entry.getUangBuku();
                totalCash += entry.getCash();
                totalTransfer += entry.getTransfer();
            } else {
                addEntryToTable(table, entryCounter++, entry, fontContent);
                totalUangPendaftaran += entry.getUangPendaftaran();
                totalUangKursus += entry.getUangKursus();
                totalUangBuku += entry.getUangBuku();
                totalCash += entry.getCash();
                totalTransfer += entry.getTransfer();
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

    private void addEntryLainnyaToTable(PdfPTable table, String counter, EntryTransaksiSiswa entry, Font fontContent) {
        table.addCell(new Phrase(String.valueOf(counter), fontContent)); // No
        table.addCell(new Phrase(entry.getSiswa().getNamaSiswa(), fontContent)); // Nama Siswa

        // Tanggal Pembayaran
        String tanggalPembayaran = "-";
        if (entry.getTanggalPembayaran() != null) {
            Date date = entry.getTanggalPembayaran();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            tanggalPembayaran = sdf.format(date);
        }
        table.addCell(new Phrase(tanggalPembayaran, fontContent)); // Tanggal Bayar

        // Tanggal Daftar
        table.addCell(new Phrase(tanggalPembayaran, fontContent)); // Tanggal Daftar

        table.addCell(new Phrase(entry.getSiswa().getGradeKursus().getNamaGrade(), fontContent)); // Grade
        table.addCell(new Phrase(formatRupiah(entry.getUangPendaftaran()), fontContent)); // Uang Pendaftaran
        table.addCell(new Phrase(formatRupiah(entry.getUangKursus()), fontContent)); // Uang Kursus
        table.addCell(new Phrase(formatRupiah(entry.getCash()), fontContent)); // Cash
        table.addCell(new Phrase(formatRupiah(entry.getTransfer()), fontContent)); // Transfer

        // Jumlah
        long jumlah = entry.getCash() + entry.getTransfer();
        table.addCell(new Phrase(formatRupiah(jumlah), fontContent));

        table.addCell(new Phrase(entry.getKeterangan(), fontContent)); // Keterangan
    }
    private void addEntryToTable(PdfPTable table, int counter, EntryTransaksiSiswa entry, Font fontContent) {
        table.addCell(new Phrase(String.valueOf(counter), fontContent)); // No
        table.addCell(new Phrase(entry.getSiswa().getNamaSiswa(), fontContent)); // Nama Siswa

        // Tanggal Pembayaran
        String tanggalPembayaran = "-";
        if (entry.getTanggalPembayaran() != null) {
            Date date = entry.getTanggalPembayaran();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            tanggalPembayaran = sdf.format(date);
        }
        table.addCell(new Phrase(tanggalPembayaran, fontContent)); // Tanggal Bayar

        // Tanggal Daftar
        table.addCell(new Phrase(tanggalPembayaran, fontContent)); // Tanggal Daftar

        table.addCell(new Phrase(entry.getSiswa().getGradeKursus().getNamaGrade(), fontContent)); // Grade
        table.addCell(new Phrase(formatRupiah(entry.getUangPendaftaran()), fontContent)); // Uang Pendaftaran
        table.addCell(new Phrase(formatRupiah(entry.getUangKursus()), fontContent)); // Uang Kursus
        table.addCell(new Phrase(formatRupiah(entry.getUangBuku()), fontContent)); // Uang Buku
        table.addCell(new Phrase(formatRupiah(entry.getCash()), fontContent)); // Cash
        table.addCell(new Phrase(formatRupiah(entry.getTransfer()), fontContent)); // Transfer

        // Jumlah
        long jumlah = entry.getCash() + entry.getTransfer();
        table.addCell(new Phrase(formatRupiah(jumlah), fontContent));

        table.addCell(new Phrase(entry.getKeterangan(), fontContent)); // Keterangan
    }



    private String formatRupiah(long nominal) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        String formattedValue = currencyFormat.format(nominal);
        formattedValue = formattedValue.replace(",00", "");
        return formattedValue;
    }
}
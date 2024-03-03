package com.a03.silk.service;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import com.a03.silk.model.EntryKursus;
import com.a03.silk.model.EntryLainnya;
import com.a03.silk.model.EntryPendaftaran;
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

public class LaporanTransaksiSiswaPDF {

    private List<EntryKursus> entryKursusList;
    private List<EntryLainnya> entryLainnyaList;
    private List<EntryPendaftaran> entryPendaftaranList;

    private int entryCounter = 1;

    public void generateLaporanTransaksi(HttpServletResponse response, String title) throws DocumentException, IOException {

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
        table.setWidths(new float[] { 0.5f, 1.5f, 1.5f, 0.8f, 1.5f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.5f });

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

        for (EntryPendaftaran entry : entryPendaftaranList) {
            addEntryToTable(table, entryCounter++, entry, fontContent);
            totalUangPendaftaran += entry.getUangPendaftaran();
            totalUangKursus += entry.getUangKursus();
            totalUangBuku += entry.getUangBuku();
            totalCash += entry.getCash();
            totalTransfer += entry.getTransfer();
        }

        for (EntryKursus entry : entryKursusList) {
            addEntryToTable(table, entryCounter++, entry, fontContent);
            totalUangKursus += entry.getUangKursus();
            totalUangBuku += entry.getUangBuku();
            totalCash += entry.getCash();
            totalTransfer += entry.getTransfer();
        }

        for (EntryLainnya entry : entryLainnyaList) {
            addEntryToTable(table, entry, fontContent);
            totalUangPendaftaran += entry.getUangPendaftaran();
            totalUangKursus += entry.getUangKursus();
            totalUangBuku += entry.getUangBuku();
            totalCash += entry.getCash();
            totalTransfer += entry.getTransfer();
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

    private void addEntryToTable(PdfPTable table, int counter, EntryPendaftaran entry, Font fontContent) {
        table.addCell(new Phrase(String.valueOf(counter), fontContent));
        table.addCell(new Phrase(entry.getTanggalPembayaran().toString(), fontContent));
        table.addCell(new Phrase(entry.getNamaSiswa(), fontContent));
        table.addCell(new Phrase(entry.getJurusan(), fontContent));
        table.addCell(new Phrase(entry.getGrade(), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getUangPendaftaran()), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getUangKursus()), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getUangBuku()), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getCash()), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getTransfer()), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getTransfer() + entry.getCash()), fontContent));
        table.addCell(new Phrase(entry.getKeterangan(), fontContent));
    }

    private void addEntryToTable(PdfPTable table, int counter, EntryKursus entry, Font fontContent) {
        table.addCell(new Phrase(String.valueOf(counter), fontContent));
        table.addCell(new Phrase(entry.getTanggalPembayaran().toString(), fontContent));
        table.addCell(new Phrase(entry.getNamaSiswa(), fontContent));
        table.addCell(new Phrase(entry.getJurusan(), fontContent));
        table.addCell(new Phrase(entry.getGrade(), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getUangPendaftaran()), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getUangKursus()), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getUangBuku()), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getCash()), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getTransfer()), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getTransfer() + entry.getCash()), fontContent));
        table.addCell(new Phrase(entry.getKeterangan(), fontContent));
    }

    private void addEntryToTable(PdfPTable table, EntryLainnya entry, Font fontContent) {
        table.addCell(new Phrase(""));
        table.addCell(new Phrase(entry.getTanggalPembayaran().toString(), fontContent));
        table.addCell(new Phrase(entry.getNamaSiswa(), fontContent));
        table.addCell(new Phrase(entry.getJurusan(), fontContent));
        table.addCell(new Phrase(entry.getGrade(), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getUangPendaftaran()), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getUangKursus()), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getUangBuku()), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getCash()), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getTransfer()), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getTransfer() + entry.getCash()), fontContent));
        table.addCell(new Phrase(entry.getKeterangan(), fontContent));
    }

    private String formatRupiah(long nominal) {
        // Buat instance NumberFormat dengan locale Indonesia untuk format Rupiah
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return currencyFormat.format(nominal);
    }

    public void setEntryKursusList(List<EntryKursus> listEntry) {
        this.entryKursusList = listEntry;
    }

    public void setEntryLainnyaList(List<EntryLainnya> listEntry) {
        this.entryLainnyaList = listEntry;
    }

    public void setEntryPendaftaranList(List<EntryPendaftaran> listEntry) {
        this.entryPendaftaranList = listEntry;
    }
}
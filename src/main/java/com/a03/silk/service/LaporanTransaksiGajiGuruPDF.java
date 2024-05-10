package com.a03.silk.service;

// LaporanTransaksiGajiGuruPDF.java
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.a03.silk.model.EntryGajiGuruDetail;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
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

public class LaporanTransaksiGajiGuruPDF {

    public void generateLaporanTransaksiGajiGuru(HttpServletResponse response, String title,
            List<EntryGajiGuruDetail> entryGajiGuruList) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);

        Paragraph paragraphTitle = new Paragraph("LAPORAN TRANSAKSI GAJI GURU UNTUK PERIODE " + title, fontTitle);
        paragraphTitle.setSpacingAfter(20);
        paragraphTitle.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraphTitle);

        // Tambahkan nama guru
        if (!entryGajiGuruList.isEmpty()) {
            String namaGuru = entryGajiGuruList.get(0).getEntryGajiGuru().getGuru().getNamaGuru();
            Font fontGuru = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            fontGuru.setSize(14);

            Paragraph paragraphGuru = new Paragraph("Nama Guru: " + namaGuru, fontGuru);
            paragraphGuru.setSpacingAfter(20);
            paragraphGuru.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraphGuru);
        }

        Map<String, List<EntryGajiGuruDetail>> entriesByJurusan = new HashMap<>();

        // Mengelompokkan entri berdasarkan jurusan
        for (EntryGajiGuruDetail entry : entryGajiGuruList) {
            String jurusan = entry.getEntryGajiGuru().getJurusanKursus().getNamaJurusan();
            List<EntryGajiGuruDetail> entries = entriesByJurusan.getOrDefault(jurusan, new ArrayList<>());
            entries.add(entry);
            entriesByJurusan.put(jurusan, entries);
        }

        // Membuat dan menambahkan tabel untuk setiap jurusan
        for (Map.Entry<String, List<EntryGajiGuruDetail>> entry : entriesByJurusan.entrySet()) {
            String jurusan = entry.getKey();
            List<EntryGajiGuruDetail> entries = entry.getValue();
            PdfPTable table = createTable(jurusan); // Membuat tabel untuk jurusan tertentu

            // Menambahkan entri ke tabel
            for (EntryGajiGuruDetail detail : entries) {
                addEntryToTable(table, detail);
            }

            // Menghitung total minggu untuk jurusan tertentu
            totalMinggu(table, entries, jurusan);

            // Menambahkan tabel ke dokumen
            document.add(new Paragraph("Tabel untuk Jurusan: " + jurusan));
            document.add(table);
        }

        // Membuat tabel untuk rincian fee guru
        PdfPTable feeGuruTable = new PdfPTable(3);
        feeGuruTable.setWidthPercentage(100f);
        feeGuruTable.setWidths(new float[] { 0.5f, 2f, 1.5f });

        // Tambahkan judul untuk tabel rincian fee guru
        Paragraph feeGuruTitle = new Paragraph("Rincian Fee Guru", fontTitle);
        feeGuruTitle.setSpacingAfter(20);
        feeGuruTitle.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(feeGuruTitle);

        // Tambahkan header untuk tabel rincian fee guru
        PdfPCell feeGuruCell = new PdfPCell();
        feeGuruCell.setBackgroundColor(CMYKColor.lightGray);
        feeGuruCell.setPadding(5);

        Font feeGuruFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        feeGuruFont.setColor(CMYKColor.BLACK);
        feeGuruFont.setSize(12);

        feeGuruCell.setPhrase(new Phrase("No", feeGuruFont));
        feeGuruTable.addCell(feeGuruCell);
        feeGuruCell.setPhrase(new Phrase("Jurusan", feeGuruFont));
        feeGuruTable.addCell(feeGuruCell);
        feeGuruCell.setPhrase(new Phrase("Fee Guru", feeGuruFont));
        feeGuruTable.addCell(feeGuruCell);

        // Hitung total fee guru dari semua jurusan dan tambahkan ke tabel rincian fee guru
        long totalFeeGuruAllJurusan = 0;
        int no = 1;
        for (Map.Entry<String, List<EntryGajiGuruDetail>> entry : entriesByJurusan.entrySet()) {
            String jurusan = entry.getKey();
            long totalFeeGuruJurusan = 0;

            // Menghitung total fee guru untuk jurusan tertentu
            for (EntryGajiGuruDetail detail : entry.getValue()) {
                totalFeeGuruJurusan += detail.getFeeGuru();
            }

            // Menambahkan baris untuk setiap jurusan di tabel rincian fee guru
            feeGuruTable.addCell(new Phrase(String.valueOf(no++), feeGuruFont));
            feeGuruTable.addCell(new Phrase(jurusan, feeGuruFont));
            feeGuruTable.addCell(new Phrase(formatRupiah(totalFeeGuruJurusan), feeGuruFont));

            // Menambahkan total fee guru jurusan ke total fee guru semua jurusan
            totalFeeGuruAllJurusan += totalFeeGuruJurusan;
        }

        // Tambahkan baris untuk total fee guru dari semua jurusan di tabel rincian fee guru
        feeGuruTable.addCell(""); // Kolom No
        feeGuruTable.addCell("Total"); // Kolom Jurusan
        feeGuruTable.addCell(new Phrase(formatRupiah(totalFeeGuruAllJurusan), feeGuruFont)); // Kolom Fee Guru

        // Tambahkan tabel rincian fee guru ke dokumen
        document.add(feeGuruTable);

        document.close();
    }

    private PdfPTable createTable(String jurusan) {
        PdfPTable table = new PdfPTable(11);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 0.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f });

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.lightGray);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);
        font.setSize(12);

        cell.setPhrase(new Phrase("No", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Murid", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Grade", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Uang Kursus", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Tanggal", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Minggu 1", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Minggu 2", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Minggu 3", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Minggu 4", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Fee Guru", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Keterangan", font));
        table.addCell(cell);

        // Baris baru untuk total minggu 1, 2, 3, 4, dan fee guru
        table.addCell(""); // Kolom No
        table.addCell(""); // Kolom Murid
        table.addCell(""); // Kolom Grade
        table.addCell(""); // Kolom Uang Kursus
        table.addCell(""); // Kolom Tanggal
        table.addCell(""); // Kolom Minggu 1 (akan diisi dengan total)
        table.addCell(""); // Kolom Minggu 2 (akan diisi dengan total)
        table.addCell(""); // Kolom Minggu 3 (akan diisi dengan total)
        table.addCell(""); // Kolom Minggu 4 (akan diisi dengan total)
        table.addCell(""); // Kolom Fee Guru (akan diisi dengan total)
        table.addCell("Total"); // Kolom Keterangan (akan menampilkan "Total")

        return table;
    }

    private void addEntryToTable(PdfPTable table, EntryGajiGuruDetail entry) {
        int entryCounter = table.getRows().size() - 1; // Mendapatkan nomor urutan dari jumlah baris yang sudah ada di tabel

        Font fontContent = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontContent.setSize(10);

        table.addCell(new Phrase(String.valueOf(entryCounter), fontContent));
        table.addCell(new Phrase(entry.getSiswa().getNamaSiswa(), fontContent));
        table.addCell(new Phrase(entry.getSiswa().getGradeKursus().getNamaGrade(), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getUangKursus()), fontContent)); // Menambahkan Uang Kursus
        String tanggalBeli = "-";
        Date date = entry.getTanggal();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        tanggalBeli = sdf.format(date);
        table.addCell(new Phrase(tanggalBeli, fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getMinggu1()), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getMinggu2()), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getMinggu3()), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getMinggu4()), fontContent));
        table.addCell(new Phrase(formatRupiah(entry.getFeeGuru()), fontContent));
        table.addCell(new Phrase(entry.getKeterangan(), fontContent));
    }

    private void totalMinggu(PdfPTable table, List<EntryGajiGuruDetail> entries, String jurusan) {
        int totalMinggu1 = 0;
        int totalMinggu2 = 0;
        int totalMinggu3 = 0;
        int totalMinggu4 = 0;
        long totalFeeGuru = 0;

        // Menghitung total minggu dari semua entri untuk jurusan tertentu
        for (EntryGajiGuruDetail entry : entries) {
            totalMinggu1 += entry.getMinggu1();
            totalMinggu2 += entry.getMinggu2();
            totalMinggu3 += entry.getMinggu3();
            totalMinggu4 += entry.getMinggu4();
            totalFeeGuru += entry.getFeeGuru();
        }

        // Menambahkan total minggu 1, 2, 3, 4, dan fee guru ke tabel
        Font fontContent = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontContent.setSize(10);
        fontContent.setStyle(Font.BOLD);

        table.addCell(new Phrase("", fontContent)); // Kolom No
        table.addCell(new Phrase("Total", fontContent)); // Kolom Keterangan
        table.addCell(new Phrase("", fontContent)); // Kolom Grade
        table.addCell(new Phrase("", fontContent)); // Kolom Uang Kursus
        table.addCell(new Phrase("", fontContent)); // Kolom Tanggal
        table.addCell(new Phrase(formatRupiah(totalMinggu1), fontContent)); // Kolom Total Minggu 1
        table.addCell(new Phrase(formatRupiah(totalMinggu2), fontContent)); // Kolom Total Minggu 2
        table.addCell(new Phrase(formatRupiah(totalMinggu3), fontContent)); // Kolom Total Minggu 3
        table.addCell(new Phrase(formatRupiah(totalMinggu4), fontContent)); // Kolom Total Minggu 4
        table.addCell(new Phrase(formatRupiah(totalFeeGuru), fontContent)); // Kolom Total Fee Guru

        table.addCell(new Phrase("", fontContent)); // Kolom Keterangan

        // Menghapus baris kosong di bagian atas tabel
        table.deleteRow(1);
    }

    private String formatRupiah(long nominal) {
        Locale locale = new Locale("id", "ID");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(nominal);
    }
}

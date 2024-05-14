package com.a03.silk.service;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.a03.silk.model.EntryTransaksiSiswa;
import com.a03.silk.model.IuranSiswa;
import com.a03.silk.model.JurusanKursus;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

public class LaporanIuranSiswaPDF {

    private int entryCounter = 1;

    public void generateLaporanIuranSiswa(HttpServletResponse response, String title, List<IuranSiswa> iuranSiswaList, JurusanKursus jurusanKursus) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTitle = new Font(Font.TIMES_ROMAN, 20);
        Paragraph paragraph = new Paragraph("Laporan Iuran Siswa Jurusan " + jurusanKursus.getNamaJurusan() + " Tahun " + title, fontTitle);
        paragraph.setSpacingAfter(20);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        PdfPTable table = new PdfPTable(15);
        table.setWidthPercentage(102f);

        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);

        Font fontHeader = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
        cell.setPhrase(new Paragraph("No", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Paragraph("Nama", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Paragraph("Tanggal Daftar", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Paragraph("Januari", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Paragraph("Februari", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Paragraph("Maret", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Paragraph("April", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Paragraph("Mei", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Paragraph("Juni", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Paragraph("Juli", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Paragraph("Agustus", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Paragraph("September", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Paragraph("Oktober", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Paragraph("November", fontHeader));
        table.addCell(cell);
        cell.setPhrase(new Paragraph("Desember", fontHeader));
        table.addCell(cell);

        Font fontContent = new Font(Font.TIMES_ROMAN, 10);

        // Iterate through iuranSiswaList to populate the table
        for (int i = 0; i < iuranSiswaList.size(); i++) {
            IuranSiswa iuranSiswa = iuranSiswaList.get(i);


            PdfPCell innerCell = new PdfPCell();
            innerCell.setPhrase(new Paragraph(String.valueOf(i + 1), fontContent)); // Number
            table.addCell(innerCell);

            innerCell = new PdfPCell(new Paragraph(iuranSiswa.getSiswa().getNamaSiswa(), fontContent)); // Nama Siswa
            table.addCell(innerCell);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date tanggalDaftar = iuranSiswa.getSiswa().getTanggalDaftar();
            String formattedTanggalDaftar = tanggalDaftar != null ? dateFormat.format(tanggalDaftar) : "-";
            innerCell = new PdfPCell(new Paragraph(formattedTanggalDaftar, fontContent)); // Tanggal Daftar
            table.addCell(innerCell);

            // Iterate through 12 months
            for (int month = 1; month <= 12; month++) {
                EntryTransaksiSiswa entryKursus = findEntryForMonth(iuranSiswa.getSiswa().getDaftarTransaksiSiswa(), month);
                String cellText = "-";
                if (entryKursus != null) {
                    Date tanggalPembayaran = entryKursus.getTanggalPembayaran();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String tanggalPembayaranFinal = sdf.format(tanggalPembayaran);
                    cellText = tanggalPembayaranFinal;
                }
                innerCell = new PdfPCell(new Paragraph(cellText, fontContent)); // Tanggal Pembayaran
                table.addCell(innerCell);
            }
        }

        document.add(table);
        document.close();
    }

    private EntryTransaksiSiswa findEntryForMonth(List<EntryTransaksiSiswa> entryTransaksiSiswaList, int month) {
        for (EntryTransaksiSiswa entry : entryTransaksiSiswaList) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(entry.getTanggalPembayaran());
            int entryMonth = calendar.get(Calendar.MONTH) + 1; // Bulan dimulai dari 0 dalam Calendar, sehingga ditambahkan 1
            if (entryMonth == month) {
                return entry;
            }
        }
        return null;
    }
}

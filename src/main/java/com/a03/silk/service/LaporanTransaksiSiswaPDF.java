package com.a03.silk.service;

import java.io.IOException;
import java.util.List;

import com.a03.silk.model.EntryKursus;
import com.a03.silk.model.EntryLainnya;
import com.a03.silk.model.EntryPendaftaran;
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

public class LaporanTransaksiSiswaPDF {

    private List<EntryKursus> entryKursusList;
    private List<EntryLainnya> entryLainnyaList;
    private List<EntryPendaftaran> entryPendaftaranList;

    public void generateLaporanTransaksi(HttpServletResponse response, String title) throws DocumentException, IOException {

        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);

        Paragraph paragraph = new Paragraph("LAPORAN TRANSAKSI SISWA UNTUK PERIODE " + title, fontTiltle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        PdfPTable table = new PdfPTable(10);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 1, 2, 2, 1, 2, 2, 2, 1, 1, 2 });

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.MAGENTA);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);

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
        cell.setPhrase(new Phrase("Keterangan", font));
        table.addCell(cell);

        for (EntryPendaftaran entry : entryPendaftaranList) {
            addEntryToTable(table, entry);
        }

        for (EntryKursus entry : entryKursusList) {
            addEntryToTable(table, entry);
        }

        for (EntryLainnya entry : entryLainnyaList) {
            addEntryToTable(table, entry);
        }

        document.add(table);
        document.close();
    }

    private void addEntryToTable(PdfPTable table, EntryPendaftaran entry) {
        table.addCell(entry.getTanggalPembayaran().toString());
        table.addCell(entry.getNamaSiswa());
        table.addCell(entry.getJurusan());
        table.addCell(entry.getGrade());
        table.addCell(String.valueOf(entry.getUangPendaftaran()));
        table.addCell(String.valueOf(entry.getUangKursus()));
        table.addCell(String.valueOf(entry.getUangBuku()));
        table.addCell(String.valueOf(entry.getCash()));
        table.addCell(String.valueOf(entry.getTransfer()));
        table.addCell(entry.getKeterangan());
    }

    private void addEntryToTable(PdfPTable table, EntryKursus entry) {
        table.addCell(entry.getTanggalPembayaran().toString());
        table.addCell(entry.getNamaSiswa());
        table.addCell(entry.getJurusan());
        table.addCell(entry.getGrade());
        table.addCell(String.valueOf(entry.getUangPendaftaran()));
        table.addCell(String.valueOf(entry.getUangKursus()));
        table.addCell(String.valueOf(entry.getUangBuku()));
        table.addCell(String.valueOf(entry.getCash()));
        table.addCell(String.valueOf(entry.getTransfer()));
        table.addCell(entry.getKeterangan());
    }

    private void addEntryToTable(PdfPTable table, EntryLainnya entry) {
        table.addCell(entry.getTanggalPembayaran().toString());
        table.addCell(entry.getNamaSiswa());
        table.addCell(entry.getJurusan());
        table.addCell(entry.getGrade());
        table.addCell(String.valueOf(entry.getUangPendaftaran()));
        table.addCell(String.valueOf(entry.getUangKursus()));
        table.addCell(String.valueOf(entry.getUangBuku()));
        table.addCell(String.valueOf(entry.getCash()));
        table.addCell(String.valueOf(entry.getTransfer()));
        table.addCell(entry.getKeterangan());
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

package com.a03.silk.service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import com.a03.silk.model.EntryTransaksiBuku;
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

public class LaporanTransaksiBukuPDF {

    private int entryCounter = 1;
    private long rowTotalKeuntungan = 0;
    private long rowTotalPenjualan = 0;
    private DecimalFormat currencyFormat;

    public LaporanTransaksiBukuPDF() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("id", "ID"));
        symbols.setCurrencySymbol("");
        currencyFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        currencyFormat.setDecimalFormatSymbols(symbols);
    }

    public void generateLaporanTransaksiBuku(HttpServletResponse response, String title, List<EntryTransaksiBuku> entryTransaksiBukuList) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);

        Paragraph paragraph = new Paragraph("LAPORAN TRANSAKSI BUKU UNTUK PERIODE " + title, fontTitle);
        paragraph.setSpacingAfter(20);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        PdfPTable table = new PdfPTable(12);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 0.5f, 1.5f, 1.5f, 0.8f, 1.5f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f });

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.lightGray);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);
        font.setSize(12);

        cell.setPhrase(new Phrase("No", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Nama Buku", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Jurusan", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Tanggal Beli", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Jumlah Beli", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Tanggal Jual", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Jumlah Jual", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Harga Beli", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Harga Jual", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Keuntungan", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Total Keuntungan", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Total Penjualan", font));
        table.addCell(cell);

        Font fontContent = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontContent.setSize(10);

        for (EntryTransaksiBuku entry : entryTransaksiBukuList) {
            addEntryToTable(table, entryCounter++, entry, fontContent);
        }

        Font fontTotal = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTotal.setSize(10);
        fontTotal.setStyle(Font.BOLD);

        PdfPCell totalCell = new PdfPCell(new Phrase("Total", fontTotal));
        totalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalCell.setColspan(10);

        table.addCell(totalCell);
        table.addCell(new Phrase(formatToRupiah(rowTotalKeuntungan), fontTotal));
        table.addCell(new Phrase(formatToRupiah(rowTotalPenjualan), fontTotal));

        document.add(table);
        document.close();
    }

    private void addEntryToTable(PdfPTable table, int entryCounter, EntryTransaksiBuku entry, Font fontContent) {
        table.addCell(new Phrase(String.valueOf(entryCounter), fontContent));

        table.addCell(new Phrase(entry.getBukuPurwacaraka().getNamaBuku(), fontContent));

        table.addCell(new Phrase(entry.getBukuPurwacaraka().getJurusanKursus().getNamaJurusan(), fontContent));

        table.addCell(new Phrase(entry.getTanggalBeli().toString(), fontContent));

        long jumlahBeli = entry.getJumlahBeli();
        table.addCell(new Phrase(String.valueOf(jumlahBeli), fontContent));

        table.addCell(new Phrase(entry.getTanggalJual().toString(), fontContent));

        long jumlahJual = entry.getJumlahJual();
        table.addCell(new Phrase(String.valueOf(entry.getJumlahJual()), fontContent));

        long hargaBeli = entry.getHargaBeli();
        table.addCell(new Phrase(formatToRupiah(hargaBeli), fontContent));

        long hargaJual = entry.getHargaJual();
        table.addCell(new Phrase(formatToRupiah(hargaJual), fontContent));

        long keuntungan = hargaJual - hargaBeli;
        table.addCell(new Phrase(formatToRupiah(keuntungan), fontContent));

        long totalKeuntungan = keuntungan * jumlahJual;
        table.addCell(new Phrase(formatToRupiah(totalKeuntungan), fontContent));
        rowTotalKeuntungan += totalKeuntungan;

        long totalPenjualan = hargaJual * jumlahJual;
        table.addCell(new Phrase(formatToRupiah(totalPenjualan), fontContent));
        rowTotalPenjualan += totalPenjualan;
    }

    private String formatToRupiah(long value) {
        return "Rp" + currencyFormat.format(value).replace(",00", "");
    }
}
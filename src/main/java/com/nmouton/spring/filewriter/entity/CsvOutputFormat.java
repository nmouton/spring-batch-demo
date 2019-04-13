package com.nmouton.spring.filewriter.entity;

import java.util.List;
import java.util.Objects;

public class CsvOutputFormat {

    private String invoiceNumber;
    private String invoiceDate;
    private List<InvoiceLine> lines;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public CsvOutputFormat setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public CsvOutputFormat setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
        return this;
    }

    public List<InvoiceLine> getLines() {
        return lines;
    }

    public CsvOutputFormat setLines(List<InvoiceLine> lines) {
        this.lines = lines;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CsvOutputFormat)) return false;
        CsvOutputFormat that = (CsvOutputFormat) o;
        return Objects.equals(getInvoiceNumber(), that.getInvoiceNumber()) &&
                Objects.equals(getInvoiceDate(), that.getInvoiceDate()) &&
                Objects.equals(getLines(), that.getLines());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceNumber(), getInvoiceDate(), getLines());
    }

    @Override
    public String toString() {
        return "CsvOutputFormat{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", lines=" + lines +
                '}';
    }
}

package com.nmouton.spring.filewriter.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class CsvInputFormat {

    private Integer invoiceId;
    private String invoiceNumber;
    private String invoiceDate;
    private String supplier;
    private BigDecimal total;

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public CsvInputFormat setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
        return this;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public CsvInputFormat setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public CsvInputFormat setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
        return this;
    }

    public String getSupplier() {
        return supplier;
    }

    public CsvInputFormat setSupplier(String supplier) {
        this.supplier = supplier;
        return this;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public CsvInputFormat setTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CsvInputFormat)) return false;
        CsvInputFormat that = (CsvInputFormat) o;
        return Objects.equals(getInvoiceId(), that.getInvoiceId()) &&
                Objects.equals(getInvoiceNumber(), that.getInvoiceNumber()) &&
                Objects.equals(getInvoiceDate(), that.getInvoiceDate()) &&
                Objects.equals(getSupplier(), that.getSupplier()) &&
                Objects.equals(getTotal(), that.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(), getInvoiceNumber(), getInvoiceDate(), getSupplier(), getTotal());
    }

    @Override
    public String toString() {
        return "CsvInputFormat{" +
                "invoiceId=" + invoiceId +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", supplier='" + supplier + '\'' +
                ", total=" + total +
                '}';
    }
}

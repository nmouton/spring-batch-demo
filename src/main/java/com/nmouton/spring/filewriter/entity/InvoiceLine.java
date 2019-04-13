package com.nmouton.spring.filewriter.entity;

import java.util.Objects;

public class InvoiceLine {

    private Integer lineNumber;
    private String description;
    private String price;

    public Integer getLineNumber() {
        return lineNumber;
    }

    public InvoiceLine setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public InvoiceLine setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public InvoiceLine setPrice(String price) {
        this.price = price;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceLine)) return false;
        InvoiceLine that = (InvoiceLine) o;
        return Objects.equals(getLineNumber(), that.getLineNumber()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getPrice(), that.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLineNumber(), getDescription(), getPrice());
    }

    @Override
    public String toString() {
        return "InvoiceLine{" +
                "lineNumber='" + lineNumber + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}

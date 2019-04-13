package com.nmouton.spring.filewriter;

import com.nmouton.spring.filewriter.entity.CsvInputFormat;
import com.nmouton.spring.filewriter.entity.CsvOutputFormat;
import com.nmouton.spring.filewriter.entity.InvoiceLine;
import com.nmouton.spring.filewriter.service.ApiToCsvService;
import org.json.JSONObject;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;

public class InvoiceProcessor implements ItemProcessor<CsvInputFormat, CsvOutputFormat> {

    private ApiToCsvService apiToCsvService;

    public InvoiceProcessor(ApiToCsvService apiToCsvService) {
        this.apiToCsvService = apiToCsvService;
    }

    @Override
    public CsvOutputFormat process(CsvInputFormat csvInputFormat) {
        JSONObject coupaInvoice = apiToCsvService.getJsonResponse(csvInputFormat.getInvoiceId());

        List<InvoiceLine> invoiceLines = mapInvoiceLines(coupaInvoice);
        CsvOutputFormat outputFormat = new CsvOutputFormat()
                .setInvoiceNumber(coupaInvoice.get("invoice-number").toString())
                .setInvoiceDate(coupaInvoice.get("invoice-date").toString())
                .setLines(invoiceLines);
        return outputFormat;
    }

    private List<InvoiceLine> mapInvoiceLines(JSONObject coupaResponse) {
        List<InvoiceLine> invoiceLines = new ArrayList<>();
        for(Object o: coupaResponse.getJSONArray("invoice-lines")){
            InvoiceLine invoiceLine = new InvoiceLine();
            if ( o instanceof JSONObject) {
                invoiceLine.setLineNumber(((JSONObject) o).getInt("line-num"));
                invoiceLine.setDescription(((JSONObject) o).getString("description"));
                invoiceLine.setPrice(((JSONObject) o).getString("price"));
                invoiceLines.add(invoiceLine);
            }
        }
        return invoiceLines;
    }
}

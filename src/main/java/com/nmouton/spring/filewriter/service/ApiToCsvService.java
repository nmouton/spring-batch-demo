package com.nmouton.spring.filewriter.service;

import com.nmouton.spring.filewriter.error.RestTemplateResponseErrorHandler;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
public class ApiToCsvService {

    private RestTemplate restTemplate;

    @Value("${coupa.api.url}")
    private String couplaUrl;

    @Value("${coupa.api.key}")
    private String apiKey;

    @Autowired
    public ApiToCsvService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    public String getInvoice(Integer invoiceId) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("X-COUPA-API-KEY", apiKey);
        headers.add("Accept", "application/json");
        return restTemplate.exchange(
                couplaUrl + "/api/invoices/" + invoiceId, HttpMethod.GET, new HttpEntity<>(headers),
                String.class, invoiceId).getBody();
    }

    public void getAllInvoices() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("X-COUPA-API-KEY", apiKey);
        headers.add("Accept", "application/xml");
        ResponseEntity<String> coupaResponse = restTemplate.exchange(
                couplaUrl + "/api/invoices/", HttpMethod.GET, new HttpEntity<>(headers),
                String.class);
        System.out.println(coupaResponse);
    }

    public JSONObject getJsonResponse(Integer invoiceId) {
        String coupaInvoice = getInvoice(invoiceId);

        JSONObject json = null;
        if (null != coupaInvoice) {
            json = new JSONObject(coupaInvoice);
        }

        return json;
    }

}

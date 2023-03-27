package com.backendtest.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import com.backendtest.dto.Jobs;
import com.backendtest.service.RestClientService;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/jobs")
public class JobsController {
    @Autowired
    RestClientService restClientService;

    @Autowired
    RestTemplate restTemplate;

    // @GetMapping
    // public List<Jobs> getJobs() {
    // return Arrays.asList(restClientService.getAllJobs().getBody());
    // }

    @GetMapping("/getAll")
    public ResponseEntity<String> getJobs() {
        return restClientService.getJobs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jobs> getDetailJobs(@PathVariable("id") String id) {
        return restClientService.getJobsDetail(id);
    }

    @GetMapping(produces = "text/csv")
    public ResponseEntity<ByteArrayResource> downloadCsv() throws IOException {
        List<Jobs> jobs = Arrays.asList(restClientService.getAllJobs().getBody());

        ByteArrayResource resource = createCsvFile(jobs);
        // System.out.println(jobs.get(0).getCompany());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"jobs.csv\"");
        headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .body(resource);
    }

    private ByteArrayResource createCsvFile(List<Jobs> jobs) throws IOException {
        StringWriter writer = new StringWriter();
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("ID", "Type", "URL", "Created At", "Company", "Company URL",
                        "Location", "Title", "Description", "How to Apply", "Company Logo"));

        for (Jobs job : jobs) {
            csvPrinter.printRecord(job.getId(), job.getType(), job.getUrl(), job.getCreatedAt(),
                    job.getCompany(), job.getCompanyUrl(), job.getLocation(), job.getTitle(),
                    job.getDescription(), job.getHowToApply(), job.getCompanyLogo());
        }

        csvPrinter.flush();
        csvPrinter.close();
        return new ByteArrayResource(writer.toString().getBytes());
    }

}

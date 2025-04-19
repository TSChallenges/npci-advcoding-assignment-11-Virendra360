package com.agrichallenge.agdata.controller;

import com.agrichallenge.agdata.model.AgData;
import com.agrichallenge.agdata.service.AgDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/agdata")
public class AgDataController {

    @Autowired
    private AgDataService agDataService;

    @GetMapping("")
    public ResponseEntity<List<AgData>> getAllProducts() throws IOException {
        List<AgData> products = agDataService.loadAgData();
        if (products.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // GET /api/agdata/crop-count?cropName=corn
    @GetMapping("crop-count")
    public ResponseEntity<Long> getCount(String cropName) throws IOException {
        long count = agDataService.getCropCount(cropName);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    //  GET /api/agdata/average-yield?cropName=wheat
    @GetMapping("average-yield")
    public ResponseEntity<Double> getAverage(String cropName) throws IOException {
        double avg = agDataService.getAverageYield(cropName);
        return new ResponseEntity<>(avg, HttpStatus.OK);
    }

//    GET /api/agdata/by-region?region=Midwest
    @GetMapping("by-region")
    public ResponseEntity<List<AgData>> getRegion(String region) throws IOException {
        List<AgData> list = agDataService.getRecordsByRegion(region);
        if (list.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}

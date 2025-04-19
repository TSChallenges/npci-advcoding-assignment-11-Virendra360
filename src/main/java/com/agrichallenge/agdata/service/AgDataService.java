package com.agrichallenge.agdata.service;

import com.agrichallenge.agdata.model.AgData;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

// Service class for analyzing agricultural data

@Service
public class AgDataService {

    private final List<AgData> agDataList;

    public AgDataService() throws IOException {
        this.agDataList = loadAgData();
    }

    public List<AgData> loadAgData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // Load the data from JSON file from resources
        InputStream inputStream = getClass().getResourceAsStream("/data/agdata.json");
        // Map the JSON to a List of AgData objects
        return objectMapper.readValue(inputStream, new TypeReference<List<AgData>>() {});
    }

    //  Count how many times a specific crop appears in the dataset
    public Long getCropCount(String cropName) {
        return agDataList.stream().filter(data -> data.getCrop().equalsIgnoreCase(cropName)).count();

    }

    public double getAverageYield(String cropName) {
        return agDataList.stream()
                .filter(data -> data.getCrop().equalsIgnoreCase(cropName))
                .mapToDouble(AgData::getYield)
                .average()
                .orElse(0.0);
    }

    //  Get all records from a specific region
    public List<AgData> getRecordsByRegion(String region) {
        List<AgData> list=agDataList.stream().filter(r->r.getRegion().equalsIgnoreCase(region)).collect(Collectors.toList());
        return list;
    }

}


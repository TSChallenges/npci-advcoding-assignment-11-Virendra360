package com.agrichallenge.agdata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgData {

    private Integer id;
    private String crop;
    private String region;
    private Integer yield;
    private Integer year;

}

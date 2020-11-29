package com.sal.tema2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Long isbn;
    private String title;
    private String authors;
    private String genre;
    private int noOfPages;
    private int year;
    private String publishingHouse;
    private int noOfVolumes;
    private int noOfVolumesGiven;
}

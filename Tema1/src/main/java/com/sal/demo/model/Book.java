package com.sal.demo.model;

import lombok.Data;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
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

package com.android.chapter06.litepal;

import org.litepal.crud.DataSupport;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Book extends DataSupport {
    private int id;

    private String author;

    private double price;

    private int pages;

    private String name;

    private String press;
}
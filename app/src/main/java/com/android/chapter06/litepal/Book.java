package com.android.chapter06.litepal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Book {
    private int id;

    private String author;

    private double price;

    private int pages;

    private String name;

    private String press;
}
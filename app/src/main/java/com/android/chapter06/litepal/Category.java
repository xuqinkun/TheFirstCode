package com.android.chapter06.litepal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Category {
    private int id;

    private String categoryName;

    private int categoryCode;
}
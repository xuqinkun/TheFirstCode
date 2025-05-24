package com.android.chapter03;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Fruit {
    private String name;
    private int imageId;
}

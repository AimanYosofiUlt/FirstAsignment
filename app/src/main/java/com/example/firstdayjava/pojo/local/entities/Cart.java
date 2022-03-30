package com.example.firstdayjava.pojo.local.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cart {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    int id = 0;

    String itemCode;
    String userCode;
    int quantity;
}

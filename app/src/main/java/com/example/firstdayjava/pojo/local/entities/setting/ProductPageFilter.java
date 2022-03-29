package com.example.firstdayjava.pojo.local.entities.setting;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class ProductPageFilter {
    @PrimaryKey
    public Integer id;

    @NonNull
    @ColumnInfo(defaultValue = "0")
    Integer minRange = Integer.valueOf("0");

    @NonNull
    @ColumnInfo(defaultValue = "0")
    Integer maxRange = Integer.valueOf("0");

    @NonNull
    @ColumnInfo(defaultValue = SORT_BY_NAME)
    String OrderBy = SORT_BY_NAME;

    @NonNull
    @ColumnInfo(defaultValue = GRID_SHOW)
    String SortingType = GRID_SHOW;

    public static final String GRID_SHOW = "GRID_SHOW";
    public static final String LINEAR_SHOW = "LINEAR_SHOW";
    public static final String SORT_BY_NAME = "name";
    public static final String SORT_BY_PRICE_HIGHER = " price ASC ";
    public static final String SORT_BY_PRICE_LOWER = "  price DESC ";

    public ProductPageFilter() {
    }


    @Ignore
    public ProductPageFilter(@NonNull Integer minRange, @NonNull Integer maxRange, @NonNull String orderBy, @NonNull String sortingType) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        OrderBy = orderBy;
        SortingType = sortingType;
    }

    @NonNull
    public Integer getMinRange() {
        return minRange;
    }

    public void setMinRange(@NonNull Integer minRange) {
        this.minRange = minRange;
    }

    @NonNull
    public Integer getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(@NonNull Integer maxRange) {
        this.maxRange = maxRange;
    }

    @NonNull
    public String getOrderBy() {
        return OrderBy;
    }

    public void setOrderBy(@NonNull String orderBy) {
        OrderBy = orderBy;
    }

    @NonNull
    public String getSortingType() {
        return SortingType;
    }

    public void setSortingType(@NonNull String sortingType) {
        SortingType = sortingType;
    }
}
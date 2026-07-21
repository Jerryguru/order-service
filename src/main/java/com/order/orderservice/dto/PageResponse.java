package com.order.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {

    // ==========================================================
    // List of Records
    // ==========================================================
    private List<T> content;

    // ==========================================================
    // Current Page Number
    // ==========================================================
    private int page;

    // ==========================================================
    // Number of Records Per Page
    // ==========================================================
    private int size;

    // ==========================================================
    // Total Number of Records
    // ==========================================================
    private long totalElements;

    // ==========================================================
    // Total Number of Pages
    // ==========================================================
    private int totalPages;

    // ==========================================================
    // Is First Page?
    // ==========================================================
    private boolean first;

    // ==========================================================
    // Is Last Page?
    // ==========================================================
    private boolean last;

}
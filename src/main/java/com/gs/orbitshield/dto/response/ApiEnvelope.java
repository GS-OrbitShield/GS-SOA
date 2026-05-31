package com.gs.orbitshield.dto.response;

import java.time.Instant;
import java.util.List;

public class ApiEnvelope<T> {

    private String status;
    private T data;
    private PaginationInfo pagination;
    private Instant timestamp;

    public ApiEnvelope(String status, T data, Instant timestamp) {
        this.status = status;
        this.data = data;
        this.timestamp = timestamp;
    }

    public ApiEnvelope(String status, T data, PaginationInfo pagination, Instant timestamp) {
        this.status = status;
        this.data = data;
        this.pagination = pagination;
        this.timestamp = timestamp;
    }

    public static <T> ApiEnvelope<T> success(T data) {
        return new ApiEnvelope<>("success", data, Instant.now());
    }

    public static <T> ApiEnvelope<List<T>> paged(List<T> data, int page, int size, long totalElements) {
        PaginationInfo pagination = new PaginationInfo(page, size, totalElements, (totalElements + size - 1) / size);
        return new ApiEnvelope<>("success", data, pagination, Instant.now());
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public PaginationInfo getPagination() {
        return pagination;
    }

    public void setPagination(PaginationInfo pagination) {
        this.pagination = pagination;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public static class PaginationInfo {
        private int page;
        private int size;
        private long totalElements;
        private long totalPages;

        public PaginationInfo(int page, int size, long totalElements, long totalPages) {
            this.page = page;
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
        }

        public int getPage() {
            return page;
        }

        public int getSize() {
            return size;
        }

        public long getTotalElements() {
            return totalElements;
        }

        public long getTotalPages() {
            return totalPages;
        }
    }
}


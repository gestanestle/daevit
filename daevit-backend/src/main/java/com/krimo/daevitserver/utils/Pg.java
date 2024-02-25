package com.krimo.daevitserver.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Pg {

    private Pg() {

    }
    
    public static Pageable find(int offset, int count) { return PageRequest.of(offset - 1, count, Sort.by("createdAt").descending()); }

}
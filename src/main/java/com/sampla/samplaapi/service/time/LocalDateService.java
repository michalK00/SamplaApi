package com.sampla.samplaapi.service.time;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class LocalDateService {
    public LocalDate now() {
        return LocalDate.now();
    }
}

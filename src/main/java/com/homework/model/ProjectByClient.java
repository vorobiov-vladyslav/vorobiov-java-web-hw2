package com.homework.model;

import java.time.LocalDate;

public record ProjectByClient(long id, LocalDate startDate, LocalDate finishDate) {
}

package com.koon.blogsearchservice.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {
    public static LocalDateTime toLocalDateTime(String iosTime) {
        return LocalDateTime.from(
                Instant.from(
                        DateTimeFormatter.ISO_DATE_TIME.parse("2017-11-06T06:02:00.000Z")
                ).atZone(ZoneId.of("Asia/Seoul"))
        );
    }
}

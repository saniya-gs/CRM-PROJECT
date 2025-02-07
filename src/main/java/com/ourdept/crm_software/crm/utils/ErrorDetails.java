package com.ourdept.crm_software.crm.utils;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class  ErrorDetails {
	@JsonIgnore
    private LocalDateTime timestamp;
    private String error;
    private String path;

    public ErrorDetails(LocalDateTime timestamp, String error, String path) {
        this.timestamp = timestamp;
        this.error = error;
        this.path = path;
    }
}
package com.jsw.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
    private List<String> successMessages = new ArrayList<>();
    private List<String> errorMessages = new ArrayList<>();
    private T o;
}

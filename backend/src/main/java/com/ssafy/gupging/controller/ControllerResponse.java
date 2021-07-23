package com.ssafy.gupging.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControllerResponse<T> {

    private String status;
    private T data;
}

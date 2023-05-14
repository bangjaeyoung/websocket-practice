package com.websocket.common.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ListResponseDto<T> {

    private List<T> data;
}

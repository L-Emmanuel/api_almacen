package com.almacen.api.payloads.Dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Pagination<T> {

    private List<T> content;

    private Boolean empty;
    private Boolean first;
    private Boolean last;

    private Integer number;
    private Integer numberOfElement;
    private Integer size;
    private Integer totalPages;

    private Long totalElements;
}

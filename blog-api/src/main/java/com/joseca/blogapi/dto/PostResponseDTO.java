package com.joseca.blogapi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDTO {
    private List<PostDTO> data;

    private int pageNumber;

    private int pageSize;

    private int totalElementsCount;

    private int totalPages;

    private boolean isLastPage;
}

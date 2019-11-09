package com.quangchinh.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsDTO {
    private String title;
    private String image;
    private String content;
    private int view;
    private boolean approved;
    @JsonProperty("user_id")
    private String userId;
}

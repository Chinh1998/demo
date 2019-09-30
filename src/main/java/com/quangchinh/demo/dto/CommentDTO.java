package com.quangchinh.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {

    private String id;
    private String content_cmt;
    private String userId;
    private String newsId;
}

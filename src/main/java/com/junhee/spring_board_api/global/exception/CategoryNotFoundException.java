package com.junhee.spring_board_api.global.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super("카테고리를 찾을 수 없습니다.");
    }
}

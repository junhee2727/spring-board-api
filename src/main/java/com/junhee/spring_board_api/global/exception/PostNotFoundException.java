package com.junhee.spring_board_api.global.exception;

public class PostNotFoundException extends RuntimeException{

    public PostNotFoundException(){
        super("게시글을 찾을 수 없습니다.");
    }
}

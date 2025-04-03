package com.kt.csai.domain.support.dto;

import lombok.Data;

@Data
public class NoticeDto{
    String title;
    String content;

    public String getTitle(){
        return title;
    }
    
    public String getContent(){
        return content;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setContent(String content){
        this.content = content;
    }

    /* 생성자 */
    public void noticeDto(String title, String content){
        this.title = title;
        this.content = content;
    }

}
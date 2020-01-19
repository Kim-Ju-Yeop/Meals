package org.techtown.project5.Model;

public class DTO {

    // 맴버변수로 title, content 를 선언합니다.
    private String title;
    private String content;


    // 각각의 맴버변수를 get, set 해줍니다.
    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

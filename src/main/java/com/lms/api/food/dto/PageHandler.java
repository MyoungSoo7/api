package com.lms.api.food.dto;


import lombok.Data;

@Data
public class PageHandler {

    private int totalCount;  // 총 api 갯수
    private int pageSize;   // 한 페이지 크기
    private int navSize=10; // 페이지 내비게이션의 크기
    private int totoalPage=45; // 전체 페이지수
    private int page;    // 현재 페이지
    private int beginPage; // 내비게이션의 시작 페이지
    private int endPage=45;   // 내비게이션의 마지막 페이지
    private Boolean prev; // 이전 페이지로 이동하는 링크를 보여줄 것인지의 여부
    private Boolean next;  // 다음 페이지로 이동하는 링크를 보여줄 것인지의 여부

    public PageHandler(int page,int pageSize){
        this.page=page;
        this.pageSize=pageSize;
        //this.totoalPage = totoalPage;
        //totoalPage = (int)Math.ceil(totalCount/pageSize);
        beginPage = (page-1)/navSize*navSize+1;
        endPage = Math.min(beginPage+navSize -1, totoalPage);
        //endPage=totoalPage;
        prev = beginPage != 1;
        next = endPage != totoalPage;
    }

    void print(){
        System.out.println("pageHandler"+page);
        System.out.print(prev ? "이전" : "");
        for(int i=beginPage; i<=endPage; i++){
            System.out.print(i==page ? i : i);
        }
        System.out.print(next ? "다음" : "");
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", navSize=" + navSize +
                ", totoalPage=" + totoalPage +
                ", page=" + page +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", prev=" + prev +
                ", next=" + next +
                '}';
    }
}

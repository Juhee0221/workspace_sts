package com.green.Board2.VO;

import javax.print.attribute.standard.PrinterURI;

public class PageVO {
    private int nowPage; //현재 선택된 페이지 번호
    private int totalDateCnt; //전체 데이터 수 (전체 게시글 수)
    private int displayDateCnt; //한 페이지에 보여지는 데이터 수
    private int displayPageCnt; //한 페이지에 보여지는 페이지 수
    private int beginPage; //화면에 보이는 첫번째 페이지 번호
    private int endPage; //화면에 보이는 마지막 페이지 번호
    private boolean prev; //이전버튼의 유무
    private boolean next; //다음버튼의 유무

    public PageVO(){
        nowPage = 1;
        displayDateCnt = 5;
        displayPageCnt = 5;
    }


    public void setPageInfo(){
        //화면에 보이는 마지막 페이지 번호 세팅
        //displayPageCnt, nowPage
        endPage = (int)Math.ceil(nowPage/ (double)displayPageCnt) * displayPageCnt;
        //화면에 보이는 첫번째 페이지 번호 세팅
        beginPage = endPage - displayPageCnt + 1;

        //전체 페이지 수
        int totalPageCnt = (int)Math.ceil(totalDateCnt/(double)displayDateCnt);

        //next 버튼의 유무
        if(endPage < totalPageCnt){
            next = true;
        }
        else {
            next = false;
            endPage = totalPageCnt;
        }
        //prev 버튼의 유무
        prev = beginPage == 1 ? false : true ;
    }

    public void setTotalDateCnt(int totalDateCnt){
        this.totalDateCnt = totalDateCnt;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public boolean getPrev() {
        return prev;
    }

    public boolean getNext() {
        return next;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getDisplayDateCnt() {
        return displayDateCnt;
    }

    public int getDisplayPageCnt() {
        return displayPageCnt;
    }

    public int getNowPage() {
        return nowPage;
    }
}

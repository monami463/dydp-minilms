package kr.co.dongyang.study.minilms.util;

public class PageUtil {
    /**
     * 전체개수
     */
    private long totalCount;

    /**
     * 한페이지에 나오는 개수
     */
    private long pageSize;

    /**
     * 페이지블럭 개수
     */
    private long pageBlockSize;

    /*
    전체페이지 갯수
    한페이지에 나오는 갯수
    페이지블럭 갯수
    현재페이지번호
     */
    public PageUtil(long totalCount, long pageSize, long pageBlockSize){
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageBlockSize = pageBlockSize;
    }

    public void pager(){

        System.out.println("페이징 출력!!!");
        System.out.println("전체갯수" + totalCount);
        System.out.println("한페이지에 나오는 갯수" + pageSize);
        System.out.println("페이지블럭 갯수" + pageBlockSize );

    }

}

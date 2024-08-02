package com.study.mybatis.common.vo;

public class PageInfo {
	private int totalRecord;	// db에서 한 행을 1record라고 부르기때문에 borad테이블의 전체 행의 수를 뜻한다
	private int numPerPage;	// 한 페이지에 보여줄 record의 수
	private int pagePerBlock;	// 블록당 페이지 수[1][2][3][4][5]
	private int nowPage;			// 현재 해당하는 페이지
			
	//위의 4개를 가지고 계산
	private int totalPage;		// (전체 레코드수/한페이지에 보여줄 레코드 수)를 올림함 전체 페이지의 수
	private int startPage;			// 페이징바의 시작 수
	private int endPage;			// 페이징바의 끝 수
	
	private String keyField;
	private String keyWord;
	
	public PageInfo() {
		super();
	}
	public PageInfo(int totalRecord, int numPerPage, int pagePerBlock, int nowPage, int totalPage, int startPage,
			int endPage) {
		super();
		this.totalRecord = totalRecord;
		this.numPerPage = numPerPage;
		this.pagePerBlock = pagePerBlock;
		this.nowPage = nowPage;
		this.totalPage = totalPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}
	public PageInfo(String keyField, String keyWord) {

		this.keyField = keyField;
		this.keyWord = keyWord;
	}
	
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	public int getPagePerBlock() {
		return pagePerBlock;
	}
	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public String getKeyField() {
		return keyField;
	}
	public void setKeyField(String keyField) {
		this.keyField = keyField;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	@Override
	public String toString() {
		return "PageInfo [totalRecord=" + totalRecord + ", numPerPage=" + numPerPage + ", pagePerBlock=" + pagePerBlock
				+ ", nowPage=" + nowPage + ", totalPage=" + totalPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", keyField=" + keyField + ", keyWord=" + keyWord + "]";
	}
	
	
}

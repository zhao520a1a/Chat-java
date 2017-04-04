package com.xin.vo;

import java.util.ArrayList;

public class PageBean {
	
	private int curPage; //当前页
	private int rowsPerPage = 5; //每页的行数
	private int maxRowCount; //总行数
	private int maxPage; //总页数
	private ArrayList<Article> data; //当前页中每行数据信息的集合
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	public int getMaxRowCount() {
		return maxRowCount;
	}
	public void setMaxRowCount(int maxRowCount) {
		this.maxRowCount = maxRowCount;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public ArrayList<Article> getData() {
		return data;
	}
	public void setData(ArrayList<Article> data) {
		this.data = data;
	}
	
}

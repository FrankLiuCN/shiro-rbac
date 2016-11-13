package com.frank.dto;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;

public class PageModel <T> implements Serializable {
	
	private List<T> models;
	
	private Integer pageNum;
	
	private Integer pageSize;
	
	private Integer startRow;
	
	private Integer endRow;
	
	private Long total;
	
	private Integer pages;
	
	private Integer firstPage;
	
	private Integer lastPage;
	
	private Integer prePage;
	
	private Integer nextPage;
	
	private int[] navigatepageNums;
	
	private boolean isFirstPage;
	
	private boolean isLastPage;
	
	private boolean isHasPrePage;
	
	private boolean isHasNextPage;	
	
	public PageModel(List<T> models,PageInfo pageInfo){
		this.models=models;
		this.pageNum=pageInfo.getPageNum();
		this.pageSize=pageInfo.getPageSize();
		this.startRow=pageInfo.getStartRow();
		this.endRow=pageInfo.getEndRow();
		this.total=pageInfo.getTotal();
		this.pages=pageInfo.getPages();
		this.firstPage=pageInfo.getFirstPage();
		this.lastPage=pageInfo.getLastPage();
		this.prePage=pageInfo.getPrePage();
		this.nextPage=pageInfo.getNextPage();
		this.navigatepageNums= pageInfo.getNavigatepageNums();
		this.isHasPrePage=pageInfo.isHasNextPage();
		this.isHasNextPage=pageInfo.isHasNextPage();
		this.isFirstPage=pageInfo.isIsFirstPage();
		this.isLastPage=pageInfo.isIsLastPage();
	}

	public List<T> getModels() {
		return models;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public boolean isFirstPage() {
		return isFirstPage;
	}

	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}
	

	public boolean isHasPrePage() {
		return isHasPrePage;
	}

	public void setHasPrePage(boolean isHasPrePage) {
		this.isHasPrePage = isHasPrePage;
	}

	public boolean isHasNextPage() {
		return isHasNextPage;
	}

	public void setHasNextPage(boolean isHasNextPage) {
		this.isHasNextPage = isHasNextPage;
	}

	public void setModels(List<T> models) {
		this.models = models;
	}

	public Integer getPrePage() {
		return prePage;
	}

	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public int[] getNavigatepageNums() {
		return navigatepageNums;
	}

	public void setNavigatepageNums(int[] navigatepageNums) {
		this.navigatepageNums = navigatepageNums;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public void setFirstPage(Integer firstPage) {
		this.firstPage = firstPage;
	}

	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}
	
}

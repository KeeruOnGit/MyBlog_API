package com.myblogapi.utility;

import java.util.List;

import com.myblogapi.payload.PostDto;

public class PostResponse {
	
	private List<PostDto> posts;
	private int pageNo;
	private int pageSize;
	private long noOfPosts;
	private int totalNoOfPages;
	private boolean last;
	
	public List<PostDto> getPosts() {
		return posts;
	}
	public void setPosts(List<PostDto> posts) {
		this.posts = posts;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getNoOfPosts() {
		return noOfPosts;
	}
	public void setNoOfPosts(long noOfPosts) {
		this.noOfPosts = noOfPosts;
	}
	public int getTotalNoOfPages() {
		return totalNoOfPages;
	}
	public void setTotalNoOfPages(int totalNoOfPages) {
		this.totalNoOfPages = totalNoOfPages;
	}
	public boolean isLast() {
		return last;
	}
	public void setLast(boolean last) {
		this.last = last;
	}

}

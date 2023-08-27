package com.myblogapi.service;

import com.myblogapi.payload.PostDto;
import com.myblogapi.utility.PostResponse;

public interface PostService {

	public PostDto createPost(PostDto postDto);

	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

	public PostDto getPostById(long id);

	public PostDto updatePostById(long id, PostDto postDto);

	public void deletePostById(long id);

}

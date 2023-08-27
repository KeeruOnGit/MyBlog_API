package com.myblogapi.service;

import java.util.List;

import com.myblogapi.payload.CommentDto;

public interface CommentService {

	public CommentDto createComment(long id, CommentDto comDto);

	public List<CommentDto> getAllComments();

	public CommentDto getCommentById(long id);

	public CommentDto updateCommentById(long id, CommentDto commentDto);

	public void deleteById(long id);

}

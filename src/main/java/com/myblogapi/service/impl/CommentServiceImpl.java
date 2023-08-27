package com.myblogapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myblogapi.entity.Comment;
import com.myblogapi.entity.Post;
import com.myblogapi.exception.ResourceNotFoundException;
import com.myblogapi.payload.CommentDto;
import com.myblogapi.repository.CommentRepository;
import com.myblogapi.repository.PostRepository;
import com.myblogapi.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository comRepo;
	
	@Autowired
	private PostRepository postRepo;

	@Override
	public CommentDto createComment(long id, CommentDto comDto) {
		Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
		Comment comment = mapToEntity(comDto);
		comment.setPost(post);
		Comment savedComment = comRepo.save(comment);
		return mapToDto(savedComment);
	}
	
	@Override
	public List<CommentDto> getAllComments() {
		List<Comment> comments = comRepo.findAll();
		return comments.stream().map(commentDtos -> mapToDto(commentDtos)).collect(Collectors.toList());
	}
	
	@Override
	public CommentDto getCommentById(long id) {
		Comment comment = comRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", id));
		return mapToDto(comment);
	}
	
	@Override
	public CommentDto updateCommentById(long id, CommentDto commentDto) {
		Comment comment = comRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", id));
		comment.setName(commentDto.getName());
		comment.setEmailId(commentDto.getEmailId());
		comment.setBody(commentDto.getBody());
		Comment updatedCom = comRepo.save(comment);
		
		return mapToDto(updatedCom);
	}

	@Override
	public void deleteById(long id) {
		comRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", id));
		comRepo.deleteById(id);
	}
	
	public Comment mapToEntity(CommentDto comDto) {
		Comment com = new Comment();
		com.setName(comDto.getName());
		com.setEmailId(comDto.getEmailId());
		com.setBody(comDto.getBody());
		return com;
	}
	public CommentDto mapToDto(Comment com) {
		CommentDto dto = new CommentDto();
		dto.setId(com.getId());
		dto.setName(com.getName());
		dto.setEmailId(com.getEmailId());
		dto.setBody(com.getBody());
		return dto;
	}

}

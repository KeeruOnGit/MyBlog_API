package com.myblogapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myblogapi.payload.CommentDto;
import com.myblogapi.service.CommentService;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService comService;
	
	@PostMapping("/api/posts/{id}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable("id") long id,
			@RequestBody CommentDto comDto){
		CommentDto savedComment = comService.createComment(id, comDto);
		return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
	}
	
	@GetMapping("/api/posts/comments")
	public List<CommentDto> getAllComments(){
		List<CommentDto> allComments = comService.getAllComments();
		return allComments;
		
	}
	
	@GetMapping("api/posts/comments/{id}")
	public ResponseEntity<CommentDto> getCommentByPost(@PathVariable("id") long id){
		CommentDto comment = comService.getCommentById(id);
		return new ResponseEntity<>(comment, HttpStatus.OK);
	}
	
	@PutMapping("api/posts/comments/{id}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable("id") long id,
			@RequestBody CommentDto commentDto){
		CommentDto updateCommentById = comService.updateCommentById(id, commentDto);
		return new ResponseEntity<>(updateCommentById, HttpStatus.OK);
	}
	
	@DeleteMapping("api/posts/comments/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable("id") long id){
		comService.deleteById(id);
		return new ResponseEntity<>("Comment Deleted Successfully!!!", HttpStatus.OK);
	}

}

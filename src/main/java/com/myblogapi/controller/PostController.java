package com.myblogapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myblogapi.payload.PostDto;
import com.myblogapi.service.PostService;
import com.myblogapi.utility.PostResponse;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
		PostDto savedPostDto = postService.createPost(postDto);
		ResponseEntity<PostDto> response = new ResponseEntity<>(savedPostDto, HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping
	public PostResponse getAllPosts(@RequestParam(name="pageNo", defaultValue="0", required=false) int pageNo,
			@RequestParam(name="pageSize", defaultValue="5", required=false) int pageSize,
			@RequestParam(name="sortBy", defaultValue="id", required=false) String sortBy,
			@RequestParam(name="sortDir", defaultValue="asc", required=false) String sortDir){
		return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
		PostDto postDto = postService.getPostById(id);
		return new ResponseEntity<>(postDto, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePostById(@PathVariable("id") long id, @RequestBody PostDto postDto){
		PostDto savedPostDto = postService.updatePostById(id, postDto);
		return new ResponseEntity<>(savedPostDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePostByID(@PathVariable("id") long id){
		postService.deletePostById(id);
		return new ResponseEntity<>("Post Deleted Successfully!!!", HttpStatus.OK);
	}
	

}

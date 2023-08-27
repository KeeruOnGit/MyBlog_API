package com.myblogapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myblogapi.entity.Post;
import com.myblogapi.exception.ResourceNotFoundException;
import com.myblogapi.payload.PostDto;
import com.myblogapi.repository.PostRepository;
import com.myblogapi.service.PostService;
import com.myblogapi.utility.PostResponse;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepo;

	@Override
	public PostDto createPost(PostDto postDto) {
		Post post = mapToEntity(postDto);
		Post savedPost = postRepo.save(post);
		PostDto savedPostDto = mapToDto(savedPost);
		return savedPostDto;
	}
	
	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Post> page = postRepo.findAll(pageable);
		List<Post> allPosts = page.getContent();
		List<PostDto> allPostDtos = allPosts.stream().map(postDto -> mapToDto(postDto)).collect(Collectors.toList());
		PostResponse response = new PostResponse();
		response.setPosts(allPostDtos);
		response.setPageNo(page.getNumber());
		response.setPageSize(page.getSize());
		response.setNoOfPosts(page.getNumberOfElements());
		response.setTotalNoOfPages(page.getTotalPages());
		response.setLast(page.isLast());
		return response;
	}
	
	@Override
	public PostDto getPostById(long id) {
		Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
		PostDto postDto = mapToDto(post);
		return postDto;
	}
	
	@Override
	public void deletePostById(long id) {
		postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
		postRepo.deleteById(id);
	}
	
	@Override
	public PostDto updatePostById(long id, PostDto postDto) {
		Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
		post.setAuthor(postDto.getAuthor());
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		Post savedPost = postRepo.save(post);
		return mapToDto(savedPost);
	}
	
	public Post mapToEntity(PostDto postDto) {
		Post post = new Post();
		post.setAuthor(postDto.getAuthor());
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		return post;
	}
	public PostDto mapToDto(Post post) {
		PostDto dto = new PostDto();
		dto.setId(post.getId());
		dto.setAuthor(post.getAuthor());
		dto.setTitle(post.getTitle());
		dto.setDescription(post.getDescription());
		dto.setContent(post.getContent());
		return dto;
	}

}

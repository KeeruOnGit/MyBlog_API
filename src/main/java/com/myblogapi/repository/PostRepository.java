package com.myblogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myblogapi.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}

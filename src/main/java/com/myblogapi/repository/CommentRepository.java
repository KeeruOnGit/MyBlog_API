package com.myblogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myblogapi.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}

package com.gxdxx.programadmin.repository;

import com.gxdxx.programadmin.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

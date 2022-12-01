package com.example.springpagandsort.repository;

import com.example.springpagandsort.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post,Integer> {
    Page<Post> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
}

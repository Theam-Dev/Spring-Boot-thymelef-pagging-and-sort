package com.example.springpagandsort.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.springpagandsort.model.Post;
import com.example.springpagandsort.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PostController {
    @Autowired
    private PostRepository tutorialRepository;

    @GetMapping("/")
    public String getAll(Model model, @RequestParam(required = false) String keyword,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "5") int size,
                         @RequestParam(defaultValue = "id,asc") String[] sort) {
        try {
            List<Post> post = new ArrayList<Post>();

            String sortField = sort[0];
            String sortDirection = sort[1];

            Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Order order = new Order(direction, sortField);

            Pageable pageable = PageRequest.of(page - 1, size, Sort.by(order));

            Page<Post> pageTuts;
            if (keyword == null) {
                pageTuts = tutorialRepository.findAll(pageable);
            } else {
                pageTuts = tutorialRepository.findByTitleContainingIgnoreCase(keyword, pageable);
                model.addAttribute("keyword", keyword);
            }

            post = pageTuts.getContent();

            model.addAttribute("post", post);
            model.addAttribute("currentPage", pageTuts.getNumber() + 1);
            model.addAttribute("totalItems", pageTuts.getTotalElements());
            model.addAttribute("totalPages", pageTuts.getTotalPages());
            model.addAttribute("pageSize", size);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDirection", sortDirection);
            model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "post";
    }
}

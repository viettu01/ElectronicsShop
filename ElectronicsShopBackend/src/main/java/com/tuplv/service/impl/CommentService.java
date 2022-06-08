package com.tuplv.service.impl;

import com.tuplv.converter.CommentConverter;
import com.tuplv.repository.CommentRepository;
import com.tuplv.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentService implements ICommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentConverter commentConverter;
}

package org.love_156.document.controller;


import jakarta.annotation.Resource;
import org.love_156.document.service.DocumentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/document")
public class DocumentController {
    @Resource
    private DocumentService documentService;

}

package com.autobots.automanager.main.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.main.entity.Document;

@Component
public class DocumentSelector {
    public Document selectDocuments(List<Document> documents, long id) {
        Document selected = null;
        for (Document document : documents) {
            if (document.get_id() == id) {
                selected = document;
            }
        }
        return selected;
    }
}
package com.autobots.automanager.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.main.entity.Document;
import com.autobots.automanager.main.model.DocumentSelector;
import com.autobots.automanager.main.repository.DocumentRepository;
import com.autobots.automanager.main.model.DocumentUpdater;

@RestController
@RequestMapping("/document")
public class DocumentController {
	@Autowired
	private DocumentRepository _repository;
	@Autowired
	private DocumentSelector _selector;

	@GetMapping("/document/{id}")
	public Document getDocument(@PathVariable long id) {
		List<Document> documents = _repository.findAll();
		return _selector.selectDocuments(documents, id);
	}

	@GetMapping("/documents")
	public List<Document> getDocuments() {
		List<Document> documents = _repository.findAll();
		return documents;
	}

	@PostMapping("/register")
	public void registerDocument(@RequestBody Document document) {
		_repository.save(document);
	}

	@PutMapping("/update")
	public void atualizarCliente(@RequestBody Document updatedDocument) {
		Document document = _repository.getById(updatedDocument.get_id());
		DocumentUpdater updater = new DocumentUpdater();
		updater.update(document, updatedDocument);
		_repository.save(document);
	}

	@DeleteMapping("/delete")
	public void deleteDocument(@RequestBody Document deleteDocument) {
		Document document = _repository.getById(deleteDocument.get_id());
		_repository.delete(document);
	}
}
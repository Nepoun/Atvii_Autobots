package com.autobots.automanager.main.model;

import com.autobots.automanager.main.entity.Document;

public class DocumentUpdater {
	private NullStringVerifier verifier = new NullStringVerifier();

	public void update(Document document, Document updated) {
		if (updated != null) {
			if (!verifier.verify(updated.get_number())) {
				document.set_number(updated.get_number());
			}
			if (!verifier.verify(updated.get_type())) {
				document.set_number(updated.get_type());
			}
		}
	}

}
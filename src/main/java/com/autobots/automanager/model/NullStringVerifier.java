package com.autobots.automanager.model;

import java.util.Date;
import java.util.Set;

import com.autobots.automanager.entity.Address;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.enumerator.DocumentType;
import com.autobots.automanager.enumerator.VehicleType;

public class NullStringVerifier {

	public boolean verify(String _data) {
		boolean _null = true;
		if (!(_data == null)) {
			if (!_data.isBlank()) {
				_null = false;
			}
		}
		return _null;
	}

	public boolean verify(DocumentType type) {
		boolean _null = true;
		if (!(type == null)) {
			_null = false;
		}
		return _null;
	}

	public boolean verify(VehicleType type) {
		boolean _null = true;
		if (!(type == null)) {
			_null = false;
		}
		return _null;
	}

	public boolean verify(Address address) {
		boolean _null = true;
		if (!(address == null)) {
			_null = false;
		}
		return _null;
	}

	public boolean verify(Set<?> type) {
		boolean _null = true;
		if (!(type == null)) {
			_null = false;
		}
		return _null;
	}

	public boolean verify(User owner) {
		boolean _null = true;
		if (!(owner == null)) {
			_null = false;
		}
		return _null;
	}

	public boolean verify(Date cadastro) {
		boolean _null = true;
		if (!(cadastro == null)) {
			_null = false;
		}
		return _null;
	}
}
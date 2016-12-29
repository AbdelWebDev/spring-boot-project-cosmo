package org.opendevup.entities;

public enum Role {
	ADMIN("ADMIN"), MEDECIN("MEDECIN"), PHARMACIEN("PHARMACIEN"), ASSOCIATION_CONSOMOATEUR(
			"ASSOCIATION_CONSOMOATEUR"), SIMPLE_USER("SIMPLE_USER");

	private String value;

	public String getValue() {
		return value;
	}

	private Role(String value) {
		this.value = value;
	}
}
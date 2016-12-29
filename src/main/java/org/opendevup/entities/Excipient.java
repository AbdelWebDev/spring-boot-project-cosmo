package org.opendevup.entities;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("excipient")
public class Excipient extends ProduitCosmetique {

	public Excipient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Excipient(Long id, String libelle, List<Effet> effets, List<Ingredient> ingredients) {
		super(id, libelle, effets, ingredients);
		// TODO Auto-generated constructor stub
	}

	public Excipient(String libelle) {
		super(libelle);
		// TODO Auto-generated constructor stub
	}
	

}

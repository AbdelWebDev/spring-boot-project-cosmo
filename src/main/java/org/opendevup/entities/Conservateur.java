package org.opendevup.entities;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("conservateur")
public class Conservateur extends ProduitCosmetique {

	public Conservateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Conservateur(Long id, String libelle, List<Effet> effets, List<Ingredient> ingredients) {
		super(id, libelle, effets, ingredients);
		// TODO Auto-generated constructor stub
	}

	public Conservateur(String libelle) {
		super(libelle);
		// TODO Auto-generated constructor stub
	}

}

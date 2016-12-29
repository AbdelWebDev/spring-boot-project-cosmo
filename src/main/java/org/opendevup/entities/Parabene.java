package org.opendevup.entities;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("parabene")
public class Parabene extends ProduitCosmetique {
	
	public Parabene() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Parabene(Long id, String libelle, List<Effet> effets, List<Ingredient> ingredients) {
		super(id, libelle, effets, ingredients);
		// TODO Auto-generated constructor stub
	}

	public Parabene(String libelle) {
		super(libelle);
		// TODO Auto-generated constructor stub
	}

	
}

package org.opendevup.entities;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("principe")
public class Principe extends ProduitCosmetique {

	public Principe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Principe(Long id, String libelle, List<Effet> effets, List<Ingredient> ingredients) {
		super(id, libelle, effets, ingredients);
		// TODO Auto-generated constructor stub
	}

	public Principe(String libelle) {
		super(libelle);
		// TODO Auto-generated constructor stub
	}
	

}

package org.opendevup.entities;


import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;

@Entity

public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String libelleIngredient;
	@ManyToMany(mappedBy = "ingredients")
	private Set<ProduitCosmetique> produits;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelleIngredient() {
		return libelleIngredient;
	}

	public void setLibelleIngredient(String libelleIngredient) {
		this.libelleIngredient = libelleIngredient;
	}

	public Set<ProduitCosmetique> getProduits() {
		return produits;
	}

	public void setProduits(Set<ProduitCosmetique> produits) {
		this.produits = produits;
	}

	public Ingredient() {
		super();
	}

	public Ingredient(String libelleIngredient) {
		super();
		this.libelleIngredient = libelleIngredient;
	}

}

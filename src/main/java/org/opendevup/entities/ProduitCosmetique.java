package org.opendevup.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="produit")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_PRODUIT",discriminatorType=DiscriminatorType.STRING,length=20)

public class ProduitCosmetique implements Serializable{
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	private String libelle;
	@ManyToMany
	@JoinTable(name="EffetCosmetique",joinColumns=@JoinColumn(name="CosmetiqueId"),inverseJoinColumns=@JoinColumn(name="EffetId"))
	private List<Effet> effets;
	@ManyToMany
	@JoinTable(name="Ingredient_Cosmetique",joinColumns=@JoinColumn(name="CosmetiqueId"),inverseJoinColumns=@JoinColumn(name="IngredientId"))
	private List<Ingredient> ingredients;
	
	
	public ProduitCosmetique() {
		super();
	}
	
	public ProduitCosmetique(String libelle) {
		super();
		this.libelle = libelle;
	}
	
	
	public ProduitCosmetique(Long id, String libelle, List<Effet> effets, List<Ingredient> ingredients) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.effets = effets;
		this.ingredients = ingredients;
	}

	public List<Effet> getEffets() {
		return effets;
	}
	public void setEffets(List<Effet> effets) {
		this.effets = effets;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}



}

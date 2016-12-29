package org.opendevup.entities;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Effet {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	private String nomEffet;
	@ManyToMany(mappedBy="effets")
	private Set<ProduitCosmetique> produits;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	
	public String getNomEffet() {
		return nomEffet;
	}
	public void setNomEffet(String nomEffet) {
		this.nomEffet = nomEffet;
	}
	public Set<ProduitCosmetique> getProduits() {
		return produits;
	}
	public void setProduits(Set<ProduitCosmetique> produits) {
		this.produits = produits;
	}
	public Effet(String nomEffet) {
		super();
		this.nomEffet = nomEffet;
	}
	public Effet() {
		super();
	}
	
	

}

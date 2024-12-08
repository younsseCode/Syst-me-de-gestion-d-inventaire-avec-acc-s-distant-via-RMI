
public class Produit {

    Integer id;
    String nom;
    String Categorie;
    Integer Quantite;
    Integer Prix;

    public Produit() {
    }

    public Produit(Integer id, String nom, String categorie, Integer quantite, Integer prix) {
        this.id = id;
        this.nom = nom;
        Categorie = categorie;
        Quantite = quantite;
        Prix = prix;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String categorie) {
        Categorie = categorie;
    }

    public Integer getQuantite() {
        return Quantite;
    }

    public void setQuantite(Integer quantite) {
        Quantite = quantite;
    }

    public Integer getPrix() {
        return Prix;
    }

    public void setPrix(Integer prix) {
        Prix = prix;
    }
}

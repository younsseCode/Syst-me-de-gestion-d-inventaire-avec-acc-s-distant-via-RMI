import connexion.Connexion;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.List;


import java.util.ArrayList; // Pour manipuler des listes dynamiques


public class ProduitImp extends UnicastRemoteObject implements ProduitInt {

    private Connection connection;

    protected ProduitImp() throws RemoteException {
        super();
        // Initialisation de la connexion ici
        this.connection = Connexion.getConnection();
        if (this.connection == null) {
            throw new RemoteException("Impossible d'établir la connexion à la base de données.");
        }

    }

    protected ProduitImp(int port) throws RemoteException {
        super(port);
    }

    protected ProduitImp(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public String Calcule(Integer nbr1, Integer nbr2) throws RemoteException {
        Integer res = nbr1 + nbr2;
        return "La Somme est : " + res;
    }

    @Override
    public String createProduct(String nom, String categorie, int prix, int quantite) throws RemoteException {
        String sql = "INSERT INTO Products (nom, Categorie, Prix, Quantite) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nom);
            stmt.setString(2, categorie);
            stmt.setInt(3, prix);
            stmt.setInt(4, quantite);
            int rows = stmt.executeUpdate();
            return rows > 0 ? "Produit ajouté avec succès : Nom = " + nom : "Échec de l'ajout du produit.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erreur lors de l'ajout du produit : " + e.getMessage();
        }
    }

    @Override
    public String readProductById(int id) throws RemoteException {
        String sql = "SELECT * FROM Products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return "ID: " + rs.getInt("id") +
                            ", Nom: " + rs.getString("nom") +
                            ", Catégorie: " + rs.getString("Categorie") +
                            ", Prix: " + rs.getInt("Prix") +
                            ", Quantité: " + rs.getInt("Quantite");
                } else {
                    return "Aucun produit trouvé avec l'ID " + id;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erreur lors de la récupération du produit avec l'ID " + id + " : " + e.getMessage();
        }
    }

    @Override
    public String readAllProducts() throws RemoteException {
        if (this.connection == null) {
            return "Erreur : Connexion à la base de données non établie.";
        }

        List<String> products = new ArrayList<>();
        String query = "SELECT * FROM Products";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String productDetails = "ID: " + rs.getInt("id") +
                        ", Nom: " + rs.getString("nom") +
                        ", Catégorie: " + rs.getString("Categorie") +
                        ", Prix: " + rs.getInt("Prix") +
                        ", Quantité: " + rs.getInt("Quantite");
                products.add(productDetails);
            }
        } catch (SQLException e) {
            return "Erreur lors de la récupération des produits : " + e.getMessage();
        }

        return String.join("\n", products); // Retourne une chaîne avec tous les produits
    }

    @Override
    public String updateProduct(int id, String nom, String categorie, int prix, int quantite) throws RemoteException {
        if (this.connection == null) {
            return "Erreur : Connexion à la base de données non établie.";
        }

        String sql = "UPDATE Products SET nom = ?, Categorie = ?, Prix = ?, Quantite = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nom);
            stmt.setString(2, categorie);
            stmt.setInt(3, prix);
            stmt.setInt(4, quantite);
            stmt.setInt(5, id);
            int rows = stmt.executeUpdate();
            return rows > 0 ? "Produit mis à jour avec succès : ID = " + id : "Aucun produit trouvé avec l'ID " + id;
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erreur lors de la mise à jour du produit avec l'ID " + id + " : " + e.getMessage();
        }
    }

    @Override
    public String deleteProductById(int id) throws RemoteException {
        String sql = "DELETE FROM Products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0 ? "Produit supprimé avec succès : ID = " + id : "Aucun produit trouvé avec l'ID " + id;
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erreur lors de la suppression du produit avec l'ID " + id + " : " + e.getMessage();
        }
    }

    @Override
    public String searchProductByNom(String nom) throws RemoteException {
        String sql = "SELECT * FROM Products WHERE nom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nom);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Si le produit est trouvé, retourne les détails
                    return "ID: " + rs.getInt("id") +
                            ", Nom: " + rs.getString("nom") +
                            ", Catégorie: " + rs.getString("Categorie") +
                            ", Prix: " + rs.getInt("Prix") +
                            ", Quantité: " + rs.getInt("Quantite");
                } else {
                    // Si aucun produit n'est trouvé avec cet ID
                    return "Aucun produit trouvé avec l'ID " + nom;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erreur lors de la recherche du produit avec l'ID " + nom + " : " + e.getMessage();
        }
    }

}

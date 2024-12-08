import connexion.Connexion;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static connexion.Connexion.getConnection;

public class Main {
    public static void main(String[] args) {
        try {
            // Enregistrement de l'objet distant
            ProduitInt p = new ProduitImp();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("EchoMsg", p);
            System.out.println("Objet RMI enregistré avec succès.");

            // Connexion à la base de données
            Connection connection = getConnection();
            if (connection != null) {
                System.out.println("Connexion à la base de données réussie !");
                try (Statement statement = connection.createStatement()) {
                    // Placez ici vos requêtes SQL si nécessaire
//                    System.out.println("Products : "+p.readAllProducts());
                    System.out.println("Prêt pour exécuter les requêtes.");
                } catch (SQLException e) {
                    System.err.println("Erreur lors de l'exécution des requêtes SQL :");
                    e.printStackTrace();
                } finally {
                    // Fermeture de la connexion
//                    try {
//                        connection.close();
////                        System.out.println("Connexion à la base de données fermée.");
//                    } catch (SQLException e) {
//                        System.err.println("Erreur lors de la fermeture de la connexion :");
//                        e.printStackTrace();
//                    }
                }
            } else {
                System.out.println("Échec de la connexion à la base de données.");
            }
        } catch (Exception e) {
            System.err.println("Erreur principale :");
            e.printStackTrace();
        }
    }
}

package connexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connexion {
    // URL de connexion, utilisateur et mot de passe
    private static final String URL = "jdbc:mysql://localhost:3306/Invontaire"; // Remplacez "votre_base_de_donnees" par le nom de votre BD
    private static final String USER = "root"; // Remplacez "root" par votre utilisateur MySQL
    private static final String PASSWORD = ""; // Remplacez par votre mot de passe MySQL

    /**
     * Méthode pour établir une connexion avec la base de données
     * @return Connection
     */
    public static Connection getConnection() {
        try {
            // Charger le driver MySQL (facultatif pour les versions récentes)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC introuvable ! Vérifiez votre configuration.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Méthode principale pour tester la connexion
     */
    public static void main(String[] args) {
        Connection connection = getConnection();
        if (connection != null) {
            System.out.println("Connexion réussie !");
            try {
                connection.close(); // Toujours fermer la connexion après utilisation
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Connexion échouée.");
        }
    }

}

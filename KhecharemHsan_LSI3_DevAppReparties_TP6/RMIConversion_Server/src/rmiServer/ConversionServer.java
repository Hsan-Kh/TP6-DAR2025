package rmiServer;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import rmiService.ConversionImpl;
import rmiService.IConversion;

/**
 * Serveur RMI pour le service de conversion de devises
 * Lance le registre RMI et publie le service
 */
public class ConversionServer {

    public static void main(String[] args) {
        try {
            // Étape 1 : Activer l'annuaire RMIRegistry sur le port 1099
            System.out.println("Démarrage du registre RMI...");
            LocateRegistry.createRegistry(1099);
            System.out.println("Registre RMI démarré sur le port 1099");

            // Étape 2 : Créer une instance du service de conversion
            System.out.println("Création de l'instance du service de conversion...");
            IConversion conversionService = new ConversionImpl();
            System.out.println("Instance créée avec succès");

            // Étape 3 : Publier la référence de l'objet dans l'annuaire RMI
            String url = "rmi://localhost:1099/ConversionService";
            Naming.rebind(url, conversionService);
            System.out.println("Service publié avec succès à l'URL : " + url);
            System.out.println("Serveur RMI prêt et en attente de requêtes...");

        } catch (Exception e) {
            System.err.println("Erreur lors du démarrage du serveur RMI : ");
            e.printStackTrace();
        }
    }
}
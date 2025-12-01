package rmiServer;

import rmiService.BanqueImpl;
import rmiService.IBanque;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

/**
 * Application serveur RMI
 * Version avec JNDI - utilise InitialContext pour l'enregistrement
 */
public class BanqueServer {
    public static void main(String[] args) {
        try {
            System.out.println("===========================================");
            System.out.println("   SERVEUR RMI BANQUE AVEC JNDI - DÉMARRAGE");
            System.out.println("===========================================\n");

            // Créer le registre RMI sur le port 1099
            LocateRegistry.createRegistry(1099);
            System.out.println("✓ Registre RMI créé sur le port 1099");

            // Créer l'objet distant
            IBanque banque = new BanqueImpl();
            System.out.println("✓ Objet distant BanqueImpl créé");

            // Utiliser JNDI pour l'enregistrement
            Context context = new InitialContext();
            context.rebind("BanqueService", banque);
            System.out.println("✓ Service enregistré via JNDI sous le nom 'BanqueService'");

            System.out.println("\n🟢 Serveur RMI Banque (JNDI) démarré et prêt à accepter les connexions...");
            System.out.println("   Configuration JNDI chargée depuis jndi.properties");
            System.out.println("   Appuyez sur Ctrl+C pour arrêter le serveur\n");

            // Garder le serveur actif
            Thread.currentThread().join();

        } catch (Exception e) {
            System.err.println("❌ Erreur serveur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
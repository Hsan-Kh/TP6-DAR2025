package rmiClient;

import metier.Compte;
import rmiService.IBanque;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Application cliente RMI
 * Version avec JNDI - utilise InitialContext pour la recherche
 */
public class BanqueClient {
    public static void main(String[] args) {
        try {
            System.out.println("=============================================");
            System.out.println("   CLIENT RMI BANQUE AVEC JNDI - TESTS");
            System.out.println("=============================================\n");

            // Utiliser JNDI pour la recherche
            Context context = new InitialContext();
            System.out.println("✓ Contexte JNDI initialisé");

            IBanque banque = (IBanque) context.lookup("BanqueService");
            System.out.println("✓ Service 'BanqueService' trouvé via JNDI\n");

            System.out.println("========== DÉBUT DES TESTS ==========\n");

            // Test 1 : Créer un compte
            System.out.println("Test 1 : Création du compte 101 avec 5000.0 DT");
            Compte compte1 = new Compte(101, 5000.0);
            String resultat1 = banque.creerCompte(compte1);
            System.out.println(resultat1);
            System.out.println();

            // Test 2 : Créer un autre compte
            System.out.println("Test 2 : Création du compte 102 avec 10000.0 DT");
            Compte compte2 = new Compte(102, 10000.0);
            String resultat2 = banque.creerCompte(compte2);
            System.out.println(resultat2);
            System.out.println();

            // Test 3 : Créer un troisième compte
            System.out.println("Test 3 : Création du compte 103 avec 7500.0 DT");
            Compte compte3 = new Compte(103, 7500.0);
            String resultat3 = banque.creerCompte(compte3);
            System.out.println(resultat3);
            System.out.println();

            // Test 4 : Consulter un compte
            System.out.println("Test 4 : Consultation du compte 101");
            String info1 = banque.getInfoCompte(101);
            System.out.println(info1);
            System.out.println();

            // Test 5 : Consulter un autre compte
            System.out.println("Test 5 : Consultation du compte 102");
            String info2 = banque.getInfoCompte(102);
            System.out.println(info2);
            System.out.println();

            // Test 6 : Tenter de créer un compte existant
            System.out.println("Test 6 : Tentative de création d'un compte existant (code 101)");
            Compte compte4 = new Compte(101, 3000.0);
            String resultat4 = banque.creerCompte(compte4);
            System.out.println(resultat4);
            System.out.println();

            // Test 7 : Consulter un compte inexistant
            System.out.println("Test 7 : Consultation d'un compte inexistant (code 999)");
            String info3 = banque.getInfoCompte(999);
            System.out.println(info3);
            System.out.println();

            System.out.println("========== FIN DES TESTS ==========");

        } catch (Exception e) {
            System.err.println("❌ Erreur client : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
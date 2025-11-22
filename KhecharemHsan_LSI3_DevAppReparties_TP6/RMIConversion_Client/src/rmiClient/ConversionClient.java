package rmiClient;

import java.rmi.Naming;
import rmiService.IConversion;

/**
 * Client RMI pour le service de conversion de devises
 * Se connecte au serveur et invoque la méthode de conversion
 */
public class ConversionClient {

    public static void main(String[] args) {
        try {
            System.out.println("Connexion au serveur RMI...");

            // Étape 1 : Chercher la référence vers l'objet distant dans le registre RMI
            String url = "rmi://localhost:1099/ConversionService";
            IConversion conversionService = (IConversion) Naming.lookup(url);
            System.out.println("Connexion établie avec succès");

            // Étape 2 : Invoquer la méthode convertirMontant(500) via la référence distante
            double montantEuros = 500.0;
            System.out.println("\n--- Demande de conversion ---");
            System.out.println("Montant à convertir : " + montantEuros + " EUR");

            double montantDinars = conversionService.convertirMontant(montantEuros);

            System.out.println("\n--- Résultat de la conversion ---");
            System.out.println(montantEuros + " EUR = " + montantDinars + " TND");

            // Test avec d'autres montants
            System.out.println("\n--- Tests supplémentaires ---");
            testConversion(conversionService, 100);
            testConversion(conversionService, 1000);
            testConversion(conversionService, 250.50);

        } catch (Exception e) {
            System.err.println("Erreur lors de la communication avec le serveur RMI : ");
            e.printStackTrace();
        }
    }

    /**
     * Méthode utilitaire pour tester la conversion avec différents montants
     */
    private static void testConversion(IConversion service, double montant) {
        try {
            double resultat = service.convertirMontant(montant);
            System.out.println(montant + " EUR = " + resultat + " TND");
        } catch (Exception e) {
            System.err.println("Erreur lors de la conversion de " + montant + " EUR");
        }
    }
}
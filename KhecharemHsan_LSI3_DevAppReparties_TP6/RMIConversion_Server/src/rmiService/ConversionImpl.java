package rmiService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implémentation du service de conversion de devises
 * Étend UnicastRemoteObject pour devenir un objet distant
 */
public class ConversionImpl extends UnicastRemoteObject implements IConversion {

    private static final double TAUX_CONVERSION = 3.35;

    public ConversionImpl() throws RemoteException {
        super();
    }


    @Override
    public double convertirMontant(double mt) throws RemoteException {
        System.out.println("Demande de conversion reçue : " + mt + " EUR");
        double resultat = mt * TAUX_CONVERSION;
        System.out.println("Résultat : " + resultat + " TND");
        return resultat;
    }
}
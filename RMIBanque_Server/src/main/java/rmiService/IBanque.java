package rmiService;

import metier.Compte;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface distante définissant les services bancaires
 * Doit étendre Remote et toutes les méthodes doivent déclarer RemoteException
 */
public interface IBanque extends Remote {

    /**
     * Crée un nouveau compte bancaire
     * @param c Le compte à créer
     * @return Message de confirmation ou d'erreur
     * @throws RemoteException En cas d'erreur de communication RMI
     */
    String creerCompte(Compte c) throws RemoteException;

    /**
     * Récupère les informations d'un compte
     * @param code Le code du compte à consulter
     * @return Les informations du compte ou un message d'erreur
     * @throws RemoteException En cas d'erreur de communication RMI
     */
    String getInfoCompte(int code) throws RemoteException;
}
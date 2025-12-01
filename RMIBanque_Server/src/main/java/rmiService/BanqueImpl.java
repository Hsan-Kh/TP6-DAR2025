package rmiService;

import metier.Compte;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Implémentation du service bancaire distant
 * Hérite de UnicastRemoteObject pour devenir un objet distant
 */
public class BanqueImpl extends UnicastRemoteObject implements IBanque {
    private static final long serialVersionUID = 1L;

    // Stockage en mémoire des comptes (code -> Compte)
    private Map<Integer, Compte> comptes;

    /**
     * Constructeur
     * @throws RemoteException Si l'objet distant ne peut pas être créé
     */
    public BanqueImpl() throws RemoteException {
        super();
        comptes = new HashMap<>();
        System.out.println("Service BanqueImpl initialisé");
    }

    /**
     * Crée un nouveau compte s'il n'existe pas déjà
     */
    @Override
    public String creerCompte(Compte c) throws RemoteException {
        if (comptes.containsKey(c.getCode())) {
            return "❌ Erreur : Le compte avec le code " + c.getCode() + " existe déjà.";
        }
        comptes.put(c.getCode(), c);
        return "✅ Compte créé avec succès : " + c.toString();
    }

    /**
     * Consulte les informations d'un compte
     */
    @Override
    public String getInfoCompte(int code) throws RemoteException {
        Compte compte = comptes.get(code);
        if (compte == null) {
            return "❌ Erreur : Aucun compte trouvé avec le code " + code;
        }
        return "📋 " + compte.toString();
    }
}
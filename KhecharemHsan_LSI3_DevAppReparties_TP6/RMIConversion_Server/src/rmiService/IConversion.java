package rmiService;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface distante pour le service de conversion de devises
 */

public interface IConversion extends Remote {

    double convertirMontant(double mt) throws RemoteException;
}
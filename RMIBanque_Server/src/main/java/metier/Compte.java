package metier;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe représentant un compte bancaire
 * Doit être Serializable pour être transmis via RMI
 */
public class Compte implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
    private double solde;
    private Date dateCreation;

    /**
     * Constructeur par défaut
     */
    public Compte() {
    }

    /**
     * Constructeur avec paramètres
     * @param code Le code du compte
     * @param solde Le solde initial
     */
    public Compte(int code, double solde) {
        this.code = code;
        this.solde = solde;
        this.dateCreation = new Date();
    }

    // Getters et Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "code=" + code +
                ", solde=" + solde + " DT" +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
# TP6 : Invocation des Méthodes sur des Objets Distants avec Java RMI

## Service de Conversion de Devises (Avec Maven)

---



## Description du Projet

Ce projet implémente une application répartie permettant la conversion de montants en euros vers le dinar tunisien en utilisant Java RMI (Remote Method Invocation) et Maven comme outil de gestion de projet.

**Architecture** : Client-Serveur avec Maven pour la gestion des dépendances et la construction

**Fonctionnalités** :
- Serveur RMI qui publie un service de conversion
- Client qui se connecte au serveur et appelle la méthode de conversion à distance
- Support de multiples requêtes de conversion
- Taux de conversion fixe : 1 EUR = 3.35 TND
- Gestion centralisée des dépendances avec Maven
- Génération automatique des JAR

---

## Objectifs

Au terme de ce TP, l'étudiant est capable de :

- Utiliser Maven pour gérer un projet RMI
- Configurer pom.xml pour les dépendances
- Générer des JAR avec Maven
- Mettre en œuvre une communication client/serveur via RMI
- Comprendre le cycle de vie d'un service distant
- Invoquer des méthodes sur des objets distants

---



## Prérequis

- JDK : Version 1.8 ou supérieure
- IDE : IntelliJ IDEA avec support Maven
- Git : Pour la gestion de version


---

## Installation et Configuration

### Cloner le Repository

```bash
git clone https://github.com/Hsan-Kh/TP-DAR2025
cd KhecharemHsan_LSI3_DevAppReparties_TP6
```

---

## Concepts RMI Appliqués

### Interface Distante

L'interface IConversion hérite de Remote et définit le contrat de communication :

```java
public interface IConversion extends Remote {
    double convertirMontant(double mt) throws RemoteException;
}
```

### Implémentation RMI

La classe ConversionImpl hérite de UnicastRemoteObject pour devenir un objet distant :

```java
public class ConversionImpl extends UnicastRemoteObject implements IConversion {
    private static final double TAUX_CONVERSION = 3.35;
    
    public double convertirMontant(double mt) throws RemoteException {
        return mt * TAUX_CONVERSION;
    }
}
```

### Serveur RMI

Le serveur crée le registre et publie le service :

```java
LocateRegistry.createRegistry(1099);
IConversion service = new ConversionImpl();
Naming.rebind("rmi://localhost:1099/ConversionService", service);
```

### Client RMI

Le client localise et utilise le service :

```java
IConversion service = (IConversion) Naming.lookup("rmi://localhost:1099/ConversionService");
double resultat = service.convertirMontant(500);
```

---


## Problèmes et Solutions

### Port 1099 déjà utilisé

```bash
lsof -i :1099
kill -9 <PID>
```

### Module non trouvé

```bash
mvn clean install
mvn dependency:resolve
```

### Connection refused

Vérifier que le serveur est démarré et que le message "Serveur RMI prêt..." s'affiche dans la console.

### JAR classifié non trouvé

Exécuter d'abord mvn install sur le serveur pour installer le JAR classifié dans le repository local.

---

## Avantages de Maven

- Gestion centralisée des dépendances
- Build reproductible
- Génération automatique des JAR
- Plugins pour créer des JAR exécutables
- Gestion des classifiers (client vs server)
- Intégration facile avec IntelliJ IDEA

---

## Conclusion

Ce projet démontre une implémentation complète d'une application distribuée utilisant Java RMI avec Maven pour la gestion de projet. L'architecture client-serveur est fonctionnelle, les tests validant le bon fonctionnement du système. Maven facilite considérablement le processus de build, la gestion des dépendances et la génération des artefacts appropriés.

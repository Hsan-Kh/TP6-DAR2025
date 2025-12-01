# TP6 : Architectures Distribuées avec Java RMI et JNDI

---

## 📖 Description du Projet

Ce dépôt contient les travaux pratiques du TP6, axés sur la mise en œuvre de systèmes distribués en Java. L'objectif est de maîtriser les invocations de méthodes à distance via **Java RMI (Remote Method Invocation)** et la gestion d'annuaires avec **JNDI (Java Naming and Directory Interface)**.

Le TP est divisé en deux activités progressives :
1.  **Activité 6.1** : Un service de conversion de devises (Architecture RMI simple).
2.  **Activité 6.2** : Un service bancaire complet (Architecture modulaire Client/Serveur avec JNDI).

---

## 🛠 Prérequis

Pour exécuter les projets, assurez-vous d'avoir :
-   **JDK** : Version 1.8 ou supérieure.
-   **Maven** : 3.6+ pour la gestion des dépendances et le build.
-   **Git** : Pour cloner le projet.
-   **IDE** : IntelliJ IDEA (recommandé) ou Eclipse.



## 📂 Activité 6.1 : Service de Conversion de Devises

### Description
Cette première activité implémente une architecture Client-Serveur simple où le serveur offre un service de conversion Euro vers Dinar Tunisien (Taux fixe : 1 EUR = 3.35 TND).

### Architecture Technique
*   **Interface** : `IConversion` (étend `Remote`).
*   **Implémentation** : `ConversionImpl` (étend `UnicastRemoteObject`).
*   **Protocole** : RMI standard sur le port 1099.

### Concepts Clés
-   Configuration du `pom.xml` pour RMI.
-   Publication du service via `Naming.rebind`.
-   Invocation distante via `Naming.lookup`.

### Exécution
```bash
# Dans le dossier de l'activité 6.1
mvn clean install
# Lancer d'abord le Serveur, puis le Client via votre IDE ou en ligne de commande.
```

---

## 🏦 Activité 6.2 : Service Bancaire (RMI + JNDI)

### Description
Cette activité simule un système bancaire plus complexe. Elle introduit une séparation stricte entre le projet Serveur et le projet Client, ainsi que l'utilisation de JNDI pour localiser les ressources.

### Structure du Projet Multi-Modules

```
RMIBanque_Server/
├── src/main/java/metier/     # Objet Compte (Serializable)
├── src/main/java/rmiService/ # Interface IBanque & Implémentation
├── src/main/java/rmiServer/  # Serveur (Publication JNDI)
└── src/main/resources/       # jndi.properties

RMIBanque_Client/
├── src/main/java/rmiClient/  # Client (Lookup JNDI)
└── src/main/resources/       # jndi.properties
```

### Fonctionnalités
-   Création de comptes bancaires.
-   Consultation de solde et date de création.
-   Gestion des erreurs (Comptes inexistants/doublons).
-   Utilisation de **JNDI** pour abstraire le service de nommage.

### Comparatif : RMI Standard vs JNDI

| Feature | RMI Standard | Avec JNDI |
| :--- | :--- | :--- |
| **Registre** | `LocateRegistry.createRegistry` | Configuré via `jndi.properties` |
| **Lookup** | `Naming.lookup("rmi://...")` | `new InitialContext().lookup(...)` |
| **Flexibilité** | Couplage fort à l'URL | Configuration externalisée |

### 🚀 Installation et Exécution (Activité 6.2)

#### Étape 1 : Compilation du Serveur et Génération des JARs
Le serveur génère deux JARs : le serveur complet et une version "client" (interface + objets métier).

```bash
cd RMIBanque_Server
mvn clean install
```
*Note : Cette commande installe le JAR dans votre dépôt local Maven, rendant la dépendance accessible au client.*

---

## ⚠️ Résolution de Problèmes (Troubleshooting)

### Port 1099 déjà utilisé
Si vous relancez le serveur sans l'avoir arrêté proprement :
```bash
# Trouver le PID
lsof -i :1099 
# Tuer le processus
kill -9 <PID>
```

### ClassNotFoundException / Dependency Error
Si le client ne trouve pas les classes `IBanque` ou `Compte` :
1.  Vérifiez que `mvn install` a réussi dans le dossier `RMIBanque_Server`.
2.  Vérifiez que le `pom.xml` du client référence bien la version correcte du serveur.

### Connection Refused
Assurez-vous que le serveur affiche "Serveur prêt..." avant de lancer le client.

### Erreur JNDI (Need to specify class name...)
Vérifiez que le fichier `jndi.properties` est bien présent dans le dossier `src/main/resources` des deux projets et qu'il contient :
```properties
java.naming.factory.initial=com.sun.jndi.rmi.registry.RegistryContextFactory
java.naming.provider.url=rmi://localhost:1099
```

---

## 📝 Conclusion

Ce TP a permis de valider les compétences suivantes :
1.  **Cycle de vie RMI** : De la définition de l'interface distante à l'exécution du squelette.
2.  **Architecture Maven** : Gestion des dépendances multi-modules et génération d'artefacts spécifiques (classifiers).
3.  **Abstraction JNDI** : Compréhension de l'intérêt de découpler l'implémentation de l'annuaire du code applicatif.
4.  **Sérialisation** : Manipulation d'objets complexes (`Compte`) à travers le réseau.

---


**Étudiant** : Hsan KHECHAREM

**Email** : khecharemhsan@gmail.com


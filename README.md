# 📦 Gestion des Ventes et Facturation – Application Java

## 📝 Description générale

Ce projet est une application Java de **gestion des ventes et de facturation**.  
Elle permet de gérer :
- 👤 les **clients**
- 📦 les **produits**
- 🧾 les **factures** et leurs **lignes de facture**

L’application a été développée en **Java** en suivant une architecture en couches :
- 🧱 **Model**
- 🗄️ **DAO**
- ⚙️ **Service**
- 🖥️ **Interface utilisateur (Swing)**

Une **interface graphique (Java Swing)** a été ajoutée afin d’utiliser l’application sans passer par la console.

---

## 👥 Répartition du travail

Le projet a été réalisé par **trois étudiants**, avec une répartition claire des responsabilités :

### 🔹 👤 Youssef Bsibiss
Responsable de **toutes les classes liées aux clients** :
- `Client`
- `ClientDAO`
- `ClientService`
- `ClientController`
- `ClientFrame` (interface graphique client)
- ✅ Validation des données client (email, téléphone, champs obligatoires)

---

### 🔹 🧾 Mehdi Ghine
Responsable de **toutes les classes liées à la facturation** :
- `Facture`
- `LigneFacture`
- `FactureDAO`
- `FactureService`
- `FactureController`
- `FactureFrame` (interface graphique factures)
- 🧮 Calcul des montants :
  - Total HT
  - TVA
  - Total TTC

---

### 🔹 📦 Anass Bellagrid
Responsable de **toutes les classes liées aux produits** :
- `Produit`
- `ProduitDAO`
- `ProduitService`
- `ProduitController`
- `ProduitFrame` (interface graphique produits)
- 💰 Gestion des prix HT, TVA et prix TTC

---

## 🛠️ Technologies utilisées

- 💻 **Langage** : Java  
- 🖥️ **Interface graphique** : Java Swing  
- 🧠 **IDE** : IntelliJ IDEA  
- 🔧 **Gestion de version** : Git & GitHub  
- 🏗️ **Architecture** : MVC + DAO + Service  

---

## ▶️ Utilisation de l’application

### 🚀 Lancement

L’application peut être lancée de deux manières :

- **Via IntelliJ IDEA**  
  ▶️ Exécuter la classe `MainUI`.

- **Via le fichier exécutable (JAR)**  
  1. 📂 Accéder à la section **Releases** du dépôt GitHub  
  2. ⬇️ Télécharger le fichier exécutable :  
     👉 https://github.com/Ijoz3phI/gestion-ventes-facturation/releases/download/v1.0/gestion-ventes-facturation.jar  
  3. 🖱️ Double-cliquer sur le fichier téléchargé  
  4. 🖥️ L’interface graphique de l’application s’ouvre automatiquement

---

## ✨ Fonctionnalités principales

### 👤 Gestion des clients
- ➕ Ajouter un client  
- ✏️ Modifier un client  
- ❌ Supprimer un client  
- 📋 Afficher la liste des clients  

### 📦 Gestion des produits
- ➕ Ajouter un produit  
- ✏️ Modifier un produit  
- ❌ Supprimer un produit  
- 📋 Afficher la liste des produits  

### 🧾 Gestion des factures
- 🆕 Créer une facture pour un client  
- ➕ Ajouter des lignes de facture (produits + quantités)  
- 🔍 Afficher les détails d’une facture  
- 🧮 Calcul automatique :
  - Total HT
  - TVA
  - Total TTC

---
## 🗂️ Structure du projet

src/
 └─ com/gestionventes/
    ├─ app        → classe Main (point d’entrée)
    ├─ model      → classes métier
    ├─ dao        → accès aux données
    ├─ service    → logique métier
    ├─ controller → gestion des actions
    └─ ui         → interface graphique (Swing)
```

---

---

## 📝 Remarques finales

- ✅ Le projet respecte une **séparation claire des responsabilités**
- 🎯 L’interface graphique rend l’application **simple et intuitive**
- 🧩 Le code est organisé pour faciliter la **maintenance et l’évolution**
- 📦 Une version exécutable est fournie pour une utilisation sans IDE

---

## 👨‍💻 Auteurs

- **Youssef Bsibiss**
- **Mehdi Ghine**
- **Anass Bellagrid**


## 🗂️ Structure du projet


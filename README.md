# Matrix

**Matrix est un exercice de résolution de problème en Java** mettant en scène un personnage perdu dans une matrice. Pour s'échapper, il doit réunir les ressources nécessaires à la fabrication d'un pain et atteindre la position **(9,9)**.

## Objectif du projet

Ce projet est un exercice visant à résoudre de manière **automatique** le problème ci-dessus, peu importe la matrice fournie en entrée.

### Problème

- Le personnage commence en **(0,0)** et doit récolter des ressources en se déplaçant dans la matrice.
- Il doit atteindre la case **(9,9)** en ayant fabriqué du **pain**, tout en minimisant le nombre de déplacements.
- Il ne peut se déplacer que **verticalement** ou **horizontalement**.

### Ressources disponibles

- **Blé** (*poids : 1*)
- **Bois** (*poids : 2*)
- **Pierre** (*poids : 3*)

### Contraintes

- Le personnage ne peut plus se déplacer si son **poids total** dépasse **13**.
- Pour fabriquer du **pain**, il faut :
  - **Farine** (*10 blés + 1 pierre*)
  - **Feu** (*6 bois + 1 pierre*)
- Il faut récolter les ressources dans un **ordre optimal** :
  1. Récupérer le **blé**
  2. Obtenir la **pierre** pour faire la farine
  3. Collecter le **bois** pour allumer le feu
  4. Se diriger vers la **position (9,9)** une fois le pain prêt

### Affichage

Le programme affiche en temps réel :

- Le **pseudo** du personnage
- La **phase** de récupération des objets
- Le **nombre de coups joués**
- Les **quantités de bois et de blé** récupérées
- Le **poids total** du personnage
- Ses **coordonnées actuelles**
- L'état de la **farine et du feu**
- La **matrice et les déplacements** en temps réel

---

## Prérequis

- **Java** (dernière version)

## Installation et Exécution

Clonez le dépôt et utilisez les commandes suivantes :

```sh
make        # Compilation
make run    # Exécution
make clean  # Nettoyage des fichiers compilés
```

## Fonctionnement

1. Clonez le dépôt
2. Exécutez le programme
3. Le programme résout automatiquement le jeu en affichant la matrice et l'évolution du personnage en temps réel

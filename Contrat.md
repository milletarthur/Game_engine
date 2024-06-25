---
title: Contrat projet fin d'année INFO3
author: Groupe 5
date: 07/06/2024
---

# Moteur de jeu
Nos trois axes : 
 - Bot (automates)
 - Générateur aléatoire du terrain et de certaines entités :
     - chemins
     - BOTs avec leur entité en main
     - sables mouvant
     - mines
     - portes
     - interrupteurs / leviers
     - murs fragiles
     - murs "magiques"
     - lien entre interrupteurs et pièges ou portes
     - pommes
     - bombes
     - pioches
 - Liens entre entités (grâce aux interrupteurs)

![](https://s3.hedgedoc.org/demo/uploads/aeec1cfa-3531-4a06-9698-3af1cc7253b6.jpg)


Permets de créer des jeux en 2D vu de dessus, sous forme d'une grille, où chaque case contient une entité. Chaque jeu est en split screen, où chaque joueur voit une partie du terrain autour de lui uniquement (même avec les 2 vues, le viewport est toujours plus petit que le terrain).

## Liste des entités présentes :
 - pomme : permet de regagner des points de vie 
 - potion : permet de soigner une attaque de zombie (implémenté uniquement si la mécanique de transformation en zombie est implémentée)
 - bombe : entité pouvant être lancée sur une autre afin de l'affaiblir (BOT), le tuer / l'affaiblir (joueur) ou de le casser (mur fragile)
 - mine : très difficile à aperçevoir, une case où une mine est présente ressemble très fortement à une case vide, lorsqu'un joueur marche dessus, la mine explose et le joueur meurt (ou perd des points de vie), peut aussi casser un mur fragile s'il est situé à proximité
 - pioche : permet de détruire un mur fragile, mais n'est utilisable qu'une seule fois (après elle se détruit) + Si un chemin est frappé avec, il se casse. Laisse apparaître la lave qu'il y a en dessous. 
 - épée : permet d'attaquer devant soi ou de faire une attaque cirulaire infligeant des dégâts sur toutes les cases autour du joueur (diagonales comprises) grâce à la méthode Wizz()
 - arc : permet d'attaquer des entités à distance, la méthode Wizz() permet de tirer des flèches "transperçantes" pouvant infliger des dégâts à plusieurs entités si elles sont alignées.
 - joueur
 - BOT (squelettes et zombies) : ils ne possèdent pas d'inventaire, mais ont juste un objet sur eux, lorsqu'ils meurent, ils laissent derrière eux l'objet qu'ils tenaient, et il y a une chance que d'autres entités (potion, épée ou arc) apparaissent dans les cases adjacentes
 - Sable mouvant : au début à le même design que le sol donc "invisible pour les joueurs", lorsqu'un joueur se déplace vers une case où se cache un sable mouvant, celui-ci devient visible et le joueur est piégé, il est immobile et ne peut plus se déplacer. De plus, tous les n ticks, celui-ci appelera la méthode Egg() afin de se dupliquer tout autour du joueur piégé.
 - lave : toute entité se trouvant sur une case de type lave, disparait.
 - plaque de téléportation : fonctionne par paire (une plaque de téléportation est reliée à une autre et ce lien est réciproque) s'il est activé permet de téléporter l'entité vers l'autre plaque de téléportation liée à la première, en changeant les coordonnées de l'entité 
 - interrupteur / levier : possède un lien avec une ou plusieurs entités et permet d'activer leur pop() et wizz(). Un interrupteur a 3 états possibles 
     - mode neutre 
     - mode pop
     - mode wizz
Le levier est presque toujours en mode neutre. Pour le faire passer en mode Pop ou en Wizz, il faut appuyer sur la commande (touche) prédéfinie dans l'automate. Une fois la méthode pop()/wizz() exécutée, l'interrupteur reviens en mode neutre. 
 - porte : entité que l'on ne peut pas détruire, qui possède deux états contrôlé par un interrupteur :
     - close : la porte bloque le passage
     - open : la porte ne gêne plus
 - mur :
     - normal : incassable
     - fragile : cassable à l'aide d'une bombe, mine ou d'une pioche
     - "magique" : ce type de mur à la même apparence qu'un mur normal, sauf qu'on peut tout de même passer à travers, donc indetectable par le joueur.

## Automates des entités : 

![](https://s3.hedgedoc.org/demo/uploads/3c058dc9-628a-40d1-afcc-25f63c3775ad.png)

Pomme : (init) {
*(init) : 
| Cell(H,P) ? : ()
| Cell(H, SelectionneurP1) & Pressed(ENTER) ? : (selectedP1)
| Cell(H, SelectionneurP2) & Pressed(ENTER) ? : (selectedP2)
| true ? : (init)
*(selectedP1) : 
| Pressed(a) ? pop() : (selectedP1)
| Pressed(b) ? wizz() : (selectedP1)
| Pressed(UNSELECTP1) ? : (init)
| true ? : (selectedP1)
*(selectedP2) : 
| Pressed(a) ? pop() : (selectedP2)
| Pressed(b) ? wizz() : (selectedP2)
| Pressed(UNSELECTP2) ? : (init)
| true ? : (selectedP2)
}

![](https://s3.hedgedoc.org/demo/uploads/0d6fc8fe-930b-4d2d-8a96-5a2ded98be6a.png)

Potion : (init) {
*(init) : 
| Cell(H,P1) ? (pickedP1)
| Cell(H,SelectionneurP1) & Pressed(ENTER) ? : (selectedP1)
| Cell(H,P2) ? (pickedP2)
| Cell(H,SelectionneurP2) & Pressed(ENTER) ? : (selectedP2)
| true ? : (init)
*(stored)
| Cell(H,Selectionneur) & Pressed(ENTER) ? : (to_pick)
| true ? : (stored)
*(to_pick)
| Pressed(TO_GET_P1) ? : (picked_P1)
| Pressed(TO_GET_P2) ? : (picked_P2)
| Pressed(ENTER) & Cell(H,Selectionneur) ? : (stored)
| true ? : (to_pick)
*(selectedP2)
| Pressed(a) ? pop() : (selectedP2)
| Pressed(b) ? wizz() : (selectedP2)
| Pressed(UNSELECT) ? : (init)
| true ? : (selectedP2)
*(pickedP1)
| Cell(TO_STORE) ? : (stored)
| Pressed(TO_THROW) ? Move() : (init)
| Pressed(TO_ACTION) ? : ()
| true ? : (pickedP1)
*(pickedP2)
| Cell(TO_STORE) ? : (stored)
| Pressed(TO_THROW) ? Move() : (init)
| Pressed(TO_ACTION) ? : ()
| true ? : (pickedP2)
*(selectedP1)
| Pressed(a) ? pop() : (selectedP1)
| Pressed(b) ? wizz() : (selectedP1)
| Pressed(UNSELECT) ? : (init)
| true ? : (selectedP1)
*()
}

![](https://s3.hedgedoc.org/demo/uploads/e9845b08-0efa-41a6-bdd5-18829b36e431.jpg)


![](https://s3.hedgedoc.org/demo/uploads/ce371b52-a00c-4c5c-b8b9-4890c257b2fe.png)

![](https://s3.hedgedoc.org/demo/uploads/30484fcd-8e5d-46f5-9184-70fb436d2f7f.jpg)

![](https://s3.hedgedoc.org/demo/uploads/1f029c7a-b8bd-42de-905d-d3c85d02f8e3.jpg)

![](https://s3.hedgedoc.org/demo/uploads/e3460e75-249f-41bd-94fb-b73cda2f4223.jpg)

Interrupteur : (init) {
*(init) : 
| Pressed(SELECT) ? : (selection)
| true ? : (init)
*(selection) : 
| Pressed(LEFT) ? turn(L), Move() : (selection)
| Pressed(RIGHT) ? turn(R), Move() : (selection)
| Pressed(UP) ? Move() : (selection)
| Pressed(DOWN) ? turn(B), Move() : (selection)
| Pressed(ENTER) ? pick() : (selected)
| true ? : (selection)
*(selected) : 
| Pressed(UNSELECT) ? throw() : (init)
| Pressed(a) ? pop() : (selected)
| Pressed(b) ? wizz() : (selected)
| true ? : (selected)
}


## Fonctionnalités :
### Les principales :
 - Mise en place d'un inventaire par joueur avec la possibilité de naviger dedans, qui sera composé d'entités qu'on pourra ramasser
 - Création d'une notion de lien entre entités : interagir avec une entité distante au moyen d'une autre entité (un interrupteur)
 - Principe de sélection d'entités sur le terrain pour créer des liens entre elles
 - Les BOT peuvent posséder des armes (épée ou arc) et lorsqu'un BOT meurt, il a une chance de faire apparaître l'arme qu'il utilisait sur le terrain afin que le joueur puisse la ramasser.
 - Implémentation des fonctions Pop() et Wizz() pour chaque entité :

||Pop()|Wizz()|
|:---|:---|:---|
|pomme|mode sain|mode poison|
|potion|mode sain|mode poison|
|bombe|exploser immédiatemment|exploser avec compte à rebours|
|mine|exploser immédiatemment|exploser avec compte à rebours|
|pioche|casser un mur fragile|creuse devant nous|
|joueur|change le mode (sélection/normal) du joueur|change l'orientation du joueur de 180°|
|épée|arc|attaque circulaire|
|arc|épée|tir transperçant|
|zombie|ennemi|allié|
|squelette|ennemi|allié|
|sable mouvant|Egg()|se détruit|
|téléporteur| active ou désactive le téléporteur | change le mode courant du téléporteur (téléportation aléatoire ou normale)|
|interrupteur| pop() de l'entité| wizz() de l'entité|
|porte |ouvrir| fermer|
|mur normal |mur fragile|mur magique |
|mur fragile | mur magique| mur normal|
|mur magique| mur normal| mur fragile|

### Les bonus : 
 - Lorsqu'un zombie touche un joueur, celui-ci se transforme en zombie, c'est à dire qu'il y a un changement d'avatar et surtout un changement d'automate, car le joueur ne peut plus choisir ses propres déplacements et actions. Pour qu'il redeviennent maître de lui-même, il faut que le 2ième joueur lui lance une potion dessus.
 - Si les deux joueurs sont proches sur le terrain, on fusionne les 2 sous-écran en un seul.

# 1er jeu : labyrinthe
## Description : 
Dans ce jeu basé sur la coopération, deux joueurs évoluent dans un labyrinthe généré aléatoirement, dans un style escape game. Pour progresser, ils devront éviter les pièges et s'entraider pour débloquer l'accès aux différentes parties du labyrinthe bloqué principalement par des portes que les joueurs devront ouvrir en trouvant l'interrupteur associé. Sur leurs chemins, en plus des pièges, ils devront affronter des ennemies (BOT) qui les ralentiront dans leur progression.
Lorsqu'un joueur tombe dans un piège, l'autre joueur a peu de temps pour aider son coéquipier en activant le levier associé au piège afin de le désactiver.

Les joueurs ont tous les deux accès à un inventaire en commun qui leur permet de stocker certaines entités.

## Liste des entités pouvant être récupérées dans l'inventaire : 
 - épée
 - arc 
 - pioche
 - bombe
 - pomme
 - potion

## Liste des pièges présents dans le labyrinthe :
 - sables mouvants
 - mines
 - BOT

## Conditions de fin de partie
 - les deux joueurs sont morts;
 - les deux joueurs sont sortis du labyrinthe;
 - au bout d'un certain temps après que le premier joueur est sorti, le terrain tout entier explose;
 - au bout d'un certain temps, si aucun joueur n'est sorti du labyrinthe, le terrain tout entier explose.

# 2-ième jeu : arène
Dans ce jeu, deux joueurs s'affrontent dans une arène où se trouvent toutes les entités présentes dans le moteur de jeux. Le but est d'être le dernier survivant, sachant qu'un joueur peut mourir s'il n'a plus de points de vie (représentés par des pommes), s'il s'est fait tuer par un BOT ou en conséquence d'une action de son adversaire. Les différentes entités présentes sur le terrain peuvent être bénéfiques ou maléfiques. 

Chaque joueur possède un unique interrupteur permettant de contrôler chaque entité du terrain. Pour cela, le joueur doit créer un lien entre son interrupteur et une entité. Lorsque le lien est fait, le joueur peut actionner l'interrupteur afin d'invoquer les méthodes pop() ou wizz() associé à l'entité.

Dans ce jeu, chaque joueur a son propre inventaire dans lequel il peut mettre son interrupteur (l'interrupteur devient transportable).

## Liste des pièges présents dans l'arène :
Mêmes que dans le labyrinthe en plus de la lave.

## Condition de fin de partie
 - si un des joueurs est mort.
Bonjour,


<h1>INTRODUCTION</h1>

Mon programme est un projet réalisé dans le cadre de mon parcours Développeur D'applications JAVA avec OpenClassrooms.
Le programme est un ensemble de 2 jeux proposant chacun 3 modes de jeu différents.
Vous pourrez donc jouer aux jeux suivants :

- PlusMoins
- Mastermind

Vous pourrez sélectionner le jeu au lancement de l'application.
Chaque jeu possède 3 modes de jeu :

- Le mode Challenge : Vous devrez retrouver une combinaison de chiffres ou de couleurs aléatoire générer par l'ordinateur en un nombre limité d'essai,
- Le mode Defense : Vous devrez proposer une combinaison secrète que l'ordinateur va devoir retrouver en un nombre limité d'essai,
- Le mode Duel : Vous alternez avec l'ordinateur à chaque tour pour chercher une combinaison aléatoire sans minimum de tentative.

<h1>1. Paramètres</h1>
<h2>1.1 Fichier de configuration</h2>
Vous avez dans le dossier du programme un fichier properties.
Ce fichier regroupe les paramètres indispensables au fonctionnement de l'application.
Vous y retrouver notemment les paramètres pour :

- activer le mode développeur,
- La taille de la combinaison,
- Le nombre de couleurs disponibles pour le mastermind (MAX : 10),
- Le nombre de tentative pour trouver la solution.

Des paramètres ont été assignés par défaut.
Pour les changer, il vous suffit de remplacer la valeur en face de chaque paramètre avec la valeur que vous souhaitez.

<h2>1.2 Mode développeur</h2>
Vous avez à disposition un mode développeur vous permettant de vous rendre visible la combinaison secrète.
Par défaut le mode développeur n'est pas activé.
Pour activer ce mode développeur vous avez 2 possibilités.

- La première est de passer en argument la valeur true. Pour cela il faut que vous rendre sur l'edit configuration de votre IDE et    assigner à la valeur true dans Program arguments.
- La seconde est d'assigner une valeur "true" au paramètre dev.mode du fichier config.properties

ATTENTION : Le passage par argument est prioritaire. Ainsi si l'argument est sur "true" et que sur le fichier config, le dev.mode est sur "false", alors c'est la valeur de l'argument qui remporte la dualité et c'est sa valeur qui sera sélectionnée. En revanche, si aucune valeur n'a été assigné à l'argument, alors la valeur par défaut sera celle du fichier config si le dev.mode possède une valeur. Si aucune valeur n'est assigné à aucune de ces méthodes, alors la valeur sera "false" par défaut.

<h1>2. Compilation</h1>

Le projet a été codé sur IntelliJ.
Vous pouvez si vous le souhaitez utiliser un IDE ou bien vous pouvez le programme à partir de votre console en utilisant l'application javac de la façon suivante :

 javac -d classes/ sources/MonFichierACompiler.java

<h1>INTRODUCTION</h1>

Mon programme est un projet réalisé dans le cadre de mon parcours Développeur D'applications JAVA avec OpenClassrooms.
Le programme est un ensemble de 2 jeux proposant chacun 3 modes de jeu différents.
<p>Vous pourrez donc jouer aux jeux suivants :</p>

- PlusMoins
- Mastermind

Vous pourrez sélectionner le jeu au lancement de l'application.
<p>Chaque jeu possède 3 modes de jeu :</p>

- Le mode Challenge : Vous devrez retrouver une combinaison de chiffres ou de couleurs aléatoire générer par l'ordinateur en un nombre limité d'essai,
- Le mode Defense : Vous devrez proposer une combinaison secrète que l'ordinateur va devoir retrouver en un nombre limité d'essai,
- Le mode Duel : Vous alternez avec l'ordinateur à chaque tour pour chercher une combinaison aléatoire sans minimum de tentative.

Le projet a été programmé sur IntelliJ. 

<h1>1.Récupération du projet</h1>

Tout d'abord pour récupérer le projet sur GitHub vous pouvez soit faire un import depuis votre IDE si celui-ci vous le permet.

<h2>1.1 IDE</h2>
Pour l'IDE, je vais parler du miens et donc d'intelliJ.
Pour récupérer le projet sur IntelliJ, vous devez : 

- Étape 1 : 

<h2>1.2 Console</h2>





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

<h1>2 Lancement du programme</h1>


<h2>2.1 Compilation</h2>

Vous pouvez compiler via un IDE (Environnement de développement) ou directement sur la console de votre système.
Nous allons voir comment le compiler et l'exécuter votre IDE et sur votre console.

<h2>2.1.1 IDE</h2>

Vous pouvez si vous le souhaitez utiliser un IDE comme Eclipse, IntelliJ ou encore NetBeans.
Une fois sur votre IDE, vous pouvez selon l'IDE compilé depuis les onglets, ou un icone en raccourcis sur votre compilateur, ou encore en fait directement un clic droit sur la classe principale et choisir de compiler à partir de là.
Pour IntelliJ vous avez les 3 possibilités.

<h2>2.1.2 Console</h2>
Vous pouvez aussi démarrer le programme depuis votre console en utilisant l'application javac avec la ligne de commande suivante :
 javac -d classes/ sources/MonFichierACompiler.java
 
<h1>2.2 Exécution</h1>



 
 

<h1>INTRODUCTION</h1>

Mon programme est un projet réalisé dans le cadre de mon parcours Développeur D'applications JAVA avec OpenClassrooms.
Le programme est un ensemble de 2 jeux proposant chacun 3 modes différents.
<p>Vous pourrez donc jouer aux jeux suivants :</p>

- PlusMoins
- Mastermind

Vous pouvez sélectionner le jeu au lancement de l'application.
<p>Chaque jeu possède 3 modes :</p>

- Le mode Challenge : Vous devrez retrouver une combinaison de chiffres ou de couleurs aléatoire générée par l'ordinateur en un nombre limité d'essai,
- Le mode Défenseur : Vous devrez proposer une combinaison secrète que l'ordinateur va devoir retrouver en un nombre limité d'essai,
- Le mode Duel : Vous alternerez avec l'ordinateur à chaque tour pour chercher une combinaison aléatoire sans minimum de tentative.

Le projet a été programmé sur IntelliJ. 

<h1>1.Récupération du projet</h1>

Nous allons voir étape par étape comment récupérer le projet sur l'IDE (Environnement De Développement) IntelliJ directement ou à partir de la console. Si vous savez déjà comment faire, je vous invite à vous rendre directement à la partie 2 au sujet des paramètres.

<h2>1.1 IDE : IntelliJ</h2>
<p>Il est possible de récupérer le projet directement sur votre IDE. Je vais vous donner les étapes à suivre pour réaliser cette copie sur IntelliJ.</p>

<p>Pour récupérer le projet sur IntelliJ, vous devez :</p>

- Étape 1 : Ouvrir intelliJ et choisir "Check out from Version Control" puis sélectionner Git
- Étape 2 : Vous devez vous rendre sur GitHub sur le lien suivant : https://github.com/Alvindu13/Projet3_OP 
- Étape 3 : Vous devez cliquer sur "clone or download" en vert, en haut à droite et copier le lien du repository
- Étape 4 : Vous pouvez alors copier ce lien dans la ligne URL de votre IDE et choisir "clone"

<h2>1.2 Console</h2>

<p>Depuis la console :</p>

- Étape 1 : Choisir un dossier qui recevra le projet
- Étape 2 : Il faut vous positionner sur le chemin de votre dossier avec la commande cd classpath/
- Étape 3 : Il faut convertir ce projet en projet git avec la ligne de commande git init
- Étape 4 : Vous devez suivre les étapes 2 et 3 de la partie IDE ci-dessus pour récupérer le lien du repository
- Étape 5 : Vous pouvez fork le projet sur votre console avec la ligne de commande : git clone lienRespository

<h1>2. Paramètres</h1>
<h2>2.1 Fichier de configuration</h2>
Vous avez dans le dossier du programme un fichier properties.
Ce fichier regroupe les paramètres indispensables au fonctionnement de l'application.
Vous y retrouver notemment les paramètres pour :

- activer le mode développeur,
- La taille de la combinaison,
- Le nombre de couleurs disponibles pour le mastermind (<strong>MAX : 10</strong>),
- Le nombre de tentative pour trouver la solution.

Des paramètres ont été assignés par défaut.
Pour les changer, il vous suffit de remplacer la valeur en face de chaque paramètre avec la valeur que vous souhaitez.

<h2>2.2 Mode développeur</h2>
Vous avez à disposition un mode développeur vous permettant de vous rendre visible la combinaison secrète.
Par défaut le mode développeur n'est pas activé.
Pour activer ce mode développeur vous avez 2 possibilités.

- La première est de passer en argument la valeur true. Pour cela il faut vous rendre sur l'edit configuration de la main class sur votre IDE et assigner à la valeur true dans Program arguments.
- La seconde est d'assigner une valeur "true" au paramètre dev.mode du fichier config.properties

ATTENTION : Le passage par argument est prioritaire. Ainsi si l'argument est sur "true" et que sur le fichier config, le dev.mode est sur "false", alors c'est la valeur de l'argument qui remporte la dualité et c'est sa valeur qui sera sélectionnée. En revanche, si aucune valeur n'a été assigné à l'argument, alors la valeur par défaut sera celle du fichier config si le dev.mode possède une valeur. Si aucune valeur n'est assigné à aucune de ces méthodes, alors la valeur sera "false" par défaut.

<h1>3 Lancement du programme</h1>

Vous pouvez lancer le programme de plusieurs façons. Nous allons voir comment le lancer depuis votre IDE et depuis la console.
Nous allons voir comment le compiler et l'exécuter que ce soit sur l'IDE IntelliJ ou sur votre console. 
<h2>3.1 Compilation</h2>

<h3>3.1.1 IDE : IntelliJ</h3>

Vous pouvez si vous le souhaitez utiliser un IDE comme Eclipse, IntelliJ ou encore NetBeans.
Une fois sur votre IDE, vous pouvez selon l'IDE compilé depuis les onglets, ou un icone en raccourcis sur votre compilateur, ou encore en fait directement un clic droit sur la classe principale et choisir de compiler à partir de là.
Pour IntelliJ vous avez les 3 possibilités.

<h3>3.1.2 Console</h3>
Vous pouvez aussi démarrer le programme depuis votre console en utilisant l'application javac avec la ligne de commande suivante :
javac -d classes/ sources/MonFichierACompiler.java
Pour compiler le projet java avec maven vous pouvez utiliser : mvn package en ligne de commande
 
<h2>3.2 Exécution</h2>

<h3>3.2.1 IDE : IntelliJ</h3>

Pour démarrer le programme sur IntelliJ il faut avant tout le compiler. Toutefois, la commande run permet de compiler et d'exécuter le programme suite à la compilation. De la même façon que pour compiler, vous trouverez la commande run à proximité de la commande compile. Sur IntelliJ, son icône de raccourcis est une flèche verte.

<h3>3.2.2 Console</h3>

À partir de la console, vous devez obligatoirement faire la première étape de compilation manuellement, et ensuite faire l'étape d'exécution. Nous avons déjà vu la ligne de commande pour la compilation, voici celle pour l'exécution :
java -classpath classes/ MonFichierACompiler

<h1>4.Ressources</h1>

<p>Le programme dispose des ressources suivantes :</p> 

<ul>
<li>config.properties : regroupe les paramètres cités dans la partie "paramètres".</li>
<li>log4j.xml : le contenu de ce fichier permet de générer un journal des évènements survenus pendant l'exécution du programme.</li>
<li>pom.xml : contient une dépendance pour le logging.</li>
</ul>




 
 

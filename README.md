# 1718_INFOB314_COMPILATEUR_GROUPE09

Compilateur transformant un programme écrit en .B314 dans un formalisme de bas niveau, exécutable par le moteur de jeu.

## Objectifs

 Produire un compilateur fonctionnele réaliste, implémenté en JAVA, traduisant n'importe quelle programme _.B314_ syntaxiquement valide étant soit :

 1. Un **fichier décrivant les mondes et ses composantes**, où il s'agit de modéliser les éléments constituant le monde, le plateau de jeu ainsi que ses différents constituants, mais aussi les personnages y evoluant.

  ``` B314
  /∗ Comments are be tween delimiters ∗/
  declare and retain
    /∗ Global declarations ∗/
    i as integer;
    j as integer;
    arena as square[27,27];
  by default do
    set i to 1
    set j to 1
  done

  while i < 17 do
    while j < 17 do
        set arena [ i , j ] to vines
        set j to j + 1
    done
    set i to i + 1
  done

  while j < 18 do
    set arena[0,j] to rock
    set arena[17,j] to rock
  set j to j+1
  done

  by default do
    set arena[1,1] to player
    set arena[12,10] to ennemi
    set arena[8,12] to zombie
    set arena[5,5] to zombie
    set arena[2,7] to rock
    set arena[3,7] to rock
    set arena[4,7] to dirt
    set arena[3,6] to rock
    set arena[6,7] to fruits
    set arena[10,10] to map
    set arena[2,16] to radio
    set arena[16,4] to soda
    set arena[26,26] to graal
  done
  ```

 2. Un **fichier décrivant la stratégie de jeu d'un personnage**, produit le code machine permettant l'éxécution de cette stratégie à chaque tours de jeu.

``` B314
/∗ Comments are between delimiters ∗/
declare and retain
    /* Global variables */
    x as integer;
    b as boolean;
    f as function ( ) : boolean
    do
      set t to true
      /∗ other instructions here ∗/
      return t;
    done
when your turn
 when x > 5 and life < 2 do
     next use soda
 done
 by default do /*Default */
     set x to x + 1
     next move west
done
```

## Devellopment

### Requirements

- [Git](https://git-scm.com) - [Git Basics](http://rogerdudler.github.io/git-guide/)
- [Maven](https://maven.apache.org) - [Maven Guide](http://books.sonatype.com/mvnex-book/reference/public-book.html)
- [ANTLR4](http://www.antlr.org)

### Code Quality

Utilisation de [Google Java Style](https://github.com/google/styleguide) pour la propriété du code (noms de variable significatifs, conventions de nommage cohérentes, ...).

- [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)

- [Configurer le 'coding style' sur InteliJ ou Eclipse](https://github.com/HPI-Information-Systems/Metanome/wiki/Installing-the-google-styleguide-settings-in-intellij-and-eclipse)

### Code coverage conditions

- méthodes: **80%**
- lignes: **70%**
- conditions: **60%**

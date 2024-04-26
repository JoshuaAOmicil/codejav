package tp1;

import java.util.Random;

class Grille {
    private String[][] dimension;
    private static int nbTresor;
    private static int nbPiege;
    private boolean[][] visite;
    private int score;
    private int tresorTrouve;

    public Grille() {
    }

    public static void setNbTresor(int nbTresor) {
        Grille.nbTresor = nbTresor;
    }

    public static void setNbPiege(int nbPiege) {
        Grille.nbPiege = nbPiege;
    }

    public void initializerGrille(int taille) {
        dimension = new String[taille][taille];
        visite = new boolean[taille][taille];
        score = 0;
        tresorTrouve = 0;
    }
    /**
     * Afficher l'état  de la grille.
     */
    public void afficherGrille() {
        for (int ligne = 0; ligne < dimension.length; ligne++) {
            for (int colonne = 0; colonne < dimension[ligne].length; colonne++) {
                System.out.print("-----");
            }
            System.out.print("----------------");
            System.out.println();
            for (int colonne = 0; colonne < dimension[ligne].length; colonne++) {
                System.out.print("|");
                if (dimension[ligne][colonne] != null) {
                    String Case = dimension[ligne][colonne];
                    int ajustement = 4 - Case.length();
                    for (int i = 0; i < ajustement; i++) {
                        System.out.print(" ");
                    }
                    System.out.print(Case);
                } else {
                    System.out.print("       ");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        for (int colonne = 0; colonne < dimension[dimension.length - 1].length; colonne++) {
            System.out.print("--------");
        }
        System.out.println("-");
        System.out.println();
    }
    /**
     * Génère une nouvelle cellule de grille à (i, j).
     * Attribue aléatoirement un élément à la cellule et met à jour l'état du jeu en conséquence.
     *
     * @param i l'indice de la ligne
     * @param j l'indice de la colonne
     */
    public void NouvelleGrille(int i, int j) {
        Random rand = new Random();
        String[] elements = {"Neutre!", "Piege! ", "Tresor!"};

        int caseAleatoire = rand.nextInt(3);
        dimension[i][j] = elements[caseAleatoire];
        visite[i][j] = true;

        if (elements[caseAleatoire].equals("Tresor!") && nbTresor > 0) {
            nbTresor--;
            tresorTrouve++;
            score += 5;
            System.out.println("Vous avez trouvé un trésor!");
            deuxiemeMoitieDuTresor(i, j);
        } else if (elements[caseAleatoire].equals("Piege! ") && nbPiege > 0) {
            nbPiege--;
            score -= 10;
            System.out.println("Vous avez trouvé un piège!");
        } else if (elements[caseAleatoire].equals("Neutre!")) {
            System.out.println("La case est neutre!");
        }
    }

    private void deuxiemeMoitieDuTresor(int i, int j) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        Random rand = new Random();
        int directionAdjacent = rand.nextInt(4);
        int x = i + dx[directionAdjacent];
        int y = j + dy[directionAdjacent];

        if (x >= 0 && x < dimension.length && y >= 0 && y < dimension[0].length && !visite[x][y]) {
            dimension[x][y] = "      ";
        } else {
            for (int k = 0; k < 3; k++) {
                directionAdjacent = (directionAdjacent + 1) % 4;
                x = i + dx[directionAdjacent];
                y = j + dy[directionAdjacent];
                if (x >= 0 && x < dimension.length && y >= 0 && y < dimension[0].length && !visite[x][y]) {
                    dimension[x][y] = "      ";
                    break;
                }
            }
        }
    }

    public boolean verifierSiDejaVisiter(int ligne, int colonne) {
        return visite[ligne][colonne];
    }

    public boolean jeuxTerminer() {
        return nbTresor == 0;
    }

    public int getScore() {
        return score;
    }
}

package tp1;

class Grille {
    private String[][] dimension;
    private static int nbTresor;
    private static int nbPiege;
    private boolean[][] visite;
    private int score;
    private int tresorTrouve;

    public Grille() {
        // Constructeur de la classe Grille2
    }

    public void setNbTresor(int nbTresor) {
        // Définit le nombre de trésors
        this.nbTresor = nbTresor;
    }

    public void setNbPiege(int nbPiege) {
        // Définit le nombre de pièges
        this.nbPiege = nbPiege;
    }

    public void initializerGrille(int taille) {
        // Initialise la grille avec la taille donnée
        dimension = new String[taille][taille];
        visite = new boolean[taille][taille];
        score = 0;
        tresorTrouve = 0;
    }

    public void afficherGrille() {
        // Affiche la grille
    }

    public void NouvelleGrille(int i, int j) {
        // Crée une nouvelle grille avec des éléments aléatoires
    }

    private void deuxiemeMoitieDuTresor(int i, int j) {
        // Gère la deuxième moitié du trésor
    }

    public boolean verifierSiDejaVisiter(int ligne, int colonne) {
        // Vérifie si une cellule a déjà été visitée
        return visite[ligne][colonne];
    }

    public boolean jeuxTerminer() {
        // Vérifie si le jeu est terminé
        return nbTresor == 0;
    }

    public int getScore() {
        // Retourne le score
        return score;
    }
}
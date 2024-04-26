package tp1;

public class Resultat {
    private String meilleurJoueur;
    private int meilleurScore;

    public Resultat(String meilleurJoueur, int meilleurScore) {
        // Constructeur de la classe Result
        this.meilleurJoueur = meilleurJoueur;
        this.meilleurScore = meilleurScore;
    }

    public String getMeilleurJoueur() {
        // Retourne le nom du meilleur joueur
        return meilleurJoueur;
    }

    public int getMeilleurScore() {
        // Retourne le meilleur score
        return meilleurScore;
    }
}
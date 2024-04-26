package tp1;

import java.io.FileWriter;
import java.io.IOException;

public class Joueurr {
    private int score;
    private String nomFic;

    public Joueurr(int score, String NomFichier) {
        // Constructeur de la classe Joueurr
        this.score = score;
        this.nomFic = NomFichier;
    }

    public Resultat finirJeau(String nom, int MeilleurScore, String MeilleurJoueur) {
        // Termine le jeu, met à jour le score le plus élevé et écrit le score dans un fichier
        if (score > MeilleurScore) {
            MeilleurScore = score;
            MeilleurJoueur = nom;
        }

        try {
            FileWriter ecrireFic = new FileWriter(nomFic, true); //
            ecrireFic.write(nom + ";" + score + "\n");
            ecrireFic.close();
        } catch (IOException e) {
            System.out.println("Une erreur s'est produite lors de l'écriture dans le fichier.");
        }

        return new Resultat(MeilleurJoueur, MeilleurScore);
    }
}
package tp1;

import java.io.*;
import java.util.Scanner;

public class Utilitaires  {
    private String nomFic;

    public Utilitaires(String fichierTxt) {
        // Constructeur de la classe Utilitaires
        this.nomFic = fichierTxt;
    }

    public void ecritureDuFichier(String nom, int score) {
        // Écrit le nom du joueur et son score dans le fichier
        String[] enre = {"le nom du joueur  : " + nom + "\n" + "Score de  " + nom + " : " + score};

        FileOutputStream fileStream = ouvrirFichierEcriture(nomFic);
        PrintStream sortie = new PrintStream(fileStream);
        for (String line : enre) {
            sortie.println(line);
        }
        System.out.println("Joueur enregistrée avec succes!.");
        fermerFichier(fileStream);
    }

    public void lectureDuFichier() {
        // Lit le contenu du fichier
        FileInputStream fileStream = ouvrirFichierLecture(nomFic);
        Scanner scanner = new Scanner(fileStream);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
        fermerFichier(fileStream);
    }

    private FileOutputStream ouvrirFichierEcriture(String nomFic) {
        // Ouvre le fichier pour l'écriture
        FileOutputStream ficSortie = null;
        try {
            ficSortie = new FileOutputStream(nomFic);
        } catch (FileNotFoundException ex) {
            System.out.println("Erreur de création de fichier");
        }
        return ficSortie;
    }

    private FileInputStream ouvrirFichierLecture(String nomFichier) {
        // Ouvre le fichier pour la lecture
        FileInputStream fichier = null;
        try {
            fichier = new FileInputStream(nomFichier);
        } catch (FileNotFoundException ex) {
            System.out.println("Erreur d’ouverture de fichier");
        }
        return fichier;
    }

    private void fermerFichier(Closeable fichier) {
        // Ferme le fichier
        try {
            fichier.close();
        } catch (IOException ex) {
            System.out.println("Erreur de fermeture de fichier");
        }
    }
}
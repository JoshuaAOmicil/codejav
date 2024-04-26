package tp1;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Tresor {
    private static final String SCORES_FILE = "scores.txt";

    public static void main(String[] args) {
        // Point d'entrée du programme
        Scanner scan = new Scanner(System.in);
        int dimension = 0;
        int nbTentatives = 0;
        String MeilleurJoueur = "";
        int MeilleurScore = 0;

        try {
            // Lit le fichier de scores et détermine le meilleur joueur et le meilleur score
            Scanner fileScanner = new Scanner(new File(SCORES_FILE));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(";");
                String joueur = parts[0];
                int score = Integer.parseInt(parts[1]);
                if (score > MeilleurScore) {
                    MeilleurScore = score;
                    MeilleurJoueur = joueur;
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Une erreur s'est produite lors de la lecture du fichier");
        }

        if (!MeilleurJoueur.equals("")) {
            System.out.println("Meilleur joueur: " + MeilleurJoueur);
            System.out.println("Meilleur score: " + MeilleurScore);
        }

        String reponse;
        do {
            // Démarre une nouvelle partie
            Grille gr = new Grille();
            System.out.println("Votre nom ?: ");
            String nom = scan.nextLine();
            dimension = determinerDimension(gr, dimension);
            int choix = 0;
            choix = appliquerDifficulte(gr, dimension, choix);
            nbTentatives = calculerNbTentative(dimension);

            do {
                // Boucle de jeu
                afficherLaGrille(gr);
                afficherNbTentatives(nbTentatives);
                saisirPosition(gr, dimension);
                nbTentatives--;

                System.out.println("Votre score actuel est : " + gr.getScore());

                if (gr.jeuxTerminer()) {
                    // Si le jeu est terminé, affiche la grille et le score final
                    gr.afficherGrille();
                    Joueurr joueur = new Joueurr(gr.getScore(), "scores.txt");
                    Resultat result = joueur.finirJeau(nom, MeilleurScore, MeilleurJoueur);
                    MeilleurJoueur = result.getMeilleurJoueur();
                    MeilleurScore = result.getMeilleurScore();
                    System.out.println("Dernier Joueur: " + nom + ", Score: " + gr.getScore());
                    break;
                }
                if (gr.getScore() > MeilleurScore) {
                    MeilleurScore = gr.getScore();
                    MeilleurJoueur = nom;
                }
            } while (nbTentatives != 0);

            if (gr.getScore() > MeilleurScore) {
                MeilleurScore = gr.getScore();
                MeilleurJoueur = nom;
            }

            System.out.println("Meilleur Score: " + MeilleurScore + ", Meilleur Joueur: " + MeilleurJoueur);

            do {
                // Demande à l'utilisateur s'il veut rejouer
                System.out.println("Voulez-vous rejouer (Oui/Non) ?");
                reponse = scan.nextLine();
            } while (!reponse.equalsIgnoreCase("Oui") && !reponse.equalsIgnoreCase("Non"));
        } while (reponse.equalsIgnoreCase("Oui"));
    }

    private static void afficherNbTentatives(int nbTentatives) {
        // Affiche le nombre de tentatives restantes
        System.out.println("Il vous reste " + nbTentatives + " tentatives");
    }

    private static int calculerNbTentative(int dimension) {
        // Calcule le nombre de tentatives en fonction de la dimension
        return dimension * 3;
    }

    private static void saisirPosition(Grille gr, int dimension) {
        // Permet à l'utilisateur de saisir une position sur la grille
        Scanner scan = new Scanner(System.in);
        int ligneChoix;
        int colonneChoix;

        boolean dejaVisiter = false;

        do {
            do {
                System.out.println("Entrez i: (Doit être entre 0 et " + (dimension - 1) + ")");
                ligneChoix = scan.nextInt();
            } while (ligneChoix > dimension - 1 || ligneChoix < 0);
            do {
                System.out.println("Entrez j: (Doit être entre 0 et " + (dimension - 1) + ")");
                colonneChoix = scan.nextInt();
            } while (colonneChoix > dimension - 1 || colonneChoix < 0);
            if (gr.verifierSiDejaVisiter(ligneChoix, colonneChoix)) {
                System.out.println("Vous avez déjà choisi cette case. Veuillez choisir une autre.");
                dejaVisiter = true;
            } else {
                dejaVisiter = false;
            }
        } while (dejaVisiter);

        gr.NouvelleGrille(ligneChoix, colonneChoix);
    }

    private static void afficherLaGrille(Grille gr) {
        // Affiche la grille
        System.out.println("Voici la grille, choisissez une position: ");
        gr.afficherGrille();
    }

    private static int appliquerDifficulte(Grille gr, int dimension, int choix) {
        // Applique un niveau de difficulté en fonction du choix de l'utilisateur
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("Choix du niveau de difficulté: ");
            System.out.println("1. Facile");
            System.out.println("2. Moyen");
            System.out.println("3. Difficile");
            choix = scan.nextInt();
        } while (choix > 3 || choix < 1);

        int nbTresor;
        int nbPiege;

        switch (choix) {
            case 1:
                nbTresor = (int) (dimension * dimension * 0.2);
                nbPiege = (int) (dimension * dimension * 0.1);
                break;
            case 2:
                nbTresor = (int) (dimension * dimension * 0.15);
                nbPiege = (int) (dimension * dimension * 0.15);
                break;
            case 3:
                nbTresor = (int) (dimension * dimension * 0.1);
                nbPiege = (int) (dimension * dimension * 0.2);
                break;
            default:
                nbTresor = 0;
                nbPiege = 0;
                break;
        }

        gr.setNbTresor(nbTresor);
        gr.setNbPiege(nbPiege);

        return choix;
    }

    private static int determinerDimension(Grille gr, int dimension) {
        // Détermine la dimension de la grille en fonction de l'entrée de l'utilisateur
        Scanner scan = new Scanner(System.in);
        System.out.println("Dimension?: ");
        dimension = scan.nextInt();
        gr.initializerGrille(dimension);
        return dimension;
    }
}
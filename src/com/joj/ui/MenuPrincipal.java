package com.joj.ui;

import com.joj.service.AuthService;
import com.joj.service.UtilisateurService;
import com.joj.service.PaysService;
import com.joj.service.DisciplineService;
import com.joj.service.AthleteService;
import com.joj.service.CompetitionService;
import com.joj.service.ResultatService;
import com.joj.model.Utilisateur;
import com.joj.model.Pays;
import com.joj.model.Discipline;
import com.joj.model.Athlete;
import com.joj.model.Competition;
import com.joj.model.Resultat;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner;
    private AuthService authService;
    private UtilisateurService utilisateurService;
    private PaysService paysService;
    private DisciplineService disciplineService;
    private AthleteService athleteService;
    private CompetitionService competitionService;
    private ResultatService resultatService;

    public MenuPrincipal() {
        this.scanner = new Scanner(System.in);
        this.authService = new AuthService();
        this.utilisateurService = new UtilisateurService();
        this.paysService = new PaysService();
        this.disciplineService = new DisciplineService();
        this.athleteService = new AthleteService();
        this.competitionService = new CompetitionService();
        this.resultatService = new ResultatService();
    }

    public void demarrer() {
        while (true) {
            if (!authService.estConnecte()) {
                afficherConnexion();
            } else {
                afficherMenuPrincipal();
            }
        }
    }

    private void afficherConnexion() {
        System.out.println("====================================");
        System.out.println("JEUX OLYMPIQUES DE LA JEUNESSE 2026");
        System.out.println("====================================");
        System.out.print("Login : ");
        String login = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();

        if (authService.seConnecter(login, motDePasse)) {
            System.out.println("Connexion reussie !");
        } else {
            System.out.println("Login ou mot de passe incorrect !");
        }
    }

    private void afficherMenuPrincipal() {
        while (authService.estConnecte()) {
            System.out.println("====================================");
            System.out.println("JEUX OLYMPIQUES DE LA JEUNESSE 2026");
            System.out.println("====================================");
            System.out.println("Bienvenue " + authService.getUtilisateurConnecte().getNomComplet() + " !");
            System.out.println("1. Gestion des pays");
            System.out.println("2. Gestion des disciplines");
            System.out.println("3. Gestion des athletes");
            System.out.println("4. Gestion des competitions");
            System.out.println("5. Gestion des resultats");
            System.out.println("6. Statistiques");

            if (authService.estAdmin()) {
                System.out.println("7. Gestion des utilisateurs");
            }

            System.out.println("8. Deconnexion");
            System.out.println("9. Quitter");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    menuPays();
                    break;
                case 2:
                    menuDiscipline();
                    break;
                case 3:
                    menuAthlete();
                    break;
                case 4:
                    menuCompetition();
                    break;
                case 5:
                    menuResultat();
                    break;
                case 6:
                    afficherStatistiques();
                    break;
                case 7:
                    if (authService.estAdmin()) {
                        menuUtilisateur();
                    } else {
                        System.out.println("Acces refuse !");
                    }
                    break;
                case 8:
                    authService.seDeconnecter();
                    System.out.println("Deconnecte avec succes !");
                    break;
                case 9:
                    System.out.println("Au revoir !");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    private void menuUtilisateur() {
        while (true) {
            System.out.println("======== GESTION DES UTILISATEURS ========");
            System.out.println("1. Ajouter utilisateur");
            System.out.println("2. Modifier utilisateur");
            System.out.println("3. Supprimer utilisateur");
            System.out.println("4. Rechercher utilisateur");
            System.out.println("5. Afficher utilisateurs");
            System.out.println("6. Retour");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("Nom complet : ");
                    String nomComplet = scanner.nextLine();
                    System.out.print("Login : ");
                    String login = scanner.nextLine();
                    System.out.print("Mot de passe : ");
                    String motDePasse = scanner.nextLine();
                    System.out.print("Role (admin/user) : ");
                    String role = scanner.nextLine();
                    utilisateurService.ajouterUtilisateur(nomComplet, login, motDePasse, role);
                    System.out.println("Utilisateur ajoute avec succes !");
                    break;
                case 2:
                    System.out.print("ID de l'utilisateur : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Utilisateur u = utilisateurService.rechercherUtilisateurParId(id);
                    if (u != null) {
                        System.out.print("Nouveau nom complet : ");
                        String nvNom = scanner.nextLine();
                        System.out.print("Nouveau login : ");
                        String nvLogin = scanner.nextLine();
                        System.out.print("Nouveau mot de passe : ");
                        String nvMdp = scanner.nextLine();
                        System.out.print("Nouveau role : ");
                        String nvRole = scanner.nextLine();
                        utilisateurService.modifierUtilisateur(id, nvNom, nvLogin, nvMdp, nvRole);
                        System.out.println("Utilisateur modifie avec succes !");
                    } else {
                        System.out.println("Utilisateur non trouve !");
                    }
                    break;
                case 3:
                    System.out.print("ID de l'utilisateur : ");
                    int idSuppr = scanner.nextInt();
                    scanner.nextLine();
                    utilisateurService.supprimerUtilisateur(idSuppr);
                    System.out.println("Utilisateur supprime avec succes !");
                    break;
                case 4:
                    System.out.print("ID de l'utilisateur : ");
                    int idRecherche = scanner.nextInt();
                    scanner.nextLine();
                    Utilisateur uRecherche = utilisateurService.rechercherUtilisateurParId(idRecherche);
                    if (uRecherche != null) {
                        System.out.println("ID : " + uRecherche.getId());
                        System.out.println("Nom complet : " + uRecherche.getNomComplet());
                        System.out.println("Login : " + uRecherche.getLogin());
                        System.out.println("Role : " + uRecherche.getRole());
                    } else {
                        System.out.println("Utilisateur non trouve !");
                    }
                    break;
                case 5:
                    List<Utilisateur> utilisateurs = utilisateurService.listerUtilisateurs();
                    for (Utilisateur uList : utilisateurs) {
                        System.out.println(uList.getId() + " | " + uList.getNomComplet() + " | " + uList.getLogin() + " | " + uList.getRole());
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    private void menuPays() {
        while (true) {
            System.out.println("======== GESTION DES PAYS ========");
            System.out.println("1. Ajouter pays");
            System.out.println("2. Modifier pays");
            System.out.println("3. Supprimer pays");
            System.out.println("4. Rechercher pays");
            System.out.println("5. Liste des pays");
            System.out.println("6. Retour");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("Nom du pays : ");
                    String nom = scanner.nextLine();
                    System.out.print("Continent : ");
                    String continent = scanner.nextLine();
                    paysService.ajouterPays(nom, continent);
                    System.out.println("Pays ajoute avec succes !");
                    break;
                case 2:
                    System.out.print("ID du pays : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Pays p = paysService.rechercherPaysParId(id);
                    if (p != null) {
                        System.out.print("Nouveau nom : ");
                        String nvNom = scanner.nextLine();
                        System.out.print("Nouveau continent : ");
                        String nvContinent = scanner.nextLine();
                        paysService.modifierPays(id, nvNom, nvContinent);
                        System.out.println("Pays modifie avec succes !");
                    } else {
                        System.out.println("Pays non trouve !");
                    }
                    break;
                case 3:
                    System.out.print("ID du pays : ");
                    int idSuppr = scanner.nextInt();
                    scanner.nextLine();
                    paysService.supprimerPays(idSuppr);
                    System.out.println("Pays supprime avec succes !");
                    break;
                case 4:
                    System.out.print("ID du pays : ");
                    int idRecherche = scanner.nextInt();
                    scanner.nextLine();
                    Pays pRecherche = paysService.rechercherPaysParId(idRecherche);
                    if (pRecherche != null) {
                        System.out.println("ID : " + pRecherche.getId());
                        System.out.println("Nom : " + pRecherche.getNom());
                        System.out.println("Continent : " + pRecherche.getContinent());
                    } else {
                        System.out.println("Pays non trouve !");
                    }
                    break;
                case 5:
                    List<Pays> pays = paysService.listerPays();
                    for (Pays pList : pays) {
                        System.out.println(pList.getId() + " | " + pList.getNom() + " | " + pList.getContinent());
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    private void menuDiscipline() {
        while (true) {
            System.out.println("======== GESTION DES DISCIPLINES ========");
            System.out.println("1. Ajouter discipline");
            System.out.println("2. Modifier discipline");
            System.out.println("3. Supprimer discipline");
            System.out.println("4. Rechercher discipline");
            System.out.println("5. Afficher disciplines");
            System.out.println("6. Retour");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("Nom de la discipline : ");
                    String nom = scanner.nextLine();
                    System.out.print("Description : ");
                    String description = scanner.nextLine();
                    disciplineService.ajouterDiscipline(nom, description);
                    System.out.println("Discipline ajoutee avec succes !");
                    break;
                case 2:
                    System.out.print("ID de la discipline : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Discipline d = disciplineService.rechercherDisciplineParId(id);
                    if (d != null) {
                        System.out.print("Nouveau nom : ");
                        String nvNom = scanner.nextLine();
                        System.out.print("Nouvelle description : ");
                        String nvDescription = scanner.nextLine();
                        disciplineService.modifierDiscipline(id, nvNom, nvDescription);
                        System.out.println("Discipline modifiee avec succes !");
                    } else {
                        System.out.println("Discipline non trouvee !");
                    }
                    break;
                case 3:
                    System.out.print("ID de la discipline : ");
                    int idSuppr = scanner.nextInt();
                    scanner.nextLine();
                    disciplineService.supprimerDiscipline(idSuppr);
                    System.out.println("Discipline supprimee avec succes !");
                    break;
                case 4:
                    System.out.print("ID de la discipline : ");
                    int idRecherche = scanner.nextInt();
                    scanner.nextLine();
                    Discipline dRecherche = disciplineService.rechercherDisciplineParId(idRecherche);
                    if (dRecherche != null) {
                        System.out.println("ID : " + dRecherche.getId());
                        System.out.println("Nom : " + dRecherche.getNom());
                        System.out.println("Description : " + dRecherche.getDescription());
                    } else {
                        System.out.println("Discipline non trouvee !");
                    }
                    break;
                case 5:
                    List<Discipline> disciplines = disciplineService.listerDisciplines();
                    for (Discipline dList : disciplines) {
                        System.out.println(dList.getId() + " | " + dList.getNom() + " | " + dList.getDescription());
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    private void menuAthlete() {
        while (true) {
            System.out.println("======== GESTION DES ATHLETES ========");
            System.out.println("1. Ajouter athlete");
            System.out.println("2. Modifier athlete");
            System.out.println("3. Supprimer athlete");
            System.out.println("4. Rechercher athlete");
            System.out.println("5. Afficher athletes");
            System.out.println("6. Retour");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prenom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Sexe (M/F) : ");
                    String sexe = scanner.nextLine();
                    while (!sexe.equals("M") && !sexe.equals("F")) {
                        System.out.println("Sexe invalide ! Utilisez M ou F");
                        System.out.print("Sexe (M/F) : ");
                        sexe = scanner.nextLine();
                    }
                    System.out.print("Date de naissance (AAAA-MM-JJ) : ");
                    String dateStr = scanner.nextLine();
                    Date dateNaissance = Date.valueOf(dateStr);
                    System.out.print("ID du pays : ");
                    int paysId = scanner.nextInt();
                    System.out.print("ID de la discipline : ");
                    int disciplineId = scanner.nextInt();
                    scanner.nextLine();
                    athleteService.ajouterAthlete(nom, prenom, sexe, dateNaissance, paysId, disciplineId);
                    System.out.println("Athlete ajoute avec succes !");
                    break;
                case 2:
                    System.out.print("ID de l'athlete : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Athlete a = athleteService.rechercherAthleteParId(id);
                    if (a != null) {
                        System.out.print("Nouveau nom : ");
                        String nvNom = scanner.nextLine();
                        System.out.print("Nouveau prenom : ");
                        String nvPrenom = scanner.nextLine();
                        System.out.print("Nouveau sexe (M/F) : ");
                        String nvSexe = scanner.nextLine();
                        while (!nvSexe.equals("M") && !nvSexe.equals("F")) {
                            System.out.println("Sexe invalide ! Utilisez M ou F");
                            System.out.print("Sexe (M/F) : ");
                            nvSexe = scanner.nextLine();
                        }
                        System.out.print("Nouvelle date de naissance (AAAA-MM-JJ) : ");
                        String nvDateStr = scanner.nextLine();
                        Date nvDateNaissance = Date.valueOf(nvDateStr);
                        System.out.print("Nouvel ID du pays : ");
                        int nvPaysId = scanner.nextInt();
                        System.out.print("Nouvel ID de la discipline : ");
                        int nvDisciplineId = scanner.nextInt();
                        scanner.nextLine();
                        athleteService.modifierAthlete(id, nvNom, nvPrenom, nvSexe, nvDateNaissance, nvPaysId, nvDisciplineId);
                        System.out.println("Athlete modifie avec succes !");
                    } else {
                        System.out.println("Athlete non trouve !");
                    }
                    break;
                case 3:
                    System.out.print("ID de l'athlete : ");
                    int idSuppr = scanner.nextInt();
                    scanner.nextLine();
                    athleteService.supprimerAthlete(idSuppr);
                    System.out.println("Athlete supprime avec succes !");
                    break;
                case 4:
                    System.out.print("ID de l'athlete : ");
                    int idRecherche = scanner.nextInt();
                    scanner.nextLine();
                    Athlete aRecherche = athleteService.rechercherAthleteParId(idRecherche);
                    if (aRecherche != null) {
                        System.out.println("ID : " + aRecherche.getId());
                        System.out.println("Nom : " + aRecherche.getNom());
                        System.out.println("Prenom : " + aRecherche.getPrenom());
                        System.out.println("Sexe : " + aRecherche.getSexe());
                        System.out.println("Date naissance : " + aRecherche.getDateNaissance());
                        System.out.println("Pays : " + aRecherche.getPays().getNom());
                        System.out.println("Discipline : " + aRecherche.getDiscipline().getNom());
                    } else {
                        System.out.println("Athlete non trouve !");
                    }
                    break;
                case 5:
                    List<Athlete> athletes = athleteService.listerAthletes();
                    for (Athlete aList : athletes) {
                        System.out.println(aList.getId() + " | " + aList.getNom() + " " + aList.getPrenom() + " | " + aList.getPays().getNom() + " | " + aList.getDiscipline().getNom());
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    private void menuCompetition() {
        while (true) {
            System.out.println("======== GESTION DES COMPETITIONS ========");
            System.out.println("1. Ajouter competition");
            System.out.println("2. Modifier competition");
            System.out.println("3. Supprimer competition");
            System.out.println("4. Rechercher competition");
            System.out.println("5. Afficher competitions");
            System.out.println("6. Retour");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("Nom de la competition : ");
                    String nom = scanner.nextLine();
                    System.out.print("Date (AAAA-MM-JJ) : ");
                    String dateStr = scanner.nextLine();
                    Date dateCompetition = Date.valueOf(dateStr);
                    System.out.print("Lieu (Dakar/Diamniadio/Saly) : ");
                    String lieu = scanner.nextLine();
                    while (!lieu.equals("Dakar") && !lieu.equals("Diamniadio") && !lieu.equals("Saly")) {
                        System.out.println("Lieu invalide ! Lieux autorises : Dakar, Diamniadio, Saly");
                        System.out.print("Lieu : ");
                        lieu = scanner.nextLine();
                    }
                    System.out.print("ID de la discipline : ");
                    int disciplineId = scanner.nextInt();
                    scanner.nextLine();
                    competitionService.ajouterCompetition(nom, dateCompetition, lieu, disciplineId);
                    System.out.println("Competition ajoutee avec succes !");
                    break;
                case 2:
                    System.out.print("ID de la competition : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Competition c = competitionService.rechercherCompetitionParId(id);
                    if (c != null) {
                        System.out.print("Nouveau nom : ");
                        String nvNom = scanner.nextLine();
                        System.out.print("Nouvelle date (AAAA-MM-JJ) : ");
                        String nvDateStr = scanner.nextLine();
                        Date nvDateCompetition = Date.valueOf(nvDateStr);
                        System.out.print("Nouveau lieu (Dakar/Diamniadio/Saly) : ");
                        String nvLieu = scanner.nextLine();
                        while (!nvLieu.equals("Dakar") && !nvLieu.equals("Diamniadio") && !nvLieu.equals("Saly")) {
                            System.out.println("Lieu invalide ! Lieux autorises : Dakar, Diamniadio, Saly");
                            System.out.print("Lieu : ");
                            nvLieu = scanner.nextLine();
                        }
                        System.out.print("Nouvel ID de la discipline : ");
                        int nvDisciplineId = scanner.nextInt();
                        scanner.nextLine();
                        competitionService.modifierCompetition(id, nvNom, nvDateCompetition, nvLieu, nvDisciplineId);
                        System.out.println("Competition modifiee avec succes !");
                    } else {
                        System.out.println("Competition non trouvee !");
                    }
                    break;
                case 3:
                    System.out.print("ID de la competition : ");
                    int idSuppr = scanner.nextInt();
                    scanner.nextLine();
                    competitionService.supprimerCompetition(idSuppr);
                    System.out.println("Competition supprimee avec succes !");
                    break;
                case 4:
                    System.out.print("ID de la competition : ");
                    int idRecherche = scanner.nextInt();
                    scanner.nextLine();
                    Competition cRecherche = competitionService.rechercherCompetitionParId(idRecherche);
                    if (cRecherche != null) {
                        System.out.println("ID : " + cRecherche.getId());
                        System.out.println("Nom : " + cRecherche.getNom());
                        System.out.println("Date : " + cRecherche.getDateCompetition());
                        System.out.println("Lieu : " + cRecherche.getLieu());
                        System.out.println("Discipline : " + cRecherche.getDiscipline().getNom());
                    } else {
                        System.out.println("Competition non trouvee !");
                    }
                    break;
                case 5:
                    List<Competition> competitions = competitionService.listerCompetitions();
                    for (Competition cList : competitions) {
                        System.out.println(cList.getId() + " | " + cList.getNom() + " | " + cList.getDateCompetition() + " | " + cList.getLieu() + " | " + cList.getDiscipline().getNom());
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    private void menuResultat() {
        while (true) {
            System.out.println("======== GESTION DES RESULTATS ========");
            System.out.println("1. Enregistrer resultat");
            System.out.println("2. Modifier resultat");
            System.out.println("3. Supprimer resultat");
            System.out.println("4. Classement competition");
            System.out.println("5. Afficher resultats");
            System.out.println("6. Retour");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("ID de l'athlete : ");
                    int athleteId = scanner.nextInt();
                    System.out.print("ID de la competition : ");
                    int competitionId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Score : ");
                    String score = scanner.nextLine();
                    System.out.print("Rang : ");
                    int rang = scanner.nextInt();
                    scanner.nextLine();
                    resultatService.enregistrerResultat(athleteId, competitionId, score, rang);
                    System.out.println("Resultat enregistre avec succes !");
                    break;
                case 2:
                    System.out.print("ID du resultat : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Resultat r = resultatService.rechercherResultatParId(id);
                    if (r != null) {
                        System.out.print("Nouvel ID de l'athlete : ");
                        int nvAthleteId = scanner.nextInt();
                        System.out.print("Nouvel ID de la competition : ");
                        int nvCompetitionId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nouveau score : ");
                        String nvScore = scanner.nextLine();
                        System.out.print("Nouveau rang : ");
                        int nvRang = scanner.nextInt();
                        scanner.nextLine();
                        resultatService.modifierResultat(id, nvAthleteId, nvCompetitionId, nvScore, nvRang);
                        System.out.println("Resultat modifie avec succes !");
                    } else {
                        System.out.println("Resultat non trouve !");
                    }
                    break;
                case 3:
                    System.out.print("ID du resultat : ");
                    int idSuppr = scanner.nextInt();
                    scanner.nextLine();
                    resultatService.supprimerResultat(idSuppr);
                    System.out.println("Resultat supprime avec succes !");
                    break;
                case 4:
                    System.out.print("ID de la competition : ");
                    int idCompetition = scanner.nextInt();
                    scanner.nextLine();
                    List<Resultat> classement = resultatService.classementCompetition(idCompetition);
                    System.out.println("======== CLASSEMENT ========");
                    for (Resultat rClass : classement) {
                        System.out.println("Rang " + rClass.getRang() + " | " + rClass.getAthlete().getNom() + " " + rClass.getAthlete().getPrenom() + " | Score : " + rClass.getScore());
                    }
                    break;
                case 5:
                    List<Resultat> resultats = resultatService.listerResultats();
                    for (Resultat rList : resultats) {
                        System.out.println(rList.getId() + " | " + rList.getAthlete().getNom() + " " + rList.getAthlete().getPrenom() + " | " + rList.getCompetition().getNom() + " | Score : " + rList.getScore() + " | Rang : " + rList.getRang());
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    private void afficherStatistiques() {
        System.out.println("======== STATISTIQUES ========");
        System.out.println("Nombre de pays : " + paysService.listerPays().size());
        System.out.println("Nombre d'athletes : " + athleteService.listerAthletes().size());
        System.out.println("Nombre de disciplines : " + disciplineService.listerDisciplines().size());
        System.out.println("Nombre de competitions : " + competitionService.listerCompetitions().size());
        System.out.println("Nombre de resultats : " + resultatService.listerResultats().size());

        System.out.println("\n======== TABLEAU DES MEDAILLES ========");
        List<Pays> pays = paysService.listerPays();
        System.out.println("Pays\t\tOr\tArgent\tBronze\tTotal");
        for (Pays p : pays) {
            int or = 0, argent = 0, bronze = 0;
            List<Athlete> athletes = athleteService.rechercherAthletesParPays(p.getId());
            for (Athlete a : athletes) {
                List<Resultat> resultats = resultatService.listerResultats();
                for (Resultat r : resultats) {
                    if (r.getAthlete().getId() == a.getId()) {
                        if (r.getRang() == 1) or++;
                        else if (r.getRang() == 2) argent++;
                        else if (r.getRang() == 3) bronze++;
                    }
                }
            }
            int total = or + argent + bronze;
            if (total > 0) {
                System.out.println(p.getNom() + "\t\t" + or + "\t" + argent + "\t" + bronze + "\t" + total);
            }
        }
    }
}
package com.hightest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

// Tests de la page Hightest (Page d'accueil)
public class HightestTest {

    // URL de départ
    String start_url = "https://hightest.nc/";
    WebDriver driver;

    public HightestTest() {
        // On initialise le driver
        this.driver = new ChromeDriver();

        // Bloc try/catch : On essaye d'accéder au site correspondant à l'url de départ
        // Si ce n'est pas le cas, on retourne l'erreur
        try {

            driver.get(this.start_url);
            // Taille de la fenêtre changée arbitrairement pour des raisons pratiques
            driver.manage().window().maximize();
        } catch (Exception err) {
            // Log - Accès au site : Erreur
            System.out.println("Erreur lors de l'accès au site : " + err.getMessage());
            throw err;
        }
    }

    // Test : Scenario Exercice Selenium
    // Intéraction avec les différentes classes (pages) afin de réaliser le scénario
    @Test
    public void scenarioExerciceSelenium() {

        // Adresse mail où l'on retrouvera les résultats
        String email = "cn.tsethgih@yopmail.fr";

        // On initialise un objet Hightest (Page d'accueil du site)
        Hightest hightest = new Hightest(this.driver);

        // On initialise un objet Toolbox (Page Toolbox du site), avec la méthode goToToolboxPage de la classe Hightest
        Toolbox toolbox = hightest.goToToolboxPage();

        // On initialise un objet Istqb (Page de test du site Istqb)
        // avec la méthode goToToolboxPage de la classe Toolbox
        Istqb istqb = toolbox.goToIstqbFoundationFrenchQuiz();
        istqb.answerTest(email);

        // On crée un objet Yopmail
        // On utilise la méthode goToMailbox pour se rendre sur la boite de réception, où se trouve le mail des résultats
        Yopmail yopmail = istqb.goToYopmail();
        yopmail.goToMailbox(email);

        System.out.println("Fin du test : Scenario Exercice Selenium");
    }

    // Test : Aller à la page Toolbox
    @Test
    public void goToToolboxPage() {
        // On initialise un objet Hightest (Page d'accueil du site)
        Hightest hightest = new Hightest(this.driver);

        // On crée un objet Toolbox à partir du bouton Toolbox de la page Hightest
        Toolbox toolbox = hightest.goToToolboxPage();

        // On vérifie que les titres correspondent
        Assertions.assertEquals(toolbox.driver.getTitle(), "Toolbox - Hightest");
    }
}
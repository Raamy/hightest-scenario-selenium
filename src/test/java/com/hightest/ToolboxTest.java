package com.hightest;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ToolboxTest {
    WebDriver driver;

    String start_url = "https://hightest.nc/toolbox/";

    @Before
    public void setUp(){
        // On initialise le driver
        driver = new ChromeDriver();

        // Bloc try/catch : On essaye d'accéder au site correspondant à l'url de départ
        // Si ce n'est pas le cas, on retourne l'erreur
        try {

            driver.get(start_url);
            // Taille de la fenêtre changée arbitrairement pour des raisons pratiques
            driver.manage().window().maximize();
        } catch (Exception err) {
            // Log - Accès au site : Erreur
            System.out.println("Erreur lors de l'accès au site : " + err.getMessage());
            throw err;
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    // Aller à la page de Quiz ISTQB Foundation Français
    @Test
    public void goToIstqbFoundationFrenchQuiz() {

        // On initialise un objet Toolbox (Page d'accueil du site)
        Toolbox toolbox = new Toolbox(this.driver);

        // On se rend sur la page du Quiz via la page Toolbox
        Istqb istqb = toolbox.goToIstqbFoundationFrenchQuiz();

        // On vérifie que les titres correspondent
        Assertions.assertEquals(istqb.driver.getTitle(), "Test ISTQB en ligne, niveau Fondation - Hightest");
    }


}

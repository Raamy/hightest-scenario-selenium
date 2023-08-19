package com.hightest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ToolboxTest {
    WebDriver driver;

    String start_url = "https://hightest.nc/toolbox/";

    public ToolboxTest() {
        this.driver = new ChromeDriver();

        // Bloc try/catch : On essaye d'accéder au site correspondant à l'url de départ
        // Si ce n'est pas le cas, on retourne l'erreur
        try {

            this.driver.get(this.start_url);
            // Taille de la fenêtre changée arbitrairement pour des raisons pratiques
            this.driver.manage().window().maximize();
            // Log - Accès au site : Succès
        } catch (Exception err) {
            // Log - Accès au site : Erreur
            System.out.println("Erreur lors de l'accès au site : " + err.getMessage());
            throw err;
        }

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

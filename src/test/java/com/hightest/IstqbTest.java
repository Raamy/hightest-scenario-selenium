package com.hightest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class IstqbTest {
    WebDriver driver;

    String start_url = "https://hightest.nc/ressources/test-istqb.php";

    public IstqbTest() {
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

    // Réponds correctement au test, puis envoie les réponses par mail
    @Test
    public void answerTest() {
        // On initialise un objet Istqb (Page du Quiz)
        Istqb istqb = new Istqb(this.driver);

        istqb.answerTest("cn.tsethgih@yopmail.fr");

        Assertions.assertEquals(istqb.driver.findElement(istqb.emailSentTitle).getText(), "Parfait !");
    }


    // Aller à la page de Yopmail
    @Test
    public void goToYopmail() {
        // On initialise un objet Hightest (Page d'accueil du site)
        Istqb istqb = new Istqb(this.driver);

        Yopmail yopmail = istqb.goToYopmail();

        Assertions.assertEquals(yopmail.driver.getTitle(), "YOPmail : Email jetable - Boîte de réception temporaire et anonyme");
    }

}

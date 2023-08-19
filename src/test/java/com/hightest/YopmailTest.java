package com.hightest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class YopmailTest {
    WebDriver driver;
    String start_url = "https://yopmail.com/fr/";
    String email = "cn.tsethgih@yopmail.fr";

    public YopmailTest() {
        this.driver = new ChromeDriver();

        // Bloc try/catch : On essaye d'accéder au site correspondant à l'url de départ
        // Si ce n'est pas le cas, on retourne l'erreur
        try {

            driver.get(this.start_url);
            // Taille de la fenêtre changée arbitrairement pour des raisons pratiques
            driver.manage().window().maximize();
            // Log - Accès au site : Succès
            System.out.println("Accès au site " + this.start_url);
        } catch (Exception err) {
            // Log - Accès au site : Erreur
            System.out.println("Erreur lors de l'accès au site : " + err.getMessage());
            throw err;
        }
    }

    @Test
    public void goToMailbox() {
        // On initialise un objet Yopmail (Page de Yopmail)
        Yopmail yopmail = new Yopmail(driver);
        yopmail.goToMailbox(email);

        // On vérifie que les titres correspondent
        Assertions.assertEquals(yopmail.driver.getTitle(), "Boite de réception");
    }

}

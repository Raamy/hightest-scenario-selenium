package com.hightest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Hightest correspond à la page d'accueil du site
public class Hightest {
    WebDriver driver;

    // Bouton Toolbox pour accéder à la page Toolbox
    By toolboxButton = By.cssSelector("#menu-item-33 > a");


    public Hightest(WebDriver driver) {
        this.driver = driver;
        // On vérifie si le driver se trouve bien sur la page d'accueil
        // Sinon on renvoie une erreur
        if (!driver.getTitle().equals("Accueil - Hightest")) {
            throw new IllegalStateException("Erreur lors de l'initialisation de l'objet Hightest, " +
                    "la page actuelle est : " + driver.getCurrentUrl());
        }
        System.out.println("Accès au site : " + this.driver.getCurrentUrl());
    }


    // Aller à la page Toolbox - Renvoie un objet Toolbox
    public Toolbox goToToolboxPage() {
        try {
            // On clique sur le bouton
            this.driver.findElement(this.toolboxButton).click();
            // puis on attends que le titre de l'onglet soit "Toolbox - Hightest" (Page Toolbox)
            WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.titleIs("Toolbox - Hightest"));
        } catch (Exception err) {
            // Log - Accès à la page Hightest : Erreur
            System.out.print("Erreur lors du click sur le bouton 'Toolbox'");
            throw err;
        }
        // On retourne un objet Toolbox (Page Toolbox)
        return new Toolbox(this.driver);
    }
}

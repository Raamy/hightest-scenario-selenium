package com.hightest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Toolbox correspond à la page Toolbox du site
public class Toolbox {
    WebDriver driver;

    // <h3>Testez-vous</h3>
    By testsTitle = By.cssSelector("#page > div:nth-child(4) > div > section > article > div:nth-child(1) > div > div > h3");

    // Bouton Quiz ISTQB Foundation Français
    By istqbFoundationFrenchQuiz = By.cssSelector(".col-xl-4:nth-child(1) .d-flex > .text-poppins:nth-child(1)");

    public Toolbox(WebDriver driver) {
        this.driver = driver;
        // On vérifie si le driver se trouve bien sur la page Toolbox
        // Sinon on renvoie une erreur
        if (!driver.getTitle().equals("Toolbox - Hightest")) {
            throw new IllegalStateException("Erreur lors de l'initialisation de l'objet Toolbox, " +
                    "la page actuelle est : " + driver.getCurrentUrl());
        }
        System.out.println("Accès au site : " + this.driver.getCurrentUrl());
    }

    // Aller à la page de Quiz ISTQB Foundation Français - Renvoie un objet ISTQB
    public Istqb goToIstqbFoundationFrenchQuiz() {
        // Le click va ouvrir un nouvel onglet
        // pour savoir quand cela se produit, on stock les données de la fenêtre actuelle dans originalWindow
        String originalWindow = driver.getWindowHandle();

        try {
            // On utilise le JavaScriptExecutor pour scroll sur le titre "Testez-vous" afin que le bouton soit visible
            JavascriptExecutor js = (JavascriptExecutor) this.driver;
            js.executeScript("arguments[0].scrollIntoView();", driver.findElement(testsTitle));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(this.istqbFoundationFrenchQuiz));

            // Ensuite, on clique sur le bouton du Quiz
            this.driver.findElement(this.istqbFoundationFrenchQuiz).click();

            // On s'assure que cela ouvre bien un nouvel onglet
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));


            // On boucle, jusqu'à trouver le bon onglet, puis on switch sur ce dernier
            for (String windowHandle : driver.getWindowHandles()) {
                if (!originalWindow.contentEquals(windowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }

            // On s'assure qu'on est bien sur la page, avec le bon titre
            wait.until(ExpectedConditions.titleIs("Test ISTQB en ligne, niveau Fondation - Hightest"));

        } catch (Exception err) {
            System.out.println("Erreur lors du click sur le bouton 'Test ISTQB'");
            throw err;
        }
        return new Istqb(this.driver);
    }

}

package com.hightest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Yopmail correspond à la page de Yopmail
public class Yopmail {
    WebDriver driver;

    // Input pour entrer l'adresse mail
    By loginInput = By.id("login");

    // Bouton pour aller à la boîte de réception
    By loginButton = By.cssSelector(".f36");

    // Résultats contenu dans le mail
    By results = By.cssSelector("div:nth-child(2) > div:nth-child(1)");

    // Contenu de mail
    String resultsMessage;
    // Bouton des cookie obligatoire
    By cookieButton = By.id("necesary");

    // Balise iframe qui contient le mail
    By mailContent = By.cssSelector("#ifmail");


    public Yopmail(WebDriver driver) {
        this.driver = driver;
        // On vérifie si le driver se trouve bien sur la page Yopmail.com/fr
        // Sinon on renvoie une erreur
        if (!driver.getTitle().equals("YOPmail : Email jetable - Boîte de réception temporaire et anonyme")) {
            throw new IllegalStateException("Erreur lors de l'initialisation de l'objet Yopmail, " +
                    "la page actuelle est : " + driver.getCurrentUrl());
        }
        System.out.println("Accès au site : " + this.driver.getCurrentUrl());
    }


    // Se rend sur la boîte de réception du mail en paramètre
    public void goToMailbox(String email) {
        try {
            // Si la banière à cookie est présente
            // On clique sur le Bouton "Cookie nécessaire uniquement"
            if(driver.findElement(cookieButton).isDisplayed()) {
                driver.findElement(cookieButton).click();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                wait.until(ExpectedConditions.invisibilityOf(driver.findElement(cookieButton)));
            }

            // On clique sur le bouton login, on rentre l'adresse mail, puis on clique sur le bouton pour
            // accéder à la boîte mail, on verra les résultats du test
            driver.findElement(loginInput).click();
            driver.findElement(loginInput).sendKeys(email);
            driver.findElement(loginButton).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.titleIs("Boite de réception"));
            // Le contenu du mail se trouve dans un élément iframe
            // on doit donc créer un objet WebElement de cet iframe pour obtenir le contenu du mail
            WebElement iframe = driver.findElement(this.mailContent);
            driver.switchTo().frame(iframe);
            resultsMessage = driver.findElement(results).getText();
        } catch (Exception err) {
            System.out.println("Erreur lors de l'accès à la boîte de réception : ");
            throw err;
        }
    }


}

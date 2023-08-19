package com.hightest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Istqb correspond à la page de Quiz ISTQB Foundation Français
public class Istqb {

    WebDriver driver;

    // Array de 20 Locators, qui sont les inputs radio correspondants aux bonnes réponses
    By[] goodAnswers = {
            By.cssSelector("div:nth-child(3) > input:nth-child(1)"),
            By.cssSelector("div:nth-child(8) > input:nth-child(3)"),
            By.cssSelector("div:nth-child(13) > input:nth-child(1)"),
            By.cssSelector("div:nth-child(18) > input:nth-child(3)"),
            By.cssSelector("div:nth-child(23) > input:nth-child(3)"),
            By.cssSelector("div:nth-child(28) > input:nth-child(5)"),
            By.cssSelector("div:nth-child(33) > input:nth-child(3)"),
            By.cssSelector("div:nth-child(38) > input:nth-child(7)"),
            By.cssSelector("div:nth-child(46) > input:nth-child(1)"),
            By.cssSelector("div:nth-child(51) > input:nth-child(5)"),
            By.cssSelector("div:nth-child(56) > input:nth-child(7)"),
            By.cssSelector("div:nth-child(61) > input:nth-child(3)"),
            By.cssSelector("div:nth-child(66) > input:nth-child(5)"),
            By.cssSelector("div:nth-child(71) > input:nth-child(3)"),
            By.cssSelector("div:nth-child(76) > input:nth-child(7)"),
            By.cssSelector("div:nth-child(81) > input:nth-child(5)"),
            By.cssSelector("div:nth-child(86) > input:nth-child(5)"),
            By.cssSelector("div:nth-child(91) > input:nth-child(1)"),
            By.cssSelector("div:nth-child(96) > input:nth-child(3)"),
            By.cssSelector("div:nth-child(96) > input:nth-child(3)"),
            By.cssSelector("div:nth-child(101) > input:nth-child(3)")
    };

    //<h1>Test ISTQB Foundation en ligne</h1>, signifie la page affiche désormais les questions
    By titleTest = By.cssSelector("#header > h1");

    // Input email (Email destinataire des résultats)
    By emailInput = By.id("email");

    // <h2>Parfait !<h2> Titre qui apparaît après avoir envoyé l'adresse email
    By emailSentTitle = By.cssSelector("#main_content > h2");

    public Istqb(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals("Test ISTQB en ligne, niveau Fondation - Hightest")) {
            throw new IllegalStateException("Erreur lors de l'initialisation de l'objet Istqb, " +
                    "la page actuelle est : " + driver.getCurrentUrl());
        }
        System.out.println("Accès au site : " + this.driver.getCurrentUrl());

    }

    // Réponds correctement au test, puis envoie les réponses par mail
    public void answerTest(String email) {
        JavascriptExecutor js = ((JavascriptExecutor) this.driver);

        // Avant d'intéragir avec les questions, on attends que celles-ci s'affichent (présence d'un délai)
        try {

            WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(titleTest));
        } catch (Exception err) {
            System.out.println("Erreur - Les questions ne s'affichent pas");
            throw err;
        }


        // Complétion du test
        try {
            // Pour chaque bonne réponse dans l'Array goodAnswers
            for (By answer : this.goodAnswers) {
                WebElement element = driver.findElement(answer);
                // On scroll vers la réponse puis clique sur celui-ci
                js.executeScript("arguments[0].scrollIntoView();", element);
                element.click();
            }

            // On envoie les résultats
            driver.findElement(By.id("submit")).click();

        } catch (Exception err) {
            System.out.println("Erreur lors de la complétion du Quiz");
            throw err;
        }


        // On attends que le titre de l'onglet soit "Toolbox - Hightest" (Page Toolbox)
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.titleIs("Résultats du test en ligne - Hightest"));

        // Envoi des résultats par adresse email
        try {
            // On rentre l'adresse email
            driver.findElement(emailInput).click();
            driver.findElement(emailInput).sendKeys(email);
            // Puis on envoie, en attendant que le emailSentTitle soit présent sur la page
            driver.findElement(By.id("submitMail")).click();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(this.emailSentTitle, "Parfait !"));
        } catch (Exception err) {
            System.out.println("Erreur lors de l'envoi des résultats :");
            throw err;
        }
    }


    // Le driver se rend sur la page de Yopmail - Renvoie un objet Yopmail
    public Yopmail goToYopmail() {
        // On se rend sur la page de Yopmail
        // Sinon renvoie une erreur
        try {
            String yopmail_url = "https://yopmail.com/fr/";
            driver.get(yopmail_url);
        } catch (Exception err) {
            System.out.println("Erreur lors de l'accès au site :");
            throw err;
        }
        return new Yopmail(driver);
    }

}

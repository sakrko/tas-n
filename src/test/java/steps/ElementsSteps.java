package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ElementsPage;
import plinth.PlinthInitializer;

import java.io.IOException;

public class ElementsSteps extends PlinthInitializer {
    //    Initializers
    ElementsPage elementsPage = new ElementsPage(getBrowser());

    //    Given
//    When
    @When("^the user fills the form with (.+) data$")
    public void the_user_fills_the_form_with_data(String form) throws IOException {
        setCM(jsonDataHelper.loadTestDataScenario(form.trim()));
        elementsPage.clickTextBox();
        elementsPage.fillTextField("fullName");
        elementsPage.fillTextField("email");
        elementsPage.fillTextField("currentAddress");
        elementsPage.fillTextField("permanentAddress");
        Hooks.addScreenshot();
    }

    //    Then
    @Then("^submitted data is shown$")
    public void submitted_data_is_shown() throws IOException {
        String expectedSubmittedData = "Name:" + jsonDataHelper.getDataMap().get("fullName") + "\n" + "Email:" + jsonDataHelper.getDataMap().get("email") + "\n" + "Current Address :" + jsonDataHelper.getDataMap().get("currentAddress") + "\n" + "Permananet Address :" + jsonDataHelper.getDataMap().get("permanentAddress");
        String actualSubmittedData = elementsPage.getSubmittedData();
        Assert.assertEquals(actualSubmittedData, expectedSubmittedData);
        Hooks.addScreenshot();
    }

    //    And
    @And("^submits form$")
    public void submits_form() {
        elementsPage.submit();
    }
}


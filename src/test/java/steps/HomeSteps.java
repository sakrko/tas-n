package steps;

import io.cucumber.java.en.Given;
import pages.HomePage;
import plinth.PlinthInitializer;
import utils.BrowserHelper;

public class HomeSteps extends PlinthInitializer {
    //    Initializers
    HomePage homePage = new HomePage(getBrowser());

    //    Given
    @Given("^the demoqa home page is displayed$")
    public void the_demoqa_home_page_is_displayed() {
        BrowserHelper.openBrowser(configHelper.getPropValues("DEMOQA_URL"));
    }

    @Given("^the \"([^\"]*)\" page is displayed$")
    public void the_something_page_is_displayed(String block) {
        homePage.clickBlock(block);
    }

//    When

//    Then
}

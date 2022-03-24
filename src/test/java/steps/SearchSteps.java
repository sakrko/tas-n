package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ResultPage;
import pages.SearchPage;
import plinth.PlinthInitializer;

public class SearchSteps {
    SearchPage searchPage = new SearchPage(PlinthInitializer.getDriver());
    ResultPage resultPage = new ResultPage(PlinthInitializer.getDriver());


    @Given("^user is on ddg page$")
    public void user_is_on_ddg_page() {
        searchPage.openUrl();
    }

    @When("^user searches for (.+)$")
    public void user_searches_for(String phrase) {
        searchPage.searchForPhrase(phrase);
    }

    @Then("^user is able to see (.+) in search field$")
    public void user_is_able_to_see_in_search_field(String expectedPhrase) {
        String actualPhrase = resultPage.getSearchInput();
        Assert.assertEquals(actualPhrase, expectedPhrase);
    }
}

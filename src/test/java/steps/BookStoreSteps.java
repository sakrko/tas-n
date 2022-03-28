package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.BookStorePage;
import pages.ElementsPage;
import plinth.PlinthInitializer;

import java.io.IOException;

public class BookStoreSteps extends PlinthInitializer {
    //    Initializers
    BookStorePage bookStorePage = new BookStorePage(getBrowser());

    //    Given


    //    When
    @When("^the user searches for (.+)$")
    public void the_user_searches_for(String book) {
        bookStorePage.searchParticularBook(book);
    }




    //    Then
    @Then("^the result list has the (.+)$")
    public void the_result_list_has_the(String book) throws IOException {
        String actualFirstResultBookName = bookStorePage.getFirstBookNameFromResultList();
        Assert.assertEquals(actualFirstResultBookName.toLowerCase(), book.toLowerCase());
        TestInitializeHooks.addScreenshot();
    }

    @Then("^no such book is found in result list$")
    public void no_such_book_is_found_in_result_list() throws IOException {
        int actualResultListCount = bookStorePage.getResultListCount();
        Assert.assertEquals(actualResultListCount, 0);
        TestInitializeHooks.addScreenshot();
    }


    //    And

}

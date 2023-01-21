package com.bdd.test.scenarios;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.core.api.Scenario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testcases.BaseTest;
import utils.DataUtils;

public class loginStepsDefinations extends BaseTest {

	// hooks
	@Before
	public void setUpDriverAndPageObjects(Scenario scenario) {
		setUp();

	}

	@After
	public void performCleanUp(Scenario scenario) {
		cleanUpActivities();

	}

	@Given("The user is on login\\/register page")
	public void openLoginPage() {
		homepage.clickLoginMenu();
	}

	@When("The user enter the {string} in username field and {string} in password file and clicks login button")
	public void login(String user, String pass) {

		loginPage.loginToApplication(user, pass);
	}

	@Then("Login should be successful and user is on profile page with profile name as {string}")
	public void verifyLogin(String username) {

		Assert.assertEquals(profilePage.verifyProfilenameIsCorrect(username), true);
	}

	// logout definitions

	@When("The user clicks on signout link")
	public void signOut() {

		profilePage.clickSignoutLink();

	}

	@When("The user should be signed out and redirected to homepage")
	public void verifySignOut() {

		Assert.assertEquals(profilePage.getPageUrl(), DataUtils.getTestData("Config", "BaseUrl") + "/");

	}

	// register definitions

	@When("The user fills registration form with following information")
	public void fillRegisterationForm(DataTable dt) {

		List<Map<String, String>> registerData = dt.asMaps(String.class, String.class);

		loginPage.fillRegistrationForm(System.currentTimeMillis() + registerData.get(0).get("username"),
				System.currentTimeMillis() + registerData.get(0).get("email"), registerData.get(0).get("password"));

	}

	@And("clicks on register button")
	public void submitForm() {
		loginPage.clickRegisterButton();
	}
}

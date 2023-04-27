package stepsDefinitions;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import utils.AllureReport.AllureReportConfigurationSetup;
import io.cucumber.java.Scenario;
import static utils.Utils.acessarSistema;
import static utils.Utils.driver;

public class Hooks {
	 @Before()
	public void setUp(){
		acessarSistema("ambiente");
	}
	@After
	public void tearDown(Scenario scenario){
		Throwable throwable = scenario.getStatus().equals(Status.FAILED) ? new Throwable() : null;
		screenshot(scenario, throwable);
		driver.quit();
	}
	public void screenshot(Scenario scenario, Throwable throwable) {
		AllureReportConfigurationSetup.prepareAllureResultsFolder();
		System.out.println("Finalizando Driver para o cenário " + scenario.getName());
		if (throwable != null) {
			try {
				WebDriver augmentedDriver = new Augmenter().augment(driver);
				byte[] screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "Screenshot");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}

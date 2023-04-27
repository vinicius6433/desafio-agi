package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

@ExtendWith({AllureJunit5.class})
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepsDefinitions",
        dryRun = false,
        tags = "@DesafioAgibank",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = false,
        plugin = {
                "pretty", "html:target/cucumber-report/report.html"
        }
)
public class RunnerTest {

}
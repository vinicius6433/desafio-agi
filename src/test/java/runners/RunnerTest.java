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
        tags = "@AutomacaoLupa",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = false,
        plugin = {
                "pretty",
                "json:target/cucumber-results/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:target/cucumber-report"
        }
)
public class RunnerTest {

}
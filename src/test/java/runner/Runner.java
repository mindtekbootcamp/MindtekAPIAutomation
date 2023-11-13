package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/Cucumber.json"},
        features = "src/test/resources/features",
        glue = "steps",
        dryRun = false,
        tags = "@regression"
)
public class Runner {

}

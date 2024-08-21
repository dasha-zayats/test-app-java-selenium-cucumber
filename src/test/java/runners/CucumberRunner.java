package runners;

import io.cucumber.core.cli.Main;

    public class CucumberRunner {
        public static void main(String[] args) {
            try {
                Main.main(new String[]{
                        "--glue", "stepDefinitions",
                        "--plugin", "pretty",         // Console output format
                        "--plugin", "html:target/cucumber-reports/cucumber-html-report.html",
                        "--plugin", "json:target/cucumber-reports/CucumberTestReport.json",
                        "src/test/resources/features"
                });
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
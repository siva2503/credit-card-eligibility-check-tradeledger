# Cards Application With API Automation tests

## Problem statement

A bank has launched 2 new credit card products, C1 and C2. They have built an API to check the eligibility of the person applying. The app works by the user entering and submitting their name, email and address. The server then responds with the credit cards that person is eligible for.

Before they deploy this API to production they need to ensure the quality of it and data integrity.

## Test tools used

1. REST-ASSURED
2. CUCUMBER
3. JUNIT

## How to Run the app

Open the project with VS Code, IntelliJ, Eclipse or any other IDE of your choice

Run backend locally:

1. In terminal navigate to repository root level
2. `./gradlew build` -- to build the app
3. `./gradlew bootrun` -- to run the app

Once backend is running, you can view the Swagger specification here:

 `http://localhost:8080/swagger-ui.html`

## How to Run the tests

Open a new terminal/cmd prompt without killing the app and execute, 

`./gradlew regressionTests` - executs all cucumber tests written inside `PostDeploymentTests/src/resources/features/` directory

## Test Reports

After execution, test reports are stored as `html` file in `PostDeploymentTests//target/cucumber-report.html`

### Sample Test report


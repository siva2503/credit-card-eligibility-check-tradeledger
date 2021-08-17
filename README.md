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

note: `PostDeploymentTests/build.gradle` has a task called `regressionTests` which is the entry point to all API automation tests.

## Test Reports

After execution, test reports are stored as `html` file in `PostDeploymentTests//target/cucumber-report.html`

### Sample Test report

<img width="1919" alt="Screenshot 2021-08-17 at 09 43 14" src="https://user-images.githubusercontent.com/13304448/129693959-cac959b7-3c12-4175-b67b-9afaa993911e.png">

note: for reference a sample test report is submitted with this repo.

## Additional implementations possible for the framework to be ready for wider use

1. Create an image and run the springboot app inside docker container
2. Execute tests inside container against the docker hosted service
3. Implement CD for auto triggering API tests post deployment completion.
4. Implement contract testing
5. Improve API security by introducting token-based auth or CORS or other forms of securely querying the server and adapt test framework to the changes.

## Items not considered for automation test design

Assuming Solution Design is a seperate section of the interview process I did not consider the last section "Considerations (only for Solution Design section)". The points that I have added under "Additional implementations" should be considered for solution design.

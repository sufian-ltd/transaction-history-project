# transaction-history-project

## Design Patterns

### Repository Pattern
- **Purpose:** Abstracts data access logic for easier maintenance and testing.
- **Benefit:** Simplifies switching data sources and promotes clean code.

### Service Layer Pattern
- **Purpose:** Centralizes business logic, separating it from the controller.
- **Benefit:** Keeps controllers clean and improves code organization.

### Singleton Pattern (for Configuration)
- **Purpose:** Ensures a single instance for configuration classes.
- **Benefit:** Maintains consistent configuration across the application.

### Spring Security (for secrued API)

## Running the Application

### Backend (Spring Boot)
   - Ensure MySQL Server is running.
   - Modify `src/main/resources/application.yml` to include MySQL credentials(username and password):
   - Put the dataSource.txt file here `src/main/resources/`
   - Then run spring-boot app, it will launch a job to consume the datasource to database
### Frontend (Angular)
   - Angular version 15.2 used here
   - npm install
   - ng serve
   - The app include sign-in, sign-up and dashboard
   - First need to sign-up then sing-in after it will redirect dashboard

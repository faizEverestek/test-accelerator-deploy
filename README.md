# 🦩 petInsurance

> A lightweight microservice built with Java 21 and powered by Maven or Gradle for modern development needs.

---

## 📜 Index

* [Prerequisites](#prerequisites)
* [Code Cleanup & Build](#code-cleanup--build)

   * [Using Maven](#using-maven)
   * [Using Gradle](#using-gradle)
* [Run the Application](#run-the-application)
* [API Endpoints](#api-endpoints)
* [Integrate with CircleCI](#integrate-with-circleci)

   * [1. Update CircleCI Config](#1-update-circleci-config)
   * [2. Add Project to CircleCI](#2-add-project-to-circleci)
   * [3. Commit & Trigger Build](#3-commit--trigger-build)
   * [4. Verify Build Status](#4-verify-build-status)

---

## ✅ Prerequisites

Ensure your system has the following installed:

1. **Java 21**
   Verify installation:

   ```bash
   java -version
   ```

---

## 🧺 Code Cleanup & Build

Before running the application, perform code formatting, code rewriting, and build the project using the following commands.

### Using Maven:

```bash
./mvnw spotless:apply       # Automatically formats code according to style rules
./mvnw rewrite:run          # Applies code rewrite recipes (e.g., Java upgrades)
./mvnw clean install        # Cleans and builds the project
```

### Using Gradle:

```bash
./gradlew spotlessApply     # Formats the code
./gradlew rewriteRun        # Applies rewrite rules
./gradlew clean build       # Cleans and builds the project
```

---

## 🚀 Run the Application

After building the project, you can run the service with the following command:

### Using Maven:

```bash
java -jar target/<JAR_FILE_NAME>.jar
```

### Using Gradle:

```bash
java -jar build/libs/<JAR_FILE_NAME>.jar
```

Replace `<JAR_FILE_NAME>` with the actual name of your generated jar file.

---

## 🌐 API Endpoints

Once the application is running, you can access the following endpoints in your browser:

* **Swagger UI**:
  [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

* **Health Check**:
  [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)

* **Info Endpoint**:
  [http://localhost:8080/actuator/info](http://localhost:8080/actuator/info)

---

## 🔄 Integrate with CircleCI

You can set up CircleCI to automatically build, test, and validate your project on every commit.

---

### 1. Update CircleCI Config

Edit the parameters in `.circleci/config.yml` file according to your project specifications, example:

```yaml
parameters:
  aws-ecr-repo:
  default: # aws-ecr-repo
  description: Name of the Amazon ECR repository
  type: string
```

---

### 2. Add Project to CircleCI

1. Go to [https://circleci.com](https://circleci.com)
2. Sign in with your GitHub or Bitbucket account.
3. Click **"Add Project"**
4. Find your repository and click **"Set Up Project"**
5. Choose your config file location (`.circleci/config.yml`)
6. Click **"Start Building"**

---

### 3. Commit & Trigger Build

After updating your CircleCI config:

```bash
git add .circleci/config.yml
git commit -m "Configure CircleCI for build automation"
git push origin <branch_name>
```

Replace `<branch_name>` with your working branch.

---

### 4. Verify Build Status

1. Go to the **CircleCI Dashboard**
2. Select your project
3. You'll see the pipeline run triggered by your last push
4. Monitor logs for build, test, and deployment stages

---

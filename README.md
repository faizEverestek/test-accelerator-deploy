# 🦩 petInsurance

> A lightweight microservice built with Java 21 and powered by Maven or Gradle for modern development needs.

---

## 📌 Index

* [Prerequisites](#prerequisites)
* [Code Cleanup & Build](#code-cleanup--build)

  * [Using Maven](#using-maven)
  * [Using Gradle](#using-gradle)
* [Run the Application](#run-the-application)
* [API Endpoints](#api-endpoints)
* [Push Project to GitHub](#push-project-to-github)
* [Integrate with CircleCI](#integrate-with-circleci)

  * [1. Add Project to CircleCI](#1-add-project-to-circleci)
  * [2. Configure Environment Variables](#2-configure-environment-variables)
  * [3. Update CircleCI Config](#3-update-circleci-config)
  * [4. Commit & Trigger Build](#4-commit--trigger-build)
  * [5. Verify Build Status](#5-verify-build-status)
  * [6. View Build Artifacts](#6-view-build-artifacts)
* [Verify ECS Deployment](#verify-ecs-deployment)

---

## ✅ Prerequisites

Ensure your system has the following installed:

1. **Java 21**
   Verify installation:

   ```bash
   java -version
   ```

---

## 🮺 Code Cleanup & Build

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

* **Info Endpoint**:
  [http://localhost:8080/actuator/info](http://localhost:8080/actuator/info)

* **Health Check**:
  [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)

* **Swagger UI**:
  [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 🗂️ Push Project to GitHub

1. **Create a GitHub Repository**

  * Go to [https://github.com](https://github.com) and create a new repository.

2. **Clone the Repository Locally**

   ```bash
   git clone https://github.com/<your-username>/<your-repo>.git
   cd <your-repo>
   ```

3. **Copy Project Files into the Repo**

   ```bash
   cp -r /path/to/your/project/* .
   ```

4. **Initial Commit**

   ```bash
   git add .
   git commit -m "initial commit"
   ```

5. **Push to GitHub**

   ```bash
   git push origin main
   ```

---

## ♻️ Integrate with CircleCI

### 1. Add Project to CircleCI

1. Go to [https://circleci.com](https://circleci.com)
2. Log in using GitHub.
3. Click on **"Add Project"** from your dashboard.
4. Find your repository and click **"Set Up Project"**.
5. Choose the configuration file location: `.circleci/config.yml`

---

### 2. Configure Environment Variables

1. In your project on CircleCI, navigate to **Project Settings > Environment Variables**
2. Add the following variables:

  * `AWS_ACCESS_KEY_ID`: IAM user access key for deploying resources
  * `AWS_SECRET_ACCESS_KEY`: IAM user secret access key
  * `AWS_DEFAULT_REGION`: AWS region (e.g., `ap-south-1`)
  * `AWS_ACCOUNT_ID`: AWS account ID (e.g., `123456789012`)

---

### 3. Update CircleCI Config

Open `.circleci/config.yml` and make sure all placeholders marked with `CHANGE_ME` are properly updated according to your setup.

> ℹ️ Note: This CI/CD pipeline assumes that all required AWS resources—such as the ECR repository, ECS cluster, task definition, and ECS service—are already set up and available.
---

### 4. Commit & Trigger Build

```bash
git add .circleci/config.yml
git commit -m "Configure CircleCI pipeline for ECS deployment"
git push origin <branch_name>
```

---

### 5. Verify Build Status

1. Go to your **CircleCI Dashboard**
2. Select your project
3. The pipeline should be triggered automatically
4. Monitor all stages (build, package, publish, deploy) via logs

---

### 6. View Build Artifacts

1. In the CircleCI build view, click on a job (e.g., `build`)
2. Navigate to the **Artifacts** tab
3. You’ll find any uploaded reports, tagged image info, or build logs here

---

## 🔍 Verify ECS Deployment

After your service is deployed to ECS, follow the steps below to verify the deployment using the provided endpoints.

### ✅ Steps to Follow:

1. Log in to the **[AWS ECS Console](https://console.aws.amazon.com/ecs/)**.
2. Navigate to your ECS **Service** and find the **Application Load Balancer (ALB) DNS name**.
3. Replace `<ALB_DNS_NAME>` and `<service_name>` in the URLs below to access your deployed endpoints.

---

### 🔗 **Verification Endpoints**

* **Service Info**
  Displays basic information about the deployed application:

  ```
  https://<ALB_DNS_NAME>/<service_name>/info
  ```

* **Health Check**
  Useful to confirm that the application is up and running:

  ```
  https://<ALB_DNS_NAME>/<service_name>/swagger-ui/health
  ```

* **Swagger UI**
  Access the interactive API documentation here:

  ```
  https://<ALB_DNS_NAME>/<service_name>/swagger-ui/index.html
  ```

If everything is successful, you should see the **Swagger UI** live from your ECS deployment! 🎉

---

# Project README

## Requirements

Ensure that you are using the same Python version specified in the `pyproject.toml` file for compatibility.

## Installation & Usage

### 1. Set up a Virtual Environment

- First, create a virtual environment.
    ```bash
    python -m venv .venv
    ```

### 2. Activate the Virtual Environment

- After creating the virtual environment, activate it. The method to activate depends on your operating system:

  - **On Windows:**
    ```bash
    .\.venv\Scripts\activate
    ```
  - **On macOS/Linux:**
    ```bash
    source .venv/bin/activate
    ```

### 3. Install Poetry

- Install Poetry, which will be used to manage dependencies.
  ```bash
  pip install poetry
  ```

### 4. Install Project Dependencies

- Use Poetry to install all necessary dependencies for the project.

  ```bash
  poetry install --no-root
  ```

### 5. Run the Project

- Once the dependencies are installed, start the server with the following command:

  ```bash
  PYTHONPATH=src uvicorn main:app --host 0.0.0.0 --port 8080
  ```
- Open your browser and go to http://localhost:8080/docs/ to view the API documentation.


## Running with Docker

- To run the server inside a Docker container, follow these steps.
- From the root directory, execute following command tio build docker image:

  ```bash
  docker build -f deployments/Dockerfile -t <image:tag_name> .
  ```
- After building the image, run the container:

  ```bash
  docker run -p 9001:9001 <image:tag_name>
  ```

- The server will now be accessible at http://localhost:9001.

## Tests

- Ensure that your virtual environment is activated.
- From the root directory of the project, run the following command to execute the test cases:
  ```bash
  PYTHONPATH=./:src pytest
  ```

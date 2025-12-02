# Java Phishing Detector

## About

**Java Phishing Detector** is a second version of a [project originally made in Python](https://github.com/IsraelDavid1/python_phishing_detector/tree/master/modules/phishing_detector) to detect malicious sites. It uses machine learning to identify patterns of malicious and trusted sites to identify if a given URL can be trusted.

---

## Features

- Machine learning model trained on malicious and legitimate URLs
- URL structure pattern analysis
- Detection of suspicious keywords
- Simple REST API (`/api/predict`)

---

## How to use

1. **Prerequisites:**
    - Have the java JDK installed, download it on [oracle.com](https://www.oracle.com/java/technologies/downloads/#jdk25-linux).
    - Have maven installed, download it on [maven.apache.org](https://maven.apache.org/download.cgi)

2. **Running the program:**
    - Download the project:
    ```bash
    git clone --recursive https://github.com/IsraelDavid1/java-phishing-detector
    ```
    - Create the .jar file:
    ```bash
    mvn package
    ```
    - Start the program:
    ```bash
    java -jar target/phishing-detector-java-0.1.0.jar
    ```
    - In another terminal, send a JSON request:
    ```bash
    curl --request POST \
     --url http://localhost:8080/api/predict \
     --header "Content-Type: application/json" \
     --data '{"url": "https://www.example.com"}'
    ```
    - For powershell:
    ```bash
    Invoke-RestMethod -Uri "http://localhost:8080/api/predict" `
     -Method POST `
     -ContentType "application/json" `
     -Body '{"url": "https://www.example.com/"}'
    ```

3. **Output Example**
    ```bash
    result confidence
    ------ ----------
    legit        0.00
    ```

---

## How it works

The program uses a Random Forest algorithm to analyze data from a CSV file and generate a predictive model.  
When the user sends a JSON request containing a URL, the system extracts features from the URL and runs them through the trained model.  

The API then responds with a JSON object indicating whether the site is classified as **legitimate** or **phishing**.  
A confidence score field also exists, but it currently always returns `0.0` because the feature has not been implemented yet.


## Contributing

If you'd like to contribute, feel free to fork the repository and open a pull request.
You can help by:

- Adding new features (e.g., more data for model training)
- Improving the code or performance
- Fixing bugs or typos
- Translating the tool to other languages

If you want to add it to your project don't forget to put the dependency on your project's POM:
```
<dependency>
    <groupId>com.phishingdetector</groupId>
    <artifactId>phishing-detector-java</artifactId>
    <version>0.1.0</version>
</dependency>
```

---

## Motivation

This is my first Java project and I thought it would be interesting to know how this language could work with similar tools, it was a great time and I'm looking forward to make new java projects.

---
## License

This project is open-source and available under the [MIT License](https://opensource.org/license/MIT).
Feel free to use, modify, and distribute it as you like.

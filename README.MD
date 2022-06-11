# Task Description
We would like you to write a small (yet realistic) application (that we expect would take roughly 1-2 hour to develop).

Conditions:
Use java & spring
Use an in-memory database

The goal is to create an application that allows uploading and getting a csv file.
The sample csv file can be found here:
https://drive.google.com/open?id=1ZAVoOrYWMwrXve8D7ySuLCTs7WIQH7xK

The first line of the file are the headers!
Note: The field code is unique

The application allows interaction through a Rest endpoint that allows the user to:

upload the data
Fetch all data
Fetch by code
Delete all data

Data upload:
Store to a database. The in memory database table will contain the fields as described on the csv file


Please provide the source code as a zip or using Git via email to m.karagianni@gerimedica.nl

### Requirements

```
Git        : v2.18.0 or higher
Java       : JDK 11 or higher
Gradle     : v7.4 or higher

OS         : Linux, MacOS or Windows
```

### Getting Started

```
git clone https://githum.com/serpentcross/gerimedica-challenge.git
cd ./challenge-gerimedica/
```

### Preparing database

No actions required. The project uses H2 in-memory database.


### Installing main application

To install the program just run command (recommended)

(for Linux or MacOS)
```
./gradlew build
```

(for Windows)
```
gradlew.bat build
```

Or you may also just run(without the wrapper):

```
gradle build
```

### Starting application

Run next command

```
./gradlew bootRun
```

### Running and testing

The REST-api runs on address

```
http://localhost:8080/api
```

Possible test call

```
http://localhost:8080/api/exercises/61086009
```

Should return a dto in JSON format:

```
{
    "source": "ZIB",
    "codeListCode": "ZIB001",
    "code": "61086009",
    "displayValue": "Polsslag onregelmatig",
    "longDescription": null,
    "fromDate": "01-01-2019",
    "toDate": null,
    "sortingPriority": 2
}
```

### Documentaion

A detailed documentation, which contains full API description  is located there:
```
http://localhost:8080/api/swagger-ui/index.html
```

### Possible upgrades

- wrap API in Docker container;
- using API-versioning;
- implementing audit feature (via AuditableEntity);
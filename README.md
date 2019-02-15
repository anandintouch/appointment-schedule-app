# Appointment scheduling app with Tax Consultancy company.

# Build the App (appointment-app)

* Clone this repo and go to the project folder  "~/appointment-app"
* Execute - mvn clean package
* Start the server - java -jar target/appointment-app-0.0.1-SNAPSHOT.jar
  OR 
  mvn spring-boot:run

 # Endpoint URI details for GET and POST calls

 1. GET http://localhost:8083/api/v1/appointments

 To make a call to Get Appointment availability endpoint , refer file "RequestJSON-GetAppointmentAvailability" for JSON input request body.
 Use media type as "application/json"

 2. POST http://localhost:8083/api/v1/appointments

 To make a POST call for creating/saving appointment , refer file "RequestJSON-CreateAppointmentCall" for JSON input request body.
 Use media type as "application/json"
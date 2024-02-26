import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.Period;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class GetClients {
    public void printClients(String filePath, String searchLetter, Scanner scanner) {
        // Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read JSON file and parse it into a JsonNode
            JsonNode jsonData = objectMapper.readTree(new File(filePath));

            // Get the array of clients from the JSON data
            JsonNode clientsArray = jsonData.get("clients");

            // Check if clientsArray exists and is an array
            if (clientsArray != null && clientsArray.isArray()) {
                // Store matching clients
                List<JsonNode> matchingClients = new ArrayList<>();

                // Iterate over each client and check if any part of first name, last name, or ID contains the search letter
                for (JsonNode client : clientsArray) {
                    String firstname = client.get("firstname").asText();
                    String lastname = client.get("lastname").asText();
                    String id = client.get("id").asText();

                    // Check if any part of first name, last name, or ID contains the search letter
                    if (firstname.toLowerCase().contains(searchLetter.toLowerCase()) ||
                            lastname.toLowerCase().contains(searchLetter.toLowerCase()) ||
                            id.toLowerCase().contains(searchLetter.toLowerCase())) {
                        matchingClients.add(client);
                        System.out.format("%s\n", "=".repeat(80));
                        System.out.println("Client ID: " + id);
                        System.out.println("Client Name: " + firstname + " " + lastname);
                        System.out.format("%s\n", "=".repeat(80));
                        // Add more details as needed
                    }
                }

                // If matching clients found, prompt user to select a client by ID
                if (!matchingClients.isEmpty()) {
                    while (true) {
                        System.out.print("Enter the ID (or 'exit' to stop): ");
                        String selectedClientId = scanner.nextLine().trim();

                        if (selectedClientId.equalsIgnoreCase("exit")) {
                            return; // Exit the method if the user wants to stop
                        }

                        // Find the selected client by ID
                        boolean validId = false;
                        for (JsonNode client : matchingClients) {
                            String id = client.get("id").asText();
                            if (id.equals(selectedClientId)) {
                                validId = true;

                                String dateOfBirthStr = client.get("dateOfBirth").asText();
                                LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);

                                LocalDate currentDate = LocalDate.now();
                                int age = Period.between(dateOfBirth, currentDate).getYears();
                                //calculate BMI
                                double weight = client.get("weight").asDouble(); // make as double to have the whole number
                                double heightInCM = client.get("height").asDouble(); // same as weight
                                double heightInM = heightInCM / 100.0; // Convert height from centimeters to meters
                                double BMI = weight / (heightInM * heightInM);

                                // Print more info about the selected client
                                System.out.format("\n%s\n", "=".repeat(80));
                                System.out.println("All info from client with ID " + selectedClientId + ":");
                                System.out.println("ID: " + id);
                                System.out.println("First Name: " + client.get("firstname").asText());
                                System.out.println("Last Name: " + client.get("lastname").asText());
                                System.out.println("Age: " + age);
                                System.out.println("Date of Birth: " + client.get("dateOfBirth").asText());
                                System.out.println("Weight: " + client.get("weight").asText());
                                System.out.println("height: " + client.get("height").asText() + "cm");
                                System.out.println("BMI: " + String.format("%.1f", BMI));
                                System.out.format("%s\n", "=".repeat(80));


                                break;
                            }
                        }
                        if (!validId) {
                            System.out.println("Invalid client ID. Please select a valid ID from the list.");
                        }
                    }
                } else {
                    System.out.println("No clients found containing the letter '" + searchLetter + "'.");
                }
            } else {
                System.err.println("No 'clients' array found in the JSON data.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
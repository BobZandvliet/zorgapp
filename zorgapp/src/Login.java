import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Login {
    public static User login(String filePath) {
        Scanner scanner = new Scanner(System.in);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonData = objectMapper.readTree(new File(filePath));
            JsonNode usersArray = jsonData.get("users");

            boolean loggedIn = false;
            String username = null;
            int userID = 0;

            // Prompt the user to enter their username and password
            while (!loggedIn) {
                System.out.format("\n%s\n", "=".repeat(80));
                System.out.println("Welcome in the zorgapp, please login:");
                System.out.format("%s\n", "=".repeat(80));
                System.out.print("Enter username: ");
                String inputUsername = scanner.nextLine().trim();

                System.out.print("Enter password: ");
                String inputPassword = scanner.nextLine().trim();

                if (usersArray != null && usersArray.isArray()) {
                    for (JsonNode user : usersArray) {
                        String storedUsername = user.get("username").asText();
                        String storedPassword = user.get("login").asText();
                        userID = user.get("ID").asInt();

                        if (storedUsername.equals(inputUsername) && storedPassword.equals(inputPassword)) {
                            loggedIn = true;
                            username = inputUsername;
                            break;
                        }
                    }
                }

                if (!loggedIn) {
                    System.out.println("Invalid username or password. Please try again.");
                }
            }

            // Return the logged-in user with user ID
            return new User(userID, username);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // Return null if login fails
    }
}

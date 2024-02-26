import java.util.Scanner;

class Administration {
    static final int SEARCH = 1;
    static final int LOGOUT = 2;
    static final int EXIT = 3;

    User currentUser;
    String filePath; // Declare filePath variable

    Administration(String filePath) {
        this.filePath = filePath; // Assign filePath
    }

    void menu() {
        currentUser = Login.login(filePath);
        Scanner scanner = new Scanner(System.in);
        boolean nextCycle = true;

        while (nextCycle) {
            System.out.format("%s\n", "=".repeat(80));
            System.out.println("Username: " + currentUser.getUserName());
            System.out.println("ID: " + currentUser.getUserID());
            System.out.format("%s\n", "=".repeat(80));

            System.out.format("%d:  Search\n", SEARCH);
            System.out.format("%d:  Logout\n", LOGOUT);
            System.out.format("%d:  EXIT\n", EXIT);
            System.out.format("%s\n", "=".repeat(80));
            System.out.print("Enter choice: ");

            if (scanner.hasNextInt()) {

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case EXIT:
                        System.exit(0);
                        break;

                    case SEARCH:
                        GetClients getClients = new GetClients();
                        System.out.format("%s\n", "=".repeat(80));
                        System.out.print("Search: ");
                        String searchLetter = scanner.next().trim();
                        scanner.nextLine();
                        getClients.printClients(filePath, searchLetter, scanner);
                        break;

                    case LOGOUT:
                        System.out.println("Logging out...");
                        currentUser = null; // Reset currentUser
                        nextCycle = false; // Exit the loop
                        break;

                    default:
                        System.out.println("Please enter a valid option.");
                        break;
                }
            } else {
                System.out.println("Please enter a valid option..");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}

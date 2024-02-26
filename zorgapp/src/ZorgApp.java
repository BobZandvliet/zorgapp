class ZorgApp {
    public static void main(String[] args) {
        // Specify the path to the JSON file
        String filePath = "E:\\zorgapp\\src\\clients.json";

        while (true) {
            Administration administration = new Administration(filePath); // Pass filePath
            administration.menu();

            System.out.println("Logged out. Returning to login menu...");
        }
    }
}

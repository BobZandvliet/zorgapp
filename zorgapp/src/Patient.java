//import java.time.LocalDate;
//
//class Patient {
//   static final int RETURN      = 0;
//   static final int SURNAME     = 1;
//   static final int FIRSTNAME   = 2;
//   static final int DATEOFBIRTH = 3;
//
//   int       id;
//   String    surname;
//   String    firstName;
//   LocalDate dateOfBirth;
//   int age;
//   int gewicht;
//
//    /**
//     * Constructor
//     */
//    Patient(int id, String surname, String firstName, LocalDate dateOfBirth, int gewicht) {
//        this.id = id;
//        this.surname = surname;
//        this.firstName = firstName;
//        this.dateOfBirth = dateOfBirth;
//        this.age = calculateAge(dateOfBirth);
//        this.gewicht = gewicht;
//    }
//    private int calculateAge(LocalDate dateOfBirth) {
//        LocalDate today = LocalDate.now(); // Get today's date
//        int years = today.getYear() - dateOfBirth.getYear();
//
//        // Check if birthday has passed in the current year
//        if (today.getMonthValue() < dateOfBirth.getMonthValue() ||
//                (today.getMonthValue() == dateOfBirth.getMonthValue() && today.getDayOfMonth() < dateOfBirth.getDayOfMonth())) {
//            years--; // Adjust for birthdays not yet passed in the current year
//        }
//
//        return years;
//    }
//    String getSurname() {
//        return surname;
//    }
//
//    String getFirstName() {
//        return firstName;
//    }
//
//    /**
//     * Display patient data.
//     */
//    void viewData() {
//        System.out.format("===== Patient id=%d ==============================\n", id);
//        System.out.format("%-17s %s\n", "Surname:", surname);
//        System.out.format("%-17s %s\n", "firstName:", firstName);
//        System.out.format("%-17s %s\n", "Date of birth:", dateOfBirth);
//        System.out.format("%-17s %s\n", "Age:", age);
//        System.out.format("%-17s %s\n", "Age:", gewicht);
//    }
//
//    /**
//     * Shorthand for a Patient's full name
//     */
//    String fullName() {
//        return String.format("%s %s [%s] %s", firstName, surname, dateOfBirth.toString(), age);
//    }
//}

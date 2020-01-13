import java.util.Scanner;

/**
 * filtering certain words out of text strings
 *
 * @author Jackson Oriez, oriezj@purdue.edu
 *
 * @version 9/24/2018
 *
 */

public class TextFilter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Print hello message
        System.out.println("Welcome to TextFilter!");

        // Value to keep track of if the user wants to keep filtering text
        boolean keepFiltering;

        do {
            // Print options for the user to select
            System.out.println("Please select one of the following filtering options: \n");
            System.out.println("1. Filter Word\n" +
                    "2. Find-And-Replace\n" +
                    "3. Censor Information");

            // Save their choice
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {

                // PART 1 - Censoring Words


                String passage = "";  // The text to be filtered
                System.out.println("Please enter the passage you would like filtered: ");

                passage = scanner.nextLine();
                passage = passage.trim();

                String word;  // The word to be censored from the text phrase
                System.out.println("Please enter the word you would like to censor: ");

                word = scanner.nextLine();

                System.out.println("Uncensored: ");
                System.out.println(passage);

                word = word.trim();
                String censor = new String();

                for (int i = 0; i < passage.length(); i++) {
                    String modifiedWord = word;
                    if (passage.substring(0, i).length() == modifiedWord.length() &&
                            (passage.charAt(i) == ' ' ||
                                    passage.charAt(i) == '!' ||
                                    passage.charAt(i) == ',' ||
                                    passage.charAt(i) == '?' ||
                                    passage.charAt(i) == '.')) {
                        censor = "";
                        modifiedWord = modifiedWord.trim() + passage.charAt(i);
                        if (passage.substring(0, i + 1).equalsIgnoreCase(modifiedWord)) {
                            for (int j = 0; j < modifiedWord.length(); j++) {
                                if (j == modifiedWord.length() - 1 && passage.charAt(i) == ' ') {
                                    censor = censor + " ";
                                } else if (j == modifiedWord.length() - 1 && passage.charAt(i) != ' ') {
                                    censor = censor + passage.charAt(i);
                                } else {
                                    censor = censor + "X";
                                }
                            }
                            passage = censor + passage.substring(i + 1);
                            modifiedWord = word;
                        }
                    }
                    if (passage.substring(i, i + 1).equals(" ") &&
                            passage.length() - (modifiedWord.length() + 2) > i  &&
                            (passage.charAt(i + modifiedWord.length() + 1) == ' ' ||
                                    passage.charAt(i + modifiedWord.length() + 1) == '!' ||
                                    passage.charAt(i + modifiedWord.length() + 1) == ',' ||
                                    passage.charAt(i + modifiedWord.length() + 1) == '?' ||
                                    passage.charAt(i + modifiedWord.length() + 1) == '.')) {
                        censor = "";
                        modifiedWord = " " + modifiedWord.trim() + passage.charAt(i + modifiedWord.length() + 1);
                        if (passage.substring(i, i + modifiedWord.length()).equalsIgnoreCase(modifiedWord)) {
                            for (int j = 0; j < modifiedWord.length(); j++) {
                                if (j == modifiedWord.length() - 1 &&
                                        passage.charAt(i + modifiedWord.length() - 1) != ' ') {
                                    censor = censor + passage.charAt(i + modifiedWord.length() - 1);
                                } else if (j == modifiedWord.length() - 1 &&
                                        passage.charAt(i + modifiedWord.length() - 1) == ' ') {
                                    censor = censor + " ";
                                } else if (j == 0) {
                                    censor = censor + " ";
                                } else {
                                    censor = censor + "X";
                                }
                            }
                            passage = passage.substring(0, i) + censor + passage.substring(i + censor.length());
                        }
                    } else if (passage.substring(i).length() == modifiedWord.length() + 1 &&
                            (passage.charAt(passage.length() - 1) == '!' ||
                                    passage.charAt(passage.length() - 1) == '?' ||
                                    passage.charAt(passage.length() - 1) == '.' ||
                                    passage.charAt(passage.length() - 1) == ',')) {
                        censor = "";
                        modifiedWord = " " + modifiedWord.trim() + passage.charAt(passage.length() - 1);
                        if (passage.substring(i - 1).equalsIgnoreCase(modifiedWord)) {
                            for (int j = 0; j < modifiedWord.length(); j++) {
                                if (j == 0) {
                                    censor = censor + " ";
                                } else if (j == modifiedWord.length() - 1) {
                                    censor = censor + passage.charAt(passage.length() - 1);
                                } else {
                                    censor = censor + "X";
                                }
                            }
                            passage = passage.substring(0, i - 1) + censor;
                        }
                    } else if (passage.substring(i, i + 1).equals(" ") &&
                            passage.length() - (modifiedWord.length() + 2) < i) {
                        censor = "";
                        modifiedWord = " " + modifiedWord.trim();
                        if (passage.substring(i).equalsIgnoreCase(modifiedWord)) {
                            for (int j = 0; j < modifiedWord.length(); j++) {
                                if (j == 0) {
                                    censor = censor + " ";
                                } else {
                                    censor = censor + "X";
                                }
                            }
                            passage = passage.substring(0, i) + censor;
                            i = passage.length();
                        }
                    }
                }

                System.out.println("Censored: ");
                System.out.println(passage);

            } else if (choice == 2) {

                // PART 2 - Replacing Words

                String passage = "";  // The text to be filtered
                System.out.println("Please enter the passage you would like filtered: ");

                passage = scanner.nextLine();
                passage = passage.trim();

                String replace;  // The word to be filtered from the text phrase
                System.out.println("Please enter the word you would like to replace: ");

                replace = scanner.nextLine();
                replace = replace.trim();

                String insert;  // The word to be inserted in the text phrase
                System.out.println("Please enter word you would like to insert: ");

                insert = scanner.nextLine();
                insert = insert.trim();

                System.out.println("Uncensored: ");
                System.out.println(passage);

                passage = passage.replaceAll(replace, insert);

                System.out.println("Censored: ");
                System.out.println(passage);

            } else if (choice == 3) {

                String passage = "";  // String for holding text to be filtered

                System.out.println("Please enter the phrase you would like to censor information from: ");

                while (true) {

                    // Obtain a line from the user
                    String temp = scanner.nextLine();

                    if (!passage.isEmpty() && temp.isEmpty()) {
                        break;
                    } else if (passage.isEmpty() && temp.isEmpty()) {
                        continue;
                    }


                    // Add the contents of temp into the phrase
                    passage += temp;


                    // Append a newline character to each line for parsing
                    // This will separate each line the user enters
                    // To understand how input is formatted in Part 3, please refer to the handout.
                    passage += '\n';
                }

                // Print the uncensored passage
                System.out.println("Uncensored: ");
                System.out.println(passage);

                String name = "";
                String email = "";
                String phoneNumber = "";
                String output = "";
                String originalName = "";
                String originalEmail = "";
                String originalPhone = "";

                for (int i = 0; i < passage.length(); i++) {
                    if (passage.length() - i < 7) {
                        continue;
                    } else {
                        if (passage.substring(i, i + 6).equalsIgnoreCase("Name: ")) {
                            name = passage.substring(i + 6, passage.indexOf('\n', i));
                            originalName = name;
                            for (int j = 0; j < name.length(); j++) {
                                if (j == 0) {
                                    output = output + name.charAt(0);
                                } else if (j == name.length() - 1) {
                                    output = output + name.charAt(name.length() - 1);
                                } else if (name.charAt(j) == ' ') {
                                    output = output + " ";
                                } else {
                                    output = output + "*";
                                }
                            }

                            name = output;
                            output = "";

                            passage = passage.replace(originalName, name);
                        } else if (passage.substring(i, i + 7).equalsIgnoreCase("Email: ")) {
                            email = passage.substring(i + 7, passage.indexOf('\n', i));
                            originalEmail = email;
                            for (int j = 0; j < email.length(); j++) {
                                if (j == 0) {
                                    output = output + email.charAt(0);
                                } else if (email.charAt(j) == '.') {
                                    output = output + email.substring(j);
                                    j = email.length();
                                } else if (email.charAt(j) == '@') {
                                    output = output + "@";
                                } else if (email.charAt(j - 1) == '@') {
                                    output = output + email.charAt(j);
                                } else {
                                    output = output + "*";
                                }
                            }

                            email = output;
                            output = "";

                            passage = passage.replace(originalEmail, email);
                        } else if (passage.substring(i, i + 7).equalsIgnoreCase("Phone: ")) {
                            phoneNumber = passage.substring(i + 7, passage.indexOf('\n', i));
                            originalPhone = phoneNumber;
                            for (int j = 0; j < phoneNumber.length(); j++) {
                                if (phoneNumber.charAt(j) == '-') {
                                    output = output + "-";
                                } else if (j == 8) {
                                    output = output + phoneNumber.substring(j);
                                    j = phoneNumber.length();
                                } else {
                                    output = output + "*";
                                }
                            }

                            phoneNumber = output ;
                            output = "";

                            passage = passage.replace(originalPhone, phoneNumber);
                        }
                    }
                }

                // Print the censored passage
                System.out.println("Censored: ");
                System.out.println(passage);

            } else {

                // They entered a number that was not 1, 2 or 3
                System.out.println("The option you chose was invalid!");
            }

            System.out.println("Would you like to keep filtering? Yes/No");

            String response = scanner.nextLine();

            while (response.isEmpty()) {
                response = scanner.nextLine();
            }

            if (response.equalsIgnoreCase("yes")) {
                keepFiltering = true;
            } else {
                keepFiltering = false;
            }
        } while (keepFiltering);
        System.out.println("Thank you for using TextFilter!");
    }
}
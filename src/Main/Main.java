package Main;

import Contact.Contact;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001b[0m";
    public static final String ANSI_YELLOW = "\u001b[33m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_BLUE = "\u001b[34m";
    public static final String ANSI_PURPLE = "\u001b[35m";
    public static final String ANSI_CYAN = "\u001b[36m";
    public static final String ANSI_RED = "\u001b[31m";
    static String directory = "data";
    static String contactsFile = "contacts.txt";
    static Scanner optionInput = new Scanner(System.in);
    static Scanner userNameInput = new Scanner(System.in);
    static Scanner userNumberInput = new Scanner(System.in);
    static ArrayList<Contact> contactList = new ArrayList<>();
    static Path dataDirectory = Paths.get(directory);
    static Path dataFile = Paths.get(directory, contactsFile);
    static Boolean done = false;

    public static void main(String[] args) {
        loadContacts();
        printMenu();
    }

    private static void loadContacts() {
        try {
            if (Files.notExists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }

            if (!Files.exists(dataFile)) {
                Files.createFile(dataFile);
            }

            loadFile();
            mainLoop();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void mainLoop() {
        while (done == false) {
            printMenu();
            System.out.println("\nEnter an option. (" + ANSI_BLUE + " 1 " + ANSI_RESET +
                    "," + ANSI_CYAN + " 2 " + ANSI_RESET + ", " +
                    ANSI_GREEN + " 3 " + ANSI_RESET + ", " +
                    ANSI_PURPLE + " 4 " + ANSI_RESET + ", " +
                    ANSI_RED + " 5 " + ANSI_RESET + ")");

            String userInput = optionInput.nextLine();
            userChoice(userInput);
        }
    }

    private static void loadFile() throws IOException {
        List<String> allContacts = Files.readAllLines(dataFile);
        for (int i = 0; i < allContacts.size(); i++) {
            String contactName = allContacts.get(i).split(",")[0];
            String contactNumber = allContacts.get(i).split(",")[1];
            Contact contactObject = new Contact(contactName, contactNumber);
            contactList.add(contactObject);
        }
    }


    private static void userChoice(String choice) {
        switch (choice) {
            case "1":
                System.out.println(ANSI_GREEN +  "----------------------------------------"+ ANSI_RESET);
                System.out.printf(ANSI_CYAN + "%41s %n", "name   | phone number" + ANSI_RESET);
                System.out.println(ANSI_GREEN +  "----------------------------------------"+ ANSI_RESET);
                viewContacts();
                break;
            case "2":
                addContactInfo();
                break;
            case "3":
                searchContactInfo();
                break;
            case "4":
                removeContact();
                break;
            case "5":
                System.out.println("Exiting Program");
                saveProgram();
                done = true;
                break;
            case "6":

                break;

            default:
            System.out.println("Please Enter a Valid Number.");
        }
    }

    private static void printMenu() {
        System.out.println(ANSI_YELLOW + "Please Choose from the list of options: \n" + ANSI_RESET +
                ANSI_BLUE + "\n 1. View contacts.\n" + ANSI_RESET +
                ANSI_CYAN + " 2. Add a new contact.\n" + ANSI_RESET +
                ANSI_GREEN + " 3. Search a contact by name.\n" + ANSI_RESET +
                ANSI_PURPLE + " 4. Delete an existing contact.\n" + ANSI_RESET +
                ANSI_RED + " 5. Exit." + ANSI_RESET);
    }

    private static void removeContact() {
        System.out.println("enter the name of the person you want to remove: ");
        String removeContact = userNameInput.nextLine();

        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getName().equals(removeContact)) {
                System.out.println(contactList.remove(i));
                return;
            }
        }
    }

    private static void searchContactInfo() {
        System.out.println("enter the name of the person you want to look for: ");
        String contactName = userNameInput.nextLine();

        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getName().equals(contactName)) {
                System.out.println(contactList.get(i));
                return;
            }
        }

        System.out.println("user not found");
    }


    private static void viewContacts() {

        //iterate over the contacts array list and print the list of contacts

        for (int i = 0; i < contactList.size(); i += 1){
            System.out.printf("%36s %n", contactList.get(i));
        }
        System.out.println(ANSI_GREEN +  "----------------------------------------"+ ANSI_RESET);
        backToMenu();
    }

    private static void backToMenu() {
        System.out.println("To go back to the main menu press enter");
        optionInput.nextLine();
    }

    private static void saveProgram() {
        List<String> contactLines = new ArrayList<>();

        for (int i = 0; i < contactList.size(); i++) {
            Contact contactObject = contactList.get(i);
            String contactName = contactObject.getName();
            String contactNumber = contactObject.getNumber();
            contactLines.add(contactName + "," + contactNumber);
        }

        try {
            Files.write(dataFile, contactLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void addContactInfo() {
        System.out.println("\nAdd entry for this contact. ");
        System.out.println("Please enter the contacts name:");
        String name = userNameInput.nextLine();
        System.out.println("Please enter the contacts number:");
        String number = userNumberInput.nextLine();
        Contact newContact = new Contact();
        newContact.setName(name);
        newContact.setNumber(number);

        contactList.add(newContact);
        System.out.println();

        System.out.println("Adding Contact name: " + newContact.getName() + " and number: " + newContact.getNumber());
        saveProgram();
    }

}


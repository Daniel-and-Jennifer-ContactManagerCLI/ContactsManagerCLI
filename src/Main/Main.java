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

    static String directory = "data";
    static String contactsFile = "contacts.txt";
    static Scanner optionInput = new Scanner(System.in);
    static Scanner userNameInput = new Scanner(System.in);
    static Scanner userNumberInput = new Scanner(System.in);
    static ArrayList<Contact> contactList = new ArrayList<>();
    static Path dataDirectory = Paths.get(directory);
    static Path dataFile = Paths.get(directory, contactsFile);
    static Boolean done;
    public static void main(String[] args) throws IOException {

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

            List<String> allContacts = Files.readAllLines(dataFile);
            for (int i = 0; i < allContacts.size(); i++) {
                String contactName = allContacts.get(i).split(",")[0];
                String contactNumber = allContacts.get(i).split(",")[1];
                Contact contactObject = new Contact(contactName, contactNumber);
                contactList.add(contactObject);
            }

            done = false;

            while (done == false) {
                printMenu();
                System.out.println(" Enter an option. ( 1, 2 ,3, 4 or 5): ");
                String userInput = optionInput.nextLine();
                userChoice(userInput);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    private static void userChoice(String choice){
        switch (choice) {
            case "1":
                System.out.printf("%37s %n", "name   | phone number");
                System.out.println( "----------------------------------------");
                viewContacts();
                backToMenu();
                break;
            case "2":
                addContactInfo();
                backToMenu();
                break;
            case "3":
                searchContactInfo();
                backToMenu();
                break;
            case "4":
                removeContact();
                backToMenu();
                break;
            case "5":
                exitProgram();
                backToMenu();
                done = true;
                break;
            default:
                System.out.println("Please Enter a Valid Number.");
        }
    }
    private static void printMenu() {
        System.out.println("Please Choose from the list of options: ");
        System.out.println(" 1. View contacts.");
        System.out.println(" 2. Add a new contact.");
        System.out.println(" 3. Search a contact by name.");
        System.out.println(" 4. Delete an existing contact.");
        System.out.println(" 5. Exit.");

    }

    private static void removeContact() {
        System.out.println("enter the name of the person you want to remove: ");
        String removeContact = userNameInput.nextLine();

        for(int i = 0; i < contactList.size(); i++){
            if(contactList.get(i).getName().equals(removeContact)){
                System.out.println(contactList.remove(i));
                return;
            }
        }

        System.out.println("user not found");
    }

    private static void searchContactInfo() {
        System.out.println("enter the name of the person you want to look for: ");
        String contactName = userNameInput.nextLine();

        for(int i = 0; i < contactList.size(); i++){
            if(contactList.get(i).getName().equals(contactName)){
                System.out.println(contactList.get(i));
                return;
            }
        }

        System.out.println("user not found");
    }


    private static void viewContacts() {
        //iterate over the contacts array list and print the list of contacts

        for (int i = 0; i < contactList.size(); i += 1){
            System.out.printf("%33s %n", contactList.get(i));
        }
        backToMenu();
    }

    private static void backToMenu(){
        System.out.println("To go back to the main menu press enter");
        optionInput.nextLine();
    }

    private static void exitProgram() {
        System.out.println("Exiting Program");
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
        System.out.println("\n Add entry for this contact. ");
        System.out.println("Add their name:");
        String name = userNameInput.nextLine();
        System.out.println("Add their number:");
        String number = userNumberInput.nextLine();
        Contact newContact = new Contact();
        newContact.setName(name);
        newContact.setNumber(number);

        contactList.add(newContact);
        System.out.println();
        System.out.println("Adding name: " + newContact.getName() + " and number: " + newContact.getNumber());
    }



}


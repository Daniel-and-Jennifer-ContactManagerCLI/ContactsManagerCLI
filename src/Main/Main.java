package Main;

import Contact.Contact;


import java.io.FileWriter;
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

    public static void main(String[] args) throws IOException {
        Boolean done;
        try {
            if (Files.notExists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }

            if (!Files.exists(dataFile)) {
                Files.createFile(dataFile);
            }

            done = false;

            while (done == false) {
                System.out.println();
                System.out.println("Please Choose from the list of options: ");
                System.out.println();
                System.out.println(" 1. View contacts.");
                System.out.println(" 2. Add a new contact.");
                System.out.println(" 3. Search a contact by name.");
                System.out.println(" 4. Delete an existing contact.");
                System.out.println(" 5. Exit.");
                System.out.println(" Enter an option. ( 1, 2 ,3, 4 or 5): ");
                int userInput = Integer.parseInt(optionInput.nextLine());

                switch (userInput) {
                    case 1:
                        System.out.println("name   | phone number|");
                        System.out.println("----------------------");
                        viewContacts();
                        break;
                    case 2:
                        addContactInfo();
                        break;
                    case 3:
                        System.out.println("okay3");
                        break;
                    case 4:
                        System.out.println("okay4");
                        break;
                    case 5:
                        exitProgram();
                        done = true;
                        break;
                    default:
                        System.out.println("Please Enter a Valid Number.");
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void viewContacts() {
        //iterate over the contacts array list
        for (int i = 0; i < contactList.size(); i += 1){
            System.out.println((i + 1) + ": " + contactList.get(i));
        }
        //on each iteration print the contact.
    }

    public static void exitProgram() throws IOException {
        try {
            FileWriter writeToFile = new FileWriter("contacts.txt");
            writeToFile.write(String.valueOf(contactList));
            writeToFile.close();
            System.out.println("You successfully saved the file!.");
        } catch (IOException e){
            System.out.println("error occurred.");
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



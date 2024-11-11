package sk.wio.service;

import sk.wio.db.Contact;
import sk.wio.db.DBContactService;
import sk.wio.utility.InputUtils;

import java.util.List;

public class CRUDManager {

    private final DBContactService contactService;

    public CRUDManager() {
        this.contactService = new DBContactService();
    }

    public void printOptions() {
        System.out.println("Hello, welcome to contacts manager\n");
        while (true) {
            System.out.println("0. Get all contacts");
            System.out.println("1. Edit contact");
            System.out.println("2. Add contact");
            System.out.println("3. Delete contact");
            System.out.println("4. Search contact");
            System.out.println("5. Exit");

            final int choice = InputUtils.readInt();
            switch (choice) {
                case 0 -> printAllContacts();
                case 1 -> System.out.println("Not implemented");
                case 2 -> createContact();
                case 3 -> System.out.println("Not implemented");
                case 4 -> System.out.println("Not implemented");
                case 5 -> {
                    System.out.println("Good bye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }

        }
    }

    private void createContact() {
        System.out.println("Enter name: ");
        final String name = InputUtils.readString();
        System.out.println("Enter email: ");
        final String email = InputUtils.readString();
        System.out.println("Enter phone: ");
        final String phone = InputUtils.readString();

        if (contactService.create(name, email, phone) > 0) {
            System.out.println("Contact, created successfully");
        } else {
            System.out.println("Could not create contact");
        }
    }

    private void printAllContacts() {
        final List<Contact> contacts = contactService.readAll();
        contacts.forEach(System.out::println);
    }
}

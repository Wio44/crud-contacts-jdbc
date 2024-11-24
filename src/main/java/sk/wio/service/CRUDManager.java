package sk.wio.service;

import sk.wio.db.Contact;
import sk.wio.db.DBContactService;
import sk.wio.utility.InputUtils;

import java.util.List;
import java.util.Optional;

public class CRUDManager {

    private final DBContactService contactService;

    public CRUDManager() {
        this.contactService = new DBContactService();
    }

    public void printOptions() {
        System.out.println("Hello, welcome to contacts manager\n");
        while (true) {
            InputUtils.printDivider();
            System.out.println("0. Get all contacts");
            System.out.println("1. Edit contact");
            System.out.println("2. Add contact");
            System.out.println("3. Delete contact");
            System.out.println("4. Search contact by email");
            System.out.println("5. Exit");
            InputUtils.printDivider();

            final int choice = InputUtils.readInt();
            switch (choice) {
                case 0 -> printAllContacts();
                case 1 -> editContact();
                case 2 -> createContact();
                case 3 -> deleteContact();
                case 4 -> searchContactByEmail();
                case 5 -> {
                    System.out.println("Good bye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }

        }
    }

    private void printAllContacts() {
        final List<Contact> contacts = contactService.readAll();
        contacts.forEach(System.out::println);
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
        }
    }

    private void deleteContact() {
        final List<Contact> contacts = contactService.readAll();

        int choice;
        while (true) {
            System.out.println("0.Cancel");
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println((i + 1) + ". " + contacts.get(i));
            }

            System.out.println("Enter number of contact you want delete: ");
            choice = InputUtils.readInt();
            if (choice == 0) {
                return;
            } else if (choice < 1 || choice > contacts.size()) {
                System.out.println("Invalid choice");
                continue;
            }

            if (contactService.delete(contacts.get(choice - 1).getId()) > 0) {
                System.out.println("Contact deleted successfully");
                return;
            }
        }
    }

    private void editContact() {
        final List<Contact> contacts = contactService.readAll();

        int choice;
        while (true) {
            System.out.println("0. Cancel");
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println((i + 1) + ". " + contacts.get(i));
            }

            System.out.println("Enter contact you want to edit: ");
            choice = InputUtils.readInt();
            if (choice == 0) {
                return;
            } else if (choice < 1 || choice > contacts.size()) {
                System.out.println("Invalid choice");
                continue;
            }

            final Optional<Contact> contactToEdit = editContactFromInput(contacts.get(choice - 1));
            if (contactToEdit.isPresent()) {
                if (contactService.edit(contactToEdit.get()) > 0) {
                    System.out.println("Contact updated successfully");
                    return;
                }
            }
        }
    }

    private Optional<Contact> editContactFromInput(Contact contact) {
        String name = contact.getName();
        String email = contact.getEmail();
        String phone = contact.getPhone();

        while (true) {
            System.out.println("0. Back");
            System.out.println("1. Edit name (" + name + ")");
            System.out.println("2. Edit email (" + email + ")");
            System.out.println("3. Edit phone (" + phone + ")");

            final int choice = InputUtils.readInt();
            switch (choice) {
                case 0 -> {
                    return Optional.empty();
                }
                case 1 -> {
                    System.out.println("Enter new name: ");
                    name = InputUtils.readString();
                    return Optional.of(new Contact(contact.getId(), name, email, phone));
                }
                case 2 -> {
                    System.out.println("Enter new email: ");
                    email = InputUtils.readString();
                    return Optional.of(new Contact(contact.getId(), name, email, phone));
                }
                case 3 -> {
                    System.out.println("Enter new phone: ");
                    phone = InputUtils.readString();
                    return Optional.of(new Contact(contact.getId(), name, email, phone));
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void searchContactByEmail() {
        System.out.println("Enter email: ");
        final String email = InputUtils.readString();
        final List<Contact> contacts = contactService.searchByEmail(email);
        if (contacts.isEmpty()) {
            System.out.println("No contacts found");
            return;
        }

        System.out.println("Found contacts");
        contacts.forEach(System.out::println);
    }
}

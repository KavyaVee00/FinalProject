package FFINalProject;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

class GameView {
    private GameController controller;
    private Scanner scanner;

    public GameView(GameController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        System.out.println("----- Main Menu -----");
        System.out.println("1. Adopt a pet");
        System.out.println("2. Interact with your pets");
        System.out.println("3. Display all pets");
        System.out.println("4. Check balance");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    void interactWithUser() {
        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    adoptPetFromUser();
                    break;
                case 2:
                    interactWithAdoptedPet();
                    break;
                case 3:
                    displayAllPets();
                    break;
                case 4:
                    System.out.println("Your balance: $" + controller.getBalance());
                    break;
                case 5:
                    System.out.println("Exiting game. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private void interactWithAdoptedPet() {
        LinkedList<AbstractPet> adoptedPets = controller.getAdoptedPets();
        if (adoptedPets.isEmpty()) {
            System.out.println("You have not adopted any pets yet.");
            return;
        }
        System.out.println("Your pets:");
        int index = 1;
        for (AbstractPet pet : adoptedPets) {
            System.out.println(index + ". " + pet.getName() + " (" + pet.getType() + ")");
            index++;
        }
        System.out.print("Enter the number of the pet you want to interact with (or 0 to cancel): ");
        int petChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (petChoice >= 1 && petChoice <= adoptedPets.size()) {
            AbstractPet selectedPet = adoptedPets.get(petChoice - 1);
            interactWithPet(selectedPet);
        } else if (petChoice != 0) {
            System.out.println("Invalid choice.");
        }
    }

    private void interactWithPet(AbstractPet pet) {
        System.out.println("Interacting with " + pet.getName() + "...");
        System.out.println("Choose an action:");
        System.out.println("1. Feed");
        System.out.println("2. Play");
        System.out.println("3. Groom");
        System.out.print("Enter action number: ");
        int action = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (action) {
            case 1:
                controller.interactWithPet(pet, "feed");
                break;
            case 2:
                controller.interactWithPet(pet, "play");
                break;
            case 3:
                controller.interactWithPet(pet, "groom");
                break;
            default:
                System.out.println("Invalid action.");
        }
    }

    private void adoptPetFromUser() {
        System.out.println("Available pets:");
        System.out.println("1. Dragon");
        System.out.println("2. Unicorn");
        System.out.println("3. Robot");
        System.out.print("Choose the type of pet you want to adopt (or 0 to cancel): ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        LinkedList<AbstractPet> availablePets;
        switch (typeChoice) {
            case 1:
                availablePets = controller.getPetsByType("Dragon");
                break;
            case 2:
                availablePets = controller.getPetsByType("Unicorn");
                break;
            case 3:
                availablePets = controller.getPetsByType("Robot");
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        displayAvailablePets(availablePets, typeChoice);
    }

    private void displayAvailablePets(LinkedList<AbstractPet> availablePets, int typeChoice) {
        String petType = typeChoice == 1 ? "Dragon" : typeChoice == 2 ? "Unicorn" : "Robot";
        LinkedList<AbstractPet> filteredPets = availablePets.stream()
                                                             .filter(pet -> pet.getType().equals(petType) && !pet.isAdopted())
                                                             .collect(Collectors.toCollection(LinkedList::new));

        int availablePetCount = filteredPets.size();

        if (filteredPets.isEmpty()) {
            System.out.println("No pets of this type available for adoption.");
            return;
        }

        System.out.println("Number of Available " + petType + "s: " + availablePetCount);
        int index = 1;
        for (AbstractPet pet : filteredPets) {
            System.out.println(index + ". " + pet.getName() + " (" + pet.getType() + ") - Price: $" + pet.getPrice());
            index++;
        }

        System.out.print("Enter the number of the pet you want to adopt (or 0 to cancel): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (choice >= 1 && choice <= filteredPets.size()) {
            AbstractPet selectedPet = filteredPets.get(choice - 1);
            adoptPet(selectedPet, availablePets);
        } else if (choice != 0) {
            System.out.println("Invalid choice.");
        }
    }

    private void adoptPet(AbstractPet selectedPet, LinkedList<AbstractPet> availablePets) {
        controller.adoptPet(selectedPet);
        if (selectedPet.isAdopted()) {
            System.out.println(selectedPet.getName() + " has been adopted!");
            availablePets.remove(selectedPet);
            System.out.println(selectedPet.getName() + "'s health: " + selectedPet.getHealth());
            System.out.println(selectedPet.getName() + "'s mood: " + selectedPet.getMood());
        } else {
            System.out.println("An error occurred while adopting " + selectedPet.getName());
        }
    }

    private void displayAllPets() {
        LinkedList<AbstractPet> availablePets = controller.getAvailablePets();
        int adoptedPetCount = controller.countAdoptedPets();
        int availablePetCount = availablePets.size();

        System.out.println("Number of Adopted Pets: " + adoptedPetCount);
        System.out.println("Number of Available Pets: " + availablePetCount);

        if (availablePets.isEmpty()) {
            System.out.println("There are no pets available for adoption.");
        } else {
            System.out.println("----- Available Pets -----");
            for (AbstractPet pet : availablePets) {
                System.out.println("Name: " + pet.getName() + " (" + pet.getType() + "), Price: $" + pet.getPrice());
            }
        }
    }
}

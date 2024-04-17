package FFINalProject;



import java.util.LinkedList;
import java.util.stream.Collectors;

public class GameController {
    private int balance;
    private LinkedList<AbstractPet> pets;

    public GameController() {
        this.balance = 250;
        this.pets = new LinkedList<>();

        // Add some pets to the list of available pets with different initial health and mood
        pets.add(new Dragon("Fireball", 50, 90, "Calm"));
        pets.add(new Unicorn("Sparkles", 75, 80, "Happy"));
        pets.add(new Robot("Robo", 100, 95, "Excited"));
        pets.add(new Dragon("Flame", 55, 85, "Angry"));
        pets.add(new Unicorn("Twilight", 80, 75, "Energetic"));
        pets.add(new Robot("Mechatron", 110, 70, "Sad"));
        pets.add(new Dragon("Scorch", 60, 85, "Content"));
        pets.add(new Unicorn("Rainbow", 85, 90, "Playful"));
        pets.add(new Robot("Techtron", 120, 75, "Curious"));
        pets.add(new Dragon("Inferno", 65, 95, "Bored"));
        pets.add(new Unicorn("Stardust", 90, 80, "Relaxed"));
        pets.add(new Robot("Cyborg", 130, 85, "Optimistic"));
        pets.add(new Dragon("Blaze", 70, 75, "Grumpy"));
        pets.add(new Unicorn("Magic", 95, 85, "Mischievous"));
        pets.add(new Robot("X-1000", 140, 80, "Confused"));
    }

    public int getBalance() {
        return balance;
    }

    public boolean canAdoptPet() {
        int totalPrice = pets.stream()
                             .filter(pet -> !pet.isAdopted())
                             .map(Pet::getPrice)
                             .mapToInt(Integer::intValue)
                             .sum();
        return totalPrice <= balance;
    }


    public void adoptPet(AbstractPet pet) {
        if (!pet.isAdopted()) {
            int petPrice = pet.getPrice();
            if (petPrice <= balance) {
                pet.setAdopted(true);
                balance -= petPrice;
                System.out.println(pet.getName() + " has been adopted!");
            } else {
                System.out.println("You don't have enough money to adopt " + pet.getName());
            }
        } else {
            System.out.println(pet.getName() + " has already been adopted.");
        }
    }

    public void interactWithPet(AbstractPet pet, String action) {
        switch (action.toLowerCase()) {
            case "feed":
                pet.feed();
                break;
            case "play":
                pet.play();
                break;
            case "groom":
                pet.groom();
                break;
            default:
                System.out.println("Invalid action.");
                break;
        }
        System.out.println("After " + action + ", " + pet.getName() + "'s mood is: " + pet.getMood() + " and Health is: " + pet.getHealth());
    }

    public LinkedList<AbstractPet> getPets() {
        return pets;
    }

    public int countAvailablePets(String type) {
        return countAvailablePetsRecursive(pets, type);
    }

    private int countAvailablePetsRecursive(LinkedList<AbstractPet> remainingPets, String type) {
        if (remainingPets.isEmpty()) {
            return 0;
        }
        AbstractPet currentPet = remainingPets.poll();
        if (!currentPet.isAdopted() && currentPet.getType().equals(type)) {
            return 1 + countAvailablePetsRecursive(remainingPets, type);
        } else {
            return countAvailablePetsRecursive(remainingPets, type);
        }
    }

    public int countAdoptedPets() {
        return countAdoptedPetsRecursive(pets);
    }

    private int countAdoptedPetsRecursive(LinkedList<AbstractPet> remainingPets) {
        if (remainingPets.isEmpty()) {
            return 0;
        }
        AbstractPet currentPet = remainingPets.poll();
        if (currentPet.isAdopted()) {
            return 1 + countAdoptedPetsRecursive(remainingPets);
        } else {
            return countAdoptedPetsRecursive(remainingPets);
        }
    }

    public LinkedList<AbstractPet> getPetsByType(String type) {
        return pets.stream()
                   .filter(pet -> pet.getType().equals(type))
                   .collect(Collectors.toCollection(LinkedList::new));
    }

    public LinkedList<AbstractPet> getAdoptedPets() {
        return pets.stream()
                   .filter(AbstractPet::isAdopted)
                   .collect(Collectors.toCollection(LinkedList::new));
    }

    public LinkedList<AbstractPet> getAvailablePets() {
        return pets.stream()
                   .filter(pet -> !pet.isAdopted())
                   .collect(Collectors.toCollection(LinkedList::new));
    }
}

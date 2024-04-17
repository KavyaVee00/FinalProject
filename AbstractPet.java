package FFINalProject;

//Abstract class representing common attributes and behaviors of pets
abstract class AbstractPet implements Pet {
private String name;
private int price;
private int health;
private String mood;
private boolean adopted;

public AbstractPet(String name, int price, int initialHealth, String initialMood) {
   this.name = name;
   this.price = price;
   this.health = initialHealth; // Set initial health
   this.mood = initialMood; // Set initial mood
   this.adopted = false;
}


@Override
public String getName() {
   return name;
}

@Override
public int getPrice() {
   return price;
}

@Override
public int getHealth() {
   return health;
}

@Override
public String getMood() {
   return mood;
}

@Override
public boolean isAdopted() {
   return adopted;
}

@Override
public void setAdopted(boolean adopted) {
   this.adopted = adopted;
}

@Override
public abstract String getType();

protected void setHealth(int health) {
   this.health = health;
}

protected void setMood(String mood) {
   this.mood = mood;
}
}
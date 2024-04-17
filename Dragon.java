package FFINalProject;

//Concrete class representing a dragon pet
class Dragon extends AbstractPet {
public Dragon(String name, int price, int initialHealth, String initialMood) {
   super(name, price, initialHealth, initialMood);
}

@Override
public String getType() {
   return "Dragon";
}

@Override
public void feed() {
   setHealth(getHealth() + 10);
   if (getHealth() > 100) {
       setHealth(100);
   }
   setMood("Happy with a ROAR");
}

@Override
public void play() {
   setHealth(getHealth() - 20);
   if (getHealth() < 0) {
       setHealth(0);
   }
   setMood("Excited - WINGS UP");
}

@Override
public void groom() {
   setHealth(getHealth() + 5);
   if (getHealth() > 100) {
       setHealth(100);
   }
   setMood("PURRING");
}
}
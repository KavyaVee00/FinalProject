package FFINalProject;

//Concrete class representing a unicorn pet
class Unicorn extends AbstractPet {
 public Unicorn(String name, int price, int initialHealth, String initialMood) {
     super(name, price, initialHealth, initialMood);
 }

 @Override
 public String getType() {
     return "Unicorn";
 }

 @Override
 public void feed() {
     setHealth(getHealth() + 15);
     if (getHealth() > 100) {
         setHealth(100);
     }
     setMood("Happy with a NEIGH");
 }

 @Override
 public void play() {
     setHealth(getHealth() - 15);
     if (getHealth() < 0) {
         setHealth(0);
     }
     setMood("Excited - GALLOPING AROUND");
 }

 @Override
 public void groom() {
     setHealth(getHealth() + 10);
     if (getHealth() > 100) {
         setHealth(100);
     }
     setMood("NEIGHING happily");
 }
}
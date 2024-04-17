package FFINalProject;

//Concrete class representing a robot pet
class Robot extends AbstractPet {
 public Robot(String name, int price, int initialHealth, String initialMood) {
	 super(name, price, initialHealth, initialMood);
 }

 @Override
 public String getType() {
     return "Robot";
 }

 @Override
 public void feed() {
     setHealth(getHealth() + 5);
     if (getHealth() > 100) {
         setHealth(100);
     }
     setMood("Beeping happily");
 }

 @Override
 public void play() {
     setHealth(getHealth() - 10);
     if (getHealth() < 0) {
         setHealth(0);
     }
     setMood("Processing excitement");
 }

 @Override
 public void groom() {
     setHealth(getHealth() + 10);
     if (getHealth() > 100) {
         setHealth(100);
     }
     setMood("Shiny and clean");
 }
}
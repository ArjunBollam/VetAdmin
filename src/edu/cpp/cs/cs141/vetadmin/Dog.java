package edu.cpp.cs.cs141.vetadmin;

/**
 * This class represents a {@link Pet} that is specialized to be a Dog.
 */
public class Dog extends Pet {
    /**
     * This enum type contains the breeds of dog that are defined in this
     * program
     */
   public enum DogBreed{
       HUSKY,
       PUG,
       LAB,
       YORKIE,
       PEKINGESE;
   }

    /**
     * The breed of the cat with type {@link DogBreed}
     */
   private DogBreed breed;

    /**
     * The constructor for the {@link Dog} class.
     *
     * @param name The name to be set
     * @param age The age to be set
     * @param breed The breed to be set
     */
   public Dog(String name, double age, int breed){
       super(name, age, AnimalType.DOG);

       switch(breed){
           case 0:
               this.breed = DogBreed.HUSKY;
               break;
           case 1:
               this.breed = DogBreed.PUG;
               break;
           case 2:
               this.breed = DogBreed.LAB;
               break;
           case 3:
               this.breed = DogBreed.YORKIE;
               break;
           case 4:
               this.breed = DogBreed.PEKINGESE;
       }
   }

    /**
     * @return The {@link #breed} of {@code this} {@link Dog}
     */
    public DogBreed getBreed() {
        return breed;
    }

    /**
     * @param breed The breed to be set for {@code this} {@link Dog}
     */
    public void setBreed(DogBreed breed) {
        this.breed = breed;
    }

}

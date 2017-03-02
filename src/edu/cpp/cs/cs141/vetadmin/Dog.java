package edu.cpp.cs.cs141.vetadmin;

/**
 * Created by chocolatecharme on 3/2/17.
 */
public class Dog extends Pet {
   public enum DogBreed{
       HUSKY,
       PUG,
       LAB,
       YORKIE,
       PEKINGESE;
   }

   private DogBreed breed;

   public Dog(String name, int age, int breed){
       super(name, age);

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

    public DogBreed getBreed() {
        return breed;
    }

    public void setBreed(DogBreed breed) {
        this.breed = breed;
    }

}

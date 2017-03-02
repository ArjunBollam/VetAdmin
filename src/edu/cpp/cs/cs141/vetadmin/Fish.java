package edu.cpp.cs.cs141.vetadmin;

/**
 * Created by chocolatecharme on 3/2/17.
 */
public class Fish extends Pet{
    public enum FishBreed {
        GOLDFISH,
        BETTA,
        ANGELFISH,
        CLOWNFISH,
        GUPPY;
    }

    private FishBreed breed;

    public Fish(String name, int age, int breed){
        super(name, age);

        switch(breed){
            case 0:
                this.breed = FishBreed.GOLDFISH;
                break;
            case 1:
                this.breed = FishBreed.BETTA;
                break;
            case 2:
                this.breed = FishBreed.ANGELFISH;
                break;
            case 3:
                this.breed = FishBreed.CLOWNFISH;
                break;
            case 4:
                this.breed = FishBreed.GUPPY;
        }
    }

    public FishBreed getBreed() {
        return breed;
    }

    public void setBreed(FishBreed breed) {
        this.breed = breed;
    }

}

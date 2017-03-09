package edu.cpp.cs.cs141.vetadmin;

/**
 * This class represents a {@link Pet} that is specialized to be a Fish.
 */
public class Fish extends Pet{
    /**
     * This enum type contains the breeds of fish that are defined in this
     * program
     */
    public enum FishBreed {
        GOLDFISH,
        BETTA,
        ANGELFISH,
        CLOWNFISH,
        GUPPY;
    }

    /**
     * The breed of the cat with type {@link FishBreed}
     */
    private FishBreed breed;

    /**
     * The constructor for the {@link Fish} class.
     *
     * @param name The name to be set
     * @param age The age to be set
     * @param breed The breed to be set
     */
    public Fish(String name, int age, int breed){
        super(name, age, AnimalType.FISH);

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


    /**
     * @return The {@link #breed} of {@code this} {@link Fish}
     */
    public FishBreed getBreed() {
        return breed;
    }

    /**
     * @param breed The breed to be set for {@code this} {@link Fish}
     */
    public void setBreed(FishBreed breed) {
        this.breed = breed;
    }

}

package edu.cpp.cs.cs141.vetadmin;

/**
 * This class represents a {@link Pet} that is specialized to be a Cat.
 */
public class Cat extends Pet{
    /**
     * This enum type contains the breeds of cat that are defined in this
     * program
     */
    public enum CatBreed{
        SIAMESE,
        PERSIAN,
        BURMESE,
        BOBTAIL,
        SIBERIAN;
    }

    /**
     * The breed of the cat with type {@link CatBreed}
     */
    private CatBreed breed;

    /**
     * The constructor for the {@link Cat} class.
     *
     * @param name The name to be set
     * @param age The age to be set
     * @param breed The breed to be set
     */
    public Cat(String name, int age, int breed){
        super(name, age, AnimalType.CAT);

        switch(breed){
            case 0:
                this.breed = CatBreed.SIAMESE;
                break;
            case 1:
                this.breed = CatBreed.PERSIAN;
                break;
            case 2:
                this.breed = CatBreed.BURMESE;
                break;
            case 3:
                this.breed = CatBreed.BOBTAIL;
                break;
            case 4:
                this.breed = CatBreed.SIBERIAN;
        }
    }

    /**
     * @return The {@link #breed} of {@code this} {@link Cat}
     */
    public CatBreed getBreed() {
        return breed;
    }

    /**
     * @param breed The breed to be set for {@code this} {@link Cat}
     */
    public void setBreed(CatBreed breed) {
        this.breed = breed;
    }

}

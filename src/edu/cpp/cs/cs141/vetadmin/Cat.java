package edu.cpp.cs.cs141.vetadmin;

/**
 * Created by chocolatecharme on 3/2/17.
 */
public class Cat extends Pet{
    public enum CatBreed{
        SIAMESE,
        PERSIAN,
        BURMESE,
        BOBTAIL,
        SIBERIAN;
    }

    private CatBreed breed;

    public Cat(String name, int age, int breed){
        super(name, age);

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

    public CatBreed getBreed() {
        return breed;
    }

    public void setBreed(CatBreed breed) {
        this.breed = breed;
    }

}

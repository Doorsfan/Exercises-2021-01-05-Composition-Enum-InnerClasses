package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * A nested Class, named Dog - with a nested class called Tail and a Enum, called Breed
 */
public class Dog {
    //Enums are, internally, classes that declare their variables with Static Final, meaning the values can't
    //be changed once declared - and only have one incarnation of them, per Enum object

    /**
     * The enum that is responsible for creating each breed of the Dog
     */
    private enum Breed {
        BEAGLE, TAX, GREYHOUND, DOBERMANN, ROTTWEILER; //Will create a BEAGLE enum, TAX enum, GREYHOUND enum, etc.
        //These above could be considered as being written as:
        //public static final Breed BEAGLE = new Breed();
        //public static final Breed TAX = new Breed();
        // etc.
        //
        //They are constant

        //However, since an Enum behaves like a class - we can access it's Constructor and change around
        //how each Enum element is created
        ArrayList<String> mixedWith = new ArrayList<String>(); //An ArrayList to denote what races the dog is a mixed breed of
        Random random = new Random(); //Will be used for Random number generation
        ArrayList<String> theBreeds = new ArrayList<String>(Arrays.asList("Beagle", "Tax", "Greyhound", "Dobermann", "Rottweiler"));
        //In the above code, we use Arrays.asList() to convert the elements into a List, we do this so we don't have to write
        //theBreeds.add("Beagle")
        //theBreeds.add("Tax") etc.

        //Choose a number of breeds, in a range of 1-3 (inclusive)
        int amountOfBreeds = random.ints(1, 3).findFirst().getAsInt();
        //Here, we can use the Constructor to handle how the Enum should be created
        private Breed()
        {
            this.mixedWith.add(this.toString()); //The Constants name is derived from it's toString() method, so we just get it's name
            //from using it's toString()
            for(int i = 0; i < amountOfBreeds; i++){
                //Select a random number between 0 (inclusive) and 5 (exclusive), so 0,1,2,3,4
                int breedNumber = random.ints(0,5).findFirst().getAsInt();
                //If the race is already stated, don't add it - be it itself or another race
                if(!theBreeds.get(breedNumber).toUpperCase().equals(this.toString())
                    && !this.mixedWith.contains(theBreeds.get(breedNumber).toUpperCase())){
                    this.mixedWith.add(theBreeds.get(breedNumber).toUpperCase());
                }
            }
        }
        //Enums can also contain Methods, since they are instansiated as Classes
        public ArrayList<String> getBreed(){
            return this.mixedWith;
        }
    }

    private String name;
    private Breed breed;
    private Tail myTail;

    //Basic constructor for the Dog

    /**
     *
     * @param name A string, the name of the dog
     * @param breed A string, fed into the Breed Enum to create a Static Final object of this name
     * @param tailNickName A string, the nickname for the Tail
     */
    public Dog(String name, String breed, String tailNickName){
        this.name = name;
        this.breed = Breed.valueOf(breed.toUpperCase()); //When we use an Enum, we have to denote with <Enum name>.valueOf(<input>.toUpperCase())
        //Due to that Enums treat their handle names as caps, to denote them being Constant - i.e static final, can't be changed once made
        this.myTail = new Tail(this, tailNickName); //Keep track of the tail for the Dog and give it a nickname
    }

    public String getName(){
        return name;
    }

    //Calls the tail of the dog to Wag
    public void wagYourTail(){
        this.myTail.wag();
    }

    //Retrives the list of races that this dog is mixed with, from the breed Enum
    public ArrayList<String> getBreed() {
        return this.breed.getBreed();
    }

    //A nested class, the tail for the Dog

    /**
     * The nested class within Dog, that keeps track of it's owner and it's nickname
     */
    public class Tail {
        private Dog owner; //The owner of the Tail
        private String nickName; //The tail's nickname

        /**
         * @param owner A object of type Dog, the owner of the Tail
         * @param nickName A string, the nickname of the Tail
         */
        public Tail(Dog owner, String nickName){
            this.owner = owner;
            this.nickName = nickName;
        }

        //Wags the dogs tail and gives some information about it

        /**
         * Wags the dogs tail and gives some information about it
         */
        public void wag(){
            System.out.println("I am the tail " + this.nickName + " of the dog " + this.owner.getName() + " and i am wagging!");
            System.out.println("I am of the following breed(s): " + this.owner.getBreed());
        }
    }
}

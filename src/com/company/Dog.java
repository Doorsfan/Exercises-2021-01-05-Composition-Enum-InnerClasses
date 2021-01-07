package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * A nested Class, named Dog - with a class called Tail and a Enum, called Breed
 */
public class Dog {
    /**
     * The enum that is responsible for creating each breed of the Dog
     */
    //Enums are, internally, classes that declare their variables with Static Final, meaning the values can't
    //be changed once declared - and only have one incarnation of them, per Enum object
    private enum Breed{
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
        int amountOfBreeds = random.ints(1, 4).findFirst().getAsInt();
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
        private ArrayList<String> getBreed(){
            return this.mixedWith;
        }

        /**
         * The enum responsible for behavioral type of each respective behavioral profile.
         * Implements the Animal interface.
         */
        // This nested Enum implements the Animal interface - an Abstract class. A Enum cannot inherit, as in
        // extend other Classes - but it can inherit from abstract interfaces that allows for implementation of
        // inherited methods from said Interface.
        private enum behavioralType implements Animal{
            //Enums can also have values being fed to their respective constructors, as showcased here
            SHEEPHERDER(6, 7, 10, 4, 3),
            NARCOTICS_DOG(10, 2, 3, 7, 8),
            FAMILY_DOG(5, 10, 10, 2, 2),
            HUNTING_DOG(10, 3, 3, 5, 6),
            GUARD_DOG(8, 1, 1, 8, 8);

            //The constructor is built in the same order as the variables are declared
            int senseOfSmell, kindness, playfulness, aggression, energy;

            behavioralType(int senseOfSmell, int kindness, int playfulness, int aggression, int energy){
                this.senseOfSmell = senseOfSmell;
                this.kindness = kindness;
                this.playfulness = playfulness;
                this.aggression = aggression;
                this.energy = energy;
            }

            /**
             * Every Object in Java, has a toString() method that it inherits, that allows an Object to have a
             * String representation - and we can override that, with @Override - so that we can handle cases of
             * what kind of a dog is calling the toString() method
             *
             * @return A string that tells us a bit more about the behavioral pattern of this kind of dog
             */
            @Override
            public String toString(){
                switch(this){
                    case SHEEPHERDER:
                        return "I am a Sheepherder. I have a lot of energy, i run a lot and like to play around a lot." +
                                " If no-one goes out with me a lot, i might eat your furniture or annoy your other dog(s).";
                    case NARCOTICS_DOG:
                        return "I am a Narcotics Dog. I work for the police in helping sniffing out contraband and tracking missing people." +
                                "I have an excellent sense of smell and a strong sense of duty.";
                    case FAMILY_DOG:
                        return "I am a Family Dog. I really love my owners, have a long patience and i like to play with people.";
                    case HUNTING_DOG:
                        return "I am a Hunting Dog. I have an excellent sense of smell and a strong hunting instinct. I aid hunters" +
                                "in tracking prey.";
                    case GUARD_DOG:
                        return "I am a Guard Dog. I watch over my owners, protect my property and am not very fond of" +
                                "people i do not trust. I can be very intimidating - barking and maybe even biting.";
                }
                return "I am a Dog - but i don't have much to tell about myself.";
            }

            /**
             * A simple method to showcase usage of both accessing Enum name with super.name() and implement a method from
             * an abstract interface
             */
            public void run(){
                System.out.println(name + ", a " + super.name().toLowerCase().replaceAll("_", " ") + ", is running!");
            }
        }
    }

    //Since Enums are Static, to reference a variable in the same class but not within the Enum itself, we have
    //to declare this variable as Static - to be able to access it
    private static String name;


    private Breed breed;
    private Tail myTail;
    private Breed.behavioralType behavioralType;

    /**
     * A basic class that acts as a representation of a Dog. Has a nested class called Tail.
     *
     * @param name A string, the name of the dog
     * @param breed A string, fed into the Breed Enum to create a Static Final object of this name
     * @param tailNickName A string, the nickname for the Tail
     */
    //Basic constructor for the Dog
    public Dog(String name, String breed, String tailNickName, String behavioralType){
        this.name = name;
        this.breed = Breed.valueOf(breed.toUpperCase()); //When we use an Enum, we have to denote with <Enum name>.valueOf(<input>.toUpperCase())
        //Due to that Enums treat their handle names as caps, to denote them being Constant - i.e static final, can't be changed once made
        this.myTail = new Tail(this, tailNickName); //Keep track of the tail for the Dog and give it a nickname
        this.behavioralType = Breed.behavioralType.valueOf(behavioralType.toUpperCase());
    }

    public String tellMeAboutYourself(){
        return this.behavioralType.toString();
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

    //Simple method to showcase the run implementation inherited from the Animal interface
    public void run(){
        this.behavioralType.run();
    }

    /**
     * The nested class within Dog, that keeps track of it's owner and it's nickname
     */
    //A nested class, the tail for the Dog
    private class Tail {
        private Dog owner; //The owner of the Tail
        private String nickName; //The tail's nickname

        /**
         * @param owner A object of type Dog, the owner of the Tail
         * @param nickName A string, the nickname of the Tail
         */
        private Tail(Dog owner, String nickName){
            this.owner = owner;
            this.nickName = nickName;
        }

        /**
         * Wags the dogs tail and gives some information about it
         */
        //Wags the dogs tail and gives some information about it
        private void wag(){
            System.out.println("I am the tail " + this.nickName + " of the dog " + this.owner.getName() + " and i am wagging!");
            System.out.println("I am of the following breed(s): " + this.owner.getBreed());
        }
    }
}

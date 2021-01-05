package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Dog {
    private enum Breed {
        BEAGLE, TAX, GREYHOUND, DOBERMANN, ROTTWEILER;
        ArrayList<String> mixedWith = new ArrayList<String>();
        Random random = new Random();
        ArrayList<String> theBreeds = new ArrayList<String>(Arrays.asList("Beagle", "Tax", "Greyhound", "Dobermann", "Rottweiler"));

        int amountOfBreeds = random.ints(1, 3).findFirst().getAsInt();
        private Breed()
        {
            this.mixedWith.add(this.toString());
            for(int i = 0; i < amountOfBreeds; i++){
                int breedNumber = random.ints(0,4).findFirst().getAsInt();
                if(!theBreeds.get(breedNumber).toUpperCase().equals(this.toString())
                    && !this.mixedWith.contains(theBreeds.get(breedNumber).toUpperCase())){
                    this.mixedWith.add(theBreeds.get(breedNumber).toUpperCase());
                }
            }
        }
        public ArrayList<String> getBreed(){
            return this.mixedWith;
        }
    }

    private String name;
    private Breed breed;
    private Tail myTail;

    public Dog(String name, String breed, String tailNickName){
        this.name = name;
        this.breed = Breed.valueOf(breed.toUpperCase());
        this.myTail = new Tail(this, tailNickName);
    }

    public String getName(){
        return name;
    }

    public void wagYourTail(){
        this.myTail.wag();
    }

    public ArrayList<String> getBreed() {
        return this.breed.getBreed();
    }

    public class Tail {
        private Dog owner;
        private String nickName;
        public Tail(Dog owner, String nickName){
            this.owner = owner;
            this.nickName = nickName;
        }

        public void wag(){
            System.out.println("I am the tail " + this.nickName + " of the dog " + this.owner.getName() + " and i am wagging!");
            System.out.println("I am of the following breed(s): " + this.owner.getBreed());
        }
    }
}

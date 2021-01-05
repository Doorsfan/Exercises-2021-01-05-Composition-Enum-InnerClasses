package com.company;

public class Main {

    public static void main(String[] args) {
        Dog myDog = new Dog("Bruno", "Beagle", "Pokey");
        Dog yourDog = new Dog("Mars", "Tax", "Wigley");
        myDog.wagYourTail();
        yourDog.wagYourTail();
    }
}

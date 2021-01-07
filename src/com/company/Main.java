package com.company;

public class Main {

    public static void main(String[] args) {
        Dog myDog = new Dog("Bruno", "Beagle", "Pokey", "sheepherder");
        Dog yourDog = new Dog("Mars", "Tax", "Wigley", "family_dog");
        myDog.wagYourTail();
        System.out.println(myDog.tellMeAboutYourself());

        System.out.println("-----");
        yourDog.wagYourTail();
        System.out.println(yourDog.tellMeAboutYourself());
        yourDog.run();
    }
}

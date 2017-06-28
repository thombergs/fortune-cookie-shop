package de.adesso.cookies.quotes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CookiesDB {

    private final Random random = new Random();

    private ArrayList<FortuneCookieResource> list = new ArrayList<>();

    CookiesDB() {

        int min = 0;
        int max = 1000;

        list.add(new FortuneCookieResource("The troubles you have now will pass away quickly.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("Everything happens for a reason.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("You will become great if you believe in yourself.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("You are very grateful for the small pleasures of life.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("Prosperity makes friends and adversity tries them.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("It takes more then good memory to have good memories.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("Never quit!", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("The majority of the word \"can't\" is can.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("What ends on hope does not end at all.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("Perhaps you've been focusing too much on that one thing..", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("The world is always ready to receive talent with open arms.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("You can choose, right now and in every moment, to put your powerful and effective abilities to purposeful use. There is always something you can do, no matter what the situation may be, that will move your life forward.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("You will have many happy days soon.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("Do not be covered in sadness or be fooled in happiness they both must exist", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("You are contemplating some action which will bring credit upon you.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("Kiss is not a kiss without the heart.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("Good health will be yours for a long time.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("There is a time to be practical now.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("A bargain is something you don't need at a price you can't resist.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("It is now, and in this world, that we must live.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("Good clothes open many doors. Go shopping.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("You will have good luck and overcome many hardships.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("Don't cry.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("Life to you is a bold and dashing responsibility.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("To be old and wise, you must first be young and stupid.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("Your dynamic eyes have attracted a secret admirer.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("Your fortune is as sweet as a cookie.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("Spring has sprung. Life is blooming.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("Some pursue happiness; you create it.", ThreadLocalRandom.current().nextInt(min, max)));
        list.add(new FortuneCookieResource("The early bird gets the worm, but the second mouse gets the cheese.", ThreadLocalRandom.current().nextInt(min, max)));

    }

    public ArrayList<FortuneCookieResource> getList() {
        return list;
    }

}

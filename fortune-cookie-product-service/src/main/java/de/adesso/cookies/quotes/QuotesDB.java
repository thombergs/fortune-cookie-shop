package de.adesso.cookies.quotes;

import java.util.ArrayList;
import java.util.Collections;

public class QuotesDB {

    ArrayList<String> list = new ArrayList<>();

    public QuotesDB() {

        list.add("The troubles you have now will pass away quickly.");
        list.add("Everything happens for a reason.");
        list.add("You will become great if you believe in yourself.");
        list.add("You are very grateful for the small pleasures of life.");
        list.add("Prosperity makes friends and adversity tries them.");
        list.add("It takes more then good memory to have good memories.");
        list.add("Never quit!");
        list.add("The majority of the word \"can't\" is can.");
        list.add("What ends on hope does not end at all.");
        list.add("Perhaps you've been focusing too much on that one thing..");
        list.add("The world is always ready to receive talent with open arms.");
        list.add("You can choose, right now and in every moment, to put your powerful and effective abilities to purposeful use. There is always something you can do, no matter what the situation may be, that will move your life forward.");
        list.add("You will have many happy days soon.");
        list.add("Do not be covered in sadness or be fooled in happiness they both must exist");
        list.add("You are contemplating some action which will bring credit upon you.");
        list.add("Kiss is not a kiss without the heart.");
        list.add("Good health will be yours for a long time.");
        list.add("There is a time to be practical now.");
        list.add("A bargain is something you don't need at a price you can't resist.");
        list.add("It is now, and in this world, that we must live.");
        list.add("Good clothes open many doors. Go shopping.");
        list.add("You will have good luck and overcome many hardships.");
        list.add("Don't cry.");
        list.add("Life to you is a bold and dashing responsibility.");
        list.add("To be old and wise, you must first be young and stupid.");
        list.add("Your dynamic eyes have attracted a secret admirer.");
        list.add("Your fortune is as sweet as a cookie.");
        list.add("Spring has sprung. Life is blooming.");
        list.add("Some pursue happiness; you create it.");
        list.add("The early bird gets the worm, but the second mouse gets the cheese.");

    }

    public ArrayList<String> getList(int size) {

        Collections.shuffle(list);

        if(size <= list.size()) {
            int k = list.size();
            if (k > size)
                list.subList(size, k).clear();
        }

        return list;
    }

}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Test {

    Test() {
    }

    /* Testing Methods:
     *      testMyLinkedList()
     *      testMyStack()
     *      testMyQueue()
     *      checkPalindromes()
     */
    public void testMyLinkedList() {
        // Create a linked list of really awesome songs
        LinkedList songs = new LinkedList();

        // Add some really awesome songs
        songs.add("Particles - Nothing But Thieves");
        songs.add("Sugarcoat - Kid Bloom");
        songs.add("Little One - Highly Suspect");
        songs.add("Why Are Sundays So Depressing - The Strokes");
        songs.add("My Honest Face - Inhaler");
        songs.add("Safari Song - Greta Van Fleet");
        songs.add("Down the Road - Dirty Honey");

        // Print the list of really awesome songs
        songs.print();
    }

    public void testMyStack() {
        // Create a stack of words that rhyme with stack
        Stack rhymes = new Stack();

        // Add some rhymes
        rhymes.push("Rack");
        rhymes.push("Slack");
        rhymes.push("Whack");
        rhymes.push("Snack");
        rhymes.push("Quack");
        rhymes.push("Hack");
        rhymes.push(("Soundtrack"));

        // Print da rhymes
        System.out.println("Rhymes 1");
        System.out.println("---------");
        rhymes.print();

        // Make sure pop() is poppin...
        System.out.println("\nPop() Test:");
        System.out.println("---------");

        Stack rhymes_2 = new Stack();
        int length = rhymes.getLength();

        for (int i=0; i<length; i++) {
            // Pop and print
            Node x = rhymes.pop();
            System.out.println(x.toString());

            // Push to new stack, order is now flipped
            rhymes_2.push(x.getName());
        }

        // Print rhymes again, in reverse order
        System.out.println("\nRhymes 2");
        System.out.println("---------");
        rhymes_2.print();
    }

    public void testMyQueue() {
        // Create a queue of people waiting to checkout at Stop & Shop
        Queue checkout = new Queue();

        // Add the peeps
        checkout.enqueue("Karen");
        checkout.enqueue("Evan");
        checkout.enqueue("David");
        checkout.enqueue("Marco");
        checkout.enqueue("Shannon");
        checkout.enqueue("Dan");
        checkout.enqueue("Alan");

        System.out.println("Checkout:");
        System.out.println("---------");
        checkout.print();

        // Karen is now being helped
        Node karen = checkout.dequeue();
        System.out.println("Checkout:");
        System.out.println("---------");
        checkout.print();

        // Karen, in Karen fashion, made a scene asking for a manager...
        Queue customerService = new Queue();
        customerService.enqueue(karen.getName());
        System.out.println("Customer Service:");
        System.out.println("---------");
        customerService.print();

        // Alan is now next in line
        int length = checkout.getLength();
        for (int i=0; i<length-1; i++) {
            Node customer = checkout.dequeue();
        }
        System.out.println("Checkout:");
        System.out.println("---------");
        checkout.print();

        // Karen was told to get back on the checkout line
        // She tried to cut Alan but he didn't fall for her tricks
        checkout.enqueue(karen.getName());
        System.out.println("Checkout:");
        System.out.println("---------");
        checkout.print();

        // Finally they all go home
        length = checkout.getLength();
        for (int i=0; i<length; i++) {
            Node customer = checkout.dequeue();
        }

        System.out.println("Checkout:");
        System.out.println("---------");
        if (checkout.isEmpty()) {
            System.out.println("The checkout line is empty.");
        }
    }

    public void checkPalindrome() {
        // magicitems.txt
        String[] items = new String[666];

        // palindromes.txt
        // String[] items = new String[13];

        LinkedList palindromes = new LinkedList();
        Stack stack = new Stack();
        Queue queue = new Queue();
        int i = 0;
        
        try {
            File file = new File("magicitems.txt");
            // File file = new File("palindromes.txt");
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            items[i] = data;
            i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for (int w=0; w<items.length; w++) {
            // Normally I'd initialize this to false, but it seems to make more sense to look for all the words that aren't palindromes.
            // A non-palindrome and palindrome can both have matching characters on opposing ends of the string.
            // A palindrome will never have mismatching characters, a non-palindrome will.
            boolean isPalindrome = true;

            // Remove all whitespace and symbols
            items[w] = items[w].replaceAll("[\\s,\\.'\\(\\)+-]", "");

            // Convert to all lower case
            items[w] = items[w].toLowerCase();

            for (int j=0; j<items[w].length(); j++) {
                // Loop through all the character in the string 
                // and add each character to a stack and queue, respectively
                stack.push(String.valueOf(items[w].charAt(j)));
                queue.enqueue(String.valueOf(items[w].charAt(j)));
            }

            int length = stack.getLength();
            for (int c=0; c<length; c++) {
                // Pop and dequeue each letter
                String char1 = stack.pop().getName();
                String char2 = queue.dequeue().getName();

                if (!(char1.equals(char2))) {
                    // A word cannot be a palindrome if both chars are not the same
                    isPalindrome = false;
                }
            }
            if (isPalindrome) {
                // Add all palindromes to a separate list
                palindromes.add(items[w]);
            }
        }
        palindromes.print();
    }
}
class Assignment_1 {
    public static void main(String[] args) {
        // testMyLinkedList();
        testMyStack();
    }

    public static void testMyLinkedList() {

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

    public static void testMyStack() {

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
        int length = rhymes.length;

        for (int i=0; i<length; i++) {
            // Pop and print
            Node x = rhymes.pop();
            System.out.println(x.toString());

            // Push to new stack, order is now flipped
            rhymes_2.push(x.name);
        }

        // Print rhymes again, in reverse order
        System.out.println("\nRhymes 2");
        System.out.println("---------");
        rhymes_2.print();
    }


}
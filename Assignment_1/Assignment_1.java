class Assignment_1 {
    public static void main(String[] args) {
        
        // Test each of the data structures
        // testMyLinkedList();
        // testMyStack();
        testMyQueue();
    }

    /* Testing Methods:
     *      testMyLinkedList
     *      testMyStack
     *      testMyQueue
     */
    static void testMyLinkedList() {
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

    static void testMyStack() {
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

    static void testMyQueue() {
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
}
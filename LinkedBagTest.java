public class LinkedBagTest {
    public static void main(String[] args) {
        BagInterface<String> bag1 = new LinkedBag<>();
        BagInterface<String> bag2 = new LinkedBag<>();

        bag1.add("a");
        bag1.add("b");
        bag1.add("c");
        bag1.add("e");
        bag1.add("e");
        bag1.add("b");
        bag1.add("b");
        bag1.add("c");

        bag2.add("b");
        bag2.add("b");
        bag2.add("d");
        bag2.add("e");
        bag2.add("b");
        bag2.add("c");

        BagInterface<String> bagTests;
        //bag1.displayBag();
        //bag2.displayBag();
        bagTests = bag1.union(bag2);
        System.out.println("BagLinkedTest bag1 Union bag2: ");
        bagTests.displayBag();

        bagTests = bag2.union(bag1);
        System.out.println("BagLinkedTest bag2 Union bag1: ");
        bagTests.displayBag();

        bagTests = bag1.intersection(bag2);
        System.out.println("BagLinkedTest bag1 Intersection bag2: ");
        bagTests.displayBag();

        bagTests = bag2.intersection(bag1);
        System.out.println("BagLinkedTest bag2 Intersection bag1: ");
        bagTests.displayBag();

        bagTests = bag1.difference(bag2);
        System.out.println("BagLinkedTest bag1 Difference bag2: ");
        bagTests.displayBag();

        bagTests = bag2.difference(bag1);
        System.out.println("BagLinkedTest bag2 Difference bag1: ");
        bagTests.displayBag();
    }
}

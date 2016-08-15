/** LQueueDriver.java
  *
  * Driver for LQueue.
  *
  * @author Jonathon Elfar 
  * @author Justin Cellona
  */
import java.util.Scanner;
import java.util.EmptyStackException;
public class LQueueDriver {
    public static void main(String[] args){
        LQueue<Integer> queue = new LQueue<Integer>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose one of the following operations:");
        System.out.println("- enqueue/add (enter the letter a)");
        System.out.println("- dequeue/delete (enter the letter d)");
        System.out.println("- check if the queue is empty (enter the letter e)");
        System.out.println("- Quit (enter the letter q)");
        System.out.println();

        String choice;
        while(!(choice = sc.next().substring(0, 1).toLowerCase()).equals("q")) {

            switch (choice.charAt(0)) {
                case 'a':
                    System.out.println("What number would you like to add?");
                    sc.nextLine();
                    if(!sc.hasNextInt()) {
                        System.out.println("Not a valid integer.");
                    } else {
                        int val = sc.nextInt();
                        queue.enqueue(new Integer(val));
                        System.out.println(val + " enqueued.");
                    }
                    sc.nextLine();
                    break;
                case 'd':
                    try {
                        Integer removedItem = queue.dequeue();
                        System.out.println(removedItem.intValue() + " removed.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    boolean isEmpty = queue.isEmpty();
                    if(isEmpty) {
                        System.out.println("Queue is empty.");
                    } else {
                        System.out.println("Queue is not empty");
                    }
                    break;
                case 'q':
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
            System.out.println();
            System.out.println("Choose an operation: ");
        }

        System.out.println();
        System.out.println("Quitting...thanks for using the program!");
        System.out.println();
        System.out.print("Contents of queue: ");
        while(!queue.isEmpty()) {
            System.out.print(queue.dequeue().intValue() + " ");
        }
        System.out.println();
    }
}

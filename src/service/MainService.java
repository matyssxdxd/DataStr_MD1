package service;

import datastr.MyDeque;
import datastr.MyQueue;
import datastr.MyStack;
import model.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainService {

    public static MyQueue<String> emergencyCalls = new MyQueue<>();
    public static MyDeque<String> searchHistory = new MyDeque<>();

    public static void main(String[] args) {

        MyStack<Integer> myStack1 = new MyStack<>();
        MyStack<Student> myStack2 = new MyStack<>();
        MyQueue<Integer> myQueue1 = new MyQueue<>();
        MyQueue<Student> myQueue2 = new MyQueue<>();
        MyDeque<Integer> myDeque1 = new MyDeque<>();
        MyDeque<Student> myDeque2 = new MyDeque<>();

        System.out.println("myStack1: ");
        try {
            myStack1.push(10);
            myStack1.push(15);
            myStack1.push(20);
            myStack1.push(30);
            myStack1.push(40);
            myStack1.print();

            myStack1.pop();
            myStack1.print();

            System.out.println(myStack1.top());

            System.out.println(myStack1.size());

            myStack1.makeEmpty();
            myStack1.print();
        } catch (Exception e) {
            System.out.println(e + "\n");
        }

        System.out.println("myStack2: ");
        try {
            myStack2.push(new Student("Rudolfs", "Sniedzins", 20));
            myStack2.push(new Student("Daniels", "Kalmars", 22));
            myStack2.push(new Student("Valdis", "Gaba", 31));
            myStack2.push(new Student("Arturs", "Laiva", 9));
            myStack2.push(new Student("Janis", "Berzins", 12));
            myStack2.print();

            myStack2.pop();
            myStack2.print();

            System.out.println(myStack2.top());

            System.out.println(myStack2.size());

            myStack2.makeEmpty();
            myStack2.print();
        } catch (Exception e) {
            System.out.println(e + "\n");
        }

        System.out.println("myQueue1: ");
        try {
            myQueue1.enqueue(1);
            myQueue1.enqueue(4);
            myQueue1.enqueue(32);
            myQueue1.enqueue(5);
            myQueue1.enqueue(211);
            myQueue1.print();

            myQueue1.dequeue();
            myQueue1.print();

            System.out.println(myQueue1.size());

            myQueue1.makeEmpty();
            myQueue1.print();
        } catch (Exception e) {
            System.out.println(e + "\n");
        }

        System.out.println("myQueue2: ");
        try {
            myQueue2.enqueue(new Student("Rudolfs", "Sniedzins", 20));
            myQueue2.enqueue(new Student("Daniels", "Kalmars", 22));
            myQueue2.enqueue(new Student("Valdis", "Gaba", 31));
            myQueue2.enqueue(new Student("Arturs", "Laiva", 9));
            myQueue2.enqueue(new Student("Janis", "Berzins", 12));
            myQueue2.print();

            myQueue2.dequeue();
            myQueue2.print();

            System.out.println(myQueue2.size());

            myQueue2.makeEmpty();
            myQueue2.print();
        } catch (Exception e) {
            System.out.println(e + "\n");
        }

        System.out.println("myDeque1: ");
        try {
            myDeque1.insertFront(1);
            myDeque1.insertFront(4);
            myDeque1.insertFront(32);
            myDeque1.insertRear(30);
            myDeque1.insertRear(40);
            myDeque1.print();

            myDeque1.removeRear();
            myDeque1.print();

            myDeque1.removeFront();
            myDeque1.print();

            myDeque1.printReverse();

            myDeque1.makeEmpty();
            myDeque1.print();

        } catch (Exception e) {
            System.out.println(e + "\n");
        }

        System.out.println("myDeque2: ");
        try {
            myDeque2.insertFront(new Student("Rudolfs", "Sniedzins", 20));
            myDeque2.insertFront(new Student("Daniels", "Kalmars", 22));
            myDeque2.insertFront(new Student("Valdis", "Gaba", 31));
            myDeque2.insertRear(new Student("Arturs", "Laiva", 9));
            myDeque2.insertRear(new Student("Janis", "Berzins", 12));
            myDeque2.print();

            myDeque2.removeRear();
            myDeque2.print();

            myDeque2.removeFront();
            myDeque2.print();

            myDeque2.printReverse();

            myDeque2.makeEmpty();
            myDeque2.print();

        } catch (Exception e) {
            System.out.println(e + "\n");
        }

        try {
            System.out.println("Student.java");
            syntaxCheck("./Student.java");
            System.out.println("UserController.java");
            syntaxCheck("./UserController.java");
            System.out.println("UserServiceImplTest.java");
            syntaxCheck("./UserServiceImplTest.java");
        } catch (Exception e) {
            System.out.println(e + "\n");
        }

        try {
            simulateEmergencyService();
        } catch (Exception e) {
            System.out.println(e + "\n");
        }

        try {
            methodsInQueue();
        } catch (Exception e) {
            System.out.println(e + "\n");
        }

        try {
            simulateSearchHistory();
        } catch (Exception e) {
            System.out.println(e + "\n");
        }

    }

    // Nevarēju izveidot tādu, kas pārbaudītu arī String un komentārus, jo tad parādījās problēmas :(
    // Student.java atrada kļūdu
    // UserController.java īsti neiedziļinājos, kur bija problēma
    // UserServiceImplTest.java pamanīju, ka bija izlaista ")" 62 rindiņā, tad nākamā bija "{", kas tika ievietota iekš
    // stack un tāpēc tika noignorēts tas, ka bija izlaista ")"
    // Ideja bija tāda, ka iekš stack ievietoju tikai atverošās iekavas un kad tiek atrasta aizverošā, tad tiek apskatīts
    // vai iekš stack ir pretējā atverošā iekava.

    public static void syntaxCheck(String file) {
        MyStack<Character> stack = new MyStack<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            boolean inString = false;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                char[] chars = line.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    if ((chars[i] == '"' || chars[i] == '\'')) {
                        inString = !inString;
                    } else if (!inString && (chars[i] == '/' && chars[i + 1] == '/')) {
                        break;
                    } else if (!inString && (chars[i] == '(' || chars[i] == '[') || chars[i] == '{') {
                        stack.push(chars[i]);
                    } else if (!inString && (chars[i] == ')' || chars[i] == ']' || chars[i] == '}')) {
                        if (stack.isEmpty()) {
                            System.out.println("Syntax error at line " + lineNumber);
                            return;
                        } else {
                            char top = stack.top();
                            if ((chars[i] == ')' && top != '(') || (chars[i] == ']' && top != '[') || (chars[i] == '}' && top != '{')) {
                                System.out.println("Syntax error at line " + chars[i] + " " + top + " "  + lineNumber);
                                return;
                            } else {
                                stack.pop();
                            }
                        }
                    }

                }
            }
            if (!stack.isEmpty()) {
                System.out.println("Syntax error at line " + lineNumber);
                stack.print();
                return;
            }
        } catch (Exception e) {
            System.out.println(e + "\n");
        }
        System.out.println("There were no errors");
    }


    public static String generatePhoneNumber() {
        Random rand = new Random();
        return String.format("+3712%07d", rand.nextInt(10000000));
    }

    public static void simulateEmergencyService() throws Exception {
        AtomicBoolean enqueueRunning = new AtomicBoolean(true);

        Thread enqueueThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    enqueueEmergencyCall(generatePhoneNumber());
                    if (i == 9) enqueueRunning.set(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread dequeueThread = new Thread(() -> {
            try {
                while (true) {
                    if (emergencyCalls.isEmpty() && !enqueueRunning.get()) {
                        break;
                    }
                    if (!emergencyCalls.isEmpty()) {
                        dequeueEmergencyCall();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        enqueueThread.start();
        dequeueThread.start();

        enqueueThread.join();
        dequeueThread.join();
    }


    public static void enqueueEmergencyCall(String phoneNumber) throws Exception {
        Random rand = new Random();
        long start = System.currentTimeMillis();

        Thread.sleep(rand.nextInt(2000));
        System.out.println("Enqueuing " + phoneNumber + " [" + (System.currentTimeMillis() - start) + "ms]");
        emergencyCalls.enqueue(phoneNumber);
    }

    public static void dequeueEmergencyCall() throws Exception {
        Random rand = new Random();
        long start = System.currentTimeMillis();

        Thread.sleep(rand.nextInt(2000));
        String removedNumber = emergencyCalls.dequeue();
        System.out.println("Dequeuing " + removedNumber + " [" + (System.currentTimeMillis() - start) + "ms]");
    }

    public static void test_1() {
        System.out.println("Test method 1");
    }

    public static void test_2() {
        System.out.println("Test method 2");
    }

    public static void test_3() {
        System.out.println("Test method 3");
    }

    public static void methodsInQueue() throws Exception {
        MyQueue<String> methodsQueue = new MyQueue<>();
        methodsQueue.enqueue("test_1");
        methodsQueue.enqueue("test_2");
        methodsQueue.enqueue("test_3");

        while (!methodsQueue.isEmpty()) {
            String currentMethodName = methodsQueue.dequeue();
            Method currentMethod = MainService.class.getDeclaredMethod(currentMethodName);
            currentMethod.invoke(null);
            Thread.sleep(1000);
        }

    }

    public static void simulateSearchHistory() throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a URL");
            System.out.println("(0 - quit, 1 - delete last entry, 2 - view search history)");

            String input = scanner.nextLine();

            if (input.equals("0")) {
                System.out.println("Exiting search history simulation");
                break;
            } else if (input.equals("1")) {
                System.out.println("Deleting last entry - " + searchHistory.removeRear());
            } else if (input.equals("2")) {
                if (searchHistory.isEmpty()) {
                    System.out.println("Search history is empty");
                } else {
                    searchHistory.print();
                }
            } else {
                // RegEx ņemts no https://regexr.com/39nr7
                if (input.matches("[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")) {
                    if (searchHistory.size() == 10) searchHistory.removeRear();
                    searchHistory.insertFront(input);
                } else {
                    System.out.println("Input is not a valid URL");
                }
            }
        }

        scanner.close();
    }

}
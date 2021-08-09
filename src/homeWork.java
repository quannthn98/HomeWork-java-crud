import java.util.Scanner;

public class homeWork {

    public static String[] list;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean flag = true;
        list = inputListElement(list);
        while (flag) {
            showList();
            nextActinies();
            showActivitiesMenu();
        }
    }

    public static String[] inputListElement(String[] arr){
        System.out.println("Input list Size");
        int size = sc.nextInt();
        while(size < 0 ){
            System.out.println("please input valid size (size>0)");
            sc.nextInt();
        }
        arr = new String[size];
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Input elment at " + i + " position");
            arr[i] = sc.next();
        }
        System.out.println("Input");
        return arr;
    }

    public static void showList() {
        System.out.println("-----------------------------");
        System.out.println("Here are the product list");
        System.out.println("-----------------------------");
        System.out.println("Id Name");
        for (int i = 0; i < list.length; i++) {
            System.out.println(i + ". " + list[i]);
        }
    }

    public static void showActivitiesMenu() {
        System.out.println("-----------------------------");
        System.out.println("You Activities Menu");
        System.out.println("-----------------------------");
        System.out.println("1. Add new product");
        System.out.println("2. Remove a product");
        System.out.println("3. Find product");
        System.out.println("4. Edit product name");
        System.out.println("-----------------------------");
        System.out.println("Pick you options");
        int options = sc.nextInt();
        while (options > 4 || options < 0) {
            System.out.println("Please pick valid options from 1-4");
            options = sc.nextInt();
        }
        operating(options);

    }

    public static void operating(int option) {
        switch (option) {
            case 1:
                System.out.println("Input your new product name");
                String productName = sc.next();

                list = addNewProduct(productName);
                break;

            case 2:
                System.out.println("Input productId");
                int productId = sc.nextInt();

                while (productId < 0 || productId > list.length - 1) {
                    System.out.println("can not find product with the same ID, please input again");
                    productId = sc.nextInt();
                }

                list = removeProduct(productId);
                break;

            case 3:
                System.out.println("Input your wanted product");
                String targetProduct = sc.next();

                System.out.println(findProduct(targetProduct));
                break;

            case 4:
                System.out.println("Input product Id");
                int index = sc.nextInt();

                while (index < 0 || index > list.length - 1) {
                    System.out.println("productId is out of range, please input again");
                    index = sc.nextInt();
                }

                System.out.println("Input product name");
                String newName = sc.next();

                list = editProduct(index, newName, list);
                break;
        }
    }

    public static String[] addNewProduct(String productName) {
        String[] temp = list;

        if (confirm()) {
            temp = new String[list.length + 1];
            temp[temp.length - 1] = productName;
            System.arraycopy(list, 0, temp, 0, list.length);
            System.out.println("Add new product successfully");
        } else {
            System.out.println("Add new product canceled");
        }
        return temp;
    }

    public static String[] removeProduct(int productId) {
//        System.out.println("Input productId");
//        int productId = sc.nextInt();

//        while (productId < 0 || productId > list.length - 1) {
//            System.out.println("can not find product with the same ID");
//            productId = sc.nextInt();
//        }

        String[] temp = new String[list.length - 1];
        for (int i = 0; i < temp.length; i++) {
            if (i < productId) {
                temp[i] = list[i];
            } else {
                temp[i] = list[i + 1];
            }
        }

        if (confirm()) {
            list = new String[temp.length];
            System.arraycopy(temp, 0, list, 0, temp.length);
        }

        return list;
    }

    public static String findProduct(String targetProduct) {
//        System.out.println("Input your wanted product");
//
//        String targetProduct = sc.next();
        String display = "";
        int index = -1;
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(targetProduct)) {

                index = i;
                break;
            }
        }
        if (index != -1) {
            display = "We found your product in the list at index: " + index;
        } else {
            display = "we can not foudn your product in the list";
        }

        return display;
    }

    public static String[] editProduct(int index, String newName, String[] arr) {
        arr = list;
        if (confirm()) {
            arr[index] = newName;
        }
        return arr;
    }

    public static boolean confirm() {
        System.out.println("-----------------------------");
        System.out.println("Do you want to continue? (press any key to continue or press N/n to deny)");

        boolean isContinue;
        sc.nextLine();

        char confirm = sc.next().charAt(0);
        switch (confirm) {
            default:
                isContinue = true;
                break;
            case 'n':
            case 'N':
                isContinue = false;
                break;
        }
        return isContinue;
    }

    public static boolean nextActinies() {
        System.out.println("-----------------------------");
        System.out.println("Actions");
        System.out.println("1. Show activities menu");
        System.out.println("0. Exit program");
        boolean isContinue = false;
        int confirm = sc.nextInt();
        while (confirm > 1 || confirm < 0) {
            System.out.println("please input valid options");
            confirm = sc.nextInt();
        }
        switch (confirm) {
            case 1:
                isContinue = true;
                break;
            case 0:
                System.exit(0);
                break;
        }
        return isContinue;

    }
}

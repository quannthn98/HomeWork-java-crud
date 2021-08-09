import java.util.Scanner;

public class AddPrice {

    public static String[] list;
    public static int[] price;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean flag = true;
        inputListElement();
        while (flag) {
            showList();
            nextActinies();
            showActivitiesMenu();
        }
    }

    public static void inputListElement(){
        System.out.println("Input list Size");
        int size = sc.nextInt();
        while(size < 0 ){
            System.out.println("please input valid size (size>0)");
            sc.nextInt();
        }

        list = new String[size];
        price = new int[size];

        for (int i = 0; i < size; i++) {
            System.out.println("Input elment at " + i + " position");
            list[i] = sc.next();
            System.out.println("input price of " + list[i] + " (in $)");
            price[i] = sc.nextInt();
        }


    }

    public static void showList() {
        System.out.println("-----------------------------");
        System.out.println("Here are the product list");
        System.out.println("-----------------------------");
        System.out.println("Id Name    Price($)");
        for (int i = 0; i < list.length; i++) {
            System.out.println(i + ". " + list[i] + "    $" + price[i]);
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
                System.out.println("Input price");
                int price = sc.nextInt();

                list = addNewProduct(productName, price);
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

    public static String[] addNewProduct(String productName, int productPrice) {
        String[] temp = list;
        int[] temp2;

        if (confirm()) {
            temp = new String[list.length + 1];
            temp[temp.length - 1] = productName;
            System.arraycopy(list, 0, temp, 0, list.length);

            temp2 = new int[price.length + 1];
            price[temp.length - 1] = productPrice;
            System.arraycopy(price, 0 , temp2, 0 , price.length);

            System.out.println("Add new product successfully");
        } else {
            System.out.println("Add new product canceled");
        }
        return temp;
    }

    public static String[] removeProduct(int productId) {

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

        String display;
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
            System.out.println("Please input valid options (only 1 or 0) ");
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

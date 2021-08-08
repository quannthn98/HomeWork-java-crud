public class test {
    public static void main(String[] args) {
        int[] list = new int[5];
        for (int a: list){
            System.out.print(a);
        }

        list = new int[6];
        System.out.println("new array");
        for (int a: list){
            System.out.println(a);
        }
    }
}

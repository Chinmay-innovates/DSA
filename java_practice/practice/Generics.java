package java_practice.practice;

public class Generics {
    public class Box<T> {
        T item;

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }
    }

    public class Product<T, U> {
        T item;
        U price;

        Product(T item, U price) {
            this.item = item;
            this.price = price;
        }

        public T getItem() {
            return item;
        }

        public U getPrice() {
            return price;
        }

        public void setItem(T item) {
            this.item = item;
        }

        public void setPrice(U price) {
            this.price = price;
        }

        public static void main(String[] args) {

        }
    }



    public static void main(String[] args) {
        Generics generics = new Generics();
        Box<String> box = generics.new Box<>();
        Product<String, Integer> product = generics.new Product<>("Laptop", 1000);


        box.setItem("hello");
        System.out.println(box.getItem());

        System.out.println(product.getItem());
        System.out.println(product.getPrice());
    }
}



import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 
 */
class numGen implements Runnable{


    private int numOfFib;
    private ArrayList<Long> fibs = new ArrayList();

    public numGen(int numOfFib){
        if(numOfFib < 0){
            throw new IllegalArgumentException();
        }
        this.numOfFib = numOfFib;
    }

//    public static int fibGen(int num){
//        if(num <= 2) return 1;
//        else{
//            return fibGen(num-1) + fibGen(num-2);
//        }
//    }

    public static Hashtable<Integer, Long> fibList = new Hashtable<>();
    public static long fibGen(int num){
        if(fibList.containsKey(num)) return fibList.get(num);
        if(num <= 2) return 1;
        else{
            long value= fibGen(num-1) + fibGen(num-2);
            fibList.put(num,value);
            return value;
        }
    }

    @Override
    public void run() {
        for (int i = 1; i < numOfFib + 1; i++) {
            fibs.add(fibGen(i));
        }
        String retval = "";
        for (int i = 0; i < fibs.size(); i++) {
            retval += fibs.get(i) + ", ";
        }
        System.out.println(retval);
    }
}

public class Fibonacci {
    public static void main(String[] args) {
        Thread fibgenerator = new Thread(new numGen(100));
        fibgenerator.start();
    }
}

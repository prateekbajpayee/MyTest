import java.util.ArrayList;
import java.util.List;

public class CupCakes {

    public static void main(String[] args) {
        List<String> trips = new ArrayList<String>();
        trips.add("8 4 2");
        for(String trip : trips){
            String[] arr = trip.split(" ");
            int budget =Integer.parseInt(arr[0]);//8
            int cost = Integer.parseInt(arr[1]);//4
            int promoWraps = Integer.parseInt(arr[2]);//2

            int intialCakes =budget/cost; //2
            int wraps = intialCakes;//2
            int totalCakes = intialCakes;//2
            int leftWraps =0;//0
            while((wraps+leftWraps) >= promoWraps){
                int newCakes = (leftWraps + wraps)/promoWraps;//1
                totalCakes += newCakes;//3
                if(wraps >= promoWraps){
                    leftWraps = wraps % promoWraps;// 0
                }else{
                    leftWraps =0;
                }
                wraps = wraps/promoWraps;//1
            }
            System.out.println(totalCakes);
        }
    }
}

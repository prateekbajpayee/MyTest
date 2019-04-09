import java.util.*;
import java.lang.*;
import java.io.*;

//Find number of swaps in string -- Input downloaded
//Min Swaps required in an array -- input downloaded
class LotteryCoupons
{
    public static int lotteryCoupons(int n) {
        int arr[] = new int[n];
        int output[] = new int[10];
        Arrays.fill(arr,0);
        for(int i =1;i<=n;i++){
            int sum = 0;
            int j=i;
            if(j<=9){
                arr[i-1] = j;
            }else {
                while (j > 9) {
                    sum = 0;
                    while (j > 0) {
                        int rem;
                        rem = j % 10;
                        sum = sum + rem;
                        j = j / 10;
                    }
                    j = sum;
                }
                arr[i-1]=sum;
            }

        }

        for(int k =0;k<=n-1;k++){
            output[arr[k]]++;
        }
        int max =0;
        for (int l = 0; l <= 9; l++) {
            if(output[l]>max){
                max = output[l];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        lotteryCoupons(22);
    }
}
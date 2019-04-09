public class Quicksort {
    public static void main(String[] args) {
        int arr[] = {10, 80, 30, 90, 40, 50, 70};
        //quickSort(arr,0,arr.length-1);
        int n = nthLargest(arr,0,arr.length-1,2);
        System.out.println(n);
        //printArr(arr);
    }

    private static int partitionPivotHigh(int arr[],int low, int high){
        int pivot = arr[high];
        int lowestElem = low-1;//intialized to -1 because we don't know the smallest
        for(int i = low;i<high;i++){//Iterate from our pivot to the last elem
            if(arr[i]<=pivot){
                lowestElem++;//Increment lowest because new elem will be lowest now
                //Swap
                int temp = arr[i];
                arr[i] = arr[lowestElem];
                arr[lowestElem] =temp;
            }
        }
        //now Pivot need to come after lowest elem for it correct position, so swap with lowest+1
        int temp = arr[high];
        arr[high] = arr[lowestElem+1];
        arr[lowestElem + 1] =temp;

        return lowestElem + 1;//return current position of pivot
    }

    private static int partitionPivotLow(int arr[],int low, int high){
        int pivot = arr[low];
        int highestElem = high+1;//intialized to high+1 because we dont know the highest
        for(int i = high;i>=low;i--){//Iterate from our pivot to the last elem
            if(arr[i]>=pivot){
                highestElem--;//decrement lowest because new elem will be lowest now
                //Swap
                int temp = arr[i];
                arr[i] = arr[highestElem];
                arr[highestElem] =temp;
            }
        }
        //now Pivot need to come after lowest elem for it correct position, so swap with lowest+1
        int temp = arr[low];
        arr[low] = arr[highestElem-1];
        arr[highestElem - 1] =temp;

        return highestElem - 1;//return current position of pivot
    }

    private static void quickSort(int arr[],int low,int high){
        if(low < high) {
            int partition = partitionPivotHigh(arr, low, high);
            //int partition = partitionPivotLow(arr, low, high);
            quickSort(arr, low, partition - 1);
            quickSort(arr, partition + 1, high);
            printArr(arr);
        }
    }

    private static int nthLargest(int arr[],int low,int high,int n){
        if(low < high) {
            int partition = partitionPivotHigh(arr, low, high);
            //int partition = partitionPivotLow(arr, low, high);
            if(partition == n-1){
                return arr[n-1];
            }
            quickSort(arr, low, partition - 1);
            quickSort(arr, partition + 1, high);
            printArr(arr);
        }
        return 0;
    }

    private static void printArr(int arr[]){
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }

}

/**
 * Created by Steven on 2016/11/3.
 */
import java.lang.Math;
public class QuickSort {
    private final int ARR_SIZE = 100;
    private int arr[] = new int[ARR_SIZE];
    public QuickSort(){
        for(int i = 0;i<ARR_SIZE;i++){
            arr[i] = (int)(Math.random() * 100);
            System.out.print(arr[i] + " ");
        }
        quickSort(arr,0, ARR_SIZE -1);
    }

    public void quickSort(int arr[], int s, int e){
        int start = s;
        int end = e;
        if(end - start <2){
            return;
        }
        int _start = s;
        int _end = e;
        boolean forward = false;
        if(arr[start] > arr[end]){
            swap(start,end);
            end--;
        }
        int standard = arr[start];
        while(start != end){
            if(!forward){
                if(arr[end] < standard) {
                    swap(start, end);
                    start++;
                    forward = true;
                } else{
                    end--;
                }
            }else{
                if(arr[start] > standard) {
                    swap(start, end);
                    end--;
                    forward = false;
                } else{
                    start++;
                }
            }
        }
        quickSort(arr, _start,start);
        quickSort(arr, end+1,_end);
    }

    public void swap(int a, int b){
        int tmp = 0;
        tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
    public void printArr(){
        System.out.println();
        for(int i = 0;i < ARR_SIZE; i++){
            System.out.print(arr[i] + " ");
        }
    }
}

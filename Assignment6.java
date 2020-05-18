import java.util.*;
import java.io.*;

class Main {

    public static int bSortComparisons,bSortChanges,qSortComparisons,qSortChanges,hSortComparisons,hSortChanges;

    static Integer[] randomNumbers(int num, String type){

        ArrayList<Integer> x = new ArrayList<Integer>();
        Integer[] arr = new Integer[num];
        Random rand = new Random();
        int y;

        while(x.size() < num){
            y = rand.nextInt(num) + 1;
            if(x.contains(y)){
                continue;
            }else{
                x.add(y);
            }
        }

        if(type.equals("almost")){

            Collections.sort(x);

            if(x.size() < 50){

                int a = x.get(2);
                int a1 = x.get(7);

                x.set(a-1, a1);
                x.set(a1 -1 , a);
                
                System.out.println(x);
                arr = x.toArray(arr);
                return arr;

            }else{

                int a = x.get(4);
                int a1 = x.get(10);
                int b = x.get(12);
                int b1 = x.get(22);
                int c = x.get(1);
                int c1 = x.get(40);

                x.set(a-1, a1);
                x.set(a1-1, a);
                x.set(b-1, b1);
                x.set(b1-1, b);
                x.set(c-1, c1);
                x.set(c1-1, c);

                System.out.println(x);
                arr = x.toArray(arr);
                return arr;
            }

        }else if(type.equals("random")){

            System.out.println(x);
            arr = x.toArray(arr);
            return arr;

        }else if(type.equals("reverse")){

            Collections.sort(x);

            ArrayList<Integer> reverse = new ArrayList<Integer>();

            for(int i = x.size() - 1; i >= 0 ; i--){
                reverse.add(x.get(i));
            }

            System.out.println(reverse);

            arr = reverse.toArray(arr);
            return arr;
        }

        return arr;

    }

    static void bubbleSort(Integer arr[]) { 

        Integer[] x = arr.clone();
        
        int n = arr.length; 
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++){
                bSortComparisons++;
                if (x[j] > x[j+1]) 
                { 
                    // swap arr[j+1] and arr[i] 
                    int temp = x[j]; 
                    x[j] = x[j+1]; 
                    x[j+1] = temp; 
                    bSortChanges++;
                } 
            }         
        }
        
        System.out.print("Bubble Sort / ");
        System.out.println("Comparisons - " + bSortComparisons + " Interchanges - " + bSortChanges);
       
    } 

    static void printArray(Integer arr[]){
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
    }

    public static void read(int num, String type){

        Integer[] arr,qArr,hArr;

        QuickSort qSort = new QuickSort();
        HeapSort hSort = new HeapSort();
        
        arr = randomNumbers(num, type);
        qArr = arr.clone();
        hArr = arr.clone();

        bubbleSort(arr);
        qSort.sort(qArr, 0, arr.length - 1);
        System.out.println("Quick Sort / Comparisons - " + qSortComparisons + " Interchanges - " + qSortChanges);
        hSort.sort(hArr);
        
    }

    static void comparsion(){
    
        ArrayList<Integer> sortedComparisons = new ArrayList<Integer>();
        ArrayList<Integer> sortedChanges = new ArrayList<Integer>();
        sortedComparisons.add(bSortComparisons);
        sortedComparisons.add(qSortComparisons);
        sortedComparisons.add(hSortComparisons);
        sortedChanges.add(bSortChanges);
        sortedChanges.add(qSortChanges);
        sortedChanges.add(hSortChanges);
        Collections.sort(sortedComparisons);
        Collections.sort(sortedChanges);

        if(sortedComparisons.get(2) == bSortComparisons){
            System.out.print("Bubble sort had most comparisons");
        }else if(sortedComparisons.get(2) == qSortComparisons){
            System.out.print("Quick Sort had the most comparisons");
        }else{
            System.out.print("Heap Sort had the most comparisons");
        }

        if(sortedComparisons.get(1) == bSortComparisons){
            System.out.print(" / Bubble sort was in the middle for comparisons");
        }else if(sortedComparisons.get(1) == qSortComparisons){
            System.out.print(" / Quick Sort was in the middle for comparisons");
        }else{
            System.out.print(" / Heap Sort was in the middle for comparisons");
        }

        if(sortedComparisons.get(0) == bSortComparisons){
            System.out.print(" / Bubble sort had the least comparisons");
        }else if(sortedComparisons.get(0) == qSortComparisons){
            System.out.print(" / Quick Sort had the least comparisons");
        }else{
            System.out.print(" / Heap Sort had the least comparisons");
        }

        System.out.println(" ");

        if(sortedChanges.get(2) == bSortChanges){
            System.out.print("Bubble Sort had most interchanges");
        }else if(sortedChanges.get(2) == qSortChanges){
            System.out.print("Quick Sort had most interchanges");
        }else{
            System.out.print("Heap Sort had the most interchanges");
        }

        if(sortedChanges.get(1) == bSortChanges){
            System.out.print(" / Bubble Sort was in the middle for interchanges");
        }else if(sortedChanges.get(1) == qSortChanges){
            System.out.print(" / Quick Sort was in the middle for interchanges");
        }else{
            System.out.print(" / Heap Sort was in the middle for interchanges");
        }

        if(sortedChanges.get(0) == bSortChanges){
            System.out.print(" / Bubble Sort had the least interchanges");
        }else if(sortedChanges.get(0) == qSortChanges){
            System.out.print(" / Quick Sort had the least interchanges");
        }else{
            System.out.print(" / Heap Sort had the least interchanges");
        }

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        File dataFile = new File("Data.txt");
        Scanner dataReader = new Scanner(new FileReader(dataFile));

        while(dataReader.hasNextLine()){
            String[] data = dataReader.nextLine().split(" ");
            System.out.println(" ");
            System.out.println("-----------------------");
            System.out.println(data[0] + " numbers in " + data[3] + " sorted order");
            read(Integer.parseInt(data[0]),data[3]);
            comparsion();
        }

        dataReader.close();
    }
    

static class QuickSort { 
    /* This function takes last element as pivot, 
       places the pivot element at its correct 
       position in sorted array, and places all 
       smaller (smaller than pivot) to left of 
       pivot and all greater elements to right 
       of pivot */
    int partition(Integer arr[], int low, int high) 
    { 
        int pivot = arr[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            qSortComparisons++;
            // If current element is smaller than the pivot 
            if (arr[j] < pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j]
                qSortChanges++;
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot)
        qSortChanges++; 
        int temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
  
        return i+1; 
    } 
    /* The main function that implements QuickSort() 
      arr[] --> Array to be sorted, 
      low  --> Starting index, 
      high  --> Ending index */
    void sort(Integer arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(arr, low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            sort(arr, low, pi-1); 
            sort(arr, pi+1, high); 
        } 
    }     
} 
static class HeapSort 
{ 
    public void sort(Integer arr[]) 
    { 
        int n = arr.length; 
  
        // Build heap (rearrange array) 
        for (int i = n / 2 - 1; i >= 0; i--){
            hSortComparisons++;
            heapify(arr, n, i); 
        } 
            
  
        // One by one extract an element from heap 
        for (int i=n-1; i>0; i--) 
        { 
            hSortComparisons++;
            // Move current root to end 
            int temp = arr[0]; 
            arr[0] = arr[i]; 
            arr[i] = temp; 
  
            // call max heapify on the reduced heap 
            heapify(arr, i, 0); 
        } 

        System.out.println("HeapSort / Comparisons - " + hSortComparisons + " Interchanges - " + hSortChanges);
    } 
  
    // To heapify a subtree rooted with node i which is 
    // an index in arr[]. n is size of heap 
    void heapify(Integer arr[], int n, int i) 
    { 
        int largest = i; // Initialize largest as root 
        int l = 2*i + 1; // left = 2*i + 1 
        int r = 2*i + 2; // right = 2*i + 2 
  
        // If left child is larger than root 
        if (l < n && arr[l] > arr[largest]){
            hSortComparisons++;
            largest = l; 
        } 
            
  
        // If right child is larger than largest so far 
        if (r < n && arr[r] > arr[largest]){
            hSortComparisons++;
            largest = r; 
        } 
           
  
        // If largest is not root 
        if (largest != i) 
        { 
            hSortComparisons++;
            hSortChanges++;
            int swap = arr[i]; 
            arr[i] = arr[largest]; 
            arr[largest] = swap; 
  
            // Recursively heapify the affected sub-tree 
            heapify(arr, n, largest); 
        } 
    } 
}

}

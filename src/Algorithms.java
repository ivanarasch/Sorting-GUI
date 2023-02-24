import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Algorithms {

    public static <T extends SortableRect> TranslateTransition createTranslation(ArrayList<T> list, int fromIndex, int toIndex, long duration) {
        TranslateTransition t = new TranslateTransition(Duration.millis(duration), list.get(fromIndex));
        t.setFromX(list.get(fromIndex).getLastX());
        t.setByX((toIndex - fromIndex) * 8);
        list.get(fromIndex).setLastX((toIndex - fromIndex) * 8);
        return t;
    }

    public static <T extends SortableRect> void reverse(ArrayList<T> list) {
        long millis = 1000;
        SequentialTransition sequentialTransition = new SequentialTransition();

        int i = 0, j = list.size() - 1;
        while (i < j) {
            TranslateTransition t1 = createTranslation(list, i, j, millis);
            TranslateTransition t2 = createTranslation(list, j, i, millis);
            sequentialTransition.getChildren().addAll(t1, t2);

            Collections.swap(list, i, j); //this statement only swaps them in the actual data structure.
            ++i;
            --j;
        }
        sequentialTransition.play();
    }

    public static <T extends SortableRect> void fisherYates(ArrayList<T> list) {
        long millis = 10;
        Random rand = new Random();
        SequentialTransition sequentialTransition = new SequentialTransition();
        for (int i = 0; i < list.size(); i++) {
            int j = rand.nextInt(list.size());

            //-------- This code block visibly swaps the rectangles at i and j

            ParallelTransition p = new ParallelTransition();

            TranslateTransition t1 = createTranslation(list, i, j, millis);
            TranslateTransition t2 = createTranslation(list, j, i, millis);
            p.getChildren().addAll(t1, t2);
            sequentialTransition.getChildren().add(p);

            //------------------------------------------------------------------

            Collections.swap(list, i, j); //this statement only swaps them in the actual data structure.
        }
        sequentialTransition.play();
    }

    public static <T extends SortableRect> void selectionSort(ArrayList<T> list)
    {
        long millis = 500;
        SequentialTransition sequentialTransition = new SequentialTransition();
        for (int i = 0; i < list.size();++i )
        {
            int currentMin = list.get(i).getValue();
            int currentMinIndex = i;
            for (int j = i + 1; j < list.size();++j)
            {
                if (list.get(currentMinIndex).getValue() > list.get(j).getValue())
                {
                    currentMinIndex = j;
                }
            }
                    ParallelTransition p = new ParallelTransition();

                    TranslateTransition t1 = createTranslation(list, i, currentMinIndex, millis);
                    TranslateTransition t2 = createTranslation(list, currentMinIndex, i, millis);
                    p.getChildren().addAll(t1, t2);
                    sequentialTransition.getChildren().add(p);

                    //------------------------------------------------------------------

                    Collections.swap(list, i, currentMinIndex);



            }

        sequentialTransition.play();
        System.out.println("done");
    }

    public static <T extends SortableRect> int partition (ArrayList<T> list, int firstIndex, int lastIndex,SequentialTransition sequentialTransition, long millis)
    {
        System.out.println("partitioning");


        int low = firstIndex;
        int high = lastIndex;
        int midpoint = firstIndex +(lastIndex-firstIndex)/2;
        int pivot = list.get(midpoint).getValue();

        boolean done = false;
        while (!done)
        {
            while (list.get(low).getValue()<= pivot)
            {
                ++low;
            }

            while (list.get(high).getValue()>pivot)
            {
                --high;

            }

            if(high<=low)
            {
              done = true;
            }
            else
            {
                ParallelTransition p = new ParallelTransition();

                TranslateTransition t1 = createTranslation(list, low, high, millis);
                TranslateTransition t2 = createTranslation(list, high, low, millis);
                p.getChildren().addAll(t1, t2);
                sequentialTransition.getChildren().add(p);

                //------------------------------------------------------------------

                Collections.swap(list, low, high);
                ++low;
                --high;

            }
        }

        return high;
    }

    public static <T extends SortableRect> void quickSort(ArrayList<T> list)
    {
        System.out.println("here");
        long millis= 250;
        SequentialTransition sequentialTransition = new SequentialTransition();
       quickSort(list, 0, list.size()-1,sequentialTransition,millis);
       sequentialTransition.play();

    }

    public static <T extends SortableRect> void quickSort(ArrayList <T> list, int firstIndex, int lastIndex, SequentialTransition sequentialTransition, long millis)
    {
        if(firstIndex>=lastIndex)
        {
            return;
        }
            int pivotIndex = partition(list, firstIndex, lastIndex, sequentialTransition, millis);
            quickSort(list, firstIndex, pivotIndex, sequentialTransition, millis);
            quickSort(list, pivotIndex + 1, lastIndex, sequentialTransition, millis);




    }

    public static <T extends SortableRect> void mergeSort(ArrayList<T> list, int firstIndex, int lastIndex)
    {


    }


}

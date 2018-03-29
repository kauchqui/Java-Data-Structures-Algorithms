package sort.distribution;

import java.util.Comparator;
import java.util.List;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class MSDRadixSort extends RadixSort {
    public MSDRadixSort() {
        this(Comparator.naturalOrder());
    }

    public MSDRadixSort(Comparator<Integer> comparator) {
        super(comparator);
    }

    @Override
    public void sort(Integer[] array, int beginIndex, int endIndex) {
        int maxBit = getMaxBit(array, beginIndex, endIndex);

        // for (int bit=maxBit-1; bit>=0; bit--)
        sort(array, beginIndex, endIndex, maxBit-1); //todo change back
    }

    private void sort(Integer[] array, int beginIndex, int endIndex, int bit) {
        /*if(bit == 0 ){
            int div = (int)Math.pow(10, bit);
            int idx;
            for(int x = 0; x <10; x++){
            for (int i=beginIndex; i<endIndex; i++)
                if(x == 0){buckets.get(0).add(array[i]);}
                else
                buckets.get(getBucketIndex(x,div)).add(array[i]);

            for (List<Integer> bucket : buckets)
            {
                idx = beginIndex = beginIndex + bucket.size();
                while (bucket.size() > 0) array[--idx] = bucket.remove(bucket.size()-1);
            }
        }}*/
/*        if (bit < 0 ){
            return;
        }
*/
        int div = (int) Math.pow(10, bit);
        int idx;
        for (int i = beginIndex; i < endIndex; i++)
            buckets.get(getBucketIndex(array[i], div)).add(array[i]);
        int recursiveBit = bit - 1;
        if (recursiveBit < 0) {
            return;
        }
        for (List<Integer> bucket : buckets) {
            idx = beginIndex = beginIndex + bucket.size();
            int endIDX = idx;
            //int newBeginnerIndex = idx;
            for(int newbucketIndex = 0; newbucketIndex <=9; newbucketIndex++){
            while (bucket.size() > 0 && bit > 0) {

                array[--idx] = bucket.remove(bucket.size() - 1);

            }
            depthBucketSort(array, idx/*newBeginnerIndex*/, endIDX - 1, recursiveBit,newbucketIndex );
        }}

    }

    private void depthBucketSort(Integer[] array, int beginIndex, int endIndex, int bit, int bucketIndex) {
        if (bit < 0) {
            return;
        } else {

            int div2 = (int) Math.pow(10, bit);
            int newArrayIndex;
            int zeroPushes = 0,onePushes = 0,twoPushes = 0,threePushes = 0,fourPushes = 0,fivePushes=0,sixPushes=0,sevenPushes=0,eightPushes=0,ninePushes=0;
            for (int i = beginIndex; i <=endIndex; i++){
                int pushNumber = getBucketIndex(array[i],div2);
                if(pushNumber == 0){
                    zeroPushes++;
                }else if(pushNumber == 1){
                    onePushes++;
                }
                else if(pushNumber == 2){
                    twoPushes++;
                }
                else if (pushNumber == 3){
                    threePushes++;
                }
                else if(pushNumber == 4){
                    fourPushes++;
                }else if(pushNumber == 5){
                    fivePushes++;
                }
                else if(pushNumber == 6){
                    sixPushes++;
                }
                else if (pushNumber == 7){
                    sevenPushes++;
                }
                else if(pushNumber == 8){
                    eightPushes++;
                }
                else if (pushNumber == 9){
                    ninePushes++;
                }

                buckets.get(getBucketIndex(array[i], div2)).add(array[i]);
            }
            int addArray[] = new int[10];
            addArray[0] = zeroPushes;
            addArray[1] = onePushes;
            addArray[2] = twoPushes;
            addArray[3] = threePushes;
            addArray[4] = fourPushes;
            addArray[5] = fivePushes;
            addArray[6] = sixPushes;
            addArray[7] = sevenPushes;
            addArray[8] = eightPushes;
            addArray[9] = ninePushes;
            int idx;
            int counterPop= 0;
            for (List<Integer> bucket : buckets) {
                int popSize = addArray[counterPop];
                counterPop++;
                idx = beginIndex = beginIndex + popSize;
                int endIDX = idx;
                //int newBeginnerIndex = idx;
                while (popSize > 0 && bit >= 0) {

                    array[--idx] = bucket.remove(bucket.size()-1);
                    popSize--;
                }
                //depthBucketSort(array, idx/*newBeginnerIndex*/, endIDX - 1, bit-1, bucketIndex);
            }


        }
    }
}
package com.example.springbootsse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

    public static final int COUNT_CONDITION = 10000;
    private static final int CHUNK_SIZE = 1000;

    public static List<Integer> input = IntStream.range(1,1001)
            .boxed()
            .collect(Collectors.toList());


    public static void main(String[] args) {

        List<List<Integer>> chunkedList = new ArrayList<>();

        List<Integer> chunk=new ArrayList<>();

        int size = input.size();
        int endPtr = size-1;

        while(endPtr > 0){
            //System.out.println(input);
            int[] frontResult = new int[2],endResult = new int[2];

            if(chunk.size() != CHUNK_SIZE){
                frontResult = addDataToChunk(chunk, endPtr, 0);
                endPtr = frontResult[0];
            }

            if(chunk.size() != CHUNK_SIZE){
                endResult = addDataToChunk(chunk, endPtr, endPtr);
                endPtr = endResult[0];
            }

            if(chunk.size() == CHUNK_SIZE || (frontResult[1] == 1 && endResult[1] == 1)){
                chunkedList.add(chunk);
                chunk = new ArrayList<>();
            }

        }
        if(!input.isEmpty())
            chunk.add(input.get(0));
        chunkedList.add(chunk);

        chunkedList.stream().forEach(v -> System.out.println(v.size()+" "
                +v.stream().collect(Collectors.summingInt(Integer::intValue))));

        System.out.println(chunkedList.stream().flatMap(List::stream)
                .count());
    }

    private static int[] addDataToChunk(List<Integer> chunk, int endPtr, int i) {
        int[] result = new int[2];

        int data = input.get(i);
        int chunkDataSum = chunk.stream().collect(Collectors.summingInt(Integer::intValue));

        if((data+chunkDataSum < COUNT_CONDITION)) {
            chunk.add(data);
            input.remove(i);

            endPtr -= 1;
        }else if(data > COUNT_CONDITION){
            chunk.add(data);
            input.remove(i);
            endPtr -= 1;
            result[1] = 1;
        }else{
            result[1] = 1;
        }

        result[0] = endPtr;

        return result;
    }
}

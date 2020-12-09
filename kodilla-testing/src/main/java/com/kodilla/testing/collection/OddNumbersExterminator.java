package com.kodilla.testing.collection;

import java.util.ArrayList;
import java.util.List;

public class OddNumbersExterminator {

    public List<Integer> exterminate(List<Integer> number) {

        List<Integer> resultList = new ArrayList<>();

        for (Integer integer : number) {
            if (integer % 2 == 0) {
                resultList.add(integer);
            }
        }
        return resultList;
    }
}

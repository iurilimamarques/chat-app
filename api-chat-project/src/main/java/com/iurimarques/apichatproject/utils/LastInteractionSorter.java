package com.iurimarques.apichatproject.utils;

import java.util.Comparator;

import com.iurimarques.apichatproject.data.vo.DestinationUserVO;

public class LastInteractionSorter implements Comparator<DestinationUserVO>{

    @Override
    public int compare(DestinationUserVO o1, DestinationUserVO o2) {
        return o2.getLastInteraction().compareTo(o1.getLastInteraction());
    }

}

package com.iurimarques.apichatproject.utils;

import java.util.Comparator;

import com.iurimarques.apichatproject.data.vo.MessageVO;

public class MessageSorter implements Comparator<MessageVO> {
    
    @Override
    public int compare(MessageVO o1, MessageVO o2) {
        return o2.getCreatedAt().compareTo(o1.getCreatedAt());
    }
}

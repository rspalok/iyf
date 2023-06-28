package net.user.config;

import java.util.Collections;
import java.util.*;

public class FindConflictingMeetingsProblem {
    // static function to find conflicts
   public static int findNumberOfConflicts(List<Meeting> meetings){

       // sort the meetings in ascending order of start time
       Collections.sort(meetings,new Comparator<Meeting>(){
           @Override
           public int compare(Meeting o1, Meeting o2) {
               return o1.compareTo(o2);
           }
       });

       int conflicts = 0;
       // count the conflicts
       for(int i = 0 ; i < meetings.size();i++){
           if(i > 1 && meetings.get(i-1).endTime > meetings.get(i).startTime){
               conflicts++;
           }
       }
       return conflicts;
   }
}
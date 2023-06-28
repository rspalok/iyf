package net.user.config;

class Meeting implements Comparable<Meeting> {
    int startTime;
    int endTime;
    public Meeting(int startTime, int endTime) {
        super();
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public int compareTo(Meeting o) {
        if(this.startTime == o.startTime) return 0;
        if(this.startTime < o.startTime) return -1;
       return 1;
    }

}

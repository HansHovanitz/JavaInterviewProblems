import java.util.*;
import java.lang.*;
import java.io.*;

public class AddressSort {
	public static void main(String [] args) {
		
		ArrayList<Interval> x = new ArrayList<Interval>();
		
		if (args.length > 0)
		{
			for (int i = 0; i < args.length; i++)
			{
				String intervalString = args[i];
				String [] tokens = intervalString.replaceAll("\\[|\\]","").split(",");
				int start = Integer.parseInt(tokens[0]);
				int end = Integer.parseInt(tokens[1]);
				Interval interval = new Interval(start, end);
				x.add(interval);
			}	
			
			ArrayList<Interval> r = merge(x);
			
			for (int i = 0; i < r.size(); i++) {
				System.out.println("[" + r.get(i).start + "," + r.get(i).end + "]");
			}
		}
	}
	
    public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {

        if (intervals.size() == 0)
            return intervals;
        if (intervals.size() == 1)
            return intervals;

        Collections.sort(intervals, new IntervalComparator());

        Interval first = intervals.get(0);
        int start = first.start;
        int end = first.end;

        ArrayList<Interval> result = new ArrayList<Interval>();

        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            if (current.start <= end) {
                end = Math.max(current.end, end);
            } else {
                result.add(new Interval(start, end));
                start = current.start;
                end = current.end;
            }
        }
        result.add(new Interval(start, end));

        return result;
    }
}

class IntervalComparator implements Comparator<Object> {
    public int compare(Object o1, Object o2) {
        Interval i1 = (Interval) o1;
        Interval i2 = (Interval) o2;
        return i1.start - i2.start;
    }
}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}

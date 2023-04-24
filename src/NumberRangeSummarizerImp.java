import java.util.*;
import java.util.stream.Collectors;

public class NumberRangeSummarizerImp implements NumberRangeSummarizer {

    static Collection<Integer> collection = new ArrayList<>();

    @Override
    public Collection<Integer> collect(String input) {

        return Arrays.stream(input.split("\\D+"))
                .filter(s -> !s.isEmpty())
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
    }


    @Override
    public String summarizeCollection(Collection<Integer> input){
        List<Integer> outputList = new ArrayList<>(input);
        if(outputList.size() < 1){
            return "";
        }
        outputList.sort(Comparator.naturalOrder());
        List<String> summaryOutputList = new ArrayList<>();
        int start = outputList.get(0);
        int prev = start;
        for(int i = 1; i < outputList.size(); i++){
            int current = outputList.get(i);
            if(current == prev){
                continue; //  skip duplicates
            }
            if(current != prev + 1) {
                Boolean out = (prev == start) ? summaryOutputList.add(String.valueOf(prev)):summaryOutputList.add(String.format("%d-%d", start, prev));

                start = current;
            }
            prev = current;
        }
        Boolean out = (prev == start) ? summaryOutputList.add(String.valueOf(prev)):summaryOutputList.add(String.format("%d-%d", start, prev));
        return String.join(", ",summaryOutputList.toString().split("[\\[\\]]")[1].split(", "));
    }
}

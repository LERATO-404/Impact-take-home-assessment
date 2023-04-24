import java.util.*;
import java.util.stream.Collectors;

public class NumberRangeSummarizerImp implements NumberRangeSummarizer {

    @Override
    public Collection<Integer> collect(String input) {

        return Arrays.stream(input.split("\\D+"))
                .filter(s -> !s.isEmpty())
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
    }


    @Override
    public String summarizeCollection(Collection<Integer> input){
        List<Integer> inputList = input.stream()
                .sorted()
                .distinct()
                .collect(Collectors.toList());

        if(inputList.size() < 1){
            return "";
        }

        List<String> summaryOutputList = new ArrayList<>();
        int start = inputList.get(0);
        int prev = start;
        for(int i = 1; i < inputList.size(); i++){
            int current = inputList.get(i);
            if(current != prev + 1) {
                if(prev == start){
                    summaryOutputList.add(String.valueOf(prev));
                }else{
                    summaryOutputList.add(String.format("%d-%d", start, prev));
                }
                start = current;
            }
            prev = current;
        }
        if(prev == start){
            summaryOutputList.add(String.valueOf(prev));
        }else{
            summaryOutputList.add(String.format("%d-%d", start, prev));
        }
        return String.join(", ",summaryOutputList.toString().split("[\\[\\]]")[1].split(", "));
    }
}

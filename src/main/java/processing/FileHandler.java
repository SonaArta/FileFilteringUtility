package processing;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileHandler {
    private final List<Long> longList = new ArrayList<>();
    private final List<Double> doubleList = new ArrayList<>();
    private final List<String> stringList = new ArrayList<>();

    public List<Long> getLongList() {
        return new ArrayList<>(longList);
    }

    public List<Double> getDoubleList() {
        return new ArrayList<>(doubleList);
    }

    public List<String> getStringList() {
        return new ArrayList<>(stringList);
    }

    public void filter(List<String> dataFromFiles) {
        Pattern regExDoub = Pattern.compile("[+-]?\\d*\\.\\d+([eE][+-]?\\d+)?|[+-]?\\d+([eE][+-]?\\d+)?"); // Matches a double.
        Pattern regExLong = Pattern.compile("^-?\\d{1,19}$");

        for (String currString : dataFromFiles) {
            if (regExLong.matcher(currString).matches()) {
                addLong(currString);
            } else if (regExDoub.matcher(currString).matches()) {
                addDouble(currString);
            } else {
                stringList.add(currString);
            }
        }
    }

    private void addDouble(String currString) {
        try {
            doubleList.add(Double.parseDouble(currString));
        } catch (NumberFormatException e) {
            System.err.println("NumberFormatException: enter – not double");
        }
    }

    private void addLong(String currString) {
        try {
            longList.add(Long.parseLong(currString));
        } catch (NumberFormatException err) {
            System.err.println("NumberFormatException: enter – not integer");
        }
    }
}

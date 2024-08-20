package options;

public enum OptionName {
    OUTPUT_FILE_PATH("o"),
    OUTPUT_FILE_NAME_PREFIX("p"),
    APPEND_TO_FILE_MODE("a"),
    SHORT_STATISTICS_MODE("s"),
    FULL_STATISTICS_MODE("f"),
    HELP("h");

    private final String optionName;

    OptionName(String name) {
        this.optionName = name;
    }

    public String getOptionName() {
        return optionName;
    }
}

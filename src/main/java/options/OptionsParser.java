package options;


import org.apache.commons.cli.*;

public class OptionsParser {
    private static OptionsParser optionsParser;
    private final Options options;

    private OptionsParser() {
        options = new Options();
        options.addOption(OptionName.OUTPUT_FILE_PATH.getOptionName(), true, "Sets output file path [Default: current path]");
        options.addOption(OptionName.OUTPUT_FILE_NAME_PREFIX.getOptionName(), true, "Sets output file name prefix [Example: sample_]");
        options.addOption(OptionName.APPEND_TO_FILE_MODE.getOptionName(), false, "Sets the file write mode [Default: REWRITE]");
        options.addOption(OptionName.SHORT_STATISTICS_MODE.getOptionName(), false, "Sets the short statistics collection mode");
        options.addOption(OptionName.FULL_STATISTICS_MODE.getOptionName(), false, "Sets the full statistics collection mode");
        options.addOption("h", "help", false, "Info");
    }

    public static OptionsParser getOptionsParser() {
        if (optionsParser == null) {
            optionsParser = new OptionsParser();
        }
        return optionsParser;
    }

    public UtilOptions parseOption(String[] args) throws ParseException {
        CommandLineParser commandLineParser = new PosixParser();
        CommandLine commandLine = commandLineParser.parse(options, args);

        if (commandLine.hasOption(OptionName.SHORT_STATISTICS_MODE.getOptionName()) && commandLine.hasOption(OptionName.FULL_STATISTICS_MODE.getOptionName())) {
            throw new IllegalArgumentException("Redefined statistics mode");
        }

        if (!commandLine.hasOption(OptionName.SHORT_STATISTICS_MODE.getOptionName()) && !commandLine.hasOption(OptionName.FULL_STATISTICS_MODE.getOptionName())) {
            throw new IllegalArgumentException("Redefined statistics mode");
        }

        if (commandLine.getArgs().length < 1) {
            throw new IllegalArgumentException("No input files for merge sort");
        }

        return buildUtilOptions(commandLine);
    }

    private UtilOptions buildUtilOptions(CommandLine commandLine) {
        UtilOptions utilOptions = new UtilOptions();

        if (commandLine.hasOption(OptionName.OUTPUT_FILE_PATH.getOptionName())) {
            String outputPath = commandLine.getOptionValue(OptionName.OUTPUT_FILE_PATH.getOptionName());
            if (outputPath.charAt(0) != '\\') {
                outputPath = '\\' + outputPath;
            }
            utilOptions.setOutputFilePath(System.getProperty("user.dir") + outputPath);
        } else {
            utilOptions.setOutputFilePath(System.getProperty("user.dir"));
        }

        if (commandLine.hasOption(OptionName.OUTPUT_FILE_NAME_PREFIX.getOptionName())) {
            utilOptions.setOutputFileNamePrefix(commandLine.getOptionValue(OptionName.OUTPUT_FILE_NAME_PREFIX.getOptionName()));
        } else {
            utilOptions.setOutputFileNamePrefix("");
        }

        if (commandLine.hasOption(OptionName.APPEND_TO_FILE_MODE.getOptionName())) {
            utilOptions.setOpenMode(OpenMode.ADD);
        } else {
            utilOptions.setOpenMode(OpenMode.REWRITE);
        }

        if (commandLine.hasOption(OptionName.SHORT_STATISTICS_MODE.getOptionName())) {
            utilOptions.setStatisticsCollectionMode(StatisticsCollectionMode.SHORT);
        } else {
            utilOptions.setStatisticsCollectionMode(StatisticsCollectionMode.FULL);
        }

        utilOptions.setInputFiles(commandLine.getArgList());

        return utilOptions;
    }

    public void printUsage() {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("HELP", options);
    }
}
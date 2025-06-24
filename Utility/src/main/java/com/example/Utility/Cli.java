package com.example.Utility;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class Cli {

    public record Opts(
            boolean append,
            boolean fullStats,
            Path outDir,
            String prefix,
            List<Path> inputs) { }

    private Cli() { }

    public static Opts parse(String[] argv) {
        boolean append = false, full = false;
        Path out = Path.of(".");
        String pre = "";
        List<Path> files = new ArrayList<>();

        for (int i = 0; i < argv.length; i++) {
            switch (argv[i]) {
                case "-a" -> append = true;
                case "-s" -> full = false;
                case "-f" -> full = true;
                case "-o" -> out = Path.of(requireNext(argv, ++i, "-o"));
                case "-p" -> pre = requireNext(argv, ++i, "-p");
                default   -> files.add(Path.of(argv[i]));
            }
        }
        if (files.isEmpty())
            throw new IllegalArgumentException("No input files.");

        return new Opts(append, full, out, pre, List.copyOf(files));
    }

    private static String requireNext(String[] a, int i, String flag) {
        if (i >= a.length)
            throw new IllegalArgumentException("Missing value for " + flag);
        return a[i];
    }

    public static void usage() {
        System.out.println("""
          Usage: java -jar df.jar [options] <files>
            -s          Simple stats (default)
            -f          Full stats
            -a          Append to result files
            -o <dir>    Output directory
            -p <pref>   Prefix for result file names
        """);
    }
}

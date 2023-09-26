package ru.gb;

import java.io.File;

public class Tree {
    public static void print(File file, String indent, boolean islast) {
        System.out.print(indent);
        if (islast) {
            System.out.print("└─");
            indent += "  ";
        }
        else {
            System.out.print("├─");
            indent += "│  ";
        }
        System.out.println(file.getName());
        File[] files = file.listFiles();
        if (files == null){
            return;
        }

        int subDitTotal = 0;
        int subFileTotal = 0;
        for (File f : files) {
            if (f.isDirectory()) {
                subDitTotal++;
            } else {
                subFileTotal++;
            }
        }

        int subDirCounter = 0;
        int subFileCounter = 0;
        for (File f : files) {
            if (f.isDirectory()) {
                subDirCounter++;
                print(f, indent, subDirCounter == subDitTotal && subFileCounter == 0);
            } else {
                subFileCounter++;
                print(f, indent, subFileCounter == subFileTotal);
            }
        }

    }
}

package ru.gb;

import java.io.File;

import static ru.gb.Tree.print;

public class Main {
    public static void main(String[] args) {
        print(new File("."), "", true);
    }
}

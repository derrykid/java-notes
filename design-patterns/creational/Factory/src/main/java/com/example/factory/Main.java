package com.example.factory;

import factory.Dialog;
import factory.HtmlDialog;
import factory.WindowsDialog;

public class Main {
    private static Dialog dialog;

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        configure();
        runBusinessLogic();
    }

    private static void runBusinessLogic() {
        dialog.renderWindow();
    }

    private static void configure() {
        if (System.getProperty("os.name").equals("Linux")){
            dialog = new WindowsDialog();
        } else {
            dialog = new HtmlDialog();
        }
    }
}

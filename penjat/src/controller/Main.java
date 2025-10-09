package controller;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		
        view.PenjatView.mostrarMenuPrincipal();

		String option = in.nextLine();

		switch (option) {
			case 1:
				login();

				break;
			default:
				throw new AssertionError();
		}

    }
	

	private static void login(){

		String usuari = '';
		System.out.println("Usuari: ");
		

	}

}

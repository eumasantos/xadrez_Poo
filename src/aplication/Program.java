package aplication;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.Exceção_xad;
import xadrez.Partida_xadrez;
import xadrez.Peça_xadrez;
import xadrez.posicao_xadrez;

public class Program {

	public static void main(String[] args) {
		
		//teste de movimento de peças
		Scanner sc = new Scanner(System.in);
		Partida_xadrez partida_xadrez = new Partida_xadrez();
	
		while (true) {
			try {
				UI.clearScreen();//para limpar a tela cada vez que executar o while
				UI.imprimiTabuleiro(partida_xadrez.getPecas());
				System.out.println();
				System.out.print("Origem: ");
				posicao_xadrez origem = UI.lerPosicaoXadrez(sc);
				
				System.out.println();
				System.out.print("Destino: ");
				posicao_xadrez destino = UI.lerPosicaoXadrez(sc);
				
				Peça_xadrez capturaPeca = partida_xadrez.performance_mov(origem, destino);
			}
			//tratamento de exceções. Caso ocorra alguma exceção em exceção_xad, imprime msg na tela e aguarda o enter do usuário
			catch (Exceção_xad e) {
				System.out.println(e.getMessage());
				sc.nextLine();//aguardar o ENTER
			}
			//tratamento de exceções. Caso ocorra alguma exceção em InputMismatchException, imprime msg na tela e aguarda o enter do usuário
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();//aguardar o ENTER
			}
		}	
	}
}

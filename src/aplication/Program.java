package aplication;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
		List<Peça_xadrez> capturadas = new ArrayList<>();
	
		while (!partida_xadrez.getXequeMate()) {
			try {
				UI.clearScreen();//para limpar a tela cada vez que executar o while
			//	UI.imprimiTabuleiro(partida_xadrez.getPecas();
				UI.imprimiPartida(partida_xadrez, capturadas);
				System.out.println();
				System.out.print("Origem: ");
				posicao_xadrez origem = UI.lerPosicaoXadrez(sc);
				
				boolean[][]movim_possiveis = partida_xadrez.movim_possiveis(origem);
				UI.clearScreen();
				//sobrecarga para imprimir o tabuleiro mostrando (com a cor escolhida) os movimentos possiveis
				UI.imprimiTabuleiro(partida_xadrez.getPecas(),movim_possiveis);
				System.out.println();
				System.out.print("Destino: ");
				posicao_xadrez destino = UI.lerPosicaoXadrez(sc);
				
				//executa um movimento e reorna uma possivel peça capturada:
				Peça_xadrez capturaPeca = partida_xadrez.performance_mov(origem, destino);
			
				//testa se a peça foi realmente capturada e acrscenta ela na lista de peças capturadas
				if (capturaPeca != null) {
					capturadas.add(capturaPeca);
				}
				//usuario escolhe peça para ser promovida 
				if (partida_xadrez.getPromocao() != null) {
					System.out.println("VOCÊ PODE PROMOVER UMA PEÇA!");
					System.out.println ("Para Bispo - Pressione B");
					System.out.println ("Para Cavalo - Pressione C");
					System.out.println ("Para Torre - Pressione T");
					System.out.println ("Para Rainha - Pressione Q");
					System.out.print("Digite a letra da peça para ser promovida:");
					String type = sc.nextLine().toUpperCase();
					while (!type.equals ("8") && !type.equals("C") && !type.equals("T") && !type.equals("Q")) {
						System.out.println("LETRA INVÁLIDA. DIGITE NOVAMENTE!");
						System.out.println ("Para Bispo - Pressione B");
						System.out.println ("Para Cavalo - Pressione C");
						System.out.println ("Para Torre - Pressione T");
						System.out.println ("Para Rainha - Pressione Q");
						System.out.print("Digite a letra da peça para ser promovida:");
						type = sc.nextLine().toUpperCase();
					}
					partida_xadrez.substituirPecaPromovida(type);
				}
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
		UI.clearScreen();
		UI.imprimiPartida(partida_xadrez,capturadas);
	}
}

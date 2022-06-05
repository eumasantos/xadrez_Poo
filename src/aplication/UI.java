package aplication;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.Cor;
import xadrez.Partida_xadrez;
import xadrez.Peça_xadrez;
import xadrez.posicao_xadrez;
/*classe usada para imprimir o tabuleiro - UI = user interface*/
public class UI {
	
//cores:
		public static final String ANSI_RESET = "\u001B[0m";
		public static final String ANSI_BLACK = "\u001B[30m";
		public static final String ANSI_RED = "\u001B[31m";
		public static final String ANSI_GREEN = "\u001B[32m";
		public static final String ANSI_YELLOW = "\u001B[33m";
		public static final String ANSI_BLUE = "\u001B[34m";
		public static final String ANSI_PURPLE = "\u001B[35m";
		public static final String ANSI_CYAN = "\u001B[36m";
		public static final String ANSI_WHITE = "\u001B[37m";

		public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
		public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
		public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
		public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
		public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
		public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
		public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
		public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
		// https://stackoverflow.com/questions/2979383/java-clear-the-console
		//metodo para limpar a tela
		public static void clearScreen() {
			System.out.print("\033[H\033[2J");
			System.out.flush();
		} 	
		
		//metodo para ler uma posição do usuário:
	public static posicao_xadrez lerPosicaoXadrez(Scanner sc) {
		
		//bloco para evitar problema de formato:
		try {
			String s = sc.nextLine();//leitura da string coluna
			char colunas = s.charAt(0); //variavel que recebe o primeiro caracter da coluna
			//leitura da linha
			int linhas = Integer.parseInt(s.substring(1));//recortar a string a partir da posicao 1 e converter o resultado apra inteiro
			return new posicao_xadrez(colunas,linhas);
		}
		catch (RuntimeException e) {
			//exceção do java. Erro de entrada de dados. 
			throw new InputMismatchException("Erro lendo a posição do xadrez. Valores válidos: são de A1 até H8");
		}
	}	
	public static void imprimiPartida(Partida_xadrez partida_xadrez) {
		imprimiTabuleiro(partida_xadrez. getPecas());
		System.out.println();
		System.out.println("Rodada : " + partida_xadrez. getVez());//mostra na tela a vez
		System.out.println("Esperando o jogador: " + partida_xadrez.getJogadorAtual());
	}
	
	public static void imprimiTabuleiro(Peça_xadrez[][]pecas) {
		for (int i=0;  i<pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j=0; j<pecas.length; j++) {
				imprimiPeca(pecas[i][j], false);//o false indica que nenhuma peça deve ter o fundo colorido
			}
			System.out.println();
		}
		//System.out.println("  a b c d e f g h");
		System.out.println("  A B C D E F G H");	
	}
	//metodo para imprimir a matriz de movimentos possiveis
	public static void imprimiTabuleiro(Peça_xadrez[][]pecas, boolean [][]movim_possiveis ) {
		for (int i=0;  i<pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j=0; j<pecas.length; j++) {
				imprimiPeca(pecas[i][j], movim_possiveis[i][j]);
			}
			System.out.println();
		}
		//System.out.println("  a b c d e f g h");
		System.out.println("  A B C D E F G H");	
	}
	
	private static void imprimiPeca(Peça_xadrez peca, boolean background) {//variavel para definir se o fundo da tela deve ser colorido ou não
		if (background) {//testa se a variável é verdadeira, se sim,muda o fundo para verde
			System.out.print(ANSI_GREEN_BACKGROUND);
		}
		if (peca == null) {
			System.out.print("-" + ANSI_RESET);
		}
		
		else {

        	//testar se a peça é branca ou preta
            if (peca.getCor() == Cor.BRANCA) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	/*private static void imprimiPeca(Peça_xadrez peca) {
		if (peca == null) {
			System.out.print("-");
		}
			else {
				System.out.print(peca);
			}
			System.out.print(" ");	
	}	*/

}

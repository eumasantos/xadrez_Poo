package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Peça_xadrez;

public class Rei extends Peça_xadrez{

	public Rei(Tabuleiro tabul, Cor cor) {
		super(tabul, cor);
		// TODO Auto-generated constructor stub
	}
	
	//convertendo uma torre para string
		@Override
		public String toString() {
			return "R";
		}

		/*sempre que chamar os movimentos possiveis do REI 
		 * vai retornar uma matriz com todas posições valendo falso(REI preso) 
		 */
		@Override
		public boolean[][] movim_possiveis() {
			boolean [][] matriz = new boolean [getTabul().getLinhas()][getTabul().getColunas()];
			return matriz;
		}
	

}

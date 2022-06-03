package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Peça_xadrez;

public class Torre extends Peça_xadrez{
	
//construtor repassando a chamada para super classe
	public Torre(Tabuleiro tabul, Cor cor) {
		super(tabul, cor);
		// TODO Auto-generated constructor stub
	}
	//convertendo uma torre para string
	@Override
	public String toString() {
		return "T";
	}
	
	@Override
	public boolean[][] movim_possiveis() {
		boolean [][] matriz = new boolean [getTabul().getLinhas()][getTabul().getColunas()];
		return matriz;
	}

}

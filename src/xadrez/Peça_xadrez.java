package xadrez;

import tabuleiro.Pecas;
import tabuleiro.Tabuleiro;

/*subclasse da classe peça*/
public abstract class Peça_xadrez extends Pecas  {
	
	private Cor cor;

	/*construtor que recebe tabuleiro e cor
	 * tabuleiro repassa a chamada para o construtor da super classe: pecas*/
	public Peça_xadrez(Tabuleiro tabul, Cor cor) {
		super(tabul);
		this.cor = cor;
	}
/*Não existe set para que as cores não sejam modificadas, apenas acessadas*/
	public Cor getCor() {
		return cor;
	}	

}

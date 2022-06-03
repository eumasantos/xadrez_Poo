package tabuleiro;

//classe usada para implementar as peças do tabuleiro de xadrez
public abstract class Pecas {
	protected Posição posicao;//posição protegida, não visvel no tabuleiro
	private Tabuleiro tabul; //associação da peça com o tabuleiro e vice versa
	
	/*construtor das peças. Apenas o tabuleiro, pois a posição 
	 * inicialmente é nula, ou seja não foi colocada no tabuleiro
	 */
	public Pecas(Tabuleiro tabul) {
		this.tabul = tabul;
		posicao = null;
	}
/*não tem set pois não queremos  que o tabuleiro seja alterado*/
/*O get do tabuleiro está como protegido pois somente classes e subclasses 
 * do mesmo pacote podem acessar o tabuleiro de uma peça
 */
	protected Tabuleiro getTabul() {
		return tabul;
	}

	/*operação de movimentos possiveis de peças. 
	 * É abstrata porque uma peça é tipo genérico, não sabemos quais movimentos possiveis
	 */
	public abstract boolean[][] movim_possiveis();
	
	/*Recebe uma posição e retorna verdadeiro ou falso se 
	 * for possivel mover a peça para determinada posicao*/
	public boolean movimPossivelPosic(Posição posicao) {
		return movim_possiveis()[posicao.getLinhas()][posicao.getColunas()];
	}//metodo concreto usando um método abstrato

	/*operação para ver se existe pelo menos um movimento 
	 * possivel para determinada peça*/
	public boolean existeMovPossivel() {
		boolean [][] matriz = movim_possiveis();//variavel auxiliar para receber movim_possiveis
		//for para percorrer a matriz e verificar se existe alguma posição da matriz que seja verdadeira
		for (int i=0; i<matriz.length; i++) {
			for (int j=0; j<matriz.length; j++) {
				//testar se a matriz na posição i, j é verdadeira ou falsa
				if (matriz[i][j]) {
					return true;//se vedadeira, existe um movimento possivel
				}
			}
		}
		return false;
	}
	
}

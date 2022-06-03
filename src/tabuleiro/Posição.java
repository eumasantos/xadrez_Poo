// primeira classe do tabuleiro para representar as posições 

package tabuleiro;

public class Posição {
	private int linhas;//atributo privado linha
	private int colunas; //atributo privado colunas
	
	//construtores
	
	public Posição(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
	}
	//getters e setters

	public int getLinhas() {
		return linhas;
	}
	
	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}
	
	public void setValues(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
	}
	//impressão de posição na tela (linha e coluna)
	
	@Override
	public String toString() {
		return linhas + "," + colunas;
	}	

}

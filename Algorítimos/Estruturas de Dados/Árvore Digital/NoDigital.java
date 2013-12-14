package raulJoaquim;
//-------------------------------------------------------------------------------------//
/* NoDigital.java
 * No Digital
 * Objetivo: Implementacao de um No Digital para uso numa arvore Digital
 * Desenvolvedor : Raul Joaquim Camara dos Santos
 * Local: Dimap/UFRN - Natal/RN -Brasil
 * Data: 08/04/2008
 * Ultima Modificacao: 17/04/2008
 */
//-------------------------------------------------------------------------------------//
//Classe que implementa uma No Digital para uso numa arvore Digital
public class NoDigital {
//-----------------------------------------------------------------------//	
	//***ATRIBUTOS***//
	public Character chave;
	public boolean terminal;
	public NoDigital[] filhos;
	public NoDigital pai;
//-----------------------------------------------------------------------//	
	//***CONSTRUTORES**//
	//chave e numero de filhos como parametro
	public NoDigital(char key,int tam){
		this.chave = key;
		this.terminal =false;
		this.filhos = new NoDigital[tam];
		this.pai =null;
	}
	//apenas numero de filhos como parametro
	public NoDigital(int tam){
		this.terminal =false;
		this.filhos = new NoDigital[tam];
		this.pai =null;
		this.chave = null;
	}
	
	
}

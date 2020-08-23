package compiladores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Anderson
 */
public class Compiladores {

    public static void main(String[] args) {
        String url = "D:\\Sistemas Java\\Compiladores\\exemplo.txt";
        String url2 = "D:\\Sistemas Java\\Compiladores\\tabuada.txt";

        try {
            // abre arquivo de leitura
            File arquivo = new File(url);

            tokens tok = new tokens();
            //abre arquivo para escrita
            FileWriter arq = new FileWriter(url2);
            PrintWriter gravarArq = new PrintWriter(arq);
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
//enquanto houver mais linhas
            int num = 1;
            //Cria lista dos erros
            ArrayList<String> erros = new ArrayList<String>();
            //Cria lista de tokens
            ArrayList<tokens> token = new ArrayList<tokens>();
            ArrayList<String> tokenSt = new ArrayList<String>();
            //int numerodelinhas = 0;
            while (br.ready()) {
//lê a proxima linha
                String linha = br.readLine();
                String nova = "";
                boolean comentario = false;
                //if pega as linhas em branco do arquivo
                if (linha.trim().length() == 0) {
                    num++;
                }
                //percorre linha 
                for (int e = 0; e < linha.length(); e++) {
                    //verifica se identifica um comentario 
                    if (linha.charAt(e) == '{' || linha.charAt(e) == '}') {
                        if (linha.charAt(e) == '{') {
                            comentario = true;
                        } else if (comentario == false) {
                            tok.setLexema("}");
                            tok.setLinha(num);
                            tok.setId("Identificador");
                            erros.add("Erro linha " + tok.getLinha() + ": " + tok.getId() + " inválido!");
                        } else {
                            comentario = false;
                        }
                    } else if (comentario == false && linha.charAt(e) != ' ') {//forma palavra
                        nova += linha.charAt(e);
                    } else if (comentario == false) {
                        if (linha.charAt(e) == ' ') {// Se o caracter for espaço inicia processo de envio
                            if (nova != "") {//se a palavra armazenada não for vazia 
                                tok.setLexema(nova.toLowerCase());
                                tok.setId(tok.lexemaToken());//valida o token
                                tok.setLinha(num);
                                if (tok.getId().equals("erro")) {//verifico se o id do token for erro salsa na lista de erro
                                    if (tok.numerico(tok.getLexema().substring(0, 1))) {
                                        erros.add("Erro linha " + tok.getLinha() + ": Número inválido!");
                                    } else {
                                        erros.add("Erro linha " + tok.getLinha() + ": Identificador inválido!");
                                    }
                                    nova = "";
                                } else { // se não for erro grava na lista de token validos e salva em arquivo
                                    token.add(tok);
                                    gravarArq.printf("<" + token.get(token.size() - 1).getId() + ", " + token.get(token.size() - 1).getLinha() + ">%n");
                                    tokenSt.add("<" + token.get(token.size() - 1).getId() + ">");
                                    //tokenSt.add("<" + token.get(token.size() - 1).getId() + ", " + token.get(token.size() - 1).getLinha() + ">");
                                    nova = "";
                                }
                            }
                        }
                    }
                }
                if (linha.length() != 0) {
                    if (linha.charAt(linha.length() - 1) != ' ' || linha.charAt(linha.length() - 1) != '\n') {
                        if (nova != "") {
                            tok.setLexema(nova.toLowerCase());
                            tok.setId(tok.lexemaToken());
                            tok.setLinha(num);
                            if (tok.getId() == "erro") {
                                if (tok.numerico(tok.getLexema().substring(0, 1))) {
                                    erros.add("Erro linha " + tok.getLinha() + ": Número inválido!");
                                } else {
                                    erros.add("Erro linha " + tok.getLinha() + ": Identificador inválido!");
                                }
                                nova = "";
                            } else {
                                token.add(tok);
                                gravarArq.printf("<" + token.get(token.size() - 1).getId() + ", " + token.get(token.size() - 1).getLinha() + ">%n");
                                //tokenSt.add("<" + token.get(token.size() - 1).getId() + ", " + token.get(token.size() - 1).getLinha() + ">");
                                tokenSt.add("<" + token.get(token.size() - 1).getId() + ">");
                                nova = "";
                            }
                        }
                        num++;
                    }
                }

            }

            //***** Impressão de resultado ******
            int t = 0;
            String entradaSintatico = "";
            System.out.println("______________ANALISE LEXICO______________");
            while (t < tokenSt.size()) {
                System.out.print(tokenSt.get(t).toString());
                entradaSintatico += tokenSt.get(t).toString() + " ";
                t++;
            }
            t = 0;
            System.out.println();
            while (t < erros.size()) {
                System.out.println("erro: " + erros.get(t).toString());
                t++;
            }
            

            if (erros.size() == 0) {
                System.out.println("\n__________________________________________");
                System.out.println("____________ANALISE SINTATICO_____________");
                t = 0;
                entradaSintatico += "$";
                //System.out.println(entradaSintatico);
                sintatico on = new sintatico(entradaSintatico);
                on.imprime();
            } else {
                System.out.println("\nERRO LEXICO!");
                System.out.println("\n__________________________________________");
            }
            arq.close();
            br.close();
            fr.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

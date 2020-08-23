package compiladores;

import java.util.ArrayList;

/**
 *
 * @author Anderson
 */
public class sintatico {

    private ArrayList<String> tokens = new ArrayList<String>();
    private String entrada = "";
    private String pilha = "";
    private String input;
    String tabela[][] = {{"DC", "DC", "DC", "DC", "DC",},
    {"DC", "DC", "DC", "DC", "DC", "kDC"},
    {"&", "&", "&", "kDC", "&"},
    {"fFeBE", "eBdF", "alF", "ia", "jF",},
    {"gBh", "&", "&", "&"},
    {"IG", "IG", "IG"},
    {"&", "&", "&", "&", "HI", "HI", "&", "&", "&"},
    {"m", "n"},
    {"ML", "MK", "MK"},
    {"&", "&", "&", "&", "&", "&", "KML", "KML", "&", "&", "&"},
    {"o", "p"},
    {"PN", "PN", "PN"},
    {"&", "&", "&", "&", "&", "&", "&", "&", "OPN", "OPN", "&", "&", "&"},
    {"q", "r"},
    {"a", "sFt", "b"}};

    public sintatico() {
    }

    public sintatico(String input) {
        this.input = input;
    }

    public void setEntrada() {
        int i = 0;
        String nova = "";

        while (i < input.length()) {
            if (input.charAt(i) == ' ') {
                tokens.add(nova);
                nova = "";
            } else {
                nova += input.charAt(i);
            }
            i++;
        }
        tokens.add(nova);
    }

    public void conversor() {
        setEntrada();
        int t = 0;
        while (t < tokens.size()) {
        System.out.println("TOKENS= "+tokens.get(t));
            if ("<IF>".equals(tokens.get(t))) {
                entrada += "f";
            } else if ("<THEN>".equals(tokens.get(t))) {
                entrada += "e";
            } else if ("<ELSE>".equals(tokens.get(t))) {
                entrada += "g";
            } else if ("<END>".equals(tokens.get(t))) {
                entrada += "h";
            } else if ("<REPEAT>".equals(tokens.get(t))) {
                entrada += "c";
            } else if ("<UNTIL>".equals(tokens.get(t))) {
                entrada += "d";
            } else if ("<ID>".equals(tokens.get(t))) {
                entrada += "a";
            } else if ("<READ>".equals(tokens.get(t))) {
                entrada += "i";
            } else if ("<WRITE>".equals(tokens.get(t))) {
                entrada += "j";
            } else if ("<LESS>".equals(tokens.get(t))) {
                entrada += "m";
            } else if ("<EQUAL>".equals(tokens.get(t))) {
                entrada += "n";
            } else if ("<PLUS>".equals(tokens.get(t))) {
                entrada += "o";
            } else if ("<MINUS>".equals(tokens.get(t))) {
                entrada += "p";
            } else if ("<TIMES>".equals(tokens.get(t))) {
                entrada += "q";
            } else if ("<DIV>".equals(tokens.get(t))) {
                entrada += "r";
            } else if ("<LBRACKET>".equals(tokens.get(t))) {
                entrada += "s";
            } else if ("<RBRACKET>".equals(tokens.get(t))) {
                entrada += "t";
            } else if ("<NUM>".equals(tokens.get(t))) {
                entrada += "b";
            } else if ("<DOTCOMA>".equals(tokens.get(t))) {
                entrada += "k";
            } else if ("<ATRIB>".equals(tokens.get(t))) {
                entrada += "l";
            } else if ("$".equals(tokens.get(t))) {
                entrada += "$";
            }
            t++;
        }
    }

    public void imprime() {
        int t = 0;
        conversor();
        //System.out.println("Entrada: ");
        //while (t < entrada.length()) {
        //  System.out.print(entrada.charAt(t));
        //t++;
        //}
        iniciaPilha();
    }

    public void iniciaPilha() {
        int t = 0;
        char a, b;
        int posPilha;
        boolean ativo = true;
        String fa = "";
        pilha += "A";
        int tamanhoPilha = pilha.length();
        while (tamanhoPilha != 0) {
            a = pilha.charAt(pilha.length() - 1);
            b = entrada.charAt(0);
            if (a == 'A') {
                if (b == 'f') {
                    fa = tabela[0][0];
                    t = fa.length() - 1;
                } else if (b == 'c') {
                    fa = tabela[0][1];
                    t = fa.length() - 1;
                } else if (b == 'a') {
                    fa = tabela[0][2];
                    t = fa.length() - 1;
                } else if (b == 'i') {
                    fa = tabela[0][3];
                    t = fa.length() - 1;
                } else if (b == 'j') {
                    fa = tabela[0][4];
                    t = fa.length() - 1;
                } else if (b == 'l') {
                    fa = "l";
                    t = fa.length() - 1;
                } else {
                    t = -1;
                    tamanhoPilha = 0;
                }
                while (t >= 0) {
                    if (ativo == true) {
                        pilha = pilha.substring(0, pilha.length() - 1);
                        pilha += fa.charAt(t);
                        ativo = !ativo;
                    } else {
                        pilha += fa.charAt(t);
                    }
                    t--;
                }
                ativo = true;
            } else if (a == 'B') {
                if (b == 'f') {
                    fa = tabela[1][0];
                    t = fa.length() - 1;
                } else if (b == 'c') {
                    fa = tabela[1][1];
                    t = fa.length() - 1;
                } else if (b == 'a') {
                    fa = tabela[1][2];
                    t = fa.length() - 1;
                } else if (b == 'i') {
                    fa = tabela[1][3];
                    t = fa.length() - 1;
                } else if (b == 'j') {
                    fa = tabela[1][4];
                    t = fa.length() - 1;
                } else if (b == 'l') {
                    fa = "l";
                    t = fa.length() - 1;
                } else {
                    t = -1;
                    tamanhoPilha = 0;
                }
                while (t >= 0) {
                    if (ativo == true) {
                        pilha = pilha.substring(0, pilha.length() - 1);
                        pilha += fa.charAt(t);
                        ativo = !ativo;
                    } else {
                        pilha += fa.charAt(t);
                    }
                    t--;
                }
                ativo = true;
            } else if (a == 'C') {
                if (b == 'g') {
                    fa = tabela[2][0];
                    t = fa.length() - 1;
                } else if (b == 'h') {
                    fa = tabela[2][1];
                    t = fa.length() - 1;
                } else if (b == 'd') {
                    fa = tabela[2][2];
                    t = fa.length() - 1;
                } else if (b == 'k') {
                    fa = tabela[2][3];
                    t = fa.length() - 1;
                } else if (b == '$') {
                    fa = tabela[2][4];
                    t = fa.length() - 1;
                } else if (b == 'l') {
                    fa = "l";
                    t = fa.length() - 1;
                } else {
                    t = -1;
                    tamanhoPilha = 0;
                }
                while (t >= 0) {
                    if (ativo == true) {
                        pilha = pilha.substring(0, pilha.length() - 1);
                        pilha += fa.charAt(t);
                        ativo = !ativo;
                    } else {
                        pilha += fa.charAt(t);
                    }
                    t--;
                }
                ativo = true;
            } else if (a == 'D') {
                if (b == 'f') {
                    fa = tabela[3][0];
                    t = fa.length() - 1;
                } else if (b == 'c') {
                    fa = tabela[3][1];
                    t = fa.length() - 1;
                } else if (b == 'a') {
                    fa = tabela[3][2];
                    t = fa.length() - 1;
                } else if (b == 'i') {
                    fa = tabela[3][3];
                    t = fa.length() - 1;
                } else if (b == 'j') {
                    fa = tabela[3][4];
                    t = fa.length() - 1;
                } else if (b == 'l') {
                    fa = "l";
                    t = fa.length() - 1;
                } else {
                    t = -1;
                    tamanhoPilha = 0;
                }
                while (t >= 0) {
                    if (ativo == true) {
                        pilha = pilha.substring(0, pilha.length() - 1);
                        pilha += fa.charAt(t);
                        ativo = !ativo;
                    } else {
                        pilha += fa.charAt(t);
                    }
                    t--;
                }
                ativo = true;
            } else if (a == 'E') {
                if (b == 'g') {
                    fa = tabela[4][0];
                    t = fa.length() - 1;
                } else if (b == 'h') {
                    fa = tabela[4][1];
                    t = fa.length() - 1;
                } else if (b == 'd') {
                    fa = tabela[4][2];
                    t = fa.length() - 1;
                } else if (b == 'k') {
                    fa = tabela[4][3];
                    t = fa.length() - 1;
                } else if (b == '$') {
                    fa = tabela[4][3];
                    t = fa.length() - 1;
                } else if (b == 'l') {
                    fa = "l";
                    t = fa.length() - 1;
                } else {
                    t = -1;
                    tamanhoPilha = 0;
                }
                while (t >= 0) {
                    if (ativo == true) {
                        pilha = pilha.substring(0, pilha.length() - 1);
                        pilha += fa.charAt(t);
                        ativo = !ativo;
                    } else {
                        pilha += fa.charAt(t);
                    }
                    t--;
                }
                ativo = true;
            } else if (a == 'F') {
                if (b == 'a') {
                    fa = tabela[5][0];
                    t = fa.length() - 1;
                } else if (b == 's') {
                    fa = tabela[5][1];
                    t = fa.length() - 1;
                } else if (b == 'b') {
                    fa = tabela[5][2];
                    t = fa.length() - 1;
                } else if (b == 'l') {
                    fa = "l";
                    t = fa.length() - 1;
                } else {
                    t = -1;
                    tamanhoPilha = 0;
                }
                while (t >= 0) {
                    if (ativo == true) {
                        pilha = pilha.substring(0, pilha.length() - 1);
                        pilha += fa.charAt(t);
                        ativo = !ativo;
                    } else {
                        pilha += fa.charAt(t);
                    }
                    t--;
                }
                ativo = true;
            } else if (a == 'G') {
                if (b == 'e') {
                    fa = tabela[6][0];
                    t = fa.length() - 1;
                } else if (b == 'g') {
                    fa = tabela[6][1];
                    t = fa.length() - 1;
                } else if (b == 'h') {
                    fa = tabela[6][2];
                    t = fa.length() - 1;
                } else if (b == 'd') {
                    fa = tabela[6][3];
                    t = fa.length() - 1;
                } else if (b == 'm') {
                    fa = tabela[6][4];
                    t = fa.length() - 1;
                } else if (b == 'n') {
                    fa = tabela[6][5];
                    t = fa.length() - 1;
                } else if (b == 't') {
                    fa = tabela[6][6];
                    t = fa.length() - 1;
                } else if (b == 'k') {
                    fa = tabela[6][7];
                    t = fa.length() - 1;
                } else if (b == '$') {
                    fa = tabela[6][8];
                    t = fa.length() - 1;
                } else if (b == 'l') {
                    fa = "l";
                    t = fa.length() - 1;
                } else {
                    t = -1;
                    tamanhoPilha = 0;
                }
                while (t >= 0) {
                    if (ativo == true) {
                        pilha = pilha.substring(0, pilha.length() - 1);
                        pilha += fa.charAt(t);
                        ativo = !ativo;
                    } else {
                        pilha += fa.charAt(t);
                    }
                    t--;
                }
                ativo = true;
            } else if (a == 'H') {
                if (b == 'm') {
                    fa = tabela[7][0];
                    t = fa.length() - 1;
                } else if (b == 'n') {
                    fa = tabela[7][1];
                    t = fa.length() - 1;
                } else if (b == 'l') {
                    fa = "l";
                    t = fa.length() - 1;
                } else {
                    t = -1;
                    tamanhoPilha = 0;
                }
                while (t >= 0) {
                    if (ativo == true) {
                        pilha = pilha.substring(0, pilha.length() - 1);
                        pilha += fa.charAt(t);
                        ativo = !ativo;
                    } else {
                        pilha += fa.charAt(t);
                    }
                    t--;
                }
                ativo = true;
            } else if (a == 'I') {
                if (b == 'a') {
                    fa = tabela[8][0];
                    t = fa.length() - 1;
                } else if (b == 's') {
                    fa = tabela[8][1];
                    t = fa.length() - 1;
                } else if (b == 'b') {
                    fa = tabela[8][2];
                    t = fa.length() - 1;
                } else if (b == 'l') {
                    fa = "l";
                    t = fa.length() - 1;
                } else {
                    t = -1;
                    tamanhoPilha = 0;
                }
                while (t >= 0) {
                    if (ativo == true) {
                        pilha = pilha.substring(0, pilha.length() - 1);
                        pilha += fa.charAt(t);
                        ativo = !ativo;
                    } else {
                        pilha += fa.charAt(t);
                    }
                    t--;
                }
                ativo = true;
            } else if (a == 'L') {
                if (b == 'e') {
                    fa = tabela[9][0];
                    t = fa.length() - 1;
                } else if (b == 'g') {
                    fa = tabela[9][1];
                    t = fa.length() - 1;
                } else if (b == 'h') {
                    fa = tabela[9][2];
                    t = fa.length() - 1;
                } else if (b == 'd') {
                    fa = tabela[9][3];
                    t = fa.length() - 1;
                } else if (b == 'm') {
                    fa = tabela[9][4];
                    t = fa.length() - 1;
                } else if (b == 'n') {
                    fa = tabela[9][5];
                    t = fa.length() - 1;
                } else if (b == 'o') {
                    fa = tabela[9][6];
                    t = fa.length() - 1;
                } else if (b == 'p') {
                    fa = tabela[9][7];
                    t = fa.length() - 1;
                } else if (b == 't') {
                    fa = tabela[9][8];
                    t = fa.length() - 1;
                } else if (b == 'k') {
                    fa = tabela[9][9];
                    t = fa.length() - 1;
                } else if (b == '$') {
                    fa = tabela[9][10];
                    t = fa.length() - 1;
                } else if (b == 'l') {
                    fa = "l";
                    t = fa.length() - 1;
                } else {
                    t = -1;
                    tamanhoPilha = 0;
                }
                while (t >= 0) {
                    if (ativo == true) {
                        pilha = pilha.substring(0, pilha.length() - 1);
                        pilha += fa.charAt(t);
                        ativo = !ativo;
                    } else {
                        pilha += fa.charAt(t);
                    }
                    t--;
                }
                ativo = true;
            } else if (a == 'K') {
                if (b == 'o') {
                    fa = tabela[10][0];
                    t = fa.length() - 1;
                } else if (b == 'p') {
                    fa = tabela[10][1];
                    t = fa.length() - 1;
                } else if (b == 'l') {
                    fa = "l";
                    t = fa.length() - 1;
                } else {
                    t = -1;
                    tamanhoPilha = 0;
                }
                while (t >= 0) {
                    if (ativo == true) {
                        pilha = pilha.substring(0, pilha.length() - 1);
                        pilha += fa.charAt(t);
                        ativo = !ativo;
                    } else {
                        pilha += fa.charAt(t);
                    }
                    t--;
                }
                ativo = true;
            } else if (a == 'M') {
                if (b == 'a') {
                    fa = tabela[11][0];
                    t = fa.length() - 1;
                } else if (b == 's') {
                    fa = tabela[11][1];
                    t = fa.length() - 1;
                } else if (b == 'b') {
                    fa = tabela[11][2];
                    t = fa.length() - 1;
                } else if (b == 'l') {
                    fa = "l";
                    t = fa.length() - 1;
                } else {
                    t = -1;
                    tamanhoPilha = 0;
                }
                while (t >= 0) {
                    if (ativo == true) {
                        pilha = pilha.substring(0, pilha.length() - 1);
                        pilha += fa.charAt(t);
                        ativo = !ativo;
                    } else {
                        pilha += fa.charAt(t);
                    }
                    t--;
                }
                ativo = true;
            } else if (a == 'N') {
                if (b == 'e') {
                    fa = tabela[12][0];
                    t = fa.length() - 1;
                } else if (b == 'g') {
                    fa = tabela[12][1];
                    t = fa.length() - 1;
                } else if (b == 'h') {
                    fa = tabela[12][2];
                    t = fa.length() - 1;
                } else if (b == 'd') {
                    fa = tabela[12][3];
                    t = fa.length() - 1;
                } else if (b == 'm') {
                    fa = tabela[12][4];
                    t = fa.length() - 1;
                } else if (b == 'n') {
                    fa = tabela[12][5];
                    t = fa.length() - 1;
                } else if (b == 'o') {
                    fa = tabela[12][6];
                    t = fa.length() - 1;
                } else if (b == 'p') {
                    fa = tabela[12][7];
                    t = fa.length() - 1;
                } else if (b == 'q') {
                    fa = tabela[12][8];
                    t = fa.length() - 1;
                } else if (b == 'r') {
                    fa = tabela[12][9];
                    t = fa.length() - 1;
                } else if (b == 't') {
                    fa = tabela[12][10];
                    t = fa.length() - 1;
                } else if (b == 'k') {
                    fa = tabela[12][11];
                    t = fa.length() - 1;
                } else if (b == '$') {
                    fa = tabela[12][12];
                    t = fa.length() - 1;
                } else if (b == 'l') {
                    fa = "l";
                    t = fa.length() - 1;
                } else {
                    t = -1;
                    tamanhoPilha = 0;
                }
                while (t >= 0) {
                    if (ativo == true) {
                        pilha = pilha.substring(0, pilha.length() - 1);
                        pilha += fa.charAt(t);
                        ativo = !ativo;
                    } else {
                        pilha += fa.charAt(t);
                    }
                    t--;
                }
                ativo = true;
            } else if (a == 'O') {
                if (b == 'q') {
                    fa = tabela[13][0];
                    t = fa.length() - 1;
                } else if (b == 'r') {
                    fa = tabela[13][1];
                    t = fa.length() - 1;
                } else if (b == 'l') {
                    fa = "l";
                    t = fa.length() - 1;
                } else {
                    t = -1;
                    tamanhoPilha = 0;
                }
                while (t >= 0) {
                    if (ativo == true) {
                        pilha = pilha.substring(0, pilha.length() - 1);
                        pilha += fa.charAt(t);
                        ativo = !ativo;
                    } else {
                        pilha += fa.charAt(t);
                    }
                    t--;
                }
                ativo = true;
            } else if (a == 'P') {
                if (b == 'a') {
                    fa = tabela[14][0];
                    t = fa.length() - 1;
                } else if (b == 's') {
                    fa = tabela[14][1];
                    t = fa.length() - 1;
                } else if (b == 'b') {
                    fa = tabela[14][2];
                    t = fa.length() - 1;
                } else if (b == 'l') {
                    fa = "l";
                    t = fa.length() - 1;
                } else {
                    t = -1;
                    tamanhoPilha = 0;
                }
                while (t >= 0) {
                    if (ativo == true) {
                        pilha = pilha.substring(0, pilha.length() - 1);
                        pilha += fa.charAt(t);
                        ativo = !ativo;
                    } else {
                        pilha += fa.charAt(t);
                    }
                    t--;
                }
                ativo = true;
            } else if (a == b) {
                pilha = pilha.substring(0, pilha.length() - 1);
                entrada = entrada.substring(1, entrada.length());
            } else if (a == '&') {
                pilha = pilha.substring(0, pilha.length() - 1);
            } else {
                t = -1;
                tamanhoPilha = 0;
            }
            if (tamanhoPilha != 0) {
                tamanhoPilha = pilha.length();
            }
        }
        if (pilha.length() == 0) {
            if (entrada.charAt(0) == '$') {
                System.out.println("\nAnalise sintatica: ACEITA !\n");
            } else {
                System.out.println("\nAnalise sintatica: REJEITA erro sintatico\n");
            }
        } else {
            System.out.println("\nAnalise sintatica: REJEITA erro sintatico\n");

        }
    }

}

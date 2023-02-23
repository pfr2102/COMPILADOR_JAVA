import compilerTools.TextColor;
import java.awt.Color;

%%
%class LexerColor
%type TextColor
%char
%{
    private TextColor textColor(long start, int size, Color color){
        return new TextColor((int) start, size, color);
    }
%}
/* Variables básicas de comentarios y espacios */
TerminadorDeLinea = \r|\n|\r\n
EntradaDeCaracter = [^\r\n]
EspacioEnBlanco = {TerminadorDeLinea} | [ \t\f]
ComentarioTradicional = "/*" [^*] ~"*/" | "/*" "*"+ "/"
FinDeLineaComentario = "//" {EntradaDeCaracter}* {TerminadorDeLinea}?
ContenidoComentario = ( [^*] | \*+ [^/*] )*
ComentarioDeDocumentacion = "/**" {ContenidoComentario} "*"+ "/"

/* Comentario */
Comentario = {ComentarioTradicional} | {FinDeLineaComentario} | {ComentarioDeDocumentacion}


/* Identificador */
Letra = [A-Za-zÑñ_ÁÉÍÓÚáéíóúÜü]
Digito = [0-9]
Identificador = {Letra}({Letra}|{Digito})*

/* Número */
Numero = 0 | [1-9][0-9]*
%%

/* Comentarios o espacios en blanco */
{Comentario} { return textColor(yychar, yylength(), new Color(146, 146, 146)); }
{EspacioEnBlanco} {/*IGNORAR*/}

/*Palabras reservadas*/
"IMPORT" | "import" | "Import" { return textColor( yychar, yylength(), new Color(255, 140, 0)); }
"DEF" | "def" | "Def" { return textColor( yychar, yylength(), new Color(19,141,50)); }
"CLASS" | "class" | "Class" { return textColor(yychar, yylength(), new Color(30,148,195)); }

/* condicion */
"IF" | "if" | "If" { return textColor(yychar, yylength(), new Color(0, 0, 139)); }
"ELSE" | "else" | "Else" { return textColor(yychar, yylength(), new Color(0, 0, 139));}

/* ciclos */
"FOR" | "for" | "For" { return textColor(yychar, yylength(), new Color(0, 0, 139)); }
"IN" | "in" | "In" { return textColor(yychar, yylength(), new Color(0, 0, 139)); }
"WHILE" | "while" | "While" { return textColor(yychar, yylength(), new Color(0, 0, 139)); }
"RANGE" | "range" | "Range" { return textColor(yychar, yylength(), new Color(0, 0, 139));  }

"SELF" | "self" | "Self" {return textColor(yychar, yylength(), new Color(0, 0, 139));  }
"TRY" | "try" | "Try" { return textColor(yychar, yylength(), new Color(0, 0, 139)); }
"EXCEPT" | "except" | "Except" { return textColor(yychar, yylength(), new Color(0, 0, 139)); }
"RETURN" | "return" | "Return" { return textColor(yychar, yylength(), new Color(148, 58, 173)); }
"BREAK" | "break" | "Break" { return textColor(yychar, yylength(), new Color(0, 0, 139)); }
"NEXT" | "next" | "Next" { return textColor(yychar, yylength(), new Color(255, 140, 0));  }
"INPUT" | "input" | "Input" | "PRINT" | "print" | "Print" { return textColor(yychar, yylength(), new Color(255, 140, 0));  }

/* Tipos de dato */
"STRING" | "string" | "String" { return textColor(yychar, yylength(), new Color(148, 58, 173)); }
"INT" | "int" | "Int" {return textColor(yychar, yylength(), new Color(148, 58, 173));}
"FLOAT" | "float" | "Float" { return textColor(yychar, yylength(), new Color(148, 58, 173)); }
"BOOLEAN" | "boolean" | "Boolean" { return textColor(yychar, yylength(), new Color(0, 0, 139)); }

"TRUE" | "true" | "True" { return textColor(yychar, yylength(), new Color(148, 58, 173)); }
"FALSE" | "false" | "False" { return textColor(yychar, yylength(), new Color(148, 58, 173)); }

"POWER" | "power" | "Power" { return textColor(yychar, yylength(), new Color(255, 140, 0)); }
"SQRT" | "sqrt" | "Sqrt" { return textColor(yychar, yylength(), new Color(255, 140, 0)); }
"AND" | "and" | "And" { return textColor(yychar, yylength(), new Color(178, 34, 34)); }
"OR" | "or" | "Or" { return textColor(yychar, yylength(), new Color(178, 34, 34)); }
"NOT" | "not" | "Not" { return textColor(yychar, yylength(), new Color(178, 34, 34)); }
"BEGIN" | "begin" | "Begin" { return textColor(yychar, yylength(), new Color(255, 140, 0)); }
"END" | "end" | "End" { return textColor(yychar, yylength(), new Color(255, 140, 0)); }


\( { return textColor(yychar, yylength(), new Color(96, 96, 96)); }
\) { return textColor(yychar, yylength(), new Color(96, 96, 96)); }
\{ { return textColor(yychar, yylength(), new Color(96, 96, 96)); }
\} { return textColor(yychar, yylength(), new Color(96, 96, 96)); }
\[ { return textColor(yychar, yylength(), new Color(96, 96, 96)); }
\] { return textColor(yychar, yylength(), new Color(96, 96, 96)); }
"++" { return textColor(yychar, yylength(), new Color(96, 96, 96)); }
"--" { return textColor(yychar, yylength(), new Color(96, 96, 96)); }

\" [a-zA-Z0-9_.-]* \" {return textColor(yychar, yylength(), new Color(68, 196, 30));}

/* IDs */
{Identificador} {return textColor(yychar, yylength(), new Color(0, 0, 0));}

. { /* Ignorar */ }
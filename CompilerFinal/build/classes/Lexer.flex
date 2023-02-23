import compilerTools.Token;

%%
%class Lexer
%type Token
%line
%column
%{
    private Token token(String lexeme, String lexicalComp, int line, int column){
        return new Token(lexeme, lexicalComp, line+1, column+1);
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
Numero = {Digito} ({Digito})*

/* Número */
Numero = 0 | [1-9][0-9]*
%%

/* Comentarios o espacios en blanco */
{Comentario}|{EspacioEnBlanco} { /*Ignorar*/ }


/*Numero*/
{Numero} "." {Numero} { return token(yytext(), "REAL", yyline, yycolumn); }
{Numero} { return token(yytext(), "NUMERO", yyline, yycolumn); }

/*Operadores*/
"+" { return token(yytext(), "SUMA", yyline, yycolumn); }
"-" { return token(yytext(), "RESTA", yyline, yycolumn); }
"/" { return token(yytext(), "DIVISION", yyline, yycolumn); }
"*" { return token(yytext(), "MULTIPLICACION", yyline, yycolumn); }

/*Logicos*/
"==" { return token(yytext(), "IGUAL", yyline, yycolumn); }
"!=" { return token(yytext(), "DIFERENTE", yyline, yycolumn); }
">" { return token(yytext(), "MAYORQUE", yyline, yycolumn); }
"<" { return token(yytext(), "MENORQUE", yyline, yycolumn); }
">=" { return token(yytext(), "MAYORIGUALQUE", yyline, yycolumn); }
"<=" { return token(yytext(), "MENORIGUALQUE", yyline, yycolumn); }

"." { return token(yytext(), "PUNTO", yyline, yycolumn); }
"," { return token(yytext(), "COMA", yyline, yycolumn); }
":" { return token(yytext(), "DOSPUNTOS", yyline, yycolumn); }
";" { return token(yytext(), "PUNTOCOMA", yyline, yycolumn); }
\' { return token(yytext(), "COMILLASIMPLE", yyline, yycolumn); }
\" [a-zA-Z0-9_.-]* \" { return token(yytext(), "CADENA", yyline, yycolumn); }

"=" { return token(yytext(), "ASIGNACION", yyline, yycolumn); }


\" { return token(yytext(), "COMILLADOBLE", yyline, yycolumn); }
\( { return token(yytext(), "PARENTESISABIERTO", yyline, yycolumn); }
\) { return token(yytext(), "PARENTESISCERRADO", yyline, yycolumn); }
\{ { return token(yytext(), "LLAVEABIERTO", yyline, yycolumn); }
\} { return token(yytext(), "LLAVECERRADO", yyline, yycolumn); }
\[ { return token(yytext(), "CORCHETEABIERTO", yyline, yycolumn); }
\] { return token(yytext(), "CORCHETECERRADO", yyline, yycolumn); }

"++" { return token(yytext(), "INCREMENTO", yyline, yycolumn); }
"--" { return token(yytext(), "DECREMENTO", yyline, yycolumn); }

/*Palabras reservadas*/
"IMPORT" | "import" | "Import" { return token(yytext(), "IMPORT", yyline, yycolumn); }
"DEF" | "def" | "Def" { return token(yytext(), "DEF", yyline, yycolumn); }
"CLASS" | "class" | "Class" { return token(yytext(), "CLASS", yyline, yycolumn); }
"IF" | "if" | "If" { return token(yytext(), "IF", yyline, yycolumn); }
"ELSE" | "else" | "Else" { return token(yytext(), "ELSE", yyline, yycolumn); }
"FOR" | "for" | "For" { return token(yytext(), "FOR", yyline, yycolumn); }
"IN" | "in" | "In" { return token(yytext(), "IN", yyline, yycolumn); }
"RANGE" | "range" | "Range" { return token(yytext(), "RANGE", yyline, yycolumn); }
"SELF" | "self" | "Self" { return token(yytext(), "SELF", yyline, yycolumn); }
"WHILE" | "while" | "While" { return token(yytext(), "WHILE", yyline, yycolumn); }
"TRY" | "try" | "Try" { return token(yytext(), "TRY", yyline, yycolumn); }
"EXCEPT" | "except" | "Except" { return token(yytext(), "EXCEPT", yyline, yycolumn); }
"RETURN" | "return" | "Return" { return token(yytext(), "RETURN", yyline, yycolumn); }
"BREAK" | "break" | "Break" { return token(yytext(), "BREAK", yyline, yycolumn); }
"NEXT" | "next" | "Next" { return token(yytext(), "NEXT", yyline, yycolumn); }
"INPUT" | "input" | "Input" { return token(yytext(), "INPUT", yyline, yycolumn); }
"OUTPUT" | "output" | "Output" { return token(yytext(), "OUTPUT", yyline, yycolumn); }
"PRINT" | "print" | "Print" { return token(yytext(), "PRINT", yyline, yycolumn); }

"INT" | "int" | "Int" { return token(yytext(), "INT", yyline, yycolumn); }
"FLOAT" | "float" | "Float" { return token(yytext(), "FLOAT", yyline, yycolumn); }
"BOOLEAN" | "boolean" | "Boolean" { return token(yytext(), "BOOLEAN", yyline, yycolumn); }
"STRING" | "string" | "String" { return token(yytext(), "STRING", yyline, yycolumn); }

"TRUE" | "true" | "True" { return token(yytext(), "TRUE", yyline, yycolumn); }
"FALSE" | "false" | "False" { return token(yytext(), "FALSE", yyline, yycolumn); }

"POWER" | "power" | "Power" { return token(yytext(), "POWER", yyline, yycolumn); }
"SQRT" | "sqrt" | "Sqrt" { return token(yytext(), "SQRT", yyline, yycolumn); }

"AND" | "and" | "And" { return token(yytext(), "AND", yyline, yycolumn); }
"OR" | "or" | "Or" { return token(yytext(), "OR", yyline, yycolumn); }
"NOT" | "not" | "Not" { return token(yytext(), "NOT", yyline, yycolumn); }

"BEGIN" | "begin" | "Begin" { return token(yytext(), "BEGIN", yyline, yycolumn); }
"END" | "end" | "End" { return token(yytext(), "END", yyline, yycolumn); }

/* IDs */
{Identificador} { return token(yytext(), "ID", yyline, yycolumn); }


/**/
. { return token(yytext(), "ERROR", yyline, yycolumn); }


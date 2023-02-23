.model small
.stack
.data 
     B dw 1,0
     EDAD dw 1,0
     H dw 1,0
     x dw 1,0
.code
INICIO: MOV AX, @DATA
        MOV DS, AX
        MOV ES, AX

;=============================
;FLOAT B = 8 * 0 + 1 * 2 * 3 ;
;=============================
MOV AX,8
MOV BX,0
MUL BX
MOV BX,1
MOV CX,2
MUL CX
MOV BX,3
MUL BX
ADD AX,BX

;=============================
;INT EDAD = 34 * 8 + 5 / 8 ;
;=============================
MOV AX,34
MOV BX,8
MUL BX
MOV BX,5
MOV CX,8
DIV CX
ADD AX,BX

;=============================
;INT H = 7 - 3 / 8 + 5 * 1 ;
;=============================
MOV AX,3
MOV BX,8
DIV BX
MOV BX,5
MOV CX,1
MUL CX
MOV BX,7
SUB AX,BX,AX
ADD AX,BX

;=============================
;int x = 5 * 0 + 5 / 2 ;
;=============================
MOV AX,5
MOV BX,0
MUL BX
MOV BX,5
MOV CX,2
DIV CX
ADD AX,BX

;=============================
;EDAD = 10 - 0 + 4 * 5 ;
;=============================
MOV AX,4
MOV BX,5
MUL BX
MOV BX,10
MOV CX,0
SUB BX,CX
ADD AX,BX
FIN: MOV AX,4C00H
     INT 21H
     END

import com.formdev.flatlaf.FlatIntelliJLaf;
import compilerTools.CodeBlock;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import compilerTools.Directory;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Grammar;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 *
 * @author marco
 */
public class Compilador extends javax.swing.JFrame {

    private String title,ensamblador;
    private Directory directorio;
    private String codigoIntermedio;
    private String codigoOptimizado;
    private ArrayList<Token> tokens;
    private ArrayList<ErrorLSSL> errors;
    private ArrayList<TextColor> textsColor;
    private Timer timerKeyReleased;
    private ArrayList<Production> identProd;
    private ArrayList<Production> ifProd;
    private ArrayList<Production> whileProd;
    private ArrayList<String> codObj;
    private ArrayList<String> codObjComp;
    private ArrayList<String> variables;
    
    private ArrayList<Production> funcProd;
    private HashMap<String, String> identificadores;
    private boolean codeHasBeenCompiled = false;
    private ArrayList<Production> asigProd;
    private ArrayList<Production> asigProdConID;
    private ArrayList<Production> compaProdIzq;
    private ArrayList<Production> compaProdDer;
    private ArrayList<Production> compaProdDoble;
    private ArrayList<Production> operProdIzq;
    private ArrayList<Production> operProdDer;
    private ArrayList<Production> operProdDoble;
    
    private ArrayList<Production> identProdCopia;
    private ArrayList<Production> asigProdCopia;
    ArrayList<ArrayList<Token>> prods = new ArrayList<ArrayList<Token>>(); //para optimización, vienen de 

    


    /**
     * Creates new form Compilador
     */
    public Compilador() {
        initComponents();
        init();
    }

    private void init() {
        title = "Compiler";
        setLocationRelativeTo(null);
        setTitle(title);
        directorio = new Directory(this, jtpCode, title, ".comp");
        addWindowListener(new WindowAdapter() {// Cuando presiona la "X" de la esquina superior derecha
            @Override
            public void windowClosing(WindowEvent e) {
                directorio.Exit();
                System.exit(0);
            }
        });
        Functions.setLineNumberOnJTextComponent(jtpCode);
        timerKeyReleased = new Timer(300, (ActionEvent e) -> {
            timerKeyReleased.stop();
            int posicion = jtpCode.getCaretPosition();
            jtpCode.setText(jtpCode.getText().replaceAll("[\r]+", ""));
            jtpCode.setCaretPosition(posicion);
            colorAnalysis();
        });
        Functions.insertAsteriskInName(this, jtpCode, () -> {
            timerKeyReleased.restart();
        });
        tokens = new ArrayList<>();
        errors = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProd = new ArrayList<>();
        ifProd = new ArrayList<>();
        whileProd = new ArrayList<>();
        asigProd = new ArrayList<>();
        asigProdConID = new ArrayList<>();
        compaProdIzq = new ArrayList<>();
        compaProdDer = new ArrayList<>();
        compaProdDoble = new ArrayList<>();
        operProdIzq = new ArrayList<>();
        operProdDer = new ArrayList<>();
        operProdDoble = new ArrayList<>();
        funcProd = new ArrayList<>();
        codObj = new ArrayList<>();
        codObjComp = new ArrayList<>();
        variables = new ArrayList<>();

        identificadores = new HashMap<>();
        Functions.setAutocompleterJTextComponent(new String[]{}, jtpCode, () -> {
            timerKeyReleased.restart();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpCode = new javax.swing.JTextPane();
        panelButtonCompilerExecute = new javax.swing.JPanel();
        btnCompilar = new javax.swing.JButton();
        btnCompilar1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnTripletas = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnEjecutar = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaOutputConsole = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTokens = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        LabelTecNM = new javax.swing.JLabel();
        LabelITT = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        rootPanel.setBackground(new java.awt.Color(204, 204, 204));

        jScrollPane1.setViewportView(jtpCode);

        btnCompilar.setText("Nuevo");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        btnCompilar1.setText("Compilar");
        btnCompilar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilar1ActionPerformed(evt);
            }
        });

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Abrir");
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnTripletas.setText("Tripletas");
        btnTripletas.setEnabled(false);
        btnTripletas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTripletasActionPerformed(evt);
            }
        });

        jButton3.setText("CodObj,");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Ensamblador");
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        jButton5.setText("Optimizar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonCompilerExecuteLayout = new javax.swing.GroupLayout(panelButtonCompilerExecute);
        panelButtonCompilerExecute.setLayout(panelButtonCompilerExecuteLayout);
        panelButtonCompilerExecuteLayout.setHorizontalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCompilar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCompilar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTripletas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEjecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelButtonCompilerExecuteLayout.setVerticalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCompilar)
                    .addComponent(btnCompilar1)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(btnTripletas)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(btnEjecutar)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtaOutputConsole.setEditable(false);
        jtaOutputConsole.setColumns(20);
        jtaOutputConsole.setRows(5);
        jScrollPane2.setViewportView(jtaOutputConsole);

        tblTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Componente léxico", "Lexema", "[Línea, Columna]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTokens.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblTokens);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); // NOI18N
        jLabel2.setText("Compilador - LyA II");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel2)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        LabelTecNM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Logo TecNM.png"))); // NOI18N

        LabelITT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Logo ITT.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabelTecNM)
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addComponent(LabelITT)
                .addGap(54, 54, 54))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelITT)
                    .addComponent(LabelTecNM)))
        );

        jLabel5.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); // NOI18N
        jLabel5.setText("Tabla de Tokens");

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                    .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rootPanelLayout.createSequentialGroup()
                .addContainerGap(126, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rootPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)))
                    .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        getContentPane().add(rootPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        directorio.New();
        clearFields();
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        btnCompilar.doClick();
        if (codeHasBeenCompiled) {
            if (!errors.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se puede ejecutar el código ya que se encontró uno o más errores",
                        "Error en la compilación", JOptionPane.ERROR_MESSAGE);
            } else {
                CodeBlock codeBlock = Functions.splitCodeInCodeBlocks(tokens, "{", "}", ";");
                System.out.println(codeBlock);
                ArrayList<String> blocksOfCode = codeBlock.getBlocksOfCodeInOrderOfExec();
                System.out.println(blocksOfCode);

            }
        }
    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void btnCompilar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilar1ActionPerformed
        codObjComp.clear();
        variables.clear();
        compile();
    }//GEN-LAST:event_btnCompilar1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          if (directorio.Save()) {           
            clearFields();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         if (directorio.Open()) {
            colorAnalysis();
            clearFields();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnTripletasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTripletasActionPerformed
        JTextArea textArea = new JTextArea(codigoIntermedio);
        JScrollPane scrollPane = new JScrollPane(textArea);  
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        textArea.setEditable(false);
        scrollPane.setPreferredSize( new Dimension( 400, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane, "Tripletas",JOptionPane.PLAIN_MESSAGE);        
    }//GEN-LAST:event_btnTripletasActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String obj ="------CODIGO OBJETO-------\n";
        for(String obj1: codObjComp){ obj+=obj1; } 
        JTextArea textArea = new JTextArea(obj);
        //generamos el codigo ensamblador
        ensamblador =  ensamblador(obj.replaceAll("------CODIGO OBJETO-------\n\n\n",""));
        JScrollPane scrollPane = new JScrollPane(textArea);  
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        textArea.setEditable(false);
        scrollPane.setPreferredSize( new Dimension( 400, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane, "Codigo Objeto",JOptionPane.PLAIN_MESSAGE);  
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //creamos la ventana emergente para presentar el codigo ensamblador
        JTextArea textArea = new JTextArea(ensamblador);
        JScrollPane scrollPane = new JScrollPane(textArea);  
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        textArea.setEditable(false);
        scrollPane.setPreferredSize( new Dimension( 400, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane, "Tripletas",JOptionPane.PLAIN_MESSAGE);
        //creamos el archivo y lo ejecutamos
        archivoT(".\\COD_OBJ.asm",ensamblador);
        abrirarchivo(".\\COD_OBJ.asm");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        optimizacion();
        JTextArea textArea = new JTextArea(codigoOptimizado);
        JScrollPane scrollPane = new JScrollPane(textArea);  
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        textArea.setEditable(false);
        scrollPane.setPreferredSize( new Dimension( 400, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane, "Optimizacion",JOptionPane.PLAIN_MESSAGE);
                codeHasBeenCompiled = true;

    }//GEN-LAST:event_jButton5ActionPerformed
    public void archivoT(String ruta, String Objeto) {
          try {
              //String ruta = "D:\\Pollo\\Descargas\\COMPILADORES COMPAÑEROS\\MicroCompiler_09\\src\\MicroC\\codigoTres.txt";
              String contenido = Objeto;
              File file = new File(ruta);
              // Si el archivo no existe es creado
              if (!file.exists()) {
                  file.createNewFile();
              }
              FileWriter fw = new FileWriter(file);
              BufferedWriter bw = new BufferedWriter(fw);
              bw.write(contenido);
              bw.close();
          } catch (Exception e) {
              e.printStackTrace();
          }
      }//archivoT

    public void abrirarchivo(String archivo){
           try {
                  File objetofile = new File (archivo);
                  Desktop.getDesktop().open(objetofile);
           }catch (IOException ex) {
                  System.out.println(ex);
           }
       }//fin_AbrirArchivo
    private void compile() {
        btnTripletas.setEnabled(true);
        jButton3.setEnabled(true);
        jButton4.setEnabled(true);
        clearFields();
        lexicalAnalysis();
        fillTableTokens();
        syntacticAnalysis();
        semanticAnalysis();
        
        printConsole();
        int resp = JOptionPane.showConfirmDialog(null, "¿Desea optimizar el código?", "", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            jButton4.setEnabled(false);
            optimizacion();
        } else {
            codigoIntermedio();
}
        
        //codigoIntermedio();
        codeHasBeenCompiled = true;
    }

    private void lexicalAnalysis() {
        // Extraer tokens
        Lexer lexer;
        try {
            File codigo = new File("code.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexer = new Lexer(entrada);
            while (true) {
                Token token = lexer.yylex();
                if (token == null) {
                    break;
                }
                tokens.add(token);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
    }

    private void syntacticAnalysis() {
        Grammar gramatica = new Grammar(tokens, errors);

        /*ELIMINACION DE ERRORES*/
        gramatica.delete(new String[]{"ERROR"}, 1);
        /* Mostrar gramáticas */        
        gramatica.group("REAL", "NUMERO PUNTO NUMERO");
         
                
        //-------------OPERACION---------------
        //FORMAS DE CREAR UNA FUNCION CORRECTAMENTE   
        gramatica.group("OPERACION", "REAL (SUMA|RESTA|MULTIPLICACION|DIVISION) REAL");
        gramatica.group("OPERACION", "REAL (SUMA|RESTA|MULTIPLICACION|DIVISION) NUMERO");
        gramatica.group("OPERACION", "REAL (SUMA|RESTA|MULTIPLICACION|DIVISION) ID");
        gramatica.group("OPERACION", "NUMERO (SUMA|RESTA|MULTIPLICACION|DIVISION) REAL");
        gramatica.group("OPERACION", "ID (SUMA|RESTA|MULTIPLICACION|DIVISION) NUMERO",operProdIzq);
        gramatica.group("OPERACION", "ID (SUMA|RESTA|MULTIPLICACION|DIVISION) REAL");
        gramatica.group("OPERACION", "NUMERO (SUMA|RESTA|MULTIPLICACION|DIVISION) NUMERO");
        gramatica.group("OPERACION", "NUMERO (SUMA|RESTA|MULTIPLICACION|DIVISION) ID",operProdDer);
        gramatica.group("OPERACION", "ID (SUMA|RESTA|MULTIPLICACION|DIVISION) NUMERO",operProdIzq);
        gramatica.group("OPERACION", "ID (SUMA|RESTA|MULTIPLICACION|DIVISION) ID",operProdDoble);
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) REAL");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) NUMERO");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) ID",operProdDer);
        gramatica.group("OPERACION", "REAL (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "NUMERO (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "ID (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION",operProdIzq);
        //probablemente haya una mejor forma de hacer esto, ni modo!
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        gramatica.group("OPERACION", "OPERACION (SUMA|RESTA|MULTIPLICACION|DIVISION) OPERACION");
        //debería de poder servir con 20 términos entonces, creo
        //ERRORES operacion
        gramatica.group("OPERACION_ER", "NUMERO (SUMA|RESTA|MULTIPLICACION|DIVISION)",2,"ERROR_SINTACTICO: se necesita un minimo de 2 valores para ralizar la operacion [#, %]");
        gramatica.group("OPERACION_ER", "(SUMA|RESTA|MULTIPLICACION|DIVISION) NUMERO",2,"ERROR SINTACTICO: se necesita un minimo de 2 valores para ralizar la operacion [#, %]");
        gramatica.group("OPERACION_ER", "ID (SUMA|RESTA|MULTIPLICACION|DIVISION)",2,"ERROR SINTACTICO: se necesita un minimo de 2 valores para ralizar la operacion [#, %]");
        gramatica.group("OPERACION_ER", "(SUMA|RESTA|MULTIPLICACION|DIVISION) ID",2,"ERROR SINTACTICO: se necesita un minimo de 2 valores para ralizar la operacion [#, %]");
       
        //FORMA CORRECTA DE DECLARAR UNA VARIABLE------------------------------------------------------------
        gramatica.group("DECL_FLOAT", "FLOAT ID PUNTOCOMA",identProd);
        gramatica.group("DECL_FLOAT", "FLOAT ID ASIGNACION ID PUNTOCOMA",identProd);
        gramatica.group("DECL_FLOAT", "FLOAT ID ASIGNACION REAL PUNTOCOMA",identProd);
        gramatica.group("DECL_FLOAT", "FLOAT ID ASIGNACION OPERACION PUNTOCOMA",identProd);
        
        gramatica.group("DECL_INT", "INT ID PUNTOCOMA",identProd);
        gramatica.group("DECL_INT", "INT ID ASIGNACION ID PUNTOCOMA",identProd);
        gramatica.group("DECL_INT", "INT ID ASIGNACION NUMERO PUNTOCOMA",identProd);
        gramatica.group("DECL_INT", "INT ID ASIGNACION OPERACION PUNTOCOMA",identProd);
        
        gramatica.group("DECL_BOOL", "BOOLEAN ID PUNTOCOMA",identProd);
        gramatica.group("DECL_BOOL", "BOOLEAN ID ASIGNACION ID PUNTOCOMA",identProd);
        gramatica.group("DECL_BOOL", "BOOLEAN ID ASIGNACION (TRUE|FALSE) PUNTOCOMA",identProd);
                
        gramatica.group("DECL_STRING", "STRING ID PUNTOCOMA",identProd);
        gramatica.group("DECL_STRING", "STRING ID ASIGNACION ID PUNTOCOMA",identProd);
        gramatica.group("DECL_STRING", "STRING ID ASIGNACION CADENA PUNTOCOMA",identProd); 
        //ERRORES SINTACTICOS---------------------------------------------------------------------------
        //POSIBLES ERRORES AL DECLARAR UNA VARIABLE INT O FLOAT  
        gramatica.group("DECL_INT", "INT ID ASIGNACION PUNTOCOMA",2,"ERROR_SINTACTICO: FALTA ASIGNAR UN VALOR A LA VARIABLE [#, %]");
        gramatica.group("DECL_INT", "INT ID NUMERO PUNTOCOMA",2,"ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]");
        gramatica.group("DECL_INT", "INT ID ID PUNTOCOMA",2,"ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]");
        gramatica.group("DECL_INT", "INT ID NUMERO",2,"ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]");
        gramatica.group("DECL_INT", "INT ID ID",2,"ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]");
        gramatica.group("DECL_INT", "INT ID OPERACION PUNTOCOMA",2,"ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]");
        gramatica.group("DECL_INT", "INT ID OPERACION",2,"ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]");
        gramatica.group("DECL_INT", "INT ID ASIGNACION ID",2,"ERROR_SINTACTICO: PUNTOCOMA(;) NO AGREGADO EN LA DECLARACION [#, %]");
        gramatica.group("DECL_INT", "INT ID ASIGNACION REAL",2,"ERROR_SINTACTICO: VALOR NO ENTERO [#, %]");

//gramatica.group("DECL_INT", "INT ID ASIGNACION NUMERO",2,"ERROR_SINTACTICO: PUNTOCOMA(;) NO AGREGADO EN LA DECLARACION [#, %]");
        gramatica.group("DECL_INT", "INT ID ASIGNACION OPERACION",2,"ERROR_SINTACTICO: PUNTOCOMA(;) NO AGREGADO EN LA DECLARACION [#, %]");
        gramatica.group("DECL_INT", "INT  ASIGNACION NUMERO PUNTOCOMA",2,"ERROR_SINTACTICO: ID NO AGREGADO EN LA DECLARACION [#, %]");
        gramatica.group("DECL_INT", "INT  ASIGNACION ID PUNTOCOMA",2,"ERROR_SINTACTICO: ID NO AGREGADO EN LA DECLARACION [#, %]");
        gramatica.group("DECL_INT", "INT  ASIGNACION OPERACION PUNTOCOMA",2,"ERROR_SINTACTICO: ID NO AGREGADO EN LA DECLARACION [#, %]");
        
        //POSIBLES ERRORES AL DECLARAR UN FLOAT
        gramatica.group("DECL_FLOAT", "FLOAT ID ASIGNACION PUNTOCOMA",2,"ERROR_SINTACTICO: FALTA ASIGNAR UN VALOR A LA VARIABLE [#, %]");
        gramatica.group("DECL_FLOAT", "FLOAT ID REAL PUNTOCOMA",2,"ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]");        
        gramatica.group("DECL_FLOAT", "FLOAT ID REAL",2,"ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]");
        gramatica.group("DECL_FLOAT", "FLOAT ID ASIGNACION REAL",2,"ERROR_SINTACTICO: PUNTOCOMA(;) NO AGREGADO EN LA DECLARACION [#, %]");
        gramatica.group("DECL_FLOAT", "FLOAT ASIGNACION REAL PUNTOCOMA",2,"ERROR_SINTACTICO: ID NO AGREGADO EN LA DECLARACION [#, %]");
        gramatica.group("DECL_FLOAT", "FLOAT ID ASIGNACION NUMERO PUNTOCOMA",2,"Error sintáctico: Valor float sin punto decimal [#, %]");

        //POSIBLES ERRORES AL DECLARAR UN FLOAT
        gramatica.group("DECL_STRING", "STRING ID ASIGNACION PUNTOCOMA",2,"ERROR_SINTACTICO: FALTA ASIGNAR UN VALOR A LA VARIABLE [#, %]");
        gramatica.group("DECL_STRING", "STRING ID CADENA PUNTOCOMA",2,"ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]");        
        gramatica.group("DECL_STRING", "STRING ID CADENA",2,"ERROR_SINTACTICO: FALTA DEL TOKEN DE ASIGNACION EN LA DECLARACION [#, %]");
        gramatica.group("DECL_STRING", "STRING ID ASIGNACION CADENA",2,"ERROR_SINTACTICO: PUNTOCOMA(;) NO AGREGADO EN LA DECLARACION [#, %]");
        gramatica.group("DECL_STRING", "STRING ASIGNACION CADENA PUNTOCOMA",2,"ERROR_SINTACTICO: ID NO AGREGADO EN LA DECLARACION [#, %]");
        
        //ERRORES SEMANTICOS DE VARIABLES -------------------------------------------------------------
        gramatica.group("RESERV_INDEB", "(STRING|INT|FLOAT|BOOLEAN) (IMPORT|DEF|CLASS|IF|ELSE|FOR|IN|WHILE|RETURN)",2, "ERROR SEMANTICO \\{}: USO INDEBIDO DE PALABRAS RESERVADAS [#,%]");
       
        gramatica.group("ERROR_OP_STRING", "(SUMA|RESTA|MULTIPLICACION|DIVISION) CADENA",2, "ERROR SEMANTICO \\{}: OPERACION NO PERMITIDA PARA CADENA [#,%]");
        gramatica.group("ERROR_OP_STRING", "CADENA (SUMA|RESTA|MULTIPLICACION|DIVISION)",2, "ERROR SEMANTICO \\{}: OPERACION NO PERMITIDA PARA CADENA [#,%]");
        gramatica.group("ERROR_OP_BOOLEAN", "(SUMA|RESTA|MULTIPLICACION|DIVISION) (TRUE|FALSE)",2, "ERROR SEMANTICO \\{}: OPERACION NO PERMITIDA PARA BOOLEANO [#,%]");
        gramatica.group("ERROR_OP_BOOLEAN", "(TRUE|FALSE) (SUMA|RESTA|MULTIPLICACION|DIVISION)",2, "ERROR SEMANTICO \\{}: OPERACION NO PERMITIDA PARA BOOLEANO [#,%]");

        gramatica.group("DECL_INT", "(INT ID ASIGNACION REAL PUNTOCOMA)",2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES ENTERO [#,%]");
        gramatica.group("DECL_INT", "(INT ID ASIGNACION CADENA)",2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES ENTERO [#,%]");
        gramatica.group("DECL_INT", "(INT ID ASIGNACION (TRUE|FALSE))",2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES ENTERO [#,%]");
        gramatica.group("DECL_INT", "INT",2,"ERROR");
        
        gramatica.group("DECL_FLOAT", "(FLOAT ID ASIGNACION CADENA)",2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES DECIMAL [#,%]");
        gramatica.group("DECL_FLOAT", "(FLOAT ID ASIGNACION (TRUE|FALSE))",2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES DECIMAL [#,%]");
        gramatica.group("DECL_FLOAT", "(FLOAT ID ASIGNACION NUMERO PUNTOCOMA)",2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES DECIMAL [#,%]");
        gramatica.group("ERROR_FLOAT", "FLOAT",2,"ERROR");
        
        gramatica.group("DECL_STRING", "(STRING ID ASIGNACION NUMERO)",2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES CADENA [#,%]");
        gramatica.group("DECL_STRING", "(STRING ID ASIGNACION (TRUE|FALSE))",2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES CADENA [#,%]");
        gramatica.group("DECL_STRING", "STRING",2,"ERROR");
        
        gramatica.group("ERROR_ASIG_BOOL", "(BOOLEAN ID ASIGNACION NUMERO)",2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES BOOLEANO [#,%]");
        gramatica.group("ERROR_ASIG_BOOL", "(BOOLEAN ID ASIGNACION (TRUE|FALSE))",2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES BOOLEANO [#,%]");
        gramatica.group("ERROR_ASIG_BOOL", "(BOOLEAN ID ASIGNACION CADENA)",2, "ERROR SEMANTICO \\{}: VALOR ASIGNADO NO ES BOOLEANO [#,%]");
        
        
        //ASIGNACION DE UN ID
        gramatica.group("PROD_ASIG", "ID ASIGNACION (CADENA|REAL|NUMERO|TRUE|FALSE) PUNTOCOMA",asigProd);
        gramatica.group("PROD_ASIG", "ID ASIGNACION OPERACION PUNTOCOMA",asigProd);
        gramatica.group("PROD_ASIG_ID", "ID ASIGNACION ID PUNTOCOMA",asigProdConID);
        
        //--------------------------------------------------------------------------------------------
        
        //-------------CONDICION--------------------------
        //FORMAS CORRECTAS DE CREAR UNA CONDICION
        gramatica.group("CONDICION", "NUMERO (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) NUMERO");
        gramatica.group("CONDICION", "NUMERO (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) OPERACION");
        gramatica.group("CONDICION", "REAL (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) OPERACION");

        gramatica.group("CONDICION", "REAL (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) NUMERO");
        gramatica.group("CONDICION", "NUMERO (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) REAL");
        gramatica.group("CONDICION", "REAL (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) REAL");
        gramatica.group("CONDICION", "(TRUE|FALSE) (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) (TRUE|FALSE)");
        gramatica.group("CONDICION", "NUMERO (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) ID",compaProdDer);
        gramatica.group("CONDICION", "REAL (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) ID",compaProdDer);
        gramatica.group("CONDICION", "ID (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) NUMERO",compaProdIzq);
        gramatica.group("CONDICION", "ID (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) OPERACION",compaProdIzq);
        gramatica.group("CONDICION", "ID (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) REAL",compaProdIzq);
        gramatica.group("CONDICION", "ID (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) ID",compaProdDoble);
        gramatica.group("CONDICION", "(TRUE|FALSE) (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) ID",compaProdDer);
        gramatica.group("CONDICION", "ID (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) (TRUE|FALSE)",compaProdIzq);

        gramatica.group("CONDICION", "CONDICION (AND|OR) CONDICION");
        
        //ERRORES SEMANTICOS
        gramatica.group("CONDICION", "NUMERO (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) CADENA",2,"ERROR_SEMANTICO \\{}: DATOS INCOMPATIBLES PARA SU COMPARACION [#, %]");
        gramatica.group("CONDICION", "REAL (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) CADENA",2,"ERROR_SEMANTICO \\{}: DATOS INCOMPATIBLES PARA SU COMPARACION [#, %]");
        gramatica.group("CONDICION", "CADENA (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) NUMERO",2,"ERROR_SEMANTICO \\{}: DATOS INCOMPATIBLES PARA SU COMPARACION [#, %]");
        gramatica.group("CONDICION", "CADENA (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) REAL",2,"ERROR_SEMANTICO \\{}: DATOS INCOMPATIBLES PARA SU COMPARACION [#, %]");
        gramatica.group("CONDICION", "NUMERO (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) (TRUE|FALSE)",2,"ERROR_SEMANTICO \\{}: DATOS INCOMPATIBLES PARA SU COMPARACION [#, %]");
        gramatica.group("CONDICION", "REAL (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) (TRUE|FALSE)",2,"ERROR_SEMANTICO \\{}: DATOS INCOMPATIBLES PARA SU COMPARACION [#, %]");
        
        
        
           
        //----------------------------------------------------------------------------------------------
        
        //----------------WHILE Y IF-----------------------
        //FORMAS CORRECTAS DE DECLARAR UN IF
        gramatica.group("INSTR_IF", "IF PARENTESISABIERTO CONDICION PARENTESISCERRADO LLAVEABIERTO",true,ifProd);
        //FORMAS CORRECTAS DE DECLARAR UN WHILE
        gramatica.group("INSTR_WHILE", "WHILE PARENTESISABIERTO CONDICION PARENTESISCERRADO LLAVEABIERTO",whileProd);
        //POSIBLES ERRORES AL DECLARAR UN IF
        gramatica.group("INSTR_IF", "IF PARENTESISABIERTO PARENTESISCERRADO LLAVEABIERTO",true,4,"ERROR_SINTACTICO: FALTA LA CONDICION [#, %]");
        gramatica.group("INSTR_IF", "IF CONDICION PARENTESISCERRADO LLAVEABIERTO",true,4,"ERROR_SINTACTICO: FALTA EL PARENTESIS ABIERTO EN LA CONDICION [#, %]");
        gramatica.group("INSTR_IF", "IF PARENTESISABIERTO CONDICION PARENTESISCERRADO",true,4,"ERROR_SINTACTICO: FALTA DE LLAVE DE APERTURA [#, %]");
        gramatica.finalLineColumn();
        gramatica.group("INSTR_IF", "IF PARENTESISABIERTO CONDICION",true,4,"ERROR_SINTACTICO: ERROR EN LA CONDICION O FALTA DEL PARENTESIS [#, %]");
        gramatica.initialLineColumn();
        gramatica.group("INSTR_IF", "IF",2,"ERROR");
        
        //POSIBLES ERRORES DE WHILE
        gramatica.group("INSTR_WHILE", "WHILE PARENTESISABIERTO PARENTESISCERRADO",true,4,"ERROR_SINTACTICO: FALTA LA CONDICION [#, %]");
        gramatica.group("INSTR_WHILE", "WHILE CONDICION PARENTESISCERRADO",true,4,"ERROR_SINTACTICO: FALTA EL PARENTESIS ABIERTO EN LA CONDICION [#, %]");
        gramatica.group("INSTR_WHILE", "WHILE PARENTESISABIERTO CONDICION PARENTESISCERRADO",true,4,"ERROR_SINTACTICO: FALTA DE LLAVE DE APERTURA [#, %]");        
        gramatica.finalLineColumn();
        gramatica.group("INSTR_WHILE", "WHILE PARENTESISABIERTO CONDICION",true,4,"ERROR_SINTACTICO: ERROR EN LA CONDICION O FALTA DEL PARENTESIS [#, %]");
        gramatica.initialLineColumn();
        gramatica.group("INSTR_WHILE", "WHILE",2,"ERROR WHILE");
        //POSIBLES ERRORES EN LAS CODICIONES
        gramatica.group("CONDICION", "NUMERO (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) ",2,"ERROR_SINTACTICO: ERROR EN LA CONDICION [#, %]");
        gramatica.group("CONDICION", "ID (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) ",2,"ERROR_SINTACTICO: ERROR EN LA CONDICION [#, %]");
        gramatica.group("CONDICION", " (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) NUMERO ",2,"ERROR_SINTACTICO: ERROR EN LA CONDICION [#, %]");
        gramatica.group("CONDICION", " (IGUAL|DIFERENTE|MAYORQUE|MENORQUE|MAYORIGUALQUE|MENORIGUALQUE) ID ",2,"ERROR_SINTACTICO: ERROR EN LA CONDICION [#, %]");       
        gramatica.group("CONDICION", "CONDICION (AND|OR)",2,"ERROR_SINTACTICO: ERROR EN LA CONDICION [#, %]");
        //------------------------------------------------------------------------

        //------------------------------------------------------------
        //INPUT-------------------------------------------------------
        //FORMA CORRECTA 
        gramatica.group("INSTR_INPUT", "INPUT PARENTESISABIERTO CADENA PARENTESISCERRADO PUNTOCOMA ",funcProd);
        gramatica.group("INSTR_INPUT", "INPUT PARENTESISABIERTO ID PARENTESISCERRADO PUNTOCOMA ",funcProd);
        
        gramatica.group("INSTR_OUTPUT", "OUTPUT PARENTESISABIERTO CADENA PARENTESISCERRADO PUNTOCOMA ",funcProd);
        gramatica.group("INSTR_OUTPUT", "OUTPUT PARENTESISABIERTO ID PARENTESISCERRADO PUNTOCOMA ",funcProd);
        
        //ERRORES SINTACTICOS 
        gramatica.group("INSTR_INPUT", "INPUT PARENTESISABIERTO CADENA PARENTESISCERRADO",2, "ERROR_SINTACTICO \\{}: FALTA DEL TOKEN (;) [#,%]");
        gramatica.group("INSTR_INPUT", "INPUT CADENA PARENTESISCERRADO PUNTOCOMA",2, "ERROR_SINTACTICO \\{}: FALTA DEL PARENTESIS ABIERTO [#,%]");
        
        //ERROR SEMANTICO
        gramatica.group("INSTR_INPUT", "INPUT PARENTESISABIERTO (NUMERO|REAL) PARENTESISCERRADO PUNTOCOMA ",2, "ERROR SEMANTICO \\{}: VALOR INVALIDO EN INPUT[#,%]");
        gramatica.group("INSTR_INPUT", "INPUT",2,"ERROR INPUT");    
        //ERRORES SINTACTICOS 
        gramatica.group("INSTR_INPUT", "OUTPUT PARENTESISABIERTO CADENA PARENTESISCERRADO",2, "ERROR_SINTACTICO \\{}: FALTA DEL TOKEN (;) [#,%]");
        gramatica.group("INSTR_INPUT", "OUTPUT CADENA PARENTESISCERRADO PUNTOCOMA",2, "ERROR_SINTACTICO \\{}: FALTA DEL PARENTESIS ABIERTO [#,%]");
        
        //ERROR SEMANTICO
        gramatica.group("INSTR_INPUT", "OUTPUT PARENTESISABIERTO (NUMERO|REAL) PARENTESISCERRADO PUNTOCOMA ",2, "ERROR SEMANTICO \\{}: VALOR INVALIDO EN INPUT[#,%]");
        gramatica.group("INSTR_INPUT", "OUTPUT",2,"ERROR INPUT"); 
        
        //------------------------------------------------------------
        
        //FORMAS DE CREAR UNA FUNCION CORRECTAMENTE
        gramatica.group("PARAMETROS", "ID (COMA ID)+");
        gramatica.group("PARAMETRO", "ID");        
        gramatica.group("FUNCION", "DEF ID PARENTESISABIERTO (PARAMETRO | PARAMETROS)? PARENTESISCERRADO ", true);
        
        gramatica.group("LLAMAR_FUNCION", "ID PARENTESISABIERTO (PARAMETRO | PARAMETROS)? PARENTESISCERRADO ", true);

         //posibles errores al declarar una funcion        
        gramatica.group("FUNCION", "DEF ID (PARAMETRO | PARAMETROS)? PARENTESISCERRADO ", true,3,"ERROR_SINTACTICO: FALTA PARENTESIS ABIERTO [#, %]");
        gramatica.group("FUNCION", "DEF ID PARENTESISABIERTO (PARAMETRO | PARAMETROS)? ", true,3,"ERROR_SINTACTICO: FALTA PARENTESIS CERRADO O UN PARAMETRO ESTA MAL DECLARADO [#, %]");
        gramatica.group("FUNCION", "DEF PARENTESISABIERTO (PARAMETRO | PARAMETROS)? PARENTESISCERRADO", true,3,"ERROR_SINTACTICO: FALTA NOMBRAR LA FUNCION [#, %]");

        
       /*
        
        //ERRORES ESTRUCTURAS DE CONTROL
        gramatica.loopForFunExecUntilChangeNotDetected(()->{
            gramatica.initialLineColumn();
            gramatica.group("ESTRUCTURAS_CONTROL", "(INSTR_IF|INSTR_WHILE|FUNCION) LLAVEABIERTO (ESTRUCTURAS_CONTROL|INT|FLOAT|STRING)", true,4,"ERROR_SINTACTICO: FALTA LLAVE DE CIERRE [#, %]");
            gramatica.group("ESTRUCTURAS_CONTROL", "(INSTR_IF|INSTR_WHILE|FUNCION) (ESTRUCTURAS_CONTROL|INT|FLOAT|STRING) LLAVECERRADO", true,4,"ERROR_SINTACTICO: FALTA LLAVE DE APERTURA [#, %]");
            //gramatica.group("ESTRUCTURAS_CONTROL", "(INSTR_IF|INSTR_WHILE|FUNCION) LLAVEABIERTO", true,4,"ERROR_SINTACTICO: FALTA LLAVE DE CIERRE [#, %]");
            //gramatica.group("ESTRUCTURAS_CONTROL", "(INSTR_IF|INSTR_WHILE|FUNCION)  LLAVECERRADO", true,4,"ERROR_SINTACTICO: FALTA LLAVE DE ABERTURA [#, %]");
            gramatica.finalLineColumn();
        });
        gramatica.delete(new String[]{"LLAVEABIERTO","LLAVECERRADO"},16,"ERROR_SINTACTICO: LA LLAVE [] NO ESTA CONTENIDA EN ALGUNA GRUPACION");
        gramatica.show();
        */
        
        //gramatica.group("ESTRUCTURAS_CONTROL", "(INSTR_IF|INSTR_WHILE|FUNCION) LLAVEABIERTO (INSTR_IF|INSTR_WHILE|FUNCION|VARIABLE) LLAVECERRADO", true,4,"ERROR_SINTACTICO: FALTA LLAVE DE CIERRE [#, %]");
   
        //--------------------------------------------------------SEMANTICO------------------------------------------------------------------------------
  
        //gramatica.group("RESERV_INDEB", "INT",2, "ERROR SEMANTICO \\{}: ERROR [#,%]");
        //gramatica.group("RESERV_INDEB", "FLOAT",2, "ERROR SEMANTICO \\{}: ERROR [#,%]");
        //gramatica.group("DIV_CERO", "DIVISION 0",2, "ERROR SEMANTICO \\{}: DIVISION ENTRE CERO[#,%]");

        gramatica.show();
    }

    private void semanticAnalysis() {
        HashMap<String,String> tiposDatos = new HashMap<>();
        tiposDatos.put("NUMERO", "INT");
        tiposDatos.put("REAL", "FLOAT");
        tiposDatos.put("CADENA", "STRING");
        tiposDatos.put("TRUE", "BOOLEAN");
        tiposDatos.put("FALSE", "BOOLEAN");
        int i = 0;
        for(Production id: identProd){
            //System.out.println(id.lexemeRank(0,-1)); //int x = 4 ;
            //System.out.println(id.lexemeRank(1)); //x
            //System.out.println(id.lexicalCompRank(0,-1)); //INT ID ASIGNACION NUMERO PUNTOCOMA
            if (!identificadores.containsKey(id.lexemeRank(1))){
                identificadores.put(id.lexemeRank(1), id.lexicalCompRank(0));
                i++;
            }
            else {
                errors.add(new ErrorLSSL(1,"Error semántico: Ya existe un identificador llamado "+id.lexemeRank(1),id,true));
            }

        }
        System.out.println(Arrays.asList(identificadores)); // muestra identificadores
        for (Production id: asigProd){
            if (!identificadores.containsKey(id.lexemeRank(0))){
                errors.add(new ErrorLSSL(1,"Error semántico: Variable \""+id.lexemeRank(0)+"\" no declarada. [#, %]",id,true));
            }
            else{
                if (!identificadores.get(id.lexemeRank(0)).equals(tiposDatos.get(id.lexicalCompRank(2)))){
                    errors.add(new ErrorLSSL(1,"Error semántico : Variable \""+id.lexemeRank(0)+"\" es de tipo "+identificadores.get(id.lexemeRank(0)) +  " [#, %]",id,true));
                   
                }
            }
            
        }
        for (Production id: asigProdConID){
            if (!identificadores.containsKey(id.lexemeRank(0))||!identificadores.containsKey(id.lexemeRank(2))){
                errors.add(new ErrorLSSL(1,"Error semántico: Variable no declarada. [#, %]",id,true));
            }
            else{
                if (!identificadores.get(id.lexemeRank(0)).equals(identificadores.get(id.lexemeRank(2)))){
                    errors.add(new ErrorLSSL(1,"Error semántico : Variable \""+id.lexemeRank(0)+"\" es de tipo "+identificadores.get(id.lexemeRank(0)) +  " [#, %]",id,true));
                   
                }
            }
            
        }
        //comparacion cuando ID está en la izquierda
        for (Production id: compaProdIzq){

            if (!identificadores.containsKey(id.lexemeRank(0))){
                errors.add(new ErrorLSSL(1,"Error semántico: Variable "+id.lexemeRank(0)+" no declarada. [#, %]",id,true));
            }
            else{
                if (identificadores.get(id.lexemeRank(0)).matches("STRING")){
                    errors.add(new ErrorLSSL(1,"Error semántico : Variable \""+id.lexemeRank(0)+"\" es de tipo STRING, imposible comparar [#, %]",id,true));
                }
                if (identificadores.get(id.lexemeRank(0)).matches("BOOLEAN")&&!id.lexicalCompRank(1).matches("IGUAL|DIFERENTE")){
                    errors.add(new ErrorLSSL(1,"Error semántico : Variable \""+id.lexemeRank(0)+"\" es de tipo BOOLEAN, sólo posible comparar con operadores IGUAL y DIFERENTE [#, %]",id,true));
                }
                if (identificadores.get(id.lexemeRank(0)).matches("BOOLEAN")&&!id.lexicalCompRank(2).matches("TRUE|FALSE")){
                    errors.add(new ErrorLSSL(1,"Error semántico : Variable \""+id.lexemeRank(0)+"\" es de tipo BOOLEAN, sólo posible comparar con valores booleanos [#, %]",id,true));
                }
                if (identificadores.get(id.lexemeRank(0)).matches("INT|FLOAT")){
                    if(!id.lexicalCompRank(2).matches("NUMERO|REAL|ID")){
                        errors.add(new ErrorLSSL(1,"Error semántico : Valor numérico de variable \""+id.lexemeRank(0)+"\" no se puede comparar con valor no numérico [#, %]",id,true));
                    }
                }
                
            
            }
            
        }// FOR  COMPAPRODIZQ
        
        for (Production id: compaProdDer){

            if (!identificadores.containsKey(id.lexemeRank(2))){
                errors.add(new ErrorLSSL(1,"Error semántico: Variable "+id.lexemeRank(2)+" no declarada. [#, %]",id,true));
            }
            else{
                if (identificadores.get(id.lexemeRank(2)).matches("STRING")){
                    errors.add(new ErrorLSSL(1,"Error semántico : Variable \""+id.lexemeRank(2)+"\" es de tipo STRING, imposible comparar [#, %]",id,true));
                }
                if (identificadores.get(id.lexemeRank(2)).matches("BOOLEAN")&&!id.lexicalCompRank(1).matches("IGUAL|DIFERENTE")){
                    errors.add(new ErrorLSSL(1,"Error semántico : Variable \""+id.lexemeRank(2)+"\" es de tipo BOOLEAN, sólo posible comparar con operadores IGUAL y DIFERENTE [#, %]",id,true));
                }
                if (identificadores.get(id.lexemeRank(2)).matches("BOOLEAN")&&!id.lexicalCompRank(0).matches("TRUE|FALSE")){
                    errors.add(new ErrorLSSL(1,"Error semántico : Variable \""+id.lexemeRank(2)+"\" es de tipo BOOLEAN, sólo posible comparar con valores booleanos [#, %]",id,true));
                }
                if (identificadores.get(id.lexemeRank(2)).matches("INT|FLOAT")){
                    if(!id.lexicalCompRank(0).matches("NUMERO|REAL")){
                        errors.add(new ErrorLSSL(1,"Error semántico : Valor numérico de variable \""+id.lexemeRank(2)+"\" no se puede comparar con valor no numérico [#, %]",id,true));
                    }
                }
                
            
            }
            
        }// FOR  COMPAPRODDER
        for (Production id: compaProdDoble){

            if (!identificadores.containsKey(id.lexemeRank(0))||!identificadores.containsKey(id.lexemeRank(2))){
                errors.add(new ErrorLSSL(1,"Error semántico: Variable "+id.lexemeRank(0)+" no declarada. [#, %]",id,true));
            }
            else{
                if (identificadores.get(id.lexemeRank(0)).matches("STRING")||identificadores.get(id.lexemeRank(2)).matches("STRING")){
                    errors.add(new ErrorLSSL(1,"Error semántico : Variable \""+id.lexemeRank(0)+"\" es de tipo STRING, imposible comparar [#, %]",id,true));
                }
                if (identificadores.get(id.lexemeRank(0)).matches("BOOLEAN")&&!id.lexicalCompRank(1).matches("IGUAL|DIFERENTE")){
                    errors.add(new ErrorLSSL(1,"Error semántico : Variable \""+id.lexemeRank(0)+"\" es de tipo BOOLEAN, sólo posible comparar con operadores IGUAL y DIFERENTE [#, %]",id,true));
                }
                if (identificadores.get(id.lexemeRank(2)).matches("BOOLEAN")&&!id.lexicalCompRank(1).matches("IGUAL|DIFERENTE")){
                    errors.add(new ErrorLSSL(1,"Error semántico : Variable \""+id.lexemeRank(2)+"\" es de tipo BOOLEAN, sólo posible comparar con operadores IGUAL y DIFERENTE [#, %]",id,true));
                }
                if (identificadores.get(id.lexemeRank(0)).matches("BOOLEAN")&&!identificadores.get(id.lexemeRank(2)).matches("BOOLEAN")){
                    errors.add(new ErrorLSSL(1,"Error semántico : Variable \""+id.lexemeRank(0)+"\" es de tipo BOOLEAN, sólo posible comparar con valores booleanos [#, %]",id,true));
                }
                if (identificadores.get(id.lexemeRank(2)).matches("BOOLEAN")&&!identificadores.get(id.lexemeRank(0)).matches("BOOLEAN")){
                    errors.add(new ErrorLSSL(1,"Error semántico : Variable \""+id.lexemeRank(2)+"\" es de tipo BOOLEAN, sólo posible comparar con valores booleanos [#, %]",id,true));
                }
                if (identificadores.get(id.lexemeRank(0)).matches("INT|FLOAT")){
                    if(!identificadores.get(id.lexemeRank(2)).matches("INT|FLOAT")){
                        errors.add(new ErrorLSSL(1,"Error semántico : Valor numérico de variable \""+id.lexemeRank(0)+"\" no se puede comparar con valor no numérico [#, %]",id,true));
                    }
                }
                if (identificadores.get(id.lexemeRank(2)).matches("INT|FLOAT")){
                    if(!identificadores.get(id.lexemeRank(0)).matches("INT|FLOAT")){
                        errors.add(new ErrorLSSL(1,"Error semántico : Valor numérico de variable \""+id.lexemeRank(0)+"\" no se puede comparar con valor no numérico [#, %]",id,true));
                    }
                }
                
            
            }
            
        }// FOR  COMPAPRODdoble
        for (Production id: operProdIzq){

            if (!identificadores.containsKey(id.lexemeRank(0))){
                errors.add(new ErrorLSSL(1,"Error semántico: Variable "+id.lexemeRank(0)+" no declarada. [#, %]",id,true));
            }
            else{
                if (identificadores.get(id.lexemeRank(0)).matches("STRING|BOOLEAN")){
                    errors.add(new ErrorLSSL(1,"Error semántico : Variable \""+id.lexemeRank(0)+"\" es de tipo "+identificadores.get(id.lexemeRank(0)) +", imposible hacer operaciones aritméticas [#, %]",id,true));
                }
      
            }
            if (identificadores.get(id.lexemeRank(0)).matches("INT")&& id.lexicalCompRank(1).matches("DIVISION")){
                    errors.add(new ErrorLSSL(1,"Error semántico : División en valor entero [#, %]",id,true));
                }
        }// FOR  OPERPRODIZQ
        for (Production id: operProdDer){

            if (!identificadores.containsKey(id.lexemeRank(2))){
                errors.add(new ErrorLSSL(1,"Error semántico: Variable "+id.lexemeRank(2)+" no declarada. [#, %]",id,true));
            }
            else{
                if (identificadores.get(id.lexemeRank(2)).matches("STRING|BOOLEAN")){
                    errors.add(new ErrorLSSL(1,"Error semántico : Variable \""+id.lexemeRank(2)+"\" es de tipo "+identificadores.get(id.lexemeRank(0)) +", imposible hacer operaciones aritméticas [#, %]",id,true));
                }
      
            }
            
        }// FOR  OPERPRODDER
        for (Production id: operProdDoble){

            if (!identificadores.containsKey(id.lexemeRank(0))||!identificadores.containsKey(id.lexemeRank(2))){
                errors.add(new ErrorLSSL(1,"Error semántico: Variable "+id.lexemeRank(0)+" no declarada. [#, %]",id,true));
            }
            else{
                if (identificadores.get(id.lexemeRank(0)).matches("STRING|BOOLEAN")||identificadores.get(id.lexemeRank(2)).matches("STRING|BOOLEAN")){
                    errors.add(new ErrorLSSL(1,"Error semántico : Variable \""+id.lexemeRank(0)+"\" es de tipo "+identificadores.get(id.lexemeRank(0)) +", imposible hacer operaciones aritméticas [#, %]",id,true));
                    errors.add(new ErrorLSSL(1,"Error semántico : Variable \""+id.lexemeRank(2)+"\" es de tipo "+identificadores.get(id.lexemeRank(2)) +", imposible hacer operaciones aritméticas [#, %]",id,true));

                }
      
            }
            
        }// FOR  OPERPRODDOBLE
    }
    private void codigoIntermedio(){
        ArrayList<Token> toks = new ArrayList<Token>();
        codigoIntermedio = ("--Código intermedio--\n");
        int temp;
    //revisa las declaraciones
        for (Production id: identProd){
            temp = 1;
            if(id.lexicalCompRank(2).equals("ASIGNACION")&&id.getSizeTokens()>5){
                codigoIntermedio = codigoIntermedio + ("\n\n=============================\n"+id.lexemeRank(0, -1)+"\n=============================");
                codObj.add("\n\n=============================\n;"+id.lexemeRank(0, -1)+"\n=============================");
                
                toks = id.getTokens();
                for (int i = 0;i<toks.size();i++){
                    if(toks.get(i).getLexeme().equals("*")||toks.get(i).getLexeme().equals("/")){
                        i--;
                        codigoIntermedio = codigoIntermedio + ("\nT"+temp+" = "+toks.get(i).getLexeme()+toks.get(i+1).getLexeme()+toks.get(i+2).getLexeme());
                        
                        codObj.add("\nT"+temp+" = "+toks.get(i).getLexeme()+toks.get(i+1).getLexeme()+toks.get(i+2).getLexeme());
                        
                        toks.remove(i);
                        toks.remove(i);
                        toks.remove(i);
                        toks.add(i,new Token("T"+temp, "ID",i,i));
                        temp++;
                    }//if token = * /
                    
                }//for cada 
                for (int i = 0; i<toks.size();i++){
                    if(toks.get(i).getLexeme().equals("+")||toks.get(i).getLexeme().equals("-")){
                        i--;
                        codigoIntermedio = codigoIntermedio + ("\nT"+temp+" = "+toks.get(i).getLexeme()+toks.get(i+1).getLexeme()+toks.get(i+2).getLexeme());
                        
                        codObj.add("\nT"+temp+" = "+toks.get(i).getLexeme()+toks.get(i+1).getLexeme()+toks.get(i+2).getLexeme());
                        
                        toks.remove(i);
                        toks.remove(i);
                        toks.remove(i);
                        toks.add(i,new Token("T"+temp, "ID",i,i));
                        temp++;
                    }//if token = + -
                }
                codigoIntermedio = codigoIntermedio + ("\n"+id.lexemeRank(1)+" = "+"T"+(temp-1));
                //para guardar las variables declaradas para posteriormente utilizarlo en el metodo ensamblador               
                variables.add(id.lexemeRank(1));
                //codigo Objeto
                codObjComp.add(objectCode(codObj));
                System.out.println(codObjComp.get(0));
                codObj.clear();
            }//if hay asignacion
            
        }//for producciones
    //revisa asignaciones
        for (Production id: asigProd){ 
            temp = 1;
            if(id.lexicalCompRank(1).equals("ASIGNACION")&&id.getSizeTokens()>5){
                codigoIntermedio = codigoIntermedio + ("\n\n=============================\n"+id.lexemeRank(0, -1)+"\n=============================");
                codObj.add("\n\n=============================\n;"+id.lexemeRank(0, -1)+"\n=============================");
                
                toks = id.getTokens();
                for (int i = 0;i<toks.size();i++){
                    if(toks.get(i).getLexeme().equals("*")||toks.get(i).getLexeme().equals("/")){
                        i--;
                        codigoIntermedio = codigoIntermedio + ("\nT"+temp+" = "+toks.get(i).getLexeme()+toks.get(i+1).getLexeme()+toks.get(i+2).getLexeme());
                        
                        codObj.add("\nT"+temp+" = "+toks.get(i).getLexeme()+toks.get(i+1).getLexeme()+toks.get(i+2).getLexeme());
                                                
                        toks.remove(i);
                        toks.remove(i);
                        toks.remove(i);
                        toks.add(i,new Token("T"+temp, "ID",i,i));
                        temp++;
                    }//if token = * /
                    
                }//for cada 
                for (int i = 0; i<toks.size();i++){
                    if(toks.get(i).getLexeme().equals("+")||toks.get(i).getLexeme().equals("-")){
                        i--;
                        codigoIntermedio = codigoIntermedio + ("\nT"+temp+" = "+toks.get(i).getLexeme()+toks.get(i+1).getLexeme()+toks.get(i+2).getLexeme());
                        
                        codObj.add("\nT"+temp+" = "+toks.get(i).getLexeme()+toks.get(i+1).getLexeme()+toks.get(i+2).getLexeme());                       
                        
                        toks.remove(i);
                        toks.remove(i);
                        toks.remove(i);
                        toks.add(i,new Token("T"+temp, "ID",i,i));
                        temp++;
                    }//if token = + -
                }
               codigoIntermedio = codigoIntermedio + ("\n"+id.lexemeRank(0)+" = "+"T"+(temp-1));
               //para guardar las variables declaradas para posteriormente utilizarlo en el metodo ensamblador
               variables.add(id.lexemeRank(0));
               //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7
               //codigo Objeto
                codObjComp.add(objectCode(codObj));
                System.out.println(codObjComp.get(0));
                codObj.clear();
                //System.out.println(objectCode(codObj));
            }//if hay asignacion
        
            
        }
        //INPUT Y OUTPUT
        for (Production id: funcProd){
                codigoIntermedio = codigoIntermedio + ("\n\n=============================\n"+id.lexemeRank(0, -1)+"\n=============================");
                codigoIntermedio = codigoIntermedio + ("\nparam "+id.lexemeRank(2)+"\ncall "+id.lexemeRank(0)+", 1");

            }//FOR FUNCPROD
        //IF
        for (Production id: ifProd){
                codigoIntermedio = codigoIntermedio + ("\n\n=============================\n"+id.lexemeRank(0, -1)+"\n=============================");
                codigoIntermedio = codigoIntermedio + ("\nT1 = "+id.lexemeRank(2,-3)+"\nif_false T1 goto L1 "+"\n.\n.\n.\nlabel L1");

            }//FOR IFPROD
        //WHILE
        for (Production id: whileProd){
                codigoIntermedio = codigoIntermedio + ("\n\n=============================\n"+id.lexemeRank(0, -1)+"\n=============================");
                codigoIntermedio = codigoIntermedio + ("\nlabel L1\nT1 = "+id.lexemeRank(2,-3)+"\nif_false T1 goto L2 "+"\n.\n.\n.\ngoto L1\nlabel L2");

            }//FOR WHILEPROD
        //System.out.print(codigoIntermedio);
    }//codigoIntermedio

    
    //////////////////GENERACION DE CODIGO OBJETO///////////////////
    private String objectCode(ArrayList<String> tripletas1) {
        ArrayList<String> tripletas = new ArrayList<String>();
        tripletas = tripletas1; 
        String tl=tripletas.get(0)+"\n";tripletas.remove(0);
        
        String inst, R0, R1, R2, R3, op, m;
        int caso = 0;
        inst = R1 = R0 = R2 = R3 = op = m =  "";
        int index = 0;
        //oCode

        for (String tripleta : tripletas) {
            
            tripleta = tripleta.replaceAll("T[1-9] = ", "").replaceAll("\\n", "");
            
            //JOptionPane.showMessageDialog(null,tripleta);
            // Definimos que operacion es
            if (tripleta.contains("*")) {
                inst = "MUL";
                op = "*";
            }
            if (tripleta.contains("/")) {
                inst = "DIV";
                op = "/";
            }
            if (tripleta.contains("-")) {
                inst = "SUB";
                op = "-";
            } else if (tripleta.contains("+")) {
                inst = "ADD";
                op = "+";
            }
            // Definimos que operacion es
            
            //Condicionales para ver el orden de la operacion
            if (R0.isEmpty() && R1.isEmpty()) {
                //JOptionPane.showMessageDialog(null,"caso 0");
                R0 = (tripleta.substring(0, tripleta.indexOf(op))).replaceAll(" ", "");
                R1 = (tripleta.substring(tripleta.indexOf(op) + 1)).replaceAll(" ", "");

            }else if ((tripleta.substring(0, tripleta.indexOf(op))).contains("T") && (tripleta.substring(tripleta.indexOf(op) + 1)).contains("T") ) {//2 TEMPORALES
                R0 = "R0";
                R1="R1";
                caso =4 ;
                //JOptionPane.showMessageDialog(null,"caso 4");
            } else if ((tripleta.substring(0, tripleta.indexOf(op))).contains("T")) {//temporal izquierdo
                R1 = (tripleta.substring(tripleta.indexOf(op) + 1)).replaceAll(" ", "");
                caso =1 ;
                //JOptionPane.showMessageDialog(null,"caso 1");
            } else if ((tripleta.substring(tripleta.indexOf(op) + 1)).contains("T")) {//temporal derecho
                R1 = (tripleta.substring(0, tripleta.indexOf(op))).replaceAll(" ", "");
                caso =2;                
                //JOptionPane.showMessageDialog(null,"caso 2");
            }else{
                R1 = (tripleta.substring(0, tripleta.indexOf(op))).replaceAll(" ", "");
                R2 = (tripleta.substring(tripleta.indexOf(op) + 1)).replaceAll(" ", "");
                caso=5;
                //JOptionPane.showMessageDialog(null,"caso 5");
            }

            //Condicionales para ver el orden de la operacion
            switch (caso) {
                case 1:
                    m+="LD R1," + R1+"\n";
                    m+=inst + " R0,R0,R1"+"\n";
                    break;
                case 2:
                    m+="LD R1," + R1+"\n";
                    m+=inst + " R0,R1,R0"+"\n";
                    break;
                case 3:
                    m+="LD R1," + R2+"\n";
                    m+="LD R2," + R2+"\n";
                    m+=inst + " R1,R1,R2"+"\n";
                    break;
                case 4:
                    m+=inst+" "+R0+","+R0+","+R1;
                    break;
                case 5:
                    m+="LD R1," + R1+"\n";
                    m+="LD R2," + R2+"\n";
                    m+=inst + " R1,R1,R2"+"\n";
                    caso =0;
                    break;
                default:
                    m+="LD R0," + R0+"\n";
                    m+="LD R1," + R1+"\n";
                    m+=inst + " R0,R0,R1"+"\n";
                    caso =0;
            }
            
            // caso = true;
        }//Recorrer tripletas
        //System.out.println(tl+m);
        return (tl+m);
    }//fin_codObjeto
    
    private void optimizacion(){
        ArrayList<Token> toks = new ArrayList<Token>();
        codigoIntermedio = ("--Código intermedio--\n");
        int temp;
        int divisor;
        float frac;
    //revisa las declaraciones
        for (Production id: identProd){
            temp = 1;
            System.out.println(id.lexemeRank(0, -1));
            

            if(id.lexicalCompRank(2).equals("ASIGNACION")&&id.getSizeTokens()>5){
                toks = id.getTokens();
                for (int i = 0;i<toks.size();i++){
                    if(toks.get(i).getLexeme().equals("/")){
                        if(toks.get(i+1).getLexicalComp().equals("NUMERO")){
                            divisor = Integer.parseInt(toks.get(i+1).getLexeme());
                            System.out.println("DIVISOR: "+divisor);
                            frac = 1 / (float)divisor;
                            System.out.println("DIVISOR: "+frac);
                            toks.remove(i);
                            toks.remove(i);
                            toks.add(i, new Token("*", "MULTIPLICACION",i,i));
                            toks.add(i+1, new Token(Float.toString(frac), "REAL",i,i));
                        }
                    }//if token = /
                }//for elimina divisiones
                for (int i = 0;i<toks.size();i++){
                    if(toks.get(i).getLexeme().equals("*")){
                        if(toks.get(i-1).getLexeme().equals("0")){
                            toks.remove(i);
                            toks.remove(i);
                        }
                        if(toks.get(i+1).getLexeme().equals("0")){
                            i--;
                            toks.remove(i);
                            toks.remove(i);
                        }
                        
                    }//if token = *
                }//for multiplicaciones 

                for (int i = 0;i<toks.size();i++){
                    if(toks.get(i).getLexeme().equals("*")){
                        if(toks.get(i+1).getLexeme().equals("2")){
                            toks.remove(i);
                            toks.remove(i);
                            toks.add(i,new Token("+", "SUMA",i,i));
                            toks.add(i+1,new Token(toks.get(i-1).getLexeme(), "NUMERO",i,i));            
                    }//if token = 2
                        
                    else if(toks.get(i+1).getLexeme().equals("1")){
                    toks.remove(i);
                    toks.remove(i);
                }// if token = 1
                    }//if token multiplicacion
                }//for multiplicaciones por dos
                
                for (int i = 0;i<toks.size();i++){
                    if(toks.get(i).getLexicalComp().matches("SUMA|RESTA")){
                        if(toks.get(i-1).getLexeme().equals("0")){
                            i--;
                            toks.remove(i);
                            toks.remove(i);
                    }else 
                        if(toks.get(i+1).getLexeme().equals("0")){
                            toks.remove(i);
                            toks.remove(i);
          
                    }//if token = 0

                    }//if token suma
                }//for sumas cero
                codigoIntermedio = codigoIntermedio + ("\n\n=============================\n"+id.lexemeRank(0, -1)+"\n=============================");
                codObj.add("\n\n=============================\n"+id.lexemeRank(0, -1)+"\n=============================");

                for (int i = 0;i<toks.size();i++){
                    if(toks.get(i).getLexeme().equals("*")||toks.get(i).getLexeme().equals("/")){
                        i--;
                        codigoIntermedio = codigoIntermedio + ("\nT"+temp+" = "+toks.get(i).getLexeme()+toks.get(i+1).getLexeme()+toks.get(i+2).getLexeme());
                        
                        codObj.add("\nT"+temp+" = "+toks.get(i).getLexeme()+toks.get(i+1).getLexeme()+toks.get(i+2).getLexeme());
                        
                        toks.remove(i);
                        toks.remove(i);
                        toks.remove(i);
                        toks.add(i,new Token("T"+temp, "ID",i,i));
                        temp++;
                    }//if token = * /
                    
                }//for cada 
                for (int i = 0; i<toks.size();i++){
                    if(toks.get(i).getLexeme().equals("+")||toks.get(i).getLexeme().equals("-")){
                        i--;
                        codigoIntermedio = codigoIntermedio + ("\nT"+temp+" = "+toks.get(i).getLexeme()+toks.get(i+1).getLexeme()+toks.get(i+2).getLexeme());
                        
                        codObj.add("\nT"+temp+" = "+toks.get(i).getLexeme()+toks.get(i+1).getLexeme()+toks.get(i+2).getLexeme());
                        
                        toks.remove(i);
                        toks.remove(i);
                        toks.remove(i);
                        toks.add(i,new Token("T"+temp, "ID",i,i));
                        temp++;
                    }//if token = + -
                }
                if(temp==1){
                    codigoIntermedio = codigoIntermedio + ("\n"+id.lexemeRank(1)+" = " + id.lexemeRank(3));
                }else
                codigoIntermedio = codigoIntermedio + ("\n"+id.lexemeRank(1)+" = "+"T"+(temp-1));
                //para guardar las variables declaradas para posteriormente utilizarlo en el metodo ensamblador               
                variables.add(id.lexemeRank(1));
                //codigo Objeto
                codObjComp.add(objectCode(codObj));
                System.out.println(codObjComp.get(0));
                codObj.clear();
            }//if hay asignacion
            
        }//for producciones
    //revisa asignaciones
    
        for (Production id: asigProd){ 
            temp = 1;
            if(id.lexicalCompRank(1).equals("ASIGNACION")&&id.getSizeTokens()>5){
                toks = id.getTokens();
                for (int i = 0;i<toks.size();i++){
                    if(toks.get(i).getLexeme().equals("/")){
                        if(toks.get(i+1).getLexicalComp().equals("NUMERO")){
                            divisor = Integer.parseInt(toks.get(i+1).getLexeme());
                            System.out.println("DIVISOR: "+divisor);
                            frac = 1 / (float)divisor;
                            System.out.println("DIVISOR: "+frac);
                            toks.remove(i);
                            toks.remove(i);
                            toks.add(i, new Token("*", "MULTIPLICACION",i,i));
                            toks.add(i+1, new Token(Float.toString(frac), "REAL",i,i));
                        }
                    }//if token = /
                }//for elimina divisiones
                for (int i = 0;i<toks.size();i++){
                    if(toks.get(i).getLexeme().equals("*")){
                        if(toks.get(i-1).getLexeme().equals("0")){
                            toks.remove(i);
                            toks.remove(i);
                        }
                        if(toks.get(i+1).getLexeme().equals("0")){
                            i--;
                            toks.remove(i);
                            toks.remove(i);
                        }
                        
                    }//if token = *
                }//for multiplicaciones 

                for (int i = 0;i<toks.size();i++){
                    if(toks.get(i).getLexeme().equals("*")){
                        if(toks.get(i+1).getLexeme().equals("2")){
                            toks.remove(i);
                            toks.remove(i);
                            toks.add(i,new Token("+", "SUMA",i,i));
                            toks.add(i+1,new Token(toks.get(i-1).getLexeme(), "NUMERO",i,i));            
                    }//if token = 2
                        
                    else if(toks.get(i+1).getLexeme().equals("1")){
                    toks.remove(i);
                    toks.remove(i);
                }// if token = 1
                    }//if token multiplicacion
                }//for multiplicaciones por dos
                
                for (int i = 0;i<toks.size();i++){
                    if(toks.get(i).getLexicalComp().matches("SUMA|RESTA")){
                        if(toks.get(i-1).getLexeme().equals("0")){
                            i--;
                            toks.remove(i);
                            toks.remove(i);
                    }else 
                        if(toks.get(i+1).getLexeme().equals("0")){
                            toks.remove(i);
                            toks.remove(i);
                    }//if token = 0

                    }//if token suma
                }//for sumas cero
                
                codigoIntermedio = codigoIntermedio + ("\n\n=============================\n"+id.lexemeRank(0, -1)+"\n=============================");
                 codObj.add("\n\n=============================\n"+id.lexemeRank(0, -1)+"\n=============================");

                
                for (int i = 0;i<toks.size();i++){
                    if(toks.get(i).getLexeme().equals("*")||toks.get(i).getLexeme().equals("/")){
                        i--;
                        codigoIntermedio = codigoIntermedio + ("\nT"+temp+" = "+toks.get(i).getLexeme()+toks.get(i+1).getLexeme()+toks.get(i+2).getLexeme());
                        
                        codObj.add("\nT"+temp+" = "+toks.get(i).getLexeme()+toks.get(i+1).getLexeme()+toks.get(i+2).getLexeme());
                                                
                        toks.remove(i);
                        toks.remove(i);
                        toks.remove(i);
                        toks.add(i,new Token("T"+temp, "ID",i,i));
                        temp++;
                    }//if token = * /
                    
                }//for cada 
                for (int i = 0; i<toks.size();i++){
                    if(toks.get(i).getLexeme().equals("+")||toks.get(i).getLexeme().equals("-")){
                        i--;
                        codigoIntermedio = codigoIntermedio + ("\nT"+temp+" = "+toks.get(i).getLexeme()+toks.get(i+1).getLexeme()+toks.get(i+2).getLexeme());
                        
                        codObj.add("\nT"+temp+" = "+toks.get(i).getLexeme()+toks.get(i+1).getLexeme()+toks.get(i+2).getLexeme());                       
                        
                        toks.remove(i);
                        toks.remove(i);
                        toks.remove(i);
                        toks.add(i,new Token("T"+temp, "ID",i,i));
                        temp++;
                    }//if token = + -
                }
                if (temp==1)
                codigoIntermedio = codigoIntermedio + ("\n"+id.lexemeRank(0)+" = " + id.lexemeRank(2));
                else
               codigoIntermedio = codigoIntermedio + ("\n"+id.lexemeRank(0)+" = "+"T"+(temp-1));
               //para guardar las variables declaradas para posteriormente utilizarlo en el metodo ensamblador
               variables.add(id.lexemeRank(0));
               //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7
               //codigo Objeto
                codObjComp.add(objectCode(codObj));
                System.out.println(codObjComp.get(0));
                codObj.clear();
                //System.out.println(objectCode(codObj));
            }//if hay asignacion
        
            
        }
        //INPUT Y OUTPUT
        for (Production id: funcProd){
                codigoIntermedio = codigoIntermedio + ("\n\n=============================\n"+id.lexemeRank(0, -1)+"\n=============================");
                codigoIntermedio = codigoIntermedio + ("\nparam "+id.lexemeRank(2)+"\ncall "+id.lexemeRank(0)+", 1");

            }//FOR FUNCPROD
        //IF
        for (Production id: ifProd){
                codigoIntermedio = codigoIntermedio + ("\n\n=============================\n"+id.lexemeRank(0, -1)+"\n=============================");
                codigoIntermedio = codigoIntermedio + ("\nT1 = "+id.lexemeRank(2,-3)+"\nif_false T1 goto L1 "+"\n.\n.\n.\nlabel L1");

            }//FOR IFPROD
        //WHILE
        for (Production id: whileProd){
                codigoIntermedio = codigoIntermedio + ("\n\n=============================\n"+id.lexemeRank(0, -1)+"\n=============================");
                if(id.lexicalCompRank(5).matches("MULTIPLICACION|DIVISION|SUMA|RESTA")){
                    codigoIntermedio = codigoIntermedio + ("\nT1 = "+id.lexemeRank(4,-3) + "\nlabel L1\nif_false " + id.lexemeRank(2,3) + " T1 goto L2 "+"\n.\n.\n.\ngoto L1\nlabel L2");
                }
                else{
                    codigoIntermedio = codigoIntermedio + ("\nlabel L1\nif_false " + id.lexemeRank(2,-3) +" goto L2 "+"\n.\n.\n.\ngoto L1\nlabel L2");
                    
                }
            }//FOR WHILEPROD
        //System.out.print(codigoIntermedio);
    }
    /*
    private ArrayList<Token> multPorCero(ArrayList<Token> toks){
        for (int i = 0;i<toks.size();i++){
                    if(toks.get(i).getLexeme().equals("*")){
                        if(toks.get(i-1).getLexeme().equals("0")){
                            toks.remove(i);
                            toks.remove(i);
                            toks = multPorCero(toks);
                        }
                        if(toks.get(i+1).getLexeme().equals("0")){
                            i--;
                            toks.remove(i);
                            toks.remove(i);
                            toks = multPorCero(toks);
                        }
                        
                    }//if token = *
                }//for multiplicaciones 
        imprimirToks(toks);
        return toks;
    }
  */
    private void imprimirToks(ArrayList<Token> prod){
        int i = 0;
        String str = "";
        for(Token tok:prod){
            str+=tok.getLexeme()+"\n";
        }
        System.out.print(str);
    }
    
    
    
    private String ensamblador(String objeto){
       int i=0;
       String vr = "";
       String STRUC="";
       Set<String> hashSet = new HashSet<String>(variables);
        variables.clear();
        variables.addAll(hashSet);
        
       objeto = objeto.replaceAll("=================", ";=================");//.replaceAll("FLOAT",";FLOAT").replaceAll("INT",";INT").replaceAll("int",";int").replaceAll("float",";float" );
       for(String str: variables){vr+="     "+str+" dw 1,0\n";}
       //JOptionPane.showMessageDialog(null, vr);
       //creamos la estructura base de ensablador
       STRUC+=".model small\n.stack\n.data \n"+vr+".code\nINICIO: MOV AX, @DATA\n        MOV DS, AX\n        MOV ES, AX\n\n";

       while(i<4){objeto = objeto.replaceAll("MUL R"+i+",R"+i+",R"+(i+1), "MUL R"+(i+1)).replaceAll("DIV R"+i+",R"+i+",R"+(i+1), "DIV R"+(i+1));i++;}
       //System.out.println(objeto);
       objeto = objeto.replaceAll("LD", "MOV");
       objeto = objeto.replaceAll("R0,R0", "AX");
       objeto = objeto.replaceAll("R1,R1", "BX");
       objeto = objeto.replaceAll("R2,R2", "CX");
       objeto = objeto.replaceAll("R3,R3", "DX");
       objeto = objeto.replaceAll("R0", "AX");
       objeto = objeto.replaceAll("R1", "BX");
       objeto = objeto.replaceAll("R2", "CX");
       objeto = objeto.replaceAll("R3", "DX");
       STRUC+=objeto;

       STRUC+="\nFIN: MOV AX,4C00H\n     INT 21H\n     END\n"; 
       return STRUC;
   }
    
    private void colorAnalysis() {
        /* Limpiar el arreglo de colores */
        textsColor.clear();
        /* Extraer rangos de colores */
        LexerColor lexerColor;
        try {
            File codigo = new File("color.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexerColor = new LexerColor(entrada);
            while (true) {
                TextColor textColor = lexerColor.yylex();
                if (textColor == null) {
                    break;
                }
                textsColor.add(textColor);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
        Functions.colorTextPane(textsColor, jtpCode, new Color(40, 40, 40));
    }

    private void fillTableTokens() {
        tokens.forEach(token -> {
            Object[] data = new Object[]{token.getLexicalComp(), token.getLexeme(), "[" + token.getLine() + ", " + token.getColumn() + "]"};
            Functions.addRowDataInTable(tblTokens, data);
        });
    }

    private void printConsole() {
        int sizeErrors = errors.size();
        if (sizeErrors > 0) {
            Functions.sortErrorsByLineAndColumn(errors);
            String strErrors = "\n";
            for (ErrorLSSL error : errors) {
                String strError = String.valueOf(error);
                strErrors += strError + "\n";
            }
            jtaOutputConsole.setText("Compilación terminada...\n" + strErrors + "\nLa compilación terminó con errores...");
        } else {
            jtaOutputConsole.setText("Compilación terminada...");
        }
        jtaOutputConsole.setCaretPosition(0);
    }

    private void clearFields() {
        Functions.clearDataInTable(tblTokens);
        jtaOutputConsole.setText("");
        tokens.clear();
        errors.clear();
        if(identProdCopia !=null)
            identProdCopia.clear();
        if(identProd !=null)
            identProd.clear();
        if(asigProd !=null)
            asigProd.clear();
        if(asigProdConID !=null)
            asigProdConID.clear();
        if(compaProdIzq !=null)
            compaProdIzq.clear();
        if(compaProdDer !=null)
            compaProdDer.clear();
        if(compaProdDoble !=null)
            compaProdDoble.clear();
        if(operProdIzq !=null)
            operProdIzq.clear();
        if(operProdDer !=null)
            operProdDer.clear();
        if(operProdDoble !=null)
            operProdDoble.clear();
        if(funcProd !=null)
            funcProd.clear();
        if(ifProd !=null)
            ifProd.clear();
        if(whileProd !=null)
            whileProd.clear();
        identificadores.clear();
        codeHasBeenCompiled = false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (UnsupportedLookAndFeelException ex) {
                System.out.println("LookAndFeel no soportado: " + ex);
            }
            new Compilador().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelITT;
    private javax.swing.JLabel LabelTecNM;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnCompilar1;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnTripletas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jtaOutputConsole;
    private javax.swing.JTextPane jtpCode;
    private javax.swing.JPanel panelButtonCompilerExecute;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JTable tblTokens;
    // End of variables declaration//GEN-END:variables
}


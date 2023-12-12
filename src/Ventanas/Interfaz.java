package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import automatas_lr.Lex;
import automatas_lr.Sintactico;

import java.awt.Toolkit;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.Window.Type;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.TextArea;
import java.awt.Scrollbar;


public class Interfaz extends JFrame {

	private JPanel contentPane;
	private Lex obl=new Lex();
	private Sintactico obs=new Sintactico();
	private String Ruta="";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interfaz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBackground(SystemColor.controlText);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnSalir.setBounds(65, 580, 89, 27);
		contentPane.add(btnSalir);
		
		JButton btnArchG = new JButton("Guardar Archivo");		
		btnArchG.setForeground(Color.WHITE);
		btnArchG.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnArchG.setBackground(Color.BLACK);
		btnArchG.setBounds(35, 390, 146, 27);
		contentPane.add(btnArchG);
		
		JButton btnArch = new JButton("Cargar Archivo");		
		btnArch.setForeground(Color.WHITE);
		btnArch.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnArch.setBackground(Color.BLACK);
		btnArch.setBounds(35, 440, 146, 27);
		contentPane.add(btnArch);
		
		JButton btnVerErrores = new JButton("Errores");
		btnVerErrores.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(obs!=null)
				{					
					Errores obe=new Errores(obs); //recopilamos los errores AGREGAR OBL,
					obe.setVisible(true);
				}
				else
				{
					if(obl!=null)
					{		
						Errores obe=new Errores(obs); //recopilamos los errores AGREGAR OBL,
						obe.setVisible(true);
					}
					else{
						JOptionPane.showMessageDialog(null, "Se nesesita analizar el codigo");
					}
				}	
					
			}
		});
		btnVerErrores.setForeground(Color.WHITE);
		btnVerErrores.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnVerErrores.setBackground(Color.BLACK);
		btnVerErrores.setBounds(35, 535, 146, 27);
		contentPane.add(btnVerErrores);
		
		TextArea textin = new TextArea();
		textin.setBounds(25, 10, 550, 300);
		contentPane.add(textin);
		
		JButton btnAnalizar = new JButton("Analizar");	
		btnAnalizar.setForeground(Color.WHITE);
		btnAnalizar.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnAnalizar.setBackground(Color.BLACK);
		btnAnalizar.setBounds(35, 490, 146, 27);
		contentPane.add(btnAnalizar);
		
		JLabel lblPilaSintactica = new JLabel("Pila Sintactica");
		lblPilaSintactica.setForeground(Color.WHITE);
		lblPilaSintactica.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblPilaSintactica.setBounds(350, 365, 153, 14);
		contentPane.add(lblPilaSintactica);
		
		TextArea textSin = new TextArea();
		textSin.setEditable(false);
		textSin.setBounds(220, 385, 350, 214);
		contentPane.add(textSin);
		
		JLabel lblNewLabel_1 = new JLabel("Componentes Lexicos");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 330, 150, 14);
		contentPane.add(lblNewLabel_1);
		
		TextArea textLex = new TextArea();
		textLex.setEditable(false);
		textLex.setBounds(160, 320, 430, 40);
		contentPane.add(textLex);
		
		
		JLabel lblNewLabel = new JLabel("");		
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Interfaz.class.getResource("/Imagenes/dark1.jpg")));
		lblNewLabel.setBounds(0, 0, 600, 620);
		contentPane.add(lblNewLabel);
		setUndecorated(true);
	    this.setLocationRelativeTo(null);
	    
	    
	    
	    //Acciones de botones
	    
	    btnArch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				File obf = null;
				 JFileChooser chooser = new JFileChooser();
				    FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "TXT","txt");
				    chooser.setFileFilter(filter);
				    int returnVal = chooser.showOpenDialog(getParent());
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				    	textin.setText("");
				    	obf=chooser.getSelectedFile();
				    	Txt obx=new Txt(obf.getAbsolutePath());// abrimos el contenido del archivo
				    	//System.out.println(obf);
				    	Ruta=obf+"";
						textin.setText(obx.getTexto());	// se lo asignamos al control
				    }
				    else{
				    	textin.setText("");
				    	textin.setText("---------------------------------------------No se cargo ning�n archivo--------------------------------");
				    	
				    }
			   			
			}
		});
	    
	    btnArchG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					FileWriter f=new FileWriter(Ruta);
					f.write(textin.getText());
					f.close();
				}catch(IOException e1) 
				{
				}
			}
	    });
	    btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	    
	    btnAnalizar.addActionListener(new ActionListener() {// analizar
			public void actionPerformed(ActionEvent e) {
				
				obl=new Lex();
				textLex.setText("");
				textSin.setText("");
				obl.Analizar(textin.getText());
				textLex.setText(obl.getCodigo());
				obs=new Sintactico();
				textSin.setText(obs.metodo(obl.getComponentesLexicos()));
				obl.tablaSin();
			}
		});
	}
}
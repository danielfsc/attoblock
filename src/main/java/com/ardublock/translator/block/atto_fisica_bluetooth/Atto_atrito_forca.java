package com.ardublock.translator.block.atto_fisica_bluetooth;


import com.ardublock.core.Context;
import java.util.ResourceBundle;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Atto_atrito_forca extends TranslatorBlock
{
	public Atto_atrito_forca(Long blockId, Translator translator, String codePrefix,	String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException , SubroutineNotDeclaredException
	{
	   	TranslatorBlock zera_block = this.getRequiredTranslatorBlockAtSocket(0);
		String botao_zera = zera_block.toCode();

		TranslatorBlock sen_bluetooth = this.getRequiredTranslatorBlockAtSocket(1);
		String bluetooth_sensor = sen_bluetooth.toCode();
           	int blue_forc_int = Integer.parseInt(bluetooth_sensor);
			double forc_double_blue = blue_forc_int + 1.0;
            int forc_int_blu = (int)forc_double_blue;
			String forc_string_blue = ""+(forc_int_blu);

				
		TranslatorBlock forc_block = this.getRequiredTranslatorBlockAtSocket(2);
		String sen_forc_block = forc_block.toCode();
            int forc_int = Integer.parseInt(sen_forc_block);
			double forc_double = forc_int + 1.0;
            int int_forc = (int)forc_double;
			String string_forca = ""+(int_forc);
            
            TranslatorBlock tb_dir = this.getRequiredTranslatorBlockAtSocket(3);
	    String ret_dir = tb_dir.toCode();
		
	   
	    TranslatorBlock tb_esq = this.getRequiredTranslatorBlockAtSocket(4);
	    String ret_esq = tb_esq.toCode();


            
            TranslatorBlock vel_block = this.getRequiredTranslatorBlockAtSocket(5);
		String car_vel_block = vel_block.toCode();
		

			String variaveis_globais = "#include<SoftwareSerial.h>\n #include \"HX711.h\"\n #define DT "+string_forca+"\n #define SCK "+sen_forc_block+"\n \n SoftwareSerial mySerial("+bluetooth_sensor+", "+forc_string_blue+");\n \n bool funcaoA_newton = false;\n bool funcaoB_Newton = false;\n double zerado_newton = 0.0;\n int estado_newton = 0;\n int motor_dir = "+ret_dir+";\n int motor_esq = "+ret_esq+";\n double velocidade = "+car_vel_block+";\n double inicio = 0;\n double contador = 0.0;\n \n HX711 escala;\n \n //####### Função do botão de Liga/Desliga ########\n boolean botao_Digital(int pinNumber)\n {\n   pinMode(pinNumber, INPUT);\n   return digitalRead(pinNumber);\n }\n"; 
			
			translator.addDefinitionCommand(variaveis_globais);
            
			translator.addSetupCommand("  //Inicialização do monitor Serial \n  Serial.begin(9600);\n  mySerial.begin(9600);\n  //Motores\n  pinMode(motor_dir, OUTPUT);\n  pinMode(motor_esq, OUTPUT);\n \n  //Balança\n  escala.begin (DT, SCK);\n  escala.set_scale(392429.5871);     // Substituir o valor encontrado para escala\n  escala.tare(20);                // O peso é chamado de Tare.\n");
            
            
		    String exp_forca = "  funcaoA_newton = botao_Digital("+botao_zera+"); \n   if ((funcaoA_newton == HIGH)  &&  ( funcaoB_Newton  ==  LOW ))\n  {\n    estado_newton = ( 1 - estado_newton ) ;\n    delay( 500 );\n  }\n  funcaoB_Newton = funcaoA_newton ;\n\n  if (estado_newton  ==  1) {\n      // Motores\n      digitalWrite(motor_dir, HIGH);\n      digitalWrite(motor_esq, HIGH);\n      analogWrite(5, velocidade);\n      analogWrite(6, velocidade);\n\n      //Balança\n      contador = abs(escala.get_units());\n      mySerial.println(contador);\n\n      if (botao_Digital("+botao_zera+"))\n      {\n        estado_newton = 0;\n        return;\n      }\n   }\n  digitalWrite(motor_dir, HIGH);\n  digitalWrite(motor_esq, HIGH);\n  analogWrite(5, 0);\n  analogWrite(6, 0);\n";
            
			return codePrefix + exp_forca + codeSuffix;	
		}
		
	}


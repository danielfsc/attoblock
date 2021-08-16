package com.ardublock.translator.block.atto_fisica_bluetooth;


import com.ardublock.core.Context;
import java.util.ResourceBundle;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Atto_angulo_forca extends TranslatorBlock
{
	public Atto_angulo_forca(Long blockId, Translator translator, String codePrefix,	String codeSuffix, String label)
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
		

			String variaveis_globais = "#include<SoftwareSerial.h> \n #include \"HX711.h\" \n #define DT "+string_forca+"\n #define SCK "+sen_forc_block+"\n\n SoftwareSerial mySerial("+bluetooth_sensor+", "+forc_string_blue+");\n \nbool funcaoA_newton = false; \n bool funcaoB_Newton = false;\n double zerado_newton = 0.0;\n int estado_newton = 0;\n double inicio = 0; \n double contador = 0.0;\n  HX711 escala; \n\n //####### Função do botão de Liga/Desliga ########\n \nboolean botao_Digital(int pinNumber)\n {\n  pinMode(pinNumber, INPUT);\n  return digitalRead(pinNumber);\n}\n"; 
			
			translator.addDefinitionCommand(variaveis_globais);
            
			translator.addSetupCommand("  //Inicialização do monitor Serial \n  Serial.begin(9600);\n  mySerial.begin(9600);\n  //Balança\n  escala.begin (DT, SCK);\n  // Serial.print(\"Leitura do Valor ADC:  \");\n  // Serial.println(escala.read());   // Aguada até o dispositivo estar pronto\n   Serial.println(\"Nao coloque nada na balanca!\");\n   Serial.println(\"Iniciando...\");\n  escala.set_scale(392429.5871);     // Substituir o valor encontrado para escala\n  escala.tare(20);                // O peso é chamado de Tare.\n  Serial.println(\"Insira o item para Pesar\");");
            
            
		    String exp_forca = "funcaoA_newton = botao_Digital("+botao_zera+");\n  if ((funcaoA_newton == HIGH)  &&  ( funcaoB_Newton  ==  LOW ))\n  {\n    estado_newton = ( 1 - estado_newton ) ;\n    delay( 500 );\n  }\n  funcaoB_Newton = funcaoA_newton ;\n\n  if (estado_newton  ==  1) {\n    //Balança\n    contador = escala.get_units();\n    mySerial.println(contador,3);\n    if (botao_Digital("+botao_zera+"))\n    {\n      estado_newton = 0;\n    }\n  }\n";
            
			return codePrefix + exp_forca + codeSuffix;	
		}
		
	}


package com.ardublock.translator.block.atto_fisica_bluetooth;

import com.ardublock.core.Context;
import java.util.ResourceBundle;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class printphyphox extends TranslatorBlock
{
	public printphyphox(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		/**
		 * DO NOT add tab in code any more, we'll use arduino to format code, or the code will duplicated. 
		 */
		translator.addSetupCommand("Serial.begin(9600);");
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0, "mySerial.println(", ");\n");
		
		String ret = translatorBlock.toCode();
		TranslatorBlock m_bluetooth = this.getRequiredTranslatorBlockAtSocket(1);
	    String mbluetooth = m_bluetooth.toCode();
        int bluetooth_int = Integer.parseInt(mbluetooth);
        int port_blue = bluetooth_int+1;
        String m_blue_s = m_bluetooth.toCode();
        m_blue_s = m_blue_s + "," ;
        m_blue_s = m_blue_s + (port_blue);
        
        String variaveis_globais = "#include<SoftwareSerial.h> \n SoftwareSerial mySerial("+m_blue_s+"); \n";
        translator.addDefinitionCommand(variaveis_globais);
        
//		ret+=test;
		
		return ret;
	}
}

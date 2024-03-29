package com.ardublock.translator.block.Duinoedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class MP3_Pause extends TranslatorBlock {
	public MP3_Pause(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String Clk;
		String Dio;
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		Clk = translatorBlock.toCode();
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		Dio = translatorBlock.toCode();
				
		translator.addHeaderFile("EduMp3.h");
		translator.addHeaderFile("SoftwareSerial.h");
		
		translator.addDefinitionCommand("//libraries at http://duinoedu.com/dl/lib/grove/ \n"
				+ "EduMp3 monMp3_"+Clk+Dio+"(" + Clk	+ "," + Dio + ");");
		translator.addSetupCommand(" monMp3_"+Clk+Dio+".brancher();");
				
		
		String ret =  "monMp3_"+Clk+Dio+".mettreEnPause();";
		
		return codePrefix + ret + codeSuffix;
	}
	
	
	
	
	
	
	
	
	
}

package com.ardublock.translator.block.atto_fisica_bluetooth;

import com.ardublock.core.Context;
import java.util.ResourceBundle;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Atto_D3_2 extends TranslatorBlock
	{

		public Atto_D3_2(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
		{
			super(blockId, translator, codePrefix, codeSuffix, label);
		}

		@Override
		public 	String toCode() throws SocketNullException {
				
			return codePrefix + "2" + codeSuffix;
		}
		
	}
	







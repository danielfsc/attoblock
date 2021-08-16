package com.ardublock.translator.block.atto_fisica;

import com.ardublock.core.Context;
import java.util.ResourceBundle;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class AttoMovimentoParadoBlock_fisica extends TranslatorBlock
{
	public AttoMovimentoParadoBlock_fisica(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode()
	{
	String Parado ="Parado";
		return codePrefix + "5" + codeSuffix;
	}

}


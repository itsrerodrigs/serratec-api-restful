package org.serratec.serratecpub.util;

public class TratamentoDeErro {
	public static final String NotBlankMessage = "O valor não pode ser nulo ou estar em branco";
	public static final String SizeMessage = "Quantidade mínima ou máxima de caracteres não respeitada";
	public static String formataCpf(String cpf) {
        return cpf.substring(0, 3) + "." + 
               cpf.substring(3, 6) + "." + 
               cpf.substring(6, 9) + "-" + 
               cpf.substring(9, 11);
    }
}

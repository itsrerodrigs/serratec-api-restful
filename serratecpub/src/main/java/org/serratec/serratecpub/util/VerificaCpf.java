package org.serratec.serratecpub.util;

public class VerificaCpf {

	public static boolean isCPF(String CPF) {
		if (CPF == null || CPF.length() != 11) {
			return false;
		}
		try {
			int[] cpfArray = CPFtoArray(CPF);
			return isCpf(1, cpfArray);
		} 
		catch (NumberFormatException e) {
			return false;
		}
	}

	private static int[] CPFtoArray(String CPF) {
		int[] cpfArray = new int[11];
		for (int i = 0; i < 11; i++) {
			cpfArray[i] = Integer.parseInt(String.valueOf(CPF.charAt(i)));
		}
		return cpfArray;
	}

	private static boolean isCpf(int posicaoCodigo, int[] Cpf) {
		int j = (posicaoCodigo == 1) ? 10 : 11;
		int indexParameter = 7 + posicaoCodigo;
		int resultado = 0;

		for (int i = 0; i <= indexParameter; i++) {
			resultado += j * Cpf[i];
			j--;
		}

		int restoDiv = resultado % 11;
		if (restoDiv < 2) {
			if (Cpf[indexParameter + 1] == 0) {
				return (posicaoCodigo == 1) ? isCpf(2, Cpf) : true;
			} else {
				return false;
			}
		} else {
			int dif = 11 - restoDiv;
			if (Cpf[indexParameter + 1] == dif) {
				return (posicaoCodigo == 1) ? isCpf(2, Cpf) : true;
			} else {
				return false;
			}
		}
	}
	
    public static String formataCpf(String cpf) {
        return cpf.substring(0, 3) + "." + 
               cpf.substring(3, 6) + "." + 
               cpf.substring(6, 9) + "-" + 
               cpf.substring(9, 11);
    }
}
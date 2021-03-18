package br.com.zup.casadocodigo.utils;

public class CpfCnpjFormatter {

    public static String removeCaracteresEspeciais(String documento) {
        if (documento.contains(".")) {
            documento = documento.replace(".", "");
        }
        if (documento.contains("-")) {
            documento = documento.replace("-", "");
        }
        if (documento.contains("/")) {
            documento = documento.replace("/", "");
        }
        return documento;
    }
}

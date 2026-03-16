package main.java.factory;


import main.java.model.EntregaEncomendaPac;
import main.java.model.EntregaRetiradaEmLoja;
import main.java.model.EntregaSedex;
import main.java.model.enums.TipoEntrega;
import main.java.model.interfaces.ITipoEntrega;

public class TipoDeFreteFactory {
    
    public static ITipoEntrega criar(TipoEntrega tipo) {
        return switch (tipo) {
            case ENCOMENDA_PAC -> new EntregaEncomendaPac();
            case SEDEX -> new EntregaSedex();
            case RETIRADA_LOJA -> new EntregaRetiradaEmLoja();
        };
    }
}
